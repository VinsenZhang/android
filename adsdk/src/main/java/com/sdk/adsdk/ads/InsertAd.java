package com.sdk.adsdk.ads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sdk.adsdk.Ad;
import com.sdk.adsdk.R;
import com.sdk.adsdk.listener.InsertListener;

public class InsertAd extends Ad {


    private View contentView;
    private LayoutInflater inflater;
    private Dialog dialog;
    private InsertListener listener;
    private ImageView headImg;
    private ImageView icon;


    public InsertAd(Activity context) {
        this.mContext = context;

    }

    @Override
    public void init(String adId) {
        this.adId = adId;
        initView();
        loadData();

    }


    private void initView() {

        dialog = new Dialog(mContext, R.style.insertDialogTheme);

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.insert, null);
        final ImageView close = contentView.findViewById(R.id.close_icon);
        headImg = contentView.findViewById(R.id.head_img);
        contentView.setOnClickListener(contentViewListenner);
        icon = contentView.findViewById(R.id.insert_icon);
        close.setOnClickListener(closeListenner);
        dialog.setContentView(contentView);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = new WindowManager.LayoutParams();
        attributes.width = getScreenWidth() * 3 / 5;
        attributes.height = getScreenHeight() * 2 / 5;
        window.setAttributes(attributes);
        window.setBackgroundDrawableResource(R.color.sdk_clarity);

    }

    // 加载数据
    @Override
    protected void loadData() {

    }


    // 关闭插屏
    private final View.OnClickListener closeListenner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (dialog != null) {
                dialog.dismiss();
            }

            if (listener != null) {
                listener.dismiss();
            }
        }
    };


    //广告点击跳转
    private final View.OnClickListener contentViewListenner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    public void show() {
        if (dialog == null) {
            throw new IllegalArgumentException("please init before show");
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }


    public void setListener(InsertListener listener) {
        this.listener = listener;
    }


    @Override
    public void destory() {
        if (contentView != null) contentView = null;
        if (inflater != null) inflater = null;
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }


}
