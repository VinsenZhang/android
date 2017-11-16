package com.sdk.adsdk.requestModel;

public class Client {

    private int type = 3;

    private Version client_version;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Version getClient_version() {
        return client_version;
    }

    public void setClient_version(Version client_version) {
        this.client_version = client_version;
    }
}
