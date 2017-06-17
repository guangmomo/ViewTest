package com.xuliwen.viewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuliwen.viewtest.attr.AttrTestActivity;
import com.xuliwen.viewtest.customview.CustomViewActivity;
import com.xuliwen.viewtest.customview.customtextview.CustomTextViewActivity;
import com.xuliwen.viewtest.viewevent.ViewEventActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toAttrTest(View view) {
        startActivity(new Intent(MainActivity.this, AttrTestActivity.class));
    }

    public void toCustomView(View view) {
        startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
    }

    public void toViewEvent(View view) {
        startActivity(new Intent(MainActivity.this, ViewEventActivity.class));
    }
}
