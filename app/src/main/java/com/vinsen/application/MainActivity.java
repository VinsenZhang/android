package com.vinsen.application;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinsen.mylibrary.ThreadManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("vinsen");
    }

    private LinearLayout container;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method


        container = (LinearLayout) findViewById(R.id.container);
        tv = new TextView(this);
        tv.setText(VoiceHelper.play("test test"));

        ThreadManager.init();

        ThreadManager.doInBackGround(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        final Button btn = (Button) findViewById(R.id.btn);
        if (ThreadManager.isAllFinish()){
            Log.e("vinsen","all task finish ...");
        }else {
            Log.e("vinsen","have task on doing");
        }
    }

}
