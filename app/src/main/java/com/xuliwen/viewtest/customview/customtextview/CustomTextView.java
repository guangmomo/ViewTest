package com.xuliwen.viewtest.customview.customtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xuliwen.viewtest.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by xlw on 2017/5/12.
 */

public class CustomTextView extends View {
    private String mTextContent;
    private int mTextColor;
    private int mTextSize;

    private Paint mPaint;
    private Rect mBound ;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        mTextContent=typedArray.getString(R.styleable.CustomTextView_cTextContent);
        mTextColor=typedArray.getColor(R.styleable.CustomTextView_cTextColor, Color.YELLOW);
        mTextSize=typedArray.getLayoutDimension(R.styleable.CustomTextView_cTextSize,"cTextSize");//获取sp，dp值都用这个方法

        typedArray.recycle();

        //一些初始化工作写在构造函数中，因为onMeasure，onDraw都会执行多次，多次初始化是没有必要的，耗时，耗内存
        init();
        initOnClickListener();

    }

    private void init(){
        mPaint=new Paint();
        mPaint.setTextSize(mTextSize);

        mBound = new Rect();

        //获取到能包裹字符串的最小矩形，跟字符串的长度和字符串的textSize有关系
        mPaint.getTextBounds(mTextContent, 0, mTextContent.length(), mBound);
    }



    private void initOnClickListener(){
        this.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mTextContent = randomText();
                invalidate();//只会执行onDraw
            }

        });
    }

    /**
     * onMeasure之前，MeasureSpec已经确定下来了，也就是说View的SpecMode和SpecSize已经确定下来了
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        /**
         * 默认是EXACTLY
         */
        int width=widthSize;
        int height=heightSize;

        /**
         * 处理wrap_content的情况，
         * 第一种方法，这种方法只处理自身的LayoutParams设置为WRAP_CONTENT的情况
         */
     /*   if(getLayoutParams().width== ViewGroup.LayoutParams.WRAP_CONTENT){
            widthSize = mBound.width();
        }
        if(getLayoutParams().height== ViewGroup.LayoutParams.WRAP_CONTENT){
            heightSize=mBound.height();
        }*/


        /**
         * 处理wrap_content的情况，其实正确的说法应该是处理SpecMode为AT_MOST的情况
         * 第二种方法，来自艺术探索。
         * 查看Button的使用，发现
         * 1）当Button的父View为wrap_content的时候，将Button设置为match_parent
         * 2) Button设置为wrap_content
         * 这两种效果是相同的，说明Button对SpecMode为AT_MOST的情景做了相同的处理
         * 所以日常开发中，可以模仿Button的这种处理方法
         */
        if(widthMode==MeasureSpec.AT_MOST){
            //处理padding
            width = mBound.width()+getPaddingLeft()+getPaddingRight();
        }
        if(heightMode==MeasureSpec.AT_MOST){
            height=mBound.height()+getPaddingTop()+getPaddingBottom();
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取到能包裹字符串的最小矩形，跟字符串的长度和字符串的textSize有关系
        mPaint.getTextBounds(mTextContent, 0, mTextContent.length(), mBound);

        mPaint.setColor(Color.YELLOW);

        //画矩形作为TextView的背景
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(mTextColor);




        //canvas.drawText(text的内容，text左上角的x坐标，text底线的y坐标，paint)
        canvas.drawText(mTextContent, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);

    }



    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
}
