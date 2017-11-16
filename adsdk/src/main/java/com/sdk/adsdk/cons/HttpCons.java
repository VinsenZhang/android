package com.sdk.adsdk.cons;


import java.util.HashMap;
import java.util.Map;

public class HttpCons {


    public static final class URL {
        public static final String api = "http://47.52.66.153:8090/v5/api";

    }


    public static Map<Integer,String> httpCode ;

    static {
        httpCode = new HashMap<>();
        httpCode.put(HttpCode.RES_OK,"success");
        httpCode.put(HttpCode.ERR_PKG,"包名填写错误，或未填写");
        httpCode.put(HttpCode.ERR_DOMAIN, "站域名填写错误");
        httpCode.put(HttpCode.ERR_URL, "地址填写错误");
        httpCode.put(HttpCode.ERR_CLIENT_VERSION, "ClientVersion填写错误");
        httpCode.put(HttpCode.ERR_SCREEN_SIZE, "屏幕宽高填写错误");
        httpCode.put(HttpCode.ERR_AD_ID, "告位ID填写错误");
        httpCode.put(HttpCode.ERR_INVALID_ID, "广告位ID无效");
        httpCode.put(HttpCode.ERR_DEVICE_IDFA, "Device.Ids idfa填写错误");
        httpCode.put(HttpCode.ERR_IMEI, "imei填写错误");
        httpCode.put(HttpCode.ERR_OS_TYPE, "OsType设备操作系统类型填写错误");
        httpCode.put(HttpCode.ERR_DEVICE_TYPE, "设备类型填写错误");
        httpCode.put(HttpCode.ERR_NETWORK_IP, "IP填写错误");
        httpCode.put(HttpCode.ERR_ADSLOT_SIZE, "告位尺寸填写错误");
        httpCode.put(HttpCode.ERR_USER_AGENT, "User_Agent填写错误");
        httpCode.put(HttpCode.ERR_DEVICE_ID, "Ids中ID有空");
        httpCode.put(HttpCode.ERR_ANALYZE, "客户端json请求，解析错误");
        httpCode.put(HttpCode.ERR_SERVER_CONFIG, "服务器配置问题，请联系管理员");
        httpCode.put(HttpCode.ERR_CALL_CONFIG, "联系运营同学配置广告");
        httpCode.put(HttpCode.ERR_UNKNOW, "未知错误");
        httpCode.put(HttpCode.ERR_HTTP_METHOD, "请求http method错误，请使 post");
        httpCode.put(HttpCode.ERR_EMPTY_HTTP, "请求http 请求内容为空");
    }


    private static final class HttpCode {


        public static final int RES_OK = 0;

        public static final int ERR_PKG = 10001;//包名填写错误，或未填写

        public static final int ERR_DOMAIN = 10002;// 站域名填写错误

        public static final int ERR_URL = 10003;// 站 地址填写错误

        public static final int ERR_CLIENT_VERSION = 10004;//ClientVersion填写错误

        public static final int ERR_SCREEN_SIZE = 10005;// 屏幕宽 填写错误

        public static final int ERR_AD_ID = 10006;// 告位ID填写错误

        public static final int ERR_INVALID_ID = 10007;//广告位ID 无效

        public static final int ERR_DEVICE_IDFA = 10008;//Device.Ids idfa填写错误

        public static final int ERR_IMEI = 10009;//Device.Ids imei填写错误

        public static final int ERR_OS_TYPE = 10010;//Device.OsType设备操作系统类型填写错误

        public static final int ERR_DEVICE_TYPE = 10011;//Device.Type 设备类型填写错误

        public static final int ERR_NETWORK_IP = 10012;//Network.Ip 服务 端API对接，IP填写错误

        public static final int ERR_ADSLOT_SIZE = 10014;//Adslot.AdslotSize  告位尺 填写错误

        public static final int ERR_USER_AGENT = 10015;//User_Agent填写错误

        public static final int ERR_DEVICE_ID = 10016;//Device.Ids中ID有空

        public static final int ERR_ANALYZE = 20001;//客户端json请求，解析错误

        public static final int ERR_SERVER_CONFIG = 2000201;//服务 配置问题，请联系管理员

        public static final int ERR_CALL_CONFIG = 2000202;//联系运营同学配置 告

        public static final int ERR_UNKNOW = 20003;//未知错误

        public static final int ERR_HTTP_METHOD = 20004;//请求http method错误，请使 post

        public static final int ERR_EMPTY_HTTP = 20005;//请求http 请求内容为空
    }


}
