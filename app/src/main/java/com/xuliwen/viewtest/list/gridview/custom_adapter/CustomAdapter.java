package com.xuliwen.viewtest.list.gridview.custom_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xuliwen.viewtest.App;
import com.xuliwen.viewtest.R;

import java.util.List;

/**
 * Created by xlw on 2017/6/13.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Integer> imageIds;

     public CustomAdapter(List<Integer> imageIds){
        this.imageIds=imageIds;
    }

    @Override
    public int getCount() {
        return imageIds.size();
    }

    @Override
    public Object getItem(int position) {
        return imageIds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view;
        ViewHolder holder;
        if(convertView!=null){
            view=convertView;
            holder= (ViewHolder) view.getTag();
        }else{
            view= LayoutInflater.from(App.getContext()).inflate(R.layout.custom_grid_adapter_item,null);
            holder=new ViewHolder((ImageView) view.findViewById(R.id.grid_imageView));
            view.setTag(holder);
        }
        holder.imageView.setImageResource(imageIds.get(position));
        return view;
    }


    private static class ViewHolder{
        ImageView imageView;

        ViewHolder(ImageView imageView){
            this.imageView=imageView;
        }
    }
}
