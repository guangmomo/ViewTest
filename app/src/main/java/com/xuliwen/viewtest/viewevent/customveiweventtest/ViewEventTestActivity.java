package com.xuliwen.viewtest.viewevent.customveiweventtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.xuliwen.viewtest.Constants;
import com.xuliwen.viewtest.R;

/**
 * 研究点击事件的传递规则
 */
public class ViewEventTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view_event_test);
        View1 view1= (View1) findViewById(R.id.view1);
        CustomRectViewGroup customRectViewGroup= (CustomRectViewGroup) findViewById(R.id.rect1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(Constants.VIEWEVENT,event.toString());
        return super.onTouchEvent(event);
    }
}
