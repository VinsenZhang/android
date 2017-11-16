package com.sdk.adsdk.requestModel;


public class Version {

    private int major;// 主版本

    private int minor;// 次版本

    private int micor;// 三位版本


    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getMicor() {
        return micor;
    }

    public void setMicor(int micor) {
        this.micor = micor;
    }
}
