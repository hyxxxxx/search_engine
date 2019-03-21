package com.dbc.exert.model;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class IDProvider {

    private IDProvider() {

    }

    private static AtomicInteger doc_raw = new AtomicInteger(1);
    private static AtomicLong word_id = new AtomicLong(1);

    public static long newWordId() {
        return word_id.getAndIncrement();
    }

    public static synchronized String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void docIdIncrease() {
        doc_raw.incrementAndGet();
    }

    public static int docId() {
        return doc_raw.get();
    }


}
