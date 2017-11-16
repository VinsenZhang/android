package com.sdk.adsdk.ads;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sdk.adsdk.R;
import com.sdk.adsdk.cons.HttpCons;
import com.sdk.adsdk.http.HttpGet;
import com.sdk.adsdk.http.HttpPost;
import com.sdk.adsdk.responseModel.Ads;
import com.sdk.adsdk.responseModel.ClickResponse;
import com.sdk.adsdk.responseModel.NativeMaterial;
import com.sdk.adsdk.responseModel.ResponseBean;
import com.sdk.adsdk.utils.CommUtils;
import com.sdk.adsdk.utils.HttpUtil;

import java.util.List;


public class OpenScreenAd extends Activity {


    private static int leftTime = 5 * 1000;// 开屏倒计时时间

    private int appIconResId = R.drawable.sdk_def;

    private String appName = "AdsSdk title";

    public static final String NEXT_ACTIVITY_CLASSNAME = "nextClass";

    public static final String APP_ICON = "appIcon";

    public static final String APP_NAME = "appName";

    public static final String AD_ID = "adId";


    private Bundle extras;
    private Class nextAc;
    private String adId;
    private FrameLayout container;
    private SimpleDraweeView adImg;
    private TextView nextBtn;
    private DisplayMetrics metric;
    private List<String> impression_monitor_url;
    private String clickUrl;
    private int interactionType;
    private SimpleDraweeView appIcon;
    private TextView appNameTv;
    private SimpleDraweeView adLogo;
    private String target_url;
    private int actionType;
    private List<String> clickMonitorUrl;
    private List<String> dendMonitorUrl;
    private List<String> dstartMonitorUrl;
    private List<String> istartMonitorUrl;
    private List<String> iendMonitorUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_screen);

        metric = CommUtils.getMetric(this);

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
        appIconResId = extras.getInt(APP_ICON, R.drawable.sdk_def);
        appName = extras.getString(APP_NAME, "Ad Sdk");
        initView();
        loadData();

    }


    private void initView() {
        container = findViewById(R.id.open_screen_container);
        adImg = findViewById(R.id.open_screen_img);
        adLogo = findViewById(R.id.ad_icon);
        adImg.setOnClickListener(onAdClickListenner);
        appIcon = findViewById(R.id.app_icon);
        appNameTv = findViewById(R.id.app_name);
        nextBtn = findViewById(R.id.go_next_btn);
        nextBtn.setText("跳过(" + leftTime + ")");
        nextBtn.setOnClickListener(nextBtnClickListenner);


        appIcon.setImageResource(appIconResId);
        appNameTv.setText(appName);
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

        String requestJson = HttpUtil.getRequestJson(this, adId, metric.widthPixels, metric.heightPixels);
        HttpPost httpPost = new HttpPost(HttpCons.URL.api, null, requestJson);
        ResponseBean responseBean = HttpUtil.sendRequest(httpPost);
        try {
            List<Ads> adDatas = responseBean.getAd_data();
            if (responseBean.isSuccess() && adDatas != null && adDatas.size() > 0) {
                Ads ad = adDatas.get(0);
                NativeMaterial nativeMaterial = ad.getNative_material();
                String adLogoUrl = nativeMaterial.getAd_logo();


                clickUrl = nativeMaterial.getClick_url();




                String imageUrl = nativeMaterial.getImage_url();
                impression_monitor_url = nativeMaterial.getImpression_monitor_url();

                adImg.setImageURI(Uri.parse(imageUrl));
                adLogo.setImageURI(Uri.parse(adLogoUrl));

            } else {
                Log.e("sdk", "load ad error : " + HttpCons.httpCode.get(responseBean.getErr_code()));
            }
        } catch (NullPointerException e) {
            Log.e("sdk", "load ad error : " + e.getMessage());
        }

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

            ClickResponse clickResponse = HttpUtil.getClickRes(clickUrl);
            if (clickResponse != null && clickResponse.isSuccess()) {
                target_url = clickResponse.getTarget_url();
                actionType = clickResponse.getAction_type();
                clickMonitorUrl = clickResponse.getClick_monitor_url();
                dendMonitorUrl = clickResponse.getDend_monitor_url();
                dstartMonitorUrl = clickResponse.getDstart_monitor_url();
                istartMonitorUrl = clickResponse.getIstart_monitor_url();
                iendMonitorUrl = clickResponse.getIend_monitor_url();
                interactionType = clickResponse.getInteractionType();
            }

            if (TextUtils.isEmpty(target_url)) return;
            if (interactionType == 3) {
                new MyDownloadAsync(OpenScreenAd.this, target_url).execute();
            } else {
                Intent it = new Intent(OpenScreenAd.this, AdWebView.class);
                it.putExtra(AdWebView.TARGET_URL, target_url);
                startActivity(it);
            }


            for (String url : clickMonitorUrl) {
                HttpGet httpGet = new HttpGet(url);
                HttpUtil.sendRequest(httpGet);
            }
        }
    };


    // 销毁的时候移除所有view
    @Override
    protected void onDestroy() {
        super.onDestroy();
        container.removeAllViews();
    }
}
