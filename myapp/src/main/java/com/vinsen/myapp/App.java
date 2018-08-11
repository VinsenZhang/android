package com.vinsen.myapp;

import android.app.Application;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.domain)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
