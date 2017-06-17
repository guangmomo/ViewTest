package com.xuliwen.viewtest.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.customview.customcircleview.CustomCircleActivity;
import com.xuliwen.viewtest.customview.customimageview.CustomImageViewActivity;
import com.xuliwen.viewtest.customview.customtextview.CustomTextViewActivity;
import com.xuliwen.viewtest.customview.customviewgroup.flowlayout.FlowLayoutActivity;
import com.xuliwen.viewtest.customview.customviewgroup.flowlayout.layoutparams.LayoutParamsTestActivity;
import com.xuliwen.viewtest.customview.customviewgroup.rectviewgroup.CusRectGroupActivity;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    public void toCustomTextView(View view) {
        startActivity(new Intent(CustomViewActivity.this, CustomTextViewActivity.class));
    }

    public void toCustomImageView(View view) {
        startActivity(new Intent(CustomViewActivity.this, CustomImageViewActivity.class));
    }


    public void toCustomCircleView(View view) {
        startActivity(new Intent(CustomViewActivity.this, CustomCircleActivity.class));
    }

    public void toCustomRectViewGroup(View view) {
        startActivity(new Intent(CustomViewActivity.this, CusRectGroupActivity.class));
    }

    public void toCustomFlowLayoutViewGroup(View view) {
        startActivity(new Intent(CustomViewActivity.this, FlowLayoutActivity.class));
    }

    public void toLayoutParamsTest(View view) {
        startActivity(new Intent(CustomViewActivity.this, LayoutParamsTestActivity.class));
    }
}
