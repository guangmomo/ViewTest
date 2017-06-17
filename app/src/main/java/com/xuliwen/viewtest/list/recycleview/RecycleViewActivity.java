package com.xuliwen.viewtest.list.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;


import com.xuliwen.viewtest.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView mTestRecycleView;
    private List<String> mDatas;
    private CommonAdapter mCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initData();
        mTestRecycleView= (RecyclerView) findViewById(R.id.test_recycleView);
     //   mTestRecycleView.setLayoutManager(new LinearLayoutManager(this));  //设置RecycleView的布局
      //  mTestRecycleView.setLayoutManager(new GridLayoutManager(this,3));
       // mTestRecycleView.setLayoutManager(new GridLayoutManager(this,3,LinearLayoutManager.HORIZONTAL,true ));

        mTestRecycleView.setAdapter(new CommonAdapter(mDatas));
    }



    protected void initData()
    {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }


}


