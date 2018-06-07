package com.vinsen.myapp.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

/**
 * @author  by zhangshengwen on 2017/11/28.
 */

public class ViewHolder {

    /**
     * 保存所有itemview的集合
     */
    private SparseArray<View> mViews;

    private View mConvertView;

    private ViewHolder(Context context, int layoutId) {
        mConvertView = View.inflate(context, layoutId, null);
        mConvertView.setTag(this);

        mViews = new SparseArray<>();
    }

    public static ViewHolder newsInstance(View convertView, Context context, int layoutId) {
        if (convertView == null) {
            return new ViewHolder(context, layoutId);

        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    /**
     * 获取根view
     */
    public View getConverView() {
        return mConvertView;
    }

    /**
     * 获取节点view
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getItemView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = mConvertView.findViewById(id);
            mViews.append(id, view);
        }

        return (T) view;
    }
}
