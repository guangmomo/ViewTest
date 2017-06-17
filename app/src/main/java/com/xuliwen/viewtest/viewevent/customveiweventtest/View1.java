package com.xuliwen.viewtest.viewevent.customveiweventtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xuliwen.viewtest.Constants;

/**
 * Created by xlw on 2017/5/17.
 */

public class View1 extends View {
    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOnClickListener();
        initOnTouchListener();

    }

    private void initOnClickListener(){
        this.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.e(Constants.VIEWEVENT,"View1+OnClickListener");
            }
        });
    }

    private void initOnTouchListener(){
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(Constants.VIEWEVENT,"View1+OnTouchListener");
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result=super.dispatchTouchEvent(event);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result=super.onTouchEvent(event);
        return result;
    }


}
