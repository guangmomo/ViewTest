package com.xuliwen.viewtest.toolbar.custom_title;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xuliwen.viewtest.R;

public class CustomTitleActivity1 extends BaseCustomTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_custom_title1);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void handlerCustomTitle(TitleHelper mTitleHelper) {
        super.handlerCustomTitle(mTitleHelper);
        mTitleHelper.setLeftImage(R.mipmap.ic_launcher)
                .setCenterTitle("CenterTitle1")
                .setRightTitle("RightTitle1");
    }

    public void toCustomTitleActivity2(View view) {
        startActivity(new Intent(this,CustomTitleActivity2.class));
    }
}
