package com.sdk.adsdk.ads;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


import com.sdk.adsdk.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyDownloadAsync extends AsyncTask<String, Integer, Integer> {

    private NotificationManager nm;
    private NotificationCompat.Builder builder;
    private Notification nf;

    private Context mContext;
    private int iconRes = R.drawable.sdk_def;
    private String downLoadUrl;
    private String apkName;


    public MyDownloadAsync(Context context, String downLoadUrl) {
        this.mContext = context;
        this.downLoadUrl = downLoadUrl;

        nm = (NotificationManager) mContext.getSystemService(Activity.NOTIFICATION_SERVICE);

        String[] split = downLoadUrl.split("/");
        apkName = split[split.length - 1];
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(iconRes).setContentInfo("下载中...").setContentTitle("正在下载");
        nf = builder.build();
    }

    @Override
    protected Integer doInBackground(String... params) {
        HttpURLConnection con = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            URL url = new URL(downLoadUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5 * 1000);  //设置超时时间
            if (con.getResponseCode() == 200) { //判断是否连接成功
                int fileLength = con.getContentLength();
                is = con.getInputStream();    //获取输入
                String path = Environment.getExternalStorageDirectory().getPath();
                os = new FileOutputStream(path + "/" + apkName);
                byte[] buffer = new byte[1024 * 1024 * 10];
                long total = 0;
                int count;
                int pro1 = 0;
                int pro2 = 0;
                while ((count = is.read(buffer)) != -1) {
                    total += count;
                    if (fileLength > 0)
                        pro1 = (int) (total * 100 / fileLength);  //传递进度（注意顺序）
                    if (pro1 != pro2)
                        publishProgress(pro2 = pro1);
                    os.write(buffer, 0, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return 1;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        if (result == 1) {
            Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        builder.setProgress(100, values[0], false);
        nf = builder.build();
        nm.notify(0, nf);
        if (values[0] == 100) {    //下载完成后点击安装
            Intent it = new Intent(Intent.ACTION_VIEW);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.setDataAndType(Uri.parse(downLoadUrl), "application/vnd.android.package-archive");
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentTitle("下载完成").setContentText("点击安装").setContentIntent(pendingIntent);
            nf = builder.build();
            nm.notify(0, nf);
        }
    }
}
