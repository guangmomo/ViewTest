package com.xuliwen.viewtest.list.listview.custom_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuliwen.viewtest.App;
import com.xuliwen.viewtest.R;

import java.lang.ref.PhantomReference;
import java.util.List;

/**
 * Created by xlw on 2017/6/10.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Soft> datas;

    public CustomAdapter(List<Soft> datas){
        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView!=null){
             view=convertView;
             viewHolder= (ViewHolder) view.getTag();
        }else {
            view= LayoutInflater.from(App.getContext()).inflate(R.layout.custom_adapter_item,null);
            ImageView softIconImageView= (ImageView) view.findViewById(R.id.soft_icon_imageView);
            TextView softNameImageView= (TextView) view.findViewById(R.id.soft_name_textView);
            viewHolder=new ViewHolder(softIconImageView,softNameImageView);
            view.setTag(viewHolder);
        }
        viewHolder.iconImageView.setImageResource(datas.get(position).getIconId());
        viewHolder.nameTextView.setText(datas.get(position).getName());
        return view;
    }

    private static class ViewHolder{

        ViewHolder(ImageView iconImageView,TextView nameTextView){
            this.iconImageView=iconImageView;
            this.nameTextView=nameTextView;
        }

        ImageView iconImageView;
        TextView nameTextView;
    }
}
