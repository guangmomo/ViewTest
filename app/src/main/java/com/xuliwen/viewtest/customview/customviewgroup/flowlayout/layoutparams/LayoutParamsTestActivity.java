package com.xuliwen.viewtest.customview.customviewgroup.flowlayout.layoutparams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuliwen.viewtest.R;

public class LayoutParamsTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_params_test);
        layoutParamsTest();
    }

    private void layoutParamsTest(){
        LinearLayout linearLayout1= (LinearLayout) findViewById(R.id.linearLayout1);
        LinearLayout linearLayout2= (LinearLayout) findViewById(R.id.linearLayout2);

        //1）不给TextView设置LayoutParams，会使用默认的LayoutParams
        TextView textView1=new TextView(this);
        textView1.setText("textView1");
        linearLayout1.addView(textView1);

        //一个子View有且仅有一个父View，所以要先remove，再add
        // linearLayout1.removeView(textView1);
        //linearLayout2.addView(textView1);

        //2）addView(View view , LayoutParams params);
        TextView textView2=new TextView(this);
        textView2.setText("textView2");
        LinearLayout.LayoutParams layoutParams2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        linearLayout1.addView(textView2,layoutParams2);

        //3）view.setLayoutParams(params); addView(view)
        TextView textView3=new TextView(this);
        textView3.setText("textView3");
        LinearLayout.LayoutParams layoutParams3=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        ViewGroup.MarginLayoutParams marginLayoutParams=new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        textView3.setLayoutParams(marginLayoutParams);
        linearLayout1.addView(textView3,2);


        //addView(textView3,1);意思是将textView3放在位置1，如果位置超过了viewCount，报错，如addView(textView3,3);将报错

        // textView3.setLayoutParams(marginLayoutParams);
        // 在addView之前，若设置了一个错误的LayoutParams，系统将生成一个默认的LayoutParams或对错误的LayoutParams进行改造（如对MarginLayoutParams进行改造）
        // 在addView之后就不要再改变View的LayoutParams了，因为将有可能出现ClassCastException
    }
}
