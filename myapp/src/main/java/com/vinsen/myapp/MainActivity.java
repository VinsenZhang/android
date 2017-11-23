package com.vinsen.myapp;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.vinsen.myapp.base.BaseAc;
import com.vinsen.myapp.category.CategoryFragment;
import com.vinsen.myapp.home.HomeFragment;
import com.vinsen.myapp.mine.MineFragment;
import com.vinsen.myapp.utils.TabChangeHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAc {


    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.main_radio_group);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(CategoryFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        new TabChangeHelper(this, fragments, radioGroup, R.id.main_container);
    }


}
