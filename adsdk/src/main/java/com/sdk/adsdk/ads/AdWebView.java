package com.sdk.adsdk.ads;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.sdk.adsdk.R;

public class AdWebView extends Activity {

    public static final String TARGET_URL = "targetUrl";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_web);

        String url = getIntent().getStringExtra(TARGET_URL);

        final WebView webView = findViewById(R.id.ad_web_view);

        final ImageView backImg = findViewById(R.id.back_icon);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdWebView.this.finish();
            }
        });

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }
}
