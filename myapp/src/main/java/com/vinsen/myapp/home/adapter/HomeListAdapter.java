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
import com.vinsen.myapp.home.bean.HomeListBean;

import java.util.Collections;
import java.util.List;

/**
 * @author by zhangshengwen on 2017/11/23.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListItem> {


    private List<HomeListBean> datas;

    private LayoutInflater inflater;

    private Context mContext;

    public HomeListAdapter(Context mContext,List<HomeListBean> datas) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.datas = datas;
    }



    @Override
    public HomeListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.home_list_item, parent,false);
        return new HomeListItem(itemView);
    }

    @Override
    public void onBindViewHolder(HomeListItem holder, int position) {
        final HomeListBean listBean = datas.get(position);
        holder.title.setText(listBean.getTitle());
        holder.subTitle.setText(listBean.getSubTitle());
        Picasso.with(mContext)
                .load(listBean.getIconUrl())
                .placeholder(R.drawable.ic_launcher)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class HomeListItem extends RecyclerView.ViewHolder {

        public ImageView icon;

        public TextView title;

        public TextView subTitle;


        public HomeListItem(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.home_list_item_icon);
            title = (TextView) itemView.findViewById(R.id.home_list_item_title);
            subTitle = (TextView) itemView.findViewById(R.id.home_list_item_desc);
        }
    }
}
