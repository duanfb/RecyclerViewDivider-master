package com.study.wnw.recyclerviewdivider;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //定义RecyclerView
    private RecyclerView mRecyclerView = null;

    //定义一个List集合，用于存放RecyclerView中的每一个数据
    private List<String> mData = null;

    //定义一个Adapter
    private MyAdapter mAdapter;

    //定义一个LinearLayoutManager
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView三步曲+LayoutManager
        initView();
        initData();
        mAdapter = new MyAdapter(this, mData);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildLayoutPosition(view);
                int i = position % mLayoutManager.getSpanCount();
                int divider = 30;
                int dividerCount = mLayoutManager.getSpanCount() - 1;
                int total = dividerCount * divider;
                int itemDiv = total / mLayoutManager.getSpanCount();

                switch (i) {
                    case 0:
                        outRect.set(0, 0, itemDiv, divider);
                        break;
                    case 1:
                        outRect.set(itemDiv / 2, 0, itemDiv / 2, divider);
                        break;
                    case 2:
                        outRect.set(itemDiv, 0, 0, divider);
                        break;
                }
            }
        });

    }

    //初始化View
    private void initView() {
        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    //初始化加载到RecyclerView中的数据, 我这里只是给每一个Item添加了String类型的数据
    private void initData() {
        mData = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mData.add("Item" + i);
        }
    }
}
