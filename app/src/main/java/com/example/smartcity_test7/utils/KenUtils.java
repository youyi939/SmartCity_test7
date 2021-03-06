package com.example.smartcity_test7.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    public static String Post(String url,String token,String msg) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,msg);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Authorization",token)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String get_t(String url,String token) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
