package com.dbc.exert.collect;

import com.dbc.exert.model.Link;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Collector implements Runnable {

    public static final Queue<String> links = new LinkedBlockingQueue<>();  //待爬取的网页
    public static final BloomFilter<String> linkFilter =    //已经爬取过的网页
            BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 10000);
    public static final Queue<Link> linkEntries = new LinkedBlockingQueue<>();
    public static long MAX_SIZE = 1024 * 1024 * 1024;  //网页文件限制大小

    abstract String getHtml();

    abstract void parsingLinks(String html);

    abstract void persistHtml(String html) throws IOException;


    public void doCollect() {
        //从待爬队列中取出链接爬取
        String html = getHtml();
        //解析出网页中的链接
        parsingLinks(html);
        try {
            //保存原始网页
            persistHtml(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
