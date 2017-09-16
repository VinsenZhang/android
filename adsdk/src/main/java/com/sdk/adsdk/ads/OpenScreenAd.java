package com.sdk.adsdk.ads;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdk.adsdk.R;


public class OpenScreenAd extends Activity {


    public int leftTime = 5 * 1000;// 开屏倒计时时间

    public int appIconResId = R.drawable.sdk_def;

    public String appName = "Sdk title";

    public static final String NEXT_ACTIVITY_CLASSNAME = "nextClass";

    public static final String AD_ID = "adId";


    private Bundle extras;
    private Class nextAc;
    private String adId;
    private FrameLayout container;
    private ImageView adImg;
    private TextView nextBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_screen);
        Intent intent = getIntent();
        extras = intent.getExtras();
        if (extras == null) {
            throw new RuntimeException("do not know next activity and adId");
        }
        String nextClassName = extras.getString(NEXT_ACTIVITY_CLASSNAME);
        try {
            nextAc = Class.forName(nextClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        adId = extras.getString(AD_ID);
        initView();
        loadData();

    }


    private void initView() {
        container = findViewById(R.id.open_screen_container);
        adImg = findViewById(R.id.open_screen_img);
        adImg.setOnClickListener(onAdClickListenner);
        nextBtn = findViewById(R.id.go_next_btn);
        nextBtn.setText("跳过(" + leftTime + ")");
        nextBtn.setOnClickListener(nextBtnClickListenner);
    }

    private void goNext() {
        Intent intent = new Intent(this, nextAc);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }


    // 跳过按钮倒计时
    private final CountDownTimer timer = new CountDownTimer(leftTime, 1000) {
        @Override
        public void onTick(long l) {
            nextBtn.setText("跳过(" + l / 1000 + ")");
        }

        @Override
        public void onFinish() {
            goNext();
        }
    };


    // 数据加载
    private void loadData() {
        timer.start();
    }


    // 跳过按钮点击
    private final View.OnClickListener nextBtnClickListenner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            timer.cancel();
            goNext();
        }
    };


    //广告点击
    private final View.OnClickListener onAdClickListenner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    // 销毁的时候移除所有view
    @Override
    protected void onDestroy() {
        super.onDestroy();
        container.removeAllViews();
    }
}
