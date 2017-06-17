package com.xuliwen.viewtest.customview.customcircleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.DensityUtils;

import java.lang.ref.PhantomReference;

/**
 * Created by xlw on 2017/5/14.
 * 这个自定义View比较简单，所以直接在View中写死了firstColor，secondColor等属性
 */

public class CustomCircleView extends View {
    private int mFirstColor= Color.BLACK;
    private int mSecondColor=Color.YELLOW;
    private int mFixedColor;
    private int mRunColor;
    private int mCircleWidth= DensityUtils.dp2px(this.getContext(),10);
    private int mInvalidateTime;
    private Paint mPaint;
    private RectF mRectF;
    private int mProgress;


    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CustomCircleView);
        mInvalidateTime=typedArray.getInt(R.styleable.CustomCircleView_invalidateTime,300);
        typedArray.recycle();
        init();
    }

    private void init(){
        /**
         * 第一圈，默认是mFirstColor固定，mSecondColor转动
         */
        mFixedColor=mFirstColor;
        mRunColor=mSecondColor;
        mPaint = new Paint();
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);// 消除锯齿
        mRectF=new RectF(); // 用于定义的圆弧的形状和大小的界限
        new Thread(){
            @Override
            public void run() {
                mProgress=0;
                while (true){
                    ++mProgress;
                    if(mProgress==360){
                        mProgress=0;
                       int tempColor=mFixedColor;
                        mFixedColor=mRunColor;
                        mRunColor=tempColor;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mInvalidateTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center=getWidth()/2;
        int radius=center-mCircleWidth;//圆圈的半径是指内圈的半径
        mRectF.set(center-radius,center-radius,center+radius,center+radius);//不要在onDraw中去创建RectF，应该是先创建RectF，再去set
        mPaint.setColor(mFixedColor);
        canvas.drawCircle(center,center,radius,mPaint);
        mPaint.setColor(mRunColor);

        //(圆弧的内圈，开始的角度，移动多少角度，是否连带半径一起画出，画笔)
        canvas.drawArc(mRectF,0,mProgress,true,mPaint);

    }
}
