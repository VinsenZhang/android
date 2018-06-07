package com.vinsen.myapp.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinsen.myapp.R;
import com.vinsen.myapp.base.CommAdapter;
import com.vinsen.myapp.base.ViewHolder;
import com.vinsen.myapp.home.bean.HomeListBean;

import java.util.List;

/**
 * @author by zhangshengwen on 2017/11/23.
 */

public class HomeListAdapter extends CommAdapter<HomeListBean> {


    public HomeListAdapter(List<HomeListBean> datas, int layoutRes) {
        super(datas, layoutRes);
    }

    @Override
    protected void setUI(ViewHolder holder, int position, Context context) {
        ImageView icon = holder.getItemView(R.id.home_list_item_icon);
        TextView title = holder.getItemView(R.id.home_list_item_title);
        TextView subTitle = holder.getItemView(R.id.home_list_item_desc);

        Picasso.with(context)
                .load(datas.get(position).getIconUrl())
                .placeholder(R.drawable.ic_launcher)
                .into(icon);

        title.setText(datas.get(position).getTitle());
        subTitle.setText(datas.get(position).getSubTitle());
    }
}
