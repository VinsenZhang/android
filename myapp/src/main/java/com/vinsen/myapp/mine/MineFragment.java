package com.vinsen.myapp.mine;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinsen.myapp.R;
import com.vinsen.myapp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class MineFragment extends BaseFragment {


    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Override
    public void loadData(int page, int pageSize) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View contentView) {

    }


    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }
}
