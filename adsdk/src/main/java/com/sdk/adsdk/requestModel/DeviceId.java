package com.sdk.adsdk.requestModel;


public class DeviceId {
    /**
     * 1:imei
     * 2:mac
     * 3:idfa
     * 4:androidid
     * 5:idfv
     * <p>
     * android: 1 2 4
     * ios: 2 3 5
     */
    private int type;

    private String id;// 设备id


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
