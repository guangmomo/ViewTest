package com.xuliwen.viewtest.viewevent.outfun;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.xuliwen.viewtest.Constants;

/**
 * Created by xlw on 2017/5/16.
 */

public class MyViewPager extends ViewPager {
    private int startX;
    private int startY;
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
      /*  boolean intercepted=false;
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startX= (int) ev.getX();
                startY= (int) ev.getY();
                intercepted=false;
                break;
            case MotionEvent.ACTION_MOVE:

                int dX= (int) (ev.getX()-startX);
                int dY= (int) (ev.getY()-startY);
                if(Math.abs(dX)>Math.abs(dY)){//左右滑动
                    intercepted=true;
                }else {//上下滑动
                    intercepted=false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted=false;
                break;
        }
        Log.e(Constants.VIEWEVENT,String.valueOf(intercepted));
        super.onInterceptTouchEvent(ev);
        return intercepted;*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(Constants.VIEWEVENT,"myViewPager+onTouchEvent");
        return super.onTouchEvent(ev);
    }


}
