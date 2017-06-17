package com.xuliwen.viewtest.toolbar.custom_title;

import android.os.Bundle;

import com.xuliwen.viewtest.R;

public class CustomTitleActivity2 extends BaseCustomTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_custom_title2);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void handlerCustomTitle(TitleHelper mTitleHelper) {
        super.handlerCustomTitle(mTitleHelper);
        mTitleHelper.setLeftImage(R.mipmap.ic_launcher)
                .setCenterTitle("CenterTitle2")
                .setRightTitle("RightTitle2");
    }
}
