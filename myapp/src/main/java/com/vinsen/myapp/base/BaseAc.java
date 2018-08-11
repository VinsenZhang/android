package com.vinsen.myapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vinsen.myapp.ActivityIn;


public abstract class BaseAc  extends AppCompatActivity implements ActivityIn {


    protected Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutRes());
        init();
        initView();
        loadData(getPage(), getPageSize());
    }







}
