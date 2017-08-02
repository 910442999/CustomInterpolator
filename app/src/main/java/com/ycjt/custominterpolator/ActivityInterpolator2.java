package com.ycjt.custominterpolator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ycjt.custominterpolator.adapter.MyAdapter;
import com.ycjt.custominterpolator.bean.DataBean;
import com.ycjt.custominterpolator.view.FullyLinearLayoutManager;
import com.ycjt.custominterpolator.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ActivityInterpolator2 extends AppCompatActivity implements MyScrollView.OnScrollListener {

    @Bind(R.id.imageview)
    ImageView mImageview;

    @Bind(R.id.parent_layout)
    LinearLayout mMainContent;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.scrollView)
    MyScrollView mScrollView;

    // 位于顶部的布局

    private LinearLayout mTopTabLayout;

    private MyAdapter mMyAdapter;
    private List<DataBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator2);
        ButterKnife.bind(this);

        mTopTabLayout = (LinearLayout) findViewById(R.id.top_tab_layout);

        mScrollView.setOnScrollListener(this);
        //解决scrollview无法显示顶部只显示RecyclerView第一条的问题
        mScrollView.smoothScrollTo(0, 0);
        dataList = new ArrayList<>();
        initRecyclerView();
        //当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要
                onScroll(mScrollView.getScrollY());

            }
        });

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Home").setActiveColorResource(R.color.colorPrimary));


        bottomNavigationBar .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        dataList.clear();
                        for (int i = 0; i < 60; i++) {
                            DataBean data2 = new DataBean();
                            data2.setText("我是第 Home 布局 第  " + i + " 条数据");
                            dataList.add(data2);
                        }
                        mMyAdapter.notifyDataSetChanged();
                        break;
                    case 1:

                        dataList.clear();
                        for (int i = 0; i < 60; i++) {
                            DataBean data2 = new DataBean();
                            data2.setText("我是第 Books 布局 第  " + i + " 条数据");
                            dataList.add(data2);
                        }
                        mMyAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        dataList.clear();
                        for (int i = 0; i < 60; i++) {
                            DataBean data2 = new DataBean();
                            data2.setText("我是第 Music 布局 第  " + i + " 条数据");
                            dataList.add(data2);
                        }
                        mMyAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }


    private void initRecyclerView() {

        if (mMyAdapter == null) {

            for (int i = 0; i < 60; i++) {
                DataBean data2 = new DataBean();
                data2.setText("我是第 " + i + " 条数据 ,阿萨德发送到");
                dataList.add(data2);
            }

            FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);
            mRecyclerview.setLayoutManager(linearLayoutManager);
            mMyAdapter = new MyAdapter(this, dataList);
            mRecyclerview.setAdapter(mMyAdapter);
//            mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                }
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//
//
//                }
//            });
        }
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mImageview.getBottom());
        mTopTabLayout.layout(0, mBuyLayout2ParentTop, mTopTabLayout.getWidth(), mBuyLayout2ParentTop + mTopTabLayout.getHeight());

    }
}
