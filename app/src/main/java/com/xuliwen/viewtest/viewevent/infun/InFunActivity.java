package com.xuliwen.viewtest.viewevent.infun;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xuliwen.viewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 冲突的内部拦截法，但事实证明ViewPager嵌套ListView，scroolView是不需要处理滑动冲突的
 */
public class InFunActivity extends AppCompatActivity {
    private ViewPager outVP;
    private ViewPager inVP;
    private List<View> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_fun);
        initView();
    }


    private void initView(){
        outVP= (ViewPager) findViewById(R.id.out_viewPager);
        mItemList=new ArrayList<>();
        for(int i=0;i<4;i++){
            View view=getLayoutInflater().inflate(R.layout.out_viewpager_layout,null);
            List<View> inViewList=new ArrayList<>();
            for(int j=0;j<4;j++){
                TextView textView=new TextView(this);
                textView.setText(j+"");
                inViewList.add(textView);
            }
            ViewPager inVP= (ViewPager) view.findViewById(R.id.in_viewpager);
            inVP.setAdapter(new MyPagerAdapter(inViewList));
            TextView textView= (TextView) view.findViewById(R.id.out_textView);
            textView.setText(i+"");
            mItemList.add(view);
        }
        outVP.setAdapter(new MyPagerAdapter(mItemList));

    }


}
