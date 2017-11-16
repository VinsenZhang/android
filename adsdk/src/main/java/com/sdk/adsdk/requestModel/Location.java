package com.sdk.adsdk.requestModel;

public class Location {

    /**
     * 坐标类型(1. WGS84 2. GCJ02 3. BD09)
     */
    private int type;


    private double longitude;

    private double latitude;

    private long timestamp;

    /**
     * 1:native
     * 2:baidu
     */
    private int source;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }
}
