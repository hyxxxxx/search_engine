package com.dbc.exert.analyze;

import com.dbc.exert.collect.Collector;
import com.dbc.exert.model.Link;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalAnalyzer extends Analyzer {

    @Override
    String readHtml() throws IOException {

        Link link = Collector.linkEntries.poll();
        String fileName = link.getFileName();
        String linkId = link.getId();
        int webSize = link.getWebSize();
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "r")) {
            raf.seek(0);
            byte[] head = new byte[linkId.length()];
            do {
                raf.read(head);
            } while (!Arrays.equals(linkId.getBytes(), head));
            raf.skipBytes(1);
            byte[] body = new byte[webSize];
            raf.read(body);
            return new String(body, 0, webSize);
        }
    }

    @Override
    String parsingContext(String htmlStr) {

        if (StringUtils.isEmpty(htmlStr)) {
            return null;
        }
        String regEx_script = "<script[^>]*?>[\\s\\S]*?</script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?</style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.replaceAll("\\r|\\n|\\s", "");
    }

    @Override
    void createIndex(String context) {
        if (StringUtils.isEmpty(context)) {
            return;
        }
        List<Term> segment = SpeedTokenizer.segment(context);
        for (Term term : segment) {
            System.out.println(term.word);
        }
    }

    @Override
    public void run() {
        while (true) {
            doAnalyze();
        }
    }

}
