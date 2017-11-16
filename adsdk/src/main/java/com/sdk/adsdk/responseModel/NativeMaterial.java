package com.sdk.adsdk.responseModel;


import com.sdk.adsdk.requestModel.Size;

import java.util.List;

public class NativeMaterial {

    private String id;

    /**
     * 广告交互类型 1.NO_INTERACT(无动 作) 2.SURFING (打开链接) 3.DOWNLOAD (下载)
     */
    private int interaction_type;


    /**
     * 1:图片 2:图文 3:文字
     */
    private int material_type;

    /**
     * 1:图片 2:图文 3:文字
     */
    private String ad_logo;

    private String title;

    private String description;

    private String image_url;

    private Size image_size;

    private String logo_url;

    private Size logo_size;

    private String click_url;

    private List<String> impression_monitor_url;

    private String app_name;

    private String app_pkg;

    private String app_size;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInteraction_type() {
        return interaction_type;
    }

    public void setInteraction_type(int interaction_type) {
        this.interaction_type = interaction_type;
    }

    public int getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(int material_type) {
        this.material_type = material_type;
    }

    public String getAd_logo() {
        return ad_logo;
    }

    public void setAd_logo(String ad_logo) {
        this.ad_logo = ad_logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Size getImage_size() {
        return image_size;
    }

    public void setImage_size(Size image_size) {
        this.image_size = image_size;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public Size getLogo_size() {
        return logo_size;
    }

    public void setLogo_size(Size logo_size) {
        this.logo_size = logo_size;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public List<String> getImpression_monitor_url() {
        return impression_monitor_url;
    }

    public void setImpression_monitor_url(List<String> impression_monitor_url) {
        this.impression_monitor_url = impression_monitor_url;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_pkg() {
        return app_pkg;
    }

    public void setApp_pkg(String app_pkg) {
        this.app_pkg = app_pkg;
    }

    public String getApp_size() {
        return app_size;
    }

    public void setApp_size(String app_size) {
        this.app_size = app_size;
    }
}
