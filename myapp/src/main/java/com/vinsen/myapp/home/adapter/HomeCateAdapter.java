package com.vinsen.myapp.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinsen.myapp.R;
import com.vinsen.myapp.base.CommAdapter;
import com.vinsen.myapp.base.ViewHolder;
import com.vinsen.myapp.home.bean.CateBean;

import java.util.List;

/**
 * @author by zhangshengwen on 2017/11/23.
 */

public class HomeCateAdapter extends CommAdapter<CateBean> {


    public HomeCateAdapter(List<CateBean> datas, int layoutRes) {
        super(datas, layoutRes);
    }

    @Override
    protected void setUI(ViewHolder holder, int position, Context context) {
        ImageView icon = holder.getItemView(R.id.home_cate_item_icon);
        TextView name = holder.getItemView(R.id.home_cate_item_name);
        Picasso.with(context)
                .load(datas.get(position).getUrl())
                .placeholder(R.drawable.ic_launcher)
                .into(icon);
        name.setText(datas.get(position).getName());
    }
}
