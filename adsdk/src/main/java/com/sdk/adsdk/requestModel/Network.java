package com.sdk.adsdk.requestModel;


public class Network {

    private String ip;

    /**
     * 0. UNKNOWN
     * 1. WIFI
     * 2. 2G
     * 3. 3G
     * 4. 4G
     */
    private int type;

    private String imsi;// optional false

    private String cellular_id;// optional false

    private String cellular_operator;// optional false


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getCellular_id() {
        return cellular_id;
    }

    public void setCellular_id(String cellular_id) {
        this.cellular_id = cellular_id;
    }

    public String getCellular_operator() {
        return cellular_operator;
    }

    public void setCellular_operator(String cellular_operator) {
        this.cellular_operator = cellular_operator;
    }
}
