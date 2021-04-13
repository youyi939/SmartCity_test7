package com.example.smartcity_test7.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KenUtils {

    /**
     * Get 无token
     * @param url
     * @return
     * @throws IOException
     */
    public static String Get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).method("GET",null).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
