package com.xuliwen.viewtest.viewevent.customveiweventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xuliwen.viewtest.Constants;

/**
 * Created by xlw on 2017/5/17.
 */

public class View3 extends View {
    public View3(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.e(Constants.VIEWEVENT,"View3+OnClickListener");
            }
        });
    }



}
