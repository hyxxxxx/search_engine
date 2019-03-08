package com.dbc.exert;

import com.dbc.exert.net.HttpHelper;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Collector implements Runnable {

    public static Queue<String> links = new LinkedBlockingQueue<>();
    public static BloomFilter<String> linkFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 100);

    @Override
    public void run() {

        String url = links.poll();
        String htmlStr = "";
        try {
            htmlStr = HttpHelper.sendGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = Jsoup.parse(htmlStr);
        Element body = document.body();
        Elements href = body.getElementsByAttribute("href");
        for (Element element : href) {
            String link = element.attr("href").trim();
            if (link.startsWith("http")) {
                links.offer(link);
            }
        }
    }
}
