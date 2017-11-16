package com.sdk.adsdk.utils;


import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sdk.adsdk.http.HttpGet;
import com.sdk.adsdk.requestModel.Adslot;
import com.sdk.adsdk.requestModel.App;
import com.sdk.adsdk.requestModel.Client;
import com.sdk.adsdk.requestModel.Device;
import com.sdk.adsdk.requestModel.DeviceId;
import com.sdk.adsdk.requestModel.Media;
import com.sdk.adsdk.requestModel.Network;
import com.sdk.adsdk.requestModel.RequestBean;
import com.sdk.adsdk.requestModel.Size;
import com.sdk.adsdk.requestModel.Version;
import com.sdk.adsdk.responseModel.ClickResponse;
import com.sdk.adsdk.responseModel.ResponseBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * 网路请求工具类
 */
public class HttpUtil {


    public static String getRequestJson(Activity context, String adId, int width, int height) {
        RequestBean requestBean = new RequestBean();


        Media media = new Media();
        media.setType(1);//app
        App app = new App();
        app.setPackage_name(CommUtils.getPkgName(context));
        Version version = new Version();
        version.setMajor(CommUtils.getVersionCode(context));
        app.setApp_version(version);
        media.setApp(app);


        Device device = new Device();
        device.setBrand(CommUtils.getBrand());
        device.setModel(CommUtils.getModel());
        device.setOs_type(1);//android
        device.setType(1);
        Version osVersion = new Version();
        osVersion.setMajor(CommUtils.getVersionCode(context));
        device.setOs_version(osVersion);

        Size screenSize = new Size();
        DisplayMetrics metric = CommUtils.getMetric(context);
        screenSize.setWidth(metric.widthPixels);
        screenSize.setHeight(metric.heightPixels);
        device.setScreen_size(screenSize);

        List<DeviceId> deviceIds = new ArrayList<>();

        DeviceId deviceId = new DeviceId();
        deviceId.setId(CommUtils.getDeviceId(context));
        deviceId.setType(1);
        deviceIds.add(deviceId);

        DeviceId mac = new DeviceId();
        mac.setId(CommUtils.getMacAddress());
        mac.setType(2);
        deviceIds.add(mac);

        DeviceId androidId = new DeviceId();
        androidId.setId(CommUtils.getAndroidId(context));
        androidId.setType(4);
        deviceIds.add(androidId);


        device.setIds(deviceIds);
        requestBean.setDevice(device);


        Network network = new Network();
        network.setType(CommUtils.getNetworkType(context));
        requestBean.setNetwork(network);


        Client client = new Client();
        client.setType(3);
        Version clientVersion = new Version();
        clientVersion.setMajor(1);
        clientVersion.setMinor(3);
        clientVersion.setMicor(2);
        client.setClient_version(clientVersion);
        requestBean.setClient(client);


        Adslot adslot = new Adslot();
        adslot.setId(adId);
        adslot.setRedirect_type(2);
        Size size = new Size();
        size.setWidth(width);
        size.setHeight(height);
        adslot.setAdslot_size(size);
        requestBean.setAdslot(adslot);


        Gson gson = new Gson();
        return gson.toJson(requestBean);
    }


    public static ResponseBean sendRequest(Callable<String> httpPost) {
        Future<String> callableRes = ThreadManager.getCallableRes(httpPost);
        try {
            String json = callableRes.get();
            Gson gson = new Gson();
            return gson.fromJson(json, ResponseBean.class);
        } catch (JsonSyntaxException | InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        }
    }


    public static ClickResponse getClickRes(String clickUrl) {
        HttpGet httpGet = new HttpGet(clickUrl);
        Future<String> callableRes = ThreadManager.getCallableRes(httpGet);
        try {
            String json = callableRes.get();
            Gson gson = new Gson();
            return gson.fromJson(json, ClickResponse.class);
        } catch (JsonSyntaxException | InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        }

    }


}
