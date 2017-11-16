package com.sdk.adsdk.requestModel;

/**
 * Created by zhangshengwen on 2017/9/18.
 */

public class App {

    private String package_name;

    private String channel_id; //optional false

    private Version app_version;//optional false


    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public Version getApp_version() {
        return app_version;
    }

    public void setApp_version(Version app_version) {
        this.app_version = app_version;
    }
}
