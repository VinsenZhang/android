package com.vinsen.myapp.home;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class HomeFragment extends Fragment {


    private ViewPager banner;
    private List<ImageView> banners = new ArrayList<>();
    private HomeBannerAdapter bannerAdapter;

    private GridView categoryRv;
    private List<CateBean> cateData = new ArrayList<>();
    private HomeCateAdapter cateAdapter;

    private GridView listRv;
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
        categoryRv = (GridView) contentView.findViewById(R.id.home_cate);
        cateAdapter = new HomeCateAdapter(cateData,R.layout.home_cate_item);
        categoryRv.setAdapter(cateAdapter);

        // list view init
        listRv = (GridView) contentView.findViewById(R.id.home_list);
        listAdapter = new HomeListAdapter(listData, R.layout.home_list_item);
        listRv.setAdapter(listAdapter);

    }

    private void loadData() {
        banners.clear();
        cateData.clear();
        listData.clear();

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            Glide.with(getActivity())
                    .load("http://mpic.tiankong.com/7a1/afd/7a1afd23a1586dccd5296ed8ccca99e1/640.jpg@360h")
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
