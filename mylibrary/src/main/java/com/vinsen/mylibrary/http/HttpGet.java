package com.vinsen.mylibrary.http;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class HttpGet extends HttpRequest {


    public HttpGet(String url) {
        this.url = url;
    }

    public HttpGet(String url, Map<String, Object> params) {
        this.url = url;
        this.params = params;
        addParams();
    }


    @Override
    public String call() throws Exception {

        String response = null;

        URL u = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod(httpGet);
        connection.setConnectTimeout(httpConnTimeOut);
        connection.setReadTimeout(httpReadTimeOut);

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        // 添加请求头
        if (header != null){
            addHeader(connection);
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


    private void addParams() {
        url = url + "?_=" + System.currentTimeMillis();
        for (Map.Entry<String, Object> next : params.entrySet()) {
            String key = next.getKey();
            Object value = next.getValue();
            url += "&" + key + "=" + value;
        }
    }

}
