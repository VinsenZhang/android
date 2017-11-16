package com.sdk.adsdk.requestModel;

import java.util.List;


public class Device {

    /**
     * 0:unknow
     * 1:phone
     * 2:pc
     * 3:tablet
     * 4:tv
     */
    private int type;

    private List<DeviceId> ids;

    /**
     * 0:unkonw
     * 1:android
     * 2:ios
     * 3:wp
     */
    private int os_type;

    // 操作系统版本
    private Version os_version;

    // 设备品牌
    private String brand;

    //设备类型
    private String model;

    // 屏幕尺寸
    private Size screen_size;

    private double screen_density;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<DeviceId> getIds() {
        return ids;
    }

    public void setIds(List<DeviceId> ids) {
        this.ids = ids;
    }

    public int getOs_type() {
        return os_type;
    }

    public void setOs_type(int os_type) {
        this.os_type = os_type;
    }

    public Version getOs_version() {
        return os_version;
    }

    public void setOs_version(Version os_version) {
        this.os_version = os_version;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Size getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(Size screen_size) {
        this.screen_size = screen_size;
    }

    public double getScreen_density() {
        return screen_density;
    }

    public void setScreen_density(double screen_density) {
        this.screen_density = screen_density;
    }
}
