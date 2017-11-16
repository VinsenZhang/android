package com.sdk.adsdk.requestModel;

/**
 * Created by zhangshengwen on 2017/9/18.
 */

public class Media {

    // 1: app, 2: wap
    private int type;

    private App app;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
