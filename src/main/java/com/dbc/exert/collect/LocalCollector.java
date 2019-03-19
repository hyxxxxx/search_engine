package com.dbc.exert.collect;

import com.dbc.exert.ConfigUtil;
import com.dbc.exert.model.IDProvider;
import com.dbc.exert.net.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class LocalCollector extends Collector {

    private String link;

    @Override
    public void run() {
        while (true) {
            doCollect();
        }
    }

    @Override
    String getHtml() {
        String url = Collector.links.poll();
        this.link = url;
        String htmlStr = "";
        try {
            htmlStr = HttpHelper.sendGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlStr;
    }

    @Override
    void parsingLinks(String html) {
        if (StringUtils.isEmpty(html)) {
            return;
        }
        Document document = Jsoup.parse(html);
        Element body = document.body();
        Elements href = body.getElementsByAttribute("href");
        for (Element element : href) {
            String link = element.attr("href").trim();
            if (link.startsWith("http")) {
                if (Collector.linkFilter.mightContain(link)) { //布隆过滤器判重
                    continue;
                }
                Collector.links.offer(link);  //加入待爬取队列
                Collector.linkFilter.put(link);   //加入过滤器
            }
        }
    }

    @Override
    void persistHtml(String html) {
        if (StringUtils.isEmpty(html)) {
            return;
        }
        long htmlId = IDProvider.newWebId();
        int docId = IDProvider.docId();
        try {
            String root = ConfigUtil.getValueStr("root");
            Path doc_raw = Paths.get(root + "doc_raw_" + docId + ".bin");
            Path doc_id = Paths.get(root + "doc_id.bin");
            if (!Files.exists(doc_raw)) {
                Files.createFile(doc_raw);
            }
            if (!Files.exists(doc_id)) {
                Files.createFile(doc_id);
            }
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(doc_raw, StandardOpenOption.APPEND))) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(htmlId).append("\t").append(html.getBytes(Charset.defaultCharset()).length).append("\t").append(html);
                writer.println(buffer);
            }
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(doc_id, StandardOpenOption.APPEND))) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(htmlId).append("\t").append(link);
                writer.println(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
