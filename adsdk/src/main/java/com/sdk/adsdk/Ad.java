package com.sdk.adsdk;


import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.sdk.adsdk.listener.AdListener;


public abstract class Ad {

    protected String adId;

    protected Activity mContext;

    protected abstract void loadData();

    public abstract void init(String adId);

    public abstract void destory();

    protected int getScreenWidth() {
        return getMetric().widthPixels;
    }

    protected int getScreenHeight() {
        return getMetric().heightPixels;
    }

    protected DisplayMetrics getMetric() {
        DisplayMetrics metric = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }
}
