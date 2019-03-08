package com.dbc.exert;

import com.dbc.exert.net.HttpHelper;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        try {
            String html = HttpHelper.sendGet("https://www.sina.com.cn/");
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService service = Executors.newFixedThreadPool(10);

        service.submit(new Collector());
//        service.submit(new Analyzer());
//        service.submit(new Indexer());
//        service.submit(new Querier());

    }

}
