package com.xuliwen.viewtest.list.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.list.gridview.custom_adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.List;


public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Integer> mDatas;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        initDatas();
         adapter=new CustomAdapter(mDatas);
        gridView= (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }

    private void initDatas(){
        mDatas=new ArrayList<>();
        for(int i=0; i<40; i++){
            mDatas.add(R.drawable.ic_chrome);
        }
    }

    public void notifyDadaSetChange(View view) {
        adapter.notifyDataSetChanged();//只会更新当前显示的items
    }
}
