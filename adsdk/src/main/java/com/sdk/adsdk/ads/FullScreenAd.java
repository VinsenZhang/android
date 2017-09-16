package com.sdk.adsdk.ads;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sdk.adsdk.Ad;
import com.sdk.adsdk.R;
import com.sdk.adsdk.listener.FullScreenListenner;

public class FullScreenAd extends Ad {

    private FullScreenListenner listener;

    private View contentView;
    private LayoutInflater inflater;
    private Dialog dialog;
    private ImageView fullScreenImg;
    private ImageView closeIcon;

    public FullScreenAd(Activity context) {
        this.mContext = context;

    }

    @Override
    protected void loadData() {

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

        }
    };


    public void show() {
        if (dialog == null) {
            throw new IllegalArgumentException("please init before show");
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
