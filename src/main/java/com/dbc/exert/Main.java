package com.dbc.exert;

import com.dbc.exert.analyze.FileStorageAnalyzer;
import com.dbc.exert.collect.Collector;
import com.dbc.exert.collect.FileStorageCollector;
import com.dbc.exert.model.IDProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(10);

        Collector.links.offer("https://news.sina.com.cn/china/");


        service.submit(new FileStorageCollector());
        TimeUnit.SECONDS.sleep(2);
        service.submit(new FileStorageAnalyzer());
//        service.submit(new Indexer());
        service.submit((Runnable) () -> {
            while (true) {
                int docId = IDProvider.docId();
                log.info("doc文件ID -> {}", docId);
                String root = ConfigUtil.getValueStr("root");
                Path doc_raw = Paths.get(root + "doc_raw_" + docId + ".bin");
                if (Files.exists(doc_raw)) {
                    try {
                        long size = Files.size(doc_raw);
                        if (size >= Collector.MAX_SIZE) {
                            IDProvider.docIdIncrease();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }


}
