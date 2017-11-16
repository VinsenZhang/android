package com.sdk.adsdk.ads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sdk.adsdk.Ad;
import com.sdk.adsdk.R;
import com.sdk.adsdk.cons.HttpCons;
import com.sdk.adsdk.http.HttpGet;
import com.sdk.adsdk.http.HttpPost;
import com.sdk.adsdk.listener.InsertListener;
import com.sdk.adsdk.responseModel.Ads;
import com.sdk.adsdk.responseModel.ClickResponse;
import com.sdk.adsdk.responseModel.NativeMaterial;
import com.sdk.adsdk.responseModel.ResponseBean;
import com.sdk.adsdk.utils.HttpUtil;

import java.util.List;

public class InsertAd extends Ad {


    private View contentView;
    private LayoutInflater inflater;
    private Dialog dialog;
    private InsertListener listener;
    private SimpleDraweeView headImg;
    private SimpleDraweeView icon;
    private TextView titleTv;
    private TextView subTitleTv;
    private String clickUrl;
    private List<String> impression_monitor_url;
    private LinearLayout titleContainer;
    private int interactionType;

    private String target_url;
    private int actionType;
    private List<String> clickMonitorUrl;
    private List<String> dendMonitorUrl;
    private List<String> dstartMonitorUrl;
    private List<String> istartMonitorUrl;
    private List<String> iendMonitorUrl;

    public InsertAd(Activity context) {
        this.mContext = context;

    }

    @Override
    public void init(String adId) {
        this.adId = adId;
        initView();
        loadData();

    }


    private void initView() {

        dialog = new Dialog(mContext, R.style.insertDialogTheme);

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.insert, null);
        contentView.setOnClickListener(contentViewListenner);

        titleContainer = contentView.findViewById(R.id.title_layout);

        headImg = contentView.findViewById(R.id.head_img);
        icon = contentView.findViewById(R.id.insert_icon);
        titleTv = contentView.findViewById(R.id.insert_title);
        subTitleTv = contentView.findViewById(R.id.insert_desc);

        final ImageView close = contentView.findViewById(R.id.close_icon);
        close.setOnClickListener(closeListenner);
        dialog.setContentView(contentView);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = new WindowManager.LayoutParams();
        attributes.width = getScreenWidth() * 3 / 5;
        attributes.height = getScreenHeight() * 2 / 5;
        window.setAttributes(attributes);
        window.setBackgroundDrawableResource(R.color.sdk_clarity);

    }

    // 加载数据
    @Override
    protected void loadData() {
        String requestJson = HttpUtil.getRequestJson(mContext, adId, getScreenWidth() * 3 / 5, getScreenWidth() * 3 / 5);
        HttpPost httpPost = new HttpPost(HttpCons.URL.api, null, requestJson);
        ResponseBean responseBean = HttpUtil.sendRequest(httpPost);
        try {
            if (responseBean != null) {
                List<Ads> adDatas = responseBean.getAd_data();
                if (responseBean.isSuccess() && adDatas != null && adDatas.size() > 0) {
                    Ads ad = adDatas.get(0);
                    NativeMaterial nativeMaterial = ad.getNative_material();

                    int materialType = nativeMaterial.getMaterial_type();


                    String adlogo = nativeMaterial.getAd_logo();
                    clickUrl = nativeMaterial.getClick_url();


                    String title = nativeMaterial.getTitle();
                    String description = nativeMaterial.getDescription();
                    String imageUrl = nativeMaterial.getImage_url();
                    impression_monitor_url = nativeMaterial.getImpression_monitor_url();


                    if (materialType == 1) {
                        // 图片
                        titleContainer.setVisibility(View.GONE);
                        headImg.setVisibility(View.VISIBLE);

                    } else if (materialType == 2) {
                        // 图文
                        headImg.setVisibility(View.VISIBLE);
                        titleContainer.setVisibility(View.VISIBLE);

                        headImg.setImageURI(Uri.parse(imageUrl));
                        titleTv.setText(title);
                        subTitleTv.setText(description);
                    } else {
                        // 文字
                        titleContainer.setVisibility(View.VISIBLE);
                        headImg.setVisibility(View.GONE);

                        titleTv.setText(title);
                        subTitleTv.setText(description);
                    }

                    icon.setImageURI(Uri.parse(adlogo));
                    if (this.listener != null) {
                        listener.insertSuccess();
                    }


                } else {
                    Log.e("sdk", "load ad error : " + HttpCons.httpCode.get(responseBean.getErr_code()));
                    if (this.listener != null) {
                        listener.failed(HttpCons.httpCode.get(responseBean.getErr_code()));
                    }
                }
            }
        } catch (NullPointerException e) {
            Log.e("sdk", "load ad error : " + e.getMessage());
            if (this.listener != null) {
                listener.failed(e.getMessage());
            }
        }
    }


    // 关闭插屏
    private final View.OnClickListener closeListenner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (dialog != null) {
                dialog.dismiss();
            }

            if (listener != null) {
                listener.dismiss();
            }
        }
    };


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


    public void setListener(InsertListener listener) {
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
