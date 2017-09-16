package com.sdk.adsdk.listener;

import com.sdk.adsdk.ads.BannerAd;

public interface BannerListenner extends AdListener {
    public void bannerSuccess(BannerAd bannerAd);
}
