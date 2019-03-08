package com.dbc.exert;

import com.dbc.exert.net.HttpHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) throws InterruptedException {

//        ExecutorService service = Executors.newFixedThreadPool(10);

//        Collector.links.offer("https://www.sina.com.cn/");

//        service.submit(new Collector());
//        service.submit(new Analyzer());
//        service.submit(new Indexer());
//        service.submit(new Querier());

        String htmlStr = "";
        try {
            htmlStr = HttpHelper.sendGet("https://www.sina.com.cn/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = Jsoup.parse(htmlStr);
        Element body = document.body();
        Elements href = body.getElementsByAttribute("href");
        for (Element element : href) {
            System.out.println(element.attr("href"));
        }


    }

}
