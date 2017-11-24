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

import com.squareup.picasso.Picasso;
import com.vinsen.myapp.R;
import com.vinsen.myapp.home.adapter.HomeBannerAdapter;
import com.vinsen.myapp.home.adapter.HomeCateAdapter;
import com.vinsen.myapp.home.adapter.HomeListAdapter;
import com.vinsen.myapp.home.bean.CateBean;
import com.vinsen.myapp.home.bean.HomeListBean;

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
    private HomeCateAdapter cateAdapter;

    private RecyclerView listRv;
    private List<HomeListBean> listData = new ArrayList<>();
    private HomeListAdapter listAdapter;


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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        loadData();
        return view;
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
        cateAdapter = new HomeCateAdapter(getActivity(), cateData);
        categoryRv.setAdapter(cateAdapter);

        // list view init
        listRv = (RecyclerView) contentView.findViewById(R.id.home_list);
        GridLayoutManager listLayoutManager = new GridLayoutManager(getActivity(), 1);
        listLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listRv.setLayoutManager(listLayoutManager);
        listAdapter = new HomeListAdapter(getActivity(), listData);
        listRv.setAdapter(listAdapter);

    }

    private void loadData() {
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity())
                    .load("http://mpic.tiankong.com/7a1/afd/7a1afd23a1586dccd5296ed8ccca99e1/640.jpg@360h")
                    .placeholder(R.drawable.ic_launcher)
                    .into(imageView);
            banners.add(imageView);
        }
        bannerAdapter.notifyDataSetChanged();

        for (int i = 0; i < 4; i++) {
            CateBean cateBean = new CateBean();
            cateBean.setUrl("http://mpic.tiankong.com/7a1/afd/7a1afd23a1586dccd5296ed8ccca99e1/640.jpg@360h");
            cateBean.setName("cate tab" + i);
            cateData.add(cateBean);
        }

        cateAdapter.notifyDataSetChanged();


        for (int i = 0; i < 20; i++) {
            HomeListBean listBean = new HomeListBean();
            listBean.setIconUrl("http://mpic.tiankong.com/7a1/afd/7a1afd23a1586dccd5296ed8ccca99e1/640.jpg@360h");
            listBean.setTitle("list data title " + i);
            listBean.setSubTitle("list sub title " + i);
            listData.add(listBean);
        }

        listAdapter.notifyDataSetChanged();
    }

}
