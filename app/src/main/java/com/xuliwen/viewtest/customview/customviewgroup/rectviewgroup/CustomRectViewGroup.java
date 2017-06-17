package com.xuliwen.viewtest.customview.customviewgroup.rectviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xlw on 2017/5/14.
 * 自定ViewGroup，能够将四个ViewView放在ViewGroup的四个角落
 */

public class CustomRectViewGroup extends ViewGroup {


    public CustomRectViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 为了ViewGroup支持margin属性，返回的是子View能够支持的LayoutParams
     * @param attrs
     * @return
     */
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * onMeasure()的作用：先完成子View的测量，在完成ViewGroup的测量
     * 自定义ViewGroup的onMeasure步骤
     * 1） measureChildren(widthMeasureSpec,heightMeasureSpec);测量每个子View
     * 2）根据子View的测量宽高，处理AT_MOST（会根据ViewGroup的特性去测量ViewGroup的宽，高，注意padding，margin的处理）
     * 3）setMeasuredDimension(widthMeasureSize,heightMeasureSize)来设置ViewGroup的测量宽高
     *
     *  获取padding值： View.getPaddingLeft()
     *  获取margin值：  MarginLayoutParams.leftMargin，需要让ViewGroup重写generateLayoutParams(AttributeSet attrs)方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);

        /**
         * 默认是EXACTLY
         */
        int widthMeasureSize=widthSpecSize;
        int heightMeasureSize=heightSpecSize;

        measureChildren(widthMeasureSpec,heightMeasureSpec);//调用这个方法，将父View的MeasureSpec传进去，从而测量子View的宽高

        if(widthSpecMode==MeasureSpec.AT_MOST){//处理AT_MOST
            int tWidth;
            int bWidth;

            View view0=getChildAt(0);//获取到第i个子View
            View view1=getChildAt(1);
            View view2=getChildAt(2);
            View view3=getChildAt(3);
            MarginLayoutParams layoutParams0= (MarginLayoutParams) view0.getLayoutParams();//获取到View的LayoutParams，从而获取到margin值
            MarginLayoutParams layoutParams1= (MarginLayoutParams) view1.getLayoutParams();
            MarginLayoutParams layoutParams2= (MarginLayoutParams) view2.getLayoutParams();
            MarginLayoutParams layoutParams3= (MarginLayoutParams) view3.getLayoutParams();
            tWidth=view0.getWidth()+layoutParams0.leftMargin+layoutParams0.rightMargin
                    +view1.getWidth()+layoutParams1.leftMargin+layoutParams1.rightMargin+getPaddingLeft()+getPaddingRight();
            bWidth=view2.getWidth()+layoutParams2.leftMargin+layoutParams2.rightMargin
                    +view3.getWidth()+layoutParams3.leftMargin+layoutParams3.rightMargin+getPaddingLeft()+getPaddingRight();

            widthMeasureSize=Math.max(tWidth,bWidth);
        }
        if(heightSpecMode==MeasureSpec.AT_MOST){
            int lHeight;
            int rHeight;

            View view0=getChildAt(0);
            View view1=getChildAt(1);
            View view2=getChildAt(2);
            View view3=getChildAt(3);
            MarginLayoutParams layoutParams0= (MarginLayoutParams) view0.getLayoutParams();
            MarginLayoutParams layoutParams1= (MarginLayoutParams) view1.getLayoutParams();
            MarginLayoutParams layoutParams2= (MarginLayoutParams) view2.getLayoutParams();
            MarginLayoutParams layoutParams3= (MarginLayoutParams) view3.getLayoutParams();
            lHeight=view0.getHeight()+layoutParams0.topMargin+layoutParams0.bottomMargin
                    +view2.getHeight()+layoutParams2.topMargin+layoutParams2.bottomMargin+getPaddingTop()+getPaddingBottom();
            rHeight=view1.getHeight()+layoutParams1.topMargin+layoutParams1.bottomMargin
                    +view3.getHeight()+layoutParams3.topMargin+layoutParams3.bottomMargin+getPaddingTop()+getPaddingBottom();
            heightMeasureSize=Math.max(lHeight,rHeight);
        }
        setMeasuredDimension(widthMeasureSize,heightMeasureSize);
    }


    /**
     * onLayout()的作用：完成子View的布局，ViewGroup的布局已经在ViewGroup的layout()中完成
     * 所以ViewGroup要重写onLayout()，而View不需要重写onLayout()
     *
     * 自定义ViewGroup的onLayout步骤
     * 1）根据子View的measureWidth，measureHeight，子View的margin，ViewGroup的padding，ViewGroup的特新去确定子View的顶点位置
     * 2） childView.layout(childLeft,childTop,childRight,childBottom);来传入确定的位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();//获取到子View的数量
        for(int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            MarginLayoutParams marginLayoutParams= (MarginLayoutParams) childView.getLayoutParams();
            int childLeft=0;
            int childTop=0;
            int childRight=0;
            int childBottom=0;
            switch (i){
                case 0:
                    childLeft=marginLayoutParams.leftMargin+getPaddingLeft();
                    childTop=marginLayoutParams.topMargin+getPaddingTop();
                    break;
                case 1:
                    childLeft=getMeasuredWidth()-marginLayoutParams.rightMargin-childView.getMeasuredWidth()-getPaddingRight();
                    childTop=marginLayoutParams.topMargin+getPaddingTop();
                    break;
                case 2:
                    childLeft=marginLayoutParams.leftMargin+getPaddingLeft();
                    childTop=getMeasuredHeight()-marginLayoutParams.bottomMargin-childView.getHeight()-getPaddingBottom();
                    break;
                case 3:
                    childLeft=getMeasuredWidth()-marginLayoutParams.rightMargin-childView.getMeasuredWidth()-getPaddingRight();
                    childTop=getMeasuredHeight()-marginLayoutParams.bottomMargin-childView.getHeight()-getPaddingBottom();
                    break;
            }
            childRight=childLeft+childView.getMeasuredWidth();
            childBottom=childTop+childView.getMeasuredHeight();
            childView.layout(childLeft,childTop,childRight,childBottom);
        }
    }
}
