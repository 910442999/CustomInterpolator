package com.ycjt.custominterpolator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycjt.custominterpolator.R;
import com.ycjt.custominterpolator.bean.DataBean;

import java.util.List;

/**
 * Created by zhang on 2016/12/24.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    public Context context;
    public List<DataBean> dataList;

    public MyAdapter(Context context, List<DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    //加载条目布局,创建Holder对象,并返回
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.my_item, null);
        //将条目布局view传入Holder中.
        MyHolder myHolder = new MyHolder(itemView);
        return myHolder;
    }

    //填充数据
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        //好友logo
        holder.tv_text.setText(dataList.get(position).getText());

    }

    //条目个数
    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public TextView tv_text;

        public MyHolder(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);

        }
    }
}
