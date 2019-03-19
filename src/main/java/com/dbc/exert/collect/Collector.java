package com.dbc.exert.collect;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Collector implements Runnable {

    public static final Queue<String> links = new LinkedBlockingQueue<>();
    public static final BloomFilter<String> linkFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 10000);
    public static long MAX_SIZE = 1024 * 1024 * 1024;

    abstract String getHtml();

    abstract void parsingLinks(String html);

    abstract void persistHtml(String html);


    public void doCollect() {
        //从待爬队列中取出链接爬取
        String html = getHtml();
        //解析出网页中的链接
        parsingLinks(html);
        //保存原始网页
        persistHtml(html);
    }

}
