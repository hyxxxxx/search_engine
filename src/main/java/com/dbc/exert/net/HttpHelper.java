package com.dbc.exert.net;

import okhttp3.*;

import java.io.IOException;

public class HttpHelper {

    private HttpHelper() {
    }

    private static OkHttpClient client = new OkHttpClient();

    public static String sendGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
