package com.sdk.testsdk;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.sdk.adsdk.ads.BannerAd;
import com.sdk.adsdk.ads.FullScreenAd;
import com.sdk.adsdk.ads.InsertAd;
import com.sdk.adsdk.ads.OpenScreenAd;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private InsertAd insertAd;
    private FrameLayout container;
    private BannerAd bannerAd;
    private FullScreenAd fullScreenAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAds();
    }

    private void initAds() {
        insertAd = new InsertAd(this);
        insertAd.init("123123");

        bannerAd = new BannerAd(this);
        bannerAd.init("123131");

        fullScreenAd = new FullScreenAd(this);
        fullScreenAd.init("132434");

    }

    private void initView() {
        container = (FrameLayout) findViewById(R.id.ad_container);
        final Button banner = (Button) findViewById(R.id.banner);
        banner.setOnClickListener(this);
        final Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(this);
        final Button openScreen = (Button) findViewById(R.id.open_screen);
        openScreen.setOnClickListener(this);
        final Button fullScreen = (Button) findViewById(R.id.full_screen);
        fullScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                showInsertAd();
                break;
            case R.id.banner:
                addBannerView();
                break;
            case R.id.full_screen:
                showFullScreenAd();
                break;
            case R.id.open_screen:
                openScreenAd();
                break;
        }
    }

    private void openScreenAd() {
        Intent intent = new Intent(this,OpenScreenAd.class);
        Bundle extras = new Bundle();
        extras.putString(OpenScreenAd.NEXT_ACTIVITY_CLASSNAME,NextActivity.class.getName());
        extras.putString(OpenScreenAd.AD_ID,"12412414");
        intent.putExtras(extras);
        startActivity(intent);
    }


    private void showFullScreenAd() {
        fullScreenAd.show();
    }

    private void addBannerView() {
        container.addView(bannerAd.getView());
    }


    private void showInsertAd() {
        insertAd.show();
    }
}
