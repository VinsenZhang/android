package com.sdk.adsdk.http;


import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpPost extends HttpRequest {


    public HttpPost(String url) {
        this.url = url;
    }


    public HttpPost(String url, Map<String, String> header, String bodyJson) {
        this.url = url;
        this.header = header;
        this.bodyjson = bodyJson;
    }

    @Override
    public String call() throws Exception {
        String response ="";

        URL u = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod(httpPost);
        connection.setConnectTimeout(httpConnTimeOut);
        connection.setReadTimeout(httpReadTimeOut);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        // 添加请求头
        if (header != null) {
            addHeader(connection);
        }

        // 添加请求body
        if (!TextUtils.isEmpty(bodyjson)) {
            addBody(connection);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                response += temp;
            }
            reader.close();
        }
        connection.disconnect();
        return response;
    }

}
