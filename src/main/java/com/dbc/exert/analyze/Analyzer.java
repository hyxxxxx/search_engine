package com.dbc.exert.analyze;

import java.io.IOException;

public abstract class Analyzer implements Runnable {

    abstract String readHtml() throws IOException;

    abstract String parsingContext(String html);

    abstract void createIndex(String context);

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
        createIndex(context);
    }

}
