package com.sdk.adsdk.http;



import android.text.TextUtils;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class HttpRequest implements Callable<String> {

    public static final int httpConnTimeOut = 3 * 1000;

    public static final int httpReadTimeOut = 3 * 1000;


    public static final String httpGet = "GET";

    public static final String httpPost = "POST";


    protected Map<String, Object> params = new HashMap<String, Object>();

    protected Map<String, String> header = new HashMap<String, String>();

    protected String bodyjson;

    protected String url;

    protected void addHeader(HttpURLConnection connection) {
        for (Map.Entry<String, String> next : header.entrySet()) {
            connection.setRequestProperty(next.getKey(), next.getValue());
        }
    }



    protected void addBody(HttpURLConnection connection) throws Exception {

        if (TextUtils.isEmpty(bodyjson))
            return;

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        // 把数据写入请求的Body
        out.write(bodyjson);
        out.flush();
        out.close();
    }


}
