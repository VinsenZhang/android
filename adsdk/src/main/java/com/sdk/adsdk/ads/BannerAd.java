package com.sdk.adsdk.ads;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdk.adsdk.Ad;
import com.sdk.adsdk.R;
import com.sdk.adsdk.listener.BannerListenner;

import java.util.Timer;
import java.util.TimerTask;

public class BannerAd extends Ad {

    public long refreshTime = 2 * 60 * 1000;// 两分钟自动刷新banner内容

    private View contentView;

    private LayoutInflater inflater;

    private BannerListenner listener;
    private ImageView icon;
    private TextView title;
    private TextView subTitle;

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
        icon = contentView.findViewById(R.id.banner_icon);
        title = contentView.findViewById(R.id.banner_title);
        subTitle = contentView.findViewById(R.id.banner_sub_title);
    }

    public View getView() {
        if (contentView == null) {
            throw new IllegalArgumentException("not init bannerAd,please init before getView");
        }
        return contentView;
    }


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
