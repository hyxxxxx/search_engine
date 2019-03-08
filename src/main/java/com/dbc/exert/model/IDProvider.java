package com.dbc.exert.model;

import java.util.concurrent.atomic.AtomicLong;

public class IDProvider {

    private IDProvider() {

    }

    private static AtomicLong atomicLong = null;

    public static long newId() {
        if (atomicLong == null) {
            synchronized (IDProvider.class) {
                if (atomicLong == null) {
                    atomicLong = new AtomicLong(1);
                }
            }
        }
        return atomicLong.getAndIncrement();
    }

}
