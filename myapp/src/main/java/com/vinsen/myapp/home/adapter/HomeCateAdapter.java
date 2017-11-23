package com.vinsen.myapp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinsen.myapp.R;
import com.vinsen.myapp.home.bean.CateBean;

import java.util.Collections;
import java.util.List;

/**
 * @author by zhangshengwen on 2017/11/23.
 */

public class HomeCateAdapter extends RecyclerView.Adapter<HomeCateAdapter.HomeCateItem> {


    private List<CateBean> datas;

    private LayoutInflater inflater;

    private Context mContext;

    public HomeCateAdapter(Context mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<CateBean> datas) {
        if (this.datas == null) {
            this.datas = Collections.emptyList();
        }
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public HomeCateItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.home_cate_item, parent);
        return new HomeCateItem(itemView);
    }

    @Override
    public void onBindViewHolder(HomeCateItem holder, int position) {
        final CateBean cateBean = datas.get(position);
        holder.name.setText(cateBean.getName());
        Picasso.with(mContext)
                .load(cateBean.getUrl())
                .placeholder(R.drawable.ic_launcher)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class HomeCateItem extends RecyclerView.ViewHolder {

        public ImageView icon;

        public TextView name;


        public HomeCateItem(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.home_cate_item_icon);
            name = (TextView) itemView.findViewById(R.id.home_cate_item_name);
        }
    }
}
