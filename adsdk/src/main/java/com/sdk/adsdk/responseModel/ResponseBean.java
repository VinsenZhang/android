package com.sdk.adsdk.responseModel;



import java.util.List;

public class ResponseBean {

    private boolean success;

    private List<Ads> ad_data;

    private int ad_src;

    private int ad_cache_time;

    private int err_code;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Ads> getAd_data() {
        return ad_data;
    }

    public void setAd_data(List<Ads> ad_data) {
        this.ad_data = ad_data;
    }

    public int getAd_src() {
        return ad_src;
    }

    public void setAd_src(int ad_src) {
        this.ad_src = ad_src;
    }

    public int getAd_cache_time() {
        return ad_cache_time;
    }

    public void setAd_cache_time(int ad_cache_time) {
        this.ad_cache_time = ad_cache_time;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

}
