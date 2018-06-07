package com.vinsen.myapp.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vinsen.myapp.R;

import java.util.List;

/**
 * @author zhangshengwen on 2017/11/17.
 */

public class TabChangeHelper implements RadioGroup.OnCheckedChangeListener {

    private List<Fragment> fragments;

    private FragmentManager fragmentManager;

    private int containerId;


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public TabChangeHelper(Activity context, List<Fragment> fragments, RadioGroup radioGroup, int containerId) {
        this.fragments = fragments;
        this.containerId = containerId;
        this.fragmentManager = context.getFragmentManager();

        RadioButton defaultBtn = (RadioButton) radioGroup.getChildAt(0);
        defaultBtn.setChecked(true);

        fragmentManager.beginTransaction().replace(containerId, fragments.get(0)).commit();

        radioGroup.setOnCheckedChangeListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Fragment fragment = null;
        switch (checkedId) {
            case R.id.radio_home:
                fragment = fragments.get(0);
                break;
            case R.id.radio_category:
                fragment = fragments.get(1);
                break;
            case R.id.radio_mine:
                fragment = fragments.get(2);
                break;
        }
        if (fragment == null) {
            throw new NullPointerException("not exist this fragment");
        }

        fragmentManager.beginTransaction().replace(containerId, fragment).commit();
    }
}
