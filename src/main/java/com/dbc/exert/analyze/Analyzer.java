package com.dbc.exert.analyze;

public abstract class Analyzer implements Runnable {

    abstract String parsingContext();

    abstract void createIndex(String context);

    public void doAnalyze() {
        //过滤掉网页标签
        String context = parsingContext();
        //分词并创建临时索引
        createIndex(context);
    }

}
