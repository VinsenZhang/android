package com.vinsen.myapp.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * @author zhangshengwen on 2017/11/23.
 */

public class HomeBannerAdapter extends PagerAdapter {


    private List<ImageView> imageViews;


    public HomeBannerAdapter(List<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
