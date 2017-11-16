package com.vinsen.myapp;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.vinsen.myapp.base.BaseAc;

public class MainActivity extends BaseAc {

    private FrameLayout container;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        container = (FrameLayout) findViewById(R.id.main_container);
        radioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
    }
}
