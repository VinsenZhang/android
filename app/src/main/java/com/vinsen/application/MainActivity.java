package com.vinsen.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {


    private ArrayList<String> datas = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        initData();
        MainAdapter adapter = new MainAdapter(this);
        adapter.setDatas(datas).setOnClickListener(listener);
        recyclerView.setAdapter(adapter);


    }

    private final MainAdapter.RecycleItemClickListener listener = new MainAdapter.RecycleItemClickListener() {
        @Override
        public void onClick(View clickView, int clickPosition) {
            try {
                String clsName = getPackageName() + "." + datas.get(clickPosition);
                Class cls = Class.forName(clsName);
                startActivity(new Intent(MainActivity.this, cls));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    private void initData() {
        datas.add(TabLayoutActivity.class.getSimpleName());
        datas.add(TabLayoutActivity.class.getSimpleName());
        datas.add(TabLayoutActivity.class.getSimpleName());
        datas.add(TabLayoutActivity.class.getSimpleName());
        datas.add(TabLayoutActivity.class.getSimpleName());
    }


    private MenuItem menuItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menuItem = menu.findItem(R.id.menu_one);
        menuItem.setTitle("one");
        menuItem = menu.findItem(R.id.menu_two);
        menuItem.setTitle("two");
        menuItem = menu.findItem(R.id.menu_three);
        menuItem.setTitle("three");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        GridLayoutManager layoutManager = null;
        switch (item.getItemId()) {
            case R.id.menu_one:
                layoutManager = new GridLayoutManager(this, 1);
                break;
            case R.id.menu_two:
                layoutManager = new GridLayoutManager(this, 2);

                break;
            case R.id.menu_three:
                layoutManager = new GridLayoutManager(this, 3);
                break;
        }
        if (layoutManager != null) {
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
        return super.onOptionsItemSelected(item);
    }
}
