package com.xuliwen.viewtest.viewevent.outfun;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xuliwen.viewtest.Constants;
import com.xuliwen.viewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 冲突的外部拦截法，但事实证明ViewPager嵌套ListView，scroolView是不需要处理滑动冲突的
 */
public class OutFunActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<ListView> mItemList;
    private List<String> mListViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_fun);
        mViewPager= (ViewPager) findViewById(R.id.viewPager1);
        mViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Constants.VIEWEVENT,"myViewPager+OnClickListener");
            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(Constants.VIEWEVENT,"myViewPager+OnTouchListener");
                return false;
            }
        });
        initListViewData();
        initItem();
        mViewPager.setAdapter(pagerAdapter);
    }

    private void initListViewData(){
        mListViewData = new ArrayList<>();
        mListViewData.add("测试数据1");
        mListViewData.add("测试数据2");
        mListViewData.add("测试数据3");
        mListViewData.add("测试数据4");
        mListViewData.add("测试数据1");
        mListViewData.add("测试数据2");
        mListViewData.add("测试数据3");
        mListViewData.add("测试数据4");
        mListViewData.add("测试数据1");
        mListViewData.add("测试数据2");
        mListViewData.add("测试数据3");
        mListViewData.add("测试数据4");
        mListViewData.add("测试数据1");
        mListViewData.add("测试数据2");
        mListViewData.add("测试数据3");
        mListViewData.add("测试数据4");

    }

    private void initItem(){
        mItemList=new ArrayList<>();
      for(int i=0;i<4;i++){
          ListView listView=new ListView(this);
          listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,mListViewData));
          mItemList.add(listView);
      }
    }


    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mItemList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            container.removeView(mItemList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(mItemList.get(position));


            return mItemList.get(position);
        }
    };
}
