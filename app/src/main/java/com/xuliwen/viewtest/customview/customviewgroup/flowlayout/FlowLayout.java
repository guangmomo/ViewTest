package com.xuliwen.viewtest.customview.customviewgroup.flowlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.xuliwen.viewtest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xlw on 2017/5/15.
 */

public class FlowLayout extends ViewGroup {
    public static final String TAG = "MOGUJUN";

    public static final int DEFAULT_SPACING = 8;
    public static final int DEFAULT_DIVIDER_COLOR = Color.parseColor("#ececec");
    public static final int DEFAULT_DIVIDER_WIDTH = 3;

    private int mGravity = (isIcs() ? Gravity.START : Gravity.LEFT) | Gravity.TOP;

    private int mVerticalSpacing; //vertical spacing
    private int mHorizontalSpacing; //horizontal spacing
    private int mDividerColor;
    private int mDividerWidth;

    private int mChildHeights;//计算所有子View的高度之和,包括第一个子View和最后一个子View两边的mVerticalSpacing

    private Paint mDividerPaint = new Paint();
    //保存所有child view
    private final List<List<View>> mLines = new ArrayList<>();
    //保存所有行高
    private final List<Integer> mLineHeights = new ArrayList<>();
    //保存所有行宽
    private final List<Integer> mLineWidths = new ArrayList<>();
    //保存所有行与左边的偏移量
    private final List<Integer> mLineMargins = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        init(context, attrs, defStyleAttr, 0);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout, defStyleAttr, defStyleRes);

        try {
            mHorizontalSpacing = (int) ta.getDimension(R.styleable.FlowLayout_horizontalSpacing, DEFAULT_SPACING);
            mVerticalSpacing = (int) ta.getDimension(R.styleable.FlowLayout_verticalSpacing, DEFAULT_SPACING);
            mDividerWidth = (int) ta.getDimension(R.styleable.FlowLayout_dividerWidth, DEFAULT_DIVIDER_WIDTH);
            mDividerColor = ta.getColor(R.styleable.FlowLayout_dividerColor, DEFAULT_DIVIDER_COLOR);
            int index = ta.getInt(R.styleable.FlowLayout_android_gravity, -1);
            if (index > 0) {
                setGravity(index);
            }
            initPaint();
        } finally {
            ta.recycle();
        }

        //解决ViewGroup的onDraw不执行 http://blog.csdn.net/jijiaxin1989/article/details/42237401
        this.setWillNotDraw(false);

    }

    private void initPaint() {
        mDividerPaint.setColor(mDividerColor);
        mDividerPaint.setAntiAlias(true);
        mDividerPaint.setDither(true);//防抖动，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰  http://blog.csdn.net/lovexieyuan520/article/details/50732023
        mDividerPaint.setStrokeWidth(mDividerWidth);
    }


    public void setHorizontalSpacing(int pixelSize) {
        mHorizontalSpacing = pixelSize;
        requestLayout();
    }

    public void setVerticalSpacing(int pixelSize) {
        mVerticalSpacing = pixelSize;
        requestLayout();
    }

    public void setDividerColor(@ColorInt int color) {
        mDividerColor = color;
        mDividerPaint.setColor(color);
        invalidate();
    }

    public void setDividerWidth(int pixelSize) {
        mDividerWidth = pixelSize;
        mDividerPaint.setStrokeWidth(pixelSize);
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mLines.clear();
        mLineHeights.clear();
        mLineWidths.clear();

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int widthUsed = getPaddingLeft() + getPaddingRight() + mHorizontalSpacing;

        int lineWidth = widthUsed;
        int lineHeight = 0;


        int childCount = getChildCount();

        List<View> lineViews = new ArrayList<>();

        for (int i = 0; i < childCount; i++) {

            View child = getChildAt(i);

            if (child.getVisibility() == View.GONE) {//如果是gone，就不测量
                continue;
            }

            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            //mHorizontalSpacing * 2是水平方向上父元素已经用掉的空间-->widthUsed
            measureChildWithMargins(child, widthMeasureSpec, mHorizontalSpacing * 2, heightMeasureSpec, mVerticalSpacing * 2);

            //child占据的空间（包含margin）
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth + mHorizontalSpacing > sizeWidth) {//如果子View占用的空间超过了FlowLayout的width，就换行
                mLineWidths.add(lineWidth);
                lineWidth = widthUsed + childWidth + mHorizontalSpacing;

                mLineHeights.add(lineHeight);
                lineHeight = childHeight;

                mLines.add(lineViews);
                lineViews = new ArrayList<>();
            } else {
                lineWidth += childWidth + mHorizontalSpacing;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            lineViews.add(child);

        }
        //最后一个child的处理
        mLineHeights.add(lineHeight);
        mLineWidths.add(lineWidth);
        mLines.add(lineViews);

        //使用Collections来取出集合中最大的元素
        //maxWidth是所有行中最宽的行的宽度
        int maxWidth = Collections.max(mLineWidths);

        processChildHeights();
        int totalHeight = getChildHeights()+getPaddingBottom()+getPaddingTop();

        //printLineHeights();
        //TODO 处理getMinimumWidth/height的情况

        //设置自身的测量宽高
        setMeasuredDimension(
                (modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : Math.min(maxWidth, sizeWidth),
                (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : Math.min(totalHeight, sizeHeight));

        //重新测量child的lp.height为MATCH_PARENT时的child的尺寸，所以一个View的onMeasure有可能被调用多次的
        remeasureChild(widthMeasureSpec);
    }



    /**
     * 计算所有子View的高度之和,包括第一个子View和最后一个子View两边的mVerticalSpacing
     */
    private void processChildHeights() {
        int totalHeight = mVerticalSpacing;
        for (Integer height : mLineHeights) {
            totalHeight += height + mVerticalSpacing;
        }
        mChildHeights = totalHeight;
    }

    private int getChildHeights() {
        return mChildHeights;
    }

    private void remeasureChild(int parentWidthSpec) {
        int numLines = mLines.size();
        for (int i = 0; i < numLines; i++) {
            int lineHeight = mLineHeights.get(i);
            List<View> lineViews = mLines.get(i);
            int children = lineViews.size();
            for (int j = 0; j < children; j++) {
                View child = lineViews.get(j);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.height == LayoutParams.MATCH_PARENT) {//如果是MATCH_PARENT，就重新测量
                    if (child.getVisibility() == View.GONE) {
                        continue;
                    }

                    int widthUsed = lp.leftMargin + lp.rightMargin +
                            getPaddingLeft() + getPaddingRight() + 2 * mHorizontalSpacing;
                    child.measure(
                            getChildMeasureSpec(parentWidthSpec, widthUsed, lp.width),
                            MeasureSpec.makeMeasureSpec(lineHeight - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY)
                    );
                }
            }
        }
    }

    private int mVerticalGravityMargin;


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        /**
         * 根据gravity属性去计算整个子View在FlowLayout的上下，左右偏移量
         */
        processHorizontalGravityMargins();
        processVerticalGravityMargin();

        int numLines = mLines.size();

        int left;
        int top = getPaddingTop()  + mVerticalGravityMargin+mVerticalSpacing;

        for (int i = 0; i < numLines; i++) {

            int lineHeight = mLineHeights.get(i);
            List<View> lineViews = mLines.get(i);
            left = mLineMargins.get(i);

            int children = lineViews.size();

            for (int j = 0; j < children; j++) {

                View child = lineViews.get(j);

                if (child.getVisibility() == View.GONE) {
                    continue;
                }

                LayoutParams lp = (LayoutParams) child.getLayoutParams();

                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();

                int gravityMargin = 0;

                /**
                 * 根据layout_gravity去计算子View在一行中的上下偏移量
                 */
                if (Gravity.isVertical(lp.gravity)) {//只支持子元素垂直居中
                    switch (lp.gravity) {
                        case Gravity.TOP://子元素默认位置是TOP
                        default:
                            gravityMargin = 0;
                            break;
                        case Gravity.CENTER_VERTICAL:
                        case Gravity.CENTER:
                            gravityMargin = (lineHeight - childHeight - lp.topMargin - lp.bottomMargin) / 2;
                            break;
                        case Gravity.BOTTOM:
                            gravityMargin = lineHeight - childHeight - lp.topMargin - lp.bottomMargin;
                            break;
                        //TODO 水平方向上可以支持gravity么？
                    }
                }

                child.layout(left + lp.leftMargin,
                        top + lp.topMargin + gravityMargin,
                        left + lp.leftMargin + childWidth,
                        top + lp.topMargin + gravityMargin + childHeight);

                Log.i(TAG, String.format("child (%d,%d) position: (%d,%d,%d,%d)",
                        i, j, child.getLeft(), child.getTop(), child.getRight(), child.getBottom()));

                left += childWidth + lp.leftMargin + lp.rightMargin + mHorizontalSpacing;

            }

            top += lineHeight + mVerticalSpacing;
        }

    }

    /**
     * 计算第一行的子View到FlowLayout顶端的距离
     */
    private void processVerticalGravityMargin() {
        int verticalGravityMargin;
        int childHeights = getChildHeights();
        switch ((mGravity & Gravity.VERTICAL_GRAVITY_MASK)) {
            case Gravity.TOP:
            default:
                verticalGravityMargin = 0;
                break;
            case Gravity.CENTER_VERTICAL:
                verticalGravityMargin = Math.max((getHeight() - childHeights-getPaddingTop()-getPaddingBottom()) / 2, 0);
                break;
            case Gravity.BOTTOM:
                verticalGravityMargin = Math.max(getHeight() - childHeights-getPaddingTop()-getPaddingBottom(), 0);
                break;
        }
        mVerticalGravityMargin = verticalGravityMargin;
    }

    /**
     * 计算所有行与FlowLayout的左边的距离
     */
    private void processHorizontalGravityMargins() {
        mLineMargins.clear();
        float horizontalGravityFactor;
        switch ((mGravity & Gravity.HORIZONTAL_GRAVITY_MASK)) {
            case Gravity.LEFT:
            default:
                horizontalGravityFactor = 0;
                break;
            case Gravity.CENTER_HORIZONTAL:
                horizontalGravityFactor = 0.5f;
                break;
            case Gravity.RIGHT:
                horizontalGravityFactor = 1;
                break;
        }

        int linesNum = mLineWidths.size();
        for (int i = 0; i < linesNum; i++) {
            int lineWidth = mLineWidths.get(i);
            mLineMargins.add((int) ((getWidth() - lineWidth) * horizontalGravityFactor) + getPaddingLeft() + mHorizontalSpacing);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mDividerWidth != 0) {
            drawDividerLine(canvas);
        }
    }

    private void drawDividerLine(Canvas canvas) {
        int top = getPaddingTop() + mVerticalSpacing + mVerticalGravityMargin;
        int numLines = mLines.size();
        for (int i = 0; i < numLines; i++) {
            int lineHeight = mLineHeights.get(i);
            top += lineHeight + mVerticalSpacing;
            if(mDividerWidth != 0){
                canvas.drawLine(getPaddingLeft(), top - mVerticalSpacing / 2,
                        getWidth() - getPaddingRight(), top - mVerticalSpacing / 2, mDividerPaint);
            }

        }
    }


    private void printLineHeights() {
        for (int i = 0; i < mLineHeights.size(); i++) {
            Log.i(TAG, String.format("line %d height : %d",
                    i, mLineHeights.get(i)));
        }
    }



    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setGravity(int gravity) {
        if (mGravity != gravity) {
            if ((gravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
                gravity |= isIcs() ? Gravity.START : Gravity.LEFT;
            }

            if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
                gravity |= Gravity.TOP;
            }

            mGravity = gravity;
            requestLayout();
        }
    }

    public int getGravity() {
        return mGravity;
    }

    /**
     * @return <code>true</code> if device is running ICS or grater version of Android.
     */
    private static boolean isIcs() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }




    //----------------------需要重写的四个重要方法-----------------------------//

    //生成默认的LayoutParams，当检测到设置的LayoutParams为null时，将调用generateDefaultLayoutParams()来生成默认的LayoutParams
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    //检查LayoutParams的类型：是否是FlowLayout.LayoutParams的实现类或子类的实现类，如果不是的话，会调用generateLayoutParams来生成正确的LayoutParams
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p) && p instanceof LayoutParams;
    }

    //当检测到LayoutParams不是FlowLayout.LayoutParams的实现类或子类的实现类时，用generateLayoutParams来生成正确的LayoutParams
    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    //为什么要重写这个
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }



    public static class LayoutParams extends MarginLayoutParams {



        public int gravity = -1;


        /**
         * 构造方法中要调用super，这样MarginLayoutParms的layout_属性就会在super中进行处理了
         * @param c
         * @param attrs
         */

        //在xml中定义layout_属性，会调用这个构造方法，用于获取LayoutParams定义的xml属性
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.FlowLayout_Layout);

            try {
                gravity = a.getInt(R.styleable.FlowLayout_Layout_android_layout_gravity, -1);
            } finally {
                a.recycle();
            }
        }

        //根据宽度，高度，生成LayoutParams，这里要给LayoutParams的xml属性设置默认值
        public LayoutParams(int width, int height) {
            super(width, height);
            gravity = Gravity.TOP;
        }

        //根据宽度，高度，LayoutParams的xml属性，生成LayoutParams
        public LayoutParams(int width, int height, int gravity) {
            super(width, height);
            this.gravity = gravity;
        }

        //将source（source不是FlowLayout.LayoutParams的实现类或子类的实现类）转换成正确的FlowLayout.LayoutParams类型
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }



        //为LayoutParams的xml属性提供set，get方法
        public int getGravity() {
            return gravity;
        }
        public void setGravity(int gravity) {
            this.gravity = gravity;
        }
    }

}
