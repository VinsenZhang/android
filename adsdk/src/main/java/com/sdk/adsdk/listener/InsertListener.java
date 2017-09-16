package com.sdk.adsdk.listener;

import com.sdk.adsdk.ads.InsertAd;

public interface InsertListener extends AdListener {

    public void insertSuccess(InsertAd insertAd);

}
