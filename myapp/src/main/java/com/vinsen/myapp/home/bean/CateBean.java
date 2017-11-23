package com.vinsen.myapp.home.bean;

/**
 * @author by zhangshengwen on 2017/11/23.
 */

public class CateBean {

    /**
     * 分类的名称
     */
    private String name;
    /**
     * 分类的图片地址
     */
    private String iconUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return iconUrl;
    }

    public void setUrl(String url) {
        this.iconUrl = url;
    }
}
