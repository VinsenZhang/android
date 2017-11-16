package com.sdk.adsdk;


import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sdk.adsdk.utils.ThreadManager;

public class AdsSdk {

    public static void init(Context context){
        ThreadManager.init();
        Fresco.initialize(context);

    }

}
