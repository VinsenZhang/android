package com.vinsen.myapp;

import android.os.Bundle;
import android.view.View;

public interface FragmentIn extends Base {

    public void init(Bundle savedInstanceState);

    public abstract void initView(View contentView);

}
