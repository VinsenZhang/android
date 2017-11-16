package com.sdk.adsdk.ads;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sdk.adsdk.Ad;
import com.sdk.adsdk.R;
import com.sdk.adsdk.cons.HttpCons;
import com.sdk.adsdk.http.HttpGet;
import com.sdk.adsdk.http.HttpPost;
import com.sdk.adsdk.listener.FullScreenListenner;
import com.sdk.adsdk.responseModel.Ads;
import com.sdk.adsdk.responseModel.ClickResponse;
import com.sdk.adsdk.responseModel.NativeMaterial;
import com.sdk.adsdk.responseModel.ResponseBean;
import com.sdk.adsdk.utils.CommUtils;
import com.sdk.adsdk.utils.HttpUtil;

import java.util.List;

public class FullScreenAd extends Ad {

    private FullScreenListenner listener;

    private View contentView;
    private LayoutInflater inflater;
    private Dialog dialog;
    private SimpleDraweeView fullScreenImg;
    private ImageView closeIcon;

    private List<String> impression_monitor_url;
    private String clickUrl;
    private SimpleDraweeView adLogo;
    private int interactionType;

    private String target_url;
    private int actionType;
    private List<String> clickMonitorUrl;
    private List<String> dendMonitorUrl;
    private List<String> dstartMonitorUrl;
    private List<String> istartMonitorUrl;
    private List<String> iendMonitorUrl;

    public FullScreenAd(Activity context) {
        this.mContext = context;

    }

    @Override
    protected void loadData() {
        String requestJson = HttpUtil.getRequestJson(mContext, adId, getScreenWidth(), getScreenHeight());
        HttpPost httpPost = new HttpPost(HttpCons.URL.api, null, requestJson);
        ResponseBean responseBean = HttpUtil.sendRequest(httpPost);
        try {
            List<Ads> adDatas = responseBean.getAd_data();
            if (responseBean.isSuccess() && adDatas != null && adDatas.size() > 0) {
                Ads ad = adDatas.get(0);
                NativeMaterial nativeMaterial = ad.getNative_material();

                clickUrl = nativeMaterial.getClick_url();


                String imageUrl = nativeMaterial.getImage_url();
                String adLogoUrl = nativeMaterial.getAd_logo();
                impression_monitor_url = nativeMaterial.getImpression_monitor_url();


                fullScreenImg.setImageURI(Uri.parse(imageUrl));
                adLogo.setImageURI(Uri.parse(adLogoUrl));

                if (this.listener != null) {
                    listener.fullScreenSuccess();
                }
                CommUtils.sendBrodcast(mContext, 1, "success");
            } else {
                if (this.listener != null) {
                    listener.failed(HttpCons.httpCode.get(responseBean.getErr_code()));
                }
                CommUtils.sendBrodcast(mContext, 0, HttpCons.httpCode.get(responseBean.getErr_code()));
                Log.e("sdk", "load ad error : " + HttpCons.httpCode.get(responseBean.getErr_code()));
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
        loadData();
    }

    private void initView() {
        dialog = new Dialog(mContext, R.style.Dialog_Fullscreen);

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.full_screen, null);
        fullScreenImg = contentView.findViewById(R.id.full_screen_img);
        fullScreenImg.setOnClickListener(onFullScreenAdClick);
        closeIcon = contentView.findViewById(R.id.close_icon);
        closeIcon.setOnClickListener(closeListenner);
        adLogo = contentView.findViewById(R.id.ad_logo);


        dialog.setContentView(contentView);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = new WindowManager.LayoutParams();
        attributes.width = getScreenWidth();
        attributes.height = getScreenHeight();
        window.setAttributes(attributes);

    }

    // 关闭广告
    private final View.OnClickListener closeListenner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (dialog != null) dialog.dismiss();

            if (listener != null) listener.dismiss();
        }
    };


    //广告点击跳转
    private final View.OnClickListener onFullScreenAdClick = new View.OnClickListener() {
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


    public void show() {
        if (dialog == null) {
            throw new IllegalArgumentException("please init before show");
        }
        if (impression_monitor_url != null) {
            for (String url : impression_monitor_url) {
                HttpGet httpGet = new HttpGet(url);
                HttpUtil.sendRequest(httpGet);
            }
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }


    public void setListener(FullScreenListenner listener) {
        this.listener = listener;
    }


    @Override
    public void destory() {
        if (contentView != null) contentView = null;
        if (inflater != null) inflater = null;
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
