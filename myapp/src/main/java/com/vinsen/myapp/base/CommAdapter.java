package com.vinsen.myapp.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author by zhangshengwen on 2017/11/28.
 */

public abstract class CommAdapter<T> extends BaseAdapter {

    protected List<T> datas;

    protected int layoutRes;

    public CommAdapter(List<T> datas, int layoutRes) {
        this.datas = datas;
        this.layoutRes = layoutRes;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.newsInstance(convertView, parent.getContext(), layoutRes);
        setUI(viewHolder, position, parent.getContext());
        return viewHolder.getConverView();
    }

    protected abstract void setUI(ViewHolder holder, int position, Context context);

}
