package com.dbc.exert.controller;

import com.alibaba.fastjson.JSONObject;
import com.dbc.exert.model.Trie;
import okhttp3.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class InterfaceSearchService {

    @RequestMapping("/interfaceSearch/{key}")
    public Object interfaceSearch(@PathVariable String key) {
        return Trie.trie.search(key);
    }

    @RequestMapping("/post")
    public Object post(String service, String version, String data) throws IOException {

//        String service = "schoolService";
//        String version = "1.0";
//        String salt = "596257";
        boolean test = true;
        long time = System.currentTimeMillis() / 1000;
//        String token = "";
//        String data = "{name:\"u\"}";

        HashMap<String, Object> map = new HashMap<>();
        map.put("service", service);
        map.put("version", version);
        map.put("test", test);
        map.put("time", time);
        map.put("data", data);

        Request request = new Request.Builder().url("http://localhost:8081/rest/do")
                .post(RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), JSONObject.toJSONString(map)))
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        return response.body().string();
    }


}
