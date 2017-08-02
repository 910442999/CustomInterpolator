package com.ycjt.custominterpolator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ycjt.custominterpolator.adapter.MyAdapter;
import com.ycjt.custominterpolator.bean.DataBean;
import com.ycjt.custominterpolator.view.FullyLinearLayoutManager;
import com.ycjt.custominterpolator.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ActivityInterpolator extends AppCompatActivity implements MyScrollView.OnScrollListener {

    @Bind(R.id.imageview)
    ImageView mImageview;

    @Bind(R.id.parent_layout)
    LinearLayout mMainContent;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.scrollView)
    MyScrollView mScrollView;
    @Bind(R.id.tab1)
    TextView mTab1;
    @Bind(R.id.tab2)
    TextView mTab2;

    // 位于顶部的布局

    private LinearLayout mTopTabLayout;

    private MyAdapter mMyAdapter;
    private List<DataBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
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

    @OnClick({R.id.tab1, R.id.tab2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                dataList.clear();
                for (int i = 0; i < 60; i++) {
                    DataBean data2 = new DataBean();
                    data2.setText("我是第 tab1 布局 第  " + i + " 条数据");
                    dataList.add(data2);
                }
                mMyAdapter.notifyDataSetChanged();
                break;
            case R.id.tab2:

                dataList.clear();
                for (int i = 0; i < 60; i++) {
                    DataBean data2 = new DataBean();
                    data2.setText("我是第 tab2 布局 第  " + i + " 条数据");
                    dataList.add(data2);
                }
                mMyAdapter.notifyDataSetChanged();

                break;
        }
    }
}
