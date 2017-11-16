package com.sdk.adsdk.ads;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sdk.adsdk.Ad;
import com.sdk.adsdk.R;
import com.sdk.adsdk.cons.HttpCons;
import com.sdk.adsdk.http.HttpGet;
import com.sdk.adsdk.http.HttpPost;
import com.sdk.adsdk.listener.BannerListenner;
import com.sdk.adsdk.responseModel.Ads;
import com.sdk.adsdk.responseModel.ClickResponse;
import com.sdk.adsdk.responseModel.NativeMaterial;
import com.sdk.adsdk.responseModel.ResponseBean;
import com.sdk.adsdk.utils.CommUtils;
import com.sdk.adsdk.utils.HttpUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerAd extends Ad {

    public long refreshTime = 2 * 60 * 1000;// 两分钟自动刷新banner内容

    private View contentView;

    private LayoutInflater inflater;

    private BannerListenner listener;
    private SimpleDraweeView icon;
    private TextView titleTv;
    private TextView subTitleTv;

    private String clickUrl;
    private List<String> impression_monitor_url;
    private LinearLayout titleContainer;
    private int interactionType;
    private SimpleDraweeView adLogo;

    private String target_url;
    private int actionType;
    private List<String> clickMonitorUrl;
    private List<String> dendMonitorUrl;
    private List<String> dstartMonitorUrl;
    private List<String> istartMonitorUrl;
    private List<String> iendMonitorUrl;

    public BannerAd(Activity context) {
        this.mContext = context;
    }

    private final Timer timer = new Timer();

    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            loadData();
        }
    };


    @Override
    protected void loadData() {
        String requestJson = HttpUtil.getRequestJson(mContext, adId, 75, 75);
        HttpPost httpPost = new HttpPost(HttpCons.URL.api, null, requestJson);
        ResponseBean responseBean = HttpUtil.sendRequest(httpPost);

        try {
            if (responseBean != null) {
                List<Ads> adDatas = responseBean.getAd_data();
                if (responseBean.isSuccess() && adDatas != null && adDatas.size() > 0) {
                    Ads ad = adDatas.get(0);
                    NativeMaterial nativeMaterial = ad.getNative_material();

                    int materialType = nativeMaterial.getMaterial_type();


                    String adLogoUrl = nativeMaterial.getAd_logo();
                    clickUrl = nativeMaterial.getClick_url();


                    String title = nativeMaterial.getTitle();
                    String description = nativeMaterial.getDescription();
                    String imageUrl = nativeMaterial.getImage_url();
                    impression_monitor_url = nativeMaterial.getImpression_monitor_url();


                    if (materialType == 1) {
                        // 图片
                        titleContainer.setVisibility(View.GONE);
                        icon.setVisibility(View.VISIBLE);
                        icon.setImageURI(Uri.parse(imageUrl));
                    } else if (materialType == 2) {
                        // 图文
                        icon.setVisibility(View.VISIBLE);
                        titleContainer.setVisibility(View.VISIBLE);

                        icon.setImageURI(Uri.parse(imageUrl));
                        titleTv.setText(title);
                        subTitleTv.setText(description);
                    } else {
                        // 文字
                        titleContainer.setVisibility(View.VISIBLE);
                        icon.setVisibility(View.GONE);

                        titleTv.setText(title);
                        subTitleTv.setText(description);
                    }

                    adLogo.setImageURI(Uri.parse(adLogoUrl));

                    if (this.listener != null) {
                        listener.bannerSuccess(contentView);
                    }

                    CommUtils.sendBrodcast(mContext, 1, "success");
                } else {
                    Log.e("sdk", "load ad error : " + HttpCons.httpCode.get(responseBean.getErr_code()));
                    if (this.listener != null) {
                        listener.failed(HttpCons.httpCode.get(responseBean.getErr_code()));
                    }
                    CommUtils.sendBrodcast(mContext, 0, HttpCons.httpCode.get(responseBean.getErr_code()));
                }
            }
        } catch (NullPointerException e) {
            Log.e("sdk", "load ad error : " + e.getMessage());
            if (this.listener != null) {
                listener.failed(e.getMessage());
            }
            CommUtils.sendBrodcast(mContext, 0, e.getMessage());
        }
    }



    @Override
    public void init(String adId) {
        this.adId = adId;
        initView();
        timer.schedule(task, 0, refreshTime);
    }

    private void initView() {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.banner, null);
        contentView.setOnClickListener(contentViewListenner);
        icon = contentView.findViewById(R.id.banner_icon);
        adLogo = contentView.findViewById(R.id.ad_logo);
        titleContainer = contentView.findViewById(R.id.title_layout);
        titleTv = contentView.findViewById(R.id.banner_title);
        subTitleTv = contentView.findViewById(R.id.banner_sub_title);
    }

    public View getView() {
        if (contentView == null) {
            throw new IllegalArgumentException("not init bannerAd,please init before getView");
        }
        if (impression_monitor_url != null) {
            for (String url : impression_monitor_url) {
                HttpGet httpGet = new HttpGet(url);
                HttpUtil.sendRequest(httpGet);
            }
        }

        return contentView;
    }


    //广告点击跳转
    private final View.OnClickListener contentViewListenner = new View.OnClickListener() {
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
                new MyDownloadAsync(mContext, target_url).execute();
            } else {
                Intent it = new Intent(mContext, AdWebView.class);
                it.putExtra(AdWebView.TARGET_URL, target_url);
                mContext.startActivity(it);
            }

            for (String url : clickMonitorUrl) {
                HttpGet httpGet = new HttpGet(url);
                HttpUtil.sendRequest(httpGet);
            }
        }
    };



    public void setListener(BannerListenner listener) {
        this.listener = listener;
    }


    @Override
    public void destory() {
        if (contentView != null) contentView = null;
        if (inflater != null) inflater = null;
        task.cancel();
        timer.cancel();
    }
}
