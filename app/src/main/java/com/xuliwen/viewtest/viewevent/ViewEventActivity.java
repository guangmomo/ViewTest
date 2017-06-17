package com.xuliwen.viewtest.viewevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.viewevent.customveiweventtest.ViewEventTestActivity;
import com.xuliwen.viewtest.viewevent.infun.InFunActivity;
import com.xuliwen.viewtest.viewevent.outfun.OutFunActivity;
import com.xuliwen.viewtest.viewevent.touchdelegate.TouchDelegateActivity;
import com.xuliwen.viewtest.viewevent.touchdelegate.TouchDelegateViewEventTestActivity;
import com.xuliwen.viewtest.viewevent.viewheight.ViewHeightActivity;

/**
 * View的事件传递
 */
public class ViewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
    }

    public void toViewEventTest(View view) {
        startActivity(new Intent(ViewEventActivity.this, ViewEventTestActivity.class));
    }

    public void toOutFun(View view) {
        startActivity(new Intent(ViewEventActivity.this, OutFunActivity.class));
    }

    public void toInFun(View view) {
        startActivity(new Intent(ViewEventActivity.this, InFunActivity.class));
    }

    public void toViewHeight(View view) {
        startActivity(new Intent(ViewEventActivity.this, ViewHeightActivity.class));
    }

    public void toTouchDelegate(View view) {
        startActivity(new Intent(ViewEventActivity.this, TouchDelegateActivity.class));
    }
}
