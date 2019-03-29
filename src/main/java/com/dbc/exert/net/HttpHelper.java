package com.dbc.exert.net;

import com.dbc.exert.stock.RxUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpHelper {

    private HttpHelper() {
    }

    private static OkHttpClient client = new OkHttpClient.Builder()
            .sslSocketFactory(RxUtils.createSSLSocketFactory())
            .hostnameVerifier(new RxUtils.TrustAllHostnameVerifier())
            .retryOnConnectionFailure(true).build();

    public static String sendGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String xueqiuGet(String url) throws IOException {
        Headers headers = Headers.of("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36",
                "Accept", "application/json, text/plain, */*",
                "Origin", "https://xueqiu.com",
                "Referer", "https://xueqiu.com/snowman/S/SZ000651/detail");
        Request request = new Request.Builder()
                .headers(headers)
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
