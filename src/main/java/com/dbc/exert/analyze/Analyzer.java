package com.dbc.exert.analyze;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Analyzer implements Runnable {

    public static final Map<String, Long> wordMap = new HashMap<>(512);

    abstract String readHtml() throws IOException;

    abstract String parsingContext(String html);

    abstract void createIndex(String context) throws IOException;

    public void doAnalyze() {
        String html = null;
        try {
            html = readHtml();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //过滤掉网页标签
        String context = parsingContext(html);
        //分词并创建临时索引
        try {
            createIndex(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
