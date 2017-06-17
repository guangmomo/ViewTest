package com.xuliwen.viewtest.list.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.xuliwen.viewtest.R;

import java.util.List;

/**
 * 类名：
 * Created by xu on 17-4-24.
 * 功能：
 */



public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.MyViewHolder>
{

    private List<String> mDatas;

    public CommonAdapter(List<String> data){
        mDatas=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)//创建ViewHolder
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview_common, parent, false);//使用LayoutInflater inflate出item对应的view
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)//给Holder的控件设置数据
    {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount()//返回item的数量
    {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder //ViewHolder的作用是获取item的控件实例
    {

        TextView tv;

        MyViewHolder(View view)//传入的是item对应的view
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
