package com.vinsen.application;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by op on 17/3/28.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private ArrayList<String> datas;

    private LayoutInflater inflater;

    private Context context;

    private RecycleItemClickListener onClickListener;

    public MainAdapter(Context context) {
        datas = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MainAdapter setDatas(ArrayList<String> data) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
        return this;
    }

    public MainAdapter setOnClickListener(RecycleItemClickListener listener) {
        this.onClickListener = listener;
        return this;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.activity_main_recycle_item, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.name.setText(datas.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public interface RecycleItemClickListener {
        void onClick(View clickView, int clickPosition);
    }


    class MainViewHolder extends RecyclerView.ViewHolder {


        private TextView name;

        public MainViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
        }

    }
}
