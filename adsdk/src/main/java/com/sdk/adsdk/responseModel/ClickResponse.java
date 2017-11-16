package com.sdk.adsdk.responseModel;


import java.util.List;

public class ClickResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 最终地址
     */
    private String target_url;

    /**
     * target动作类型:1,直接下载 2,下 面方法下载
     */
    private int action_type;

    /**
     * 交互类型:
     * 1 无动作
     * 2 打开LP着陆页 3下载
     */
    private int InteractionType;


    /**
     * 点击监控地址
     */
    private List<String> click_monitor_url;


    /**
     * 下载开始监控地址
     */
    private List<String> dstart_monitor_url;


    /**
     * 下载完成监控地址
     */
    private List<String> dend_monitor_url;


    /**
     * 安装开始监控地址
     */
    private List<String> istart_monitor_url;


    /**
     * 安装完成监控地址
     */
    private List<String> iend_monitor_url;

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public int getAction_type() {
        return action_type;
    }

    public void setAction_type(int action_type) {
        this.action_type = action_type;
    }

    public int getInteractionType() {
        return InteractionType;
    }

    public void setInteractionType(int interactionType) {
        InteractionType = interactionType;
    }

    public List<String> getClick_monitor_url() {
        return click_monitor_url;
    }

    public void setClick_monitor_url(List<String> click_monitor_url) {
        this.click_monitor_url = click_monitor_url;
    }

    public List<String> getDstart_monitor_url() {
        return dstart_monitor_url;
    }

    public void setDstart_monitor_url(List<String> dstart_monitor_url) {
        this.dstart_monitor_url = dstart_monitor_url;
    }

    public List<String> getDend_monitor_url() {
        return dend_monitor_url;
    }

    public void setDend_monitor_url(List<String> dend_monitor_url) {
        this.dend_monitor_url = dend_monitor_url;
    }

    public List<String> getIstart_monitor_url() {
        return istart_monitor_url;
    }

    public void setIstart_monitor_url(List<String> istart_monitor_url) {
        this.istart_monitor_url = istart_monitor_url;
    }

    public List<String> getIend_monitor_url() {
        return iend_monitor_url;
    }

    public void setIend_monitor_url(List<String> iend_monitor_url) {
        this.iend_monitor_url = iend_monitor_url;
    }
}
