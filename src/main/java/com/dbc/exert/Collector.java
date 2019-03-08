package com.dbc.exert;

import com.dbc.exert.net.HttpHelper;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Collector implements Runnable {

    public static Queue<String> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {

        String url = queue.poll();
        String body;
        try {
            body = HttpHelper.sendGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
