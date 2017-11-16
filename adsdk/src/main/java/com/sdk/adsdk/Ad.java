package com.sdk.adsdk;


import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.sdk.adsdk.listener.AdListener;
import com.sdk.adsdk.utils.CommUtils;


public abstract class Ad {

    protected String adId;

    protected Activity mContext;

    protected abstract void loadData();

    public abstract void init(String adId);

    public abstract void destory();

    protected int getScreenWidth() {
        return CommUtils.getMetric(mContext).widthPixels;
    }

    protected int getScreenHeight() {
        return CommUtils.getMetric(mContext).heightPixels;
    }


}
