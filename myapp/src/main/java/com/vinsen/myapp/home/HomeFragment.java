package com.vinsen.myapp.home;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vinsen.myapp.R;
import com.vinsen.myapp.home.adapter.HomeBannerAdapter;
import com.vinsen.myapp.home.bean.CateBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private ViewPager banner;
    private List<ImageView> banners = new ArrayList<>();
    private HomeBannerAdapter bannerAdapter;

    private RecyclerView categoryRv;
    private List<CateBean> cateData = new ArrayList<>();

    private RecyclerView listRv;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    private void initView(View contentView) {
        // banner view init
        banner = (ViewPager) contentView.findViewById(R.id.home_banner);
        bannerAdapter = new HomeBannerAdapter(banners);
        banner.setAdapter(bannerAdapter);


        // cate view init
        categoryRv = (RecyclerView) contentView.findViewById(R.id.home_cate);
        GridLayoutManager cateLayoutManager = new GridLayoutManager(this.getActivity(), 4);
        cateLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRv.setLayoutManager(cateLayoutManager);


        // list view init
        listRv = (RecyclerView) contentView.findViewById(R.id.home_list);
    }
}
