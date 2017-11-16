package com.sdk.adsdk.requestModel;

public class Adslot {

    private String id;

    private Size adslot_size;

    private int redirect_type;

    public int getRedirect_type() {
        return redirect_type;
    }

    public void setRedirect_type(int redirect_type) {
        this.redirect_type = redirect_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Size getAdslot_size() {
        return adslot_size;
    }

    public void setAdslot_size(Size adslot_size) {
        this.adslot_size = adslot_size;
    }
}
