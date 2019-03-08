package com.dbc.exert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void run(Runnable runnable) {
        executor.submit(runnable);
    }

}
