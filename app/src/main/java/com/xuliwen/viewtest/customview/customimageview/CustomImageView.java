package com.xuliwen.viewtest.customview.customimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.xuliwen.viewtest.R;

/**
 * Created by xlw on 2017/5/14.
 */

public class CustomImageView extends View {
    private String mTitle;
    private int mTitleSize;
    private static final int FILL_XY=0;
    private static final int CENTER=1;
    private Bitmap mImageBitmap;
    private int mScaleType;
    private Paint mPaint;
    private Rect mTextBound;
    private Rect mBitmapRect;
    private int mBorderColor;
    private TextPaint mTextPaint;
    private int mBorderWidth;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CustomImageView);
        mTitle=typedArray.getString(R.styleable.CustomImageView_titleText);
        mTitleSize=typedArray.getLayoutDimension(R.styleable.CustomImageView_titleSize,"titleSize");
        mImageBitmap = BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(R.styleable.CustomImageView_imageResource,R.mipmap.ic_launcher));
        mScaleType=typedArray.getInt(R.styleable.CustomImageView_imageScaleType,FILL_XY);
        mBorderColor=typedArray.getColor(R.styleable.CustomImageView_borderColor, Color.BLACK);
        mBorderWidth=typedArray.getLayoutDimension(R.styleable.CustomImageView_borderWidth,"borderWidth");
        typedArray.recycle();

        init();
    }

    private void init(){
        mPaint=new Paint();
        mTextBound=new Rect();
        mBitmapRect=new Rect();
        mPaint.setTextSize(mTitleSize);
        mPaint.getTextBounds(mTitle,0,mTitle.length(),mTextBound);
        mTextPaint=new TextPaint();

    }

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

        if(widthSpecMode==MeasureSpec.AT_MOST){
            int desireByImage= mImageBitmap.getWidth()+getPaddingLeft()+getPaddingBottom()+2*mBorderWidth;//由image决定的宽
            int desireByTitle=mTextBound.width()+getPaddingLeft()+getPaddingBottom()+2*mBorderWidth;// 由title决定的宽
            int desire=Math.max(desireByImage,desireByTitle);
            widthMeasureSize=Math.min(desire,widthSpecSize);
        }
        if(heightSpecMode==MeasureSpec.AT_MOST){
            int desire=mImageBitmap.getHeight()+mTextBound.height()+getPaddingTop()+getPaddingBottom()+2*mBorderWidth;
            heightMeasureSize=Math.min(desire,heightSpecSize);
        }

        setMeasuredDimension(widthMeasureSize,heightMeasureSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measureWidth=getMeasuredWidth();
        int measureHeight=getMeasuredHeight();
        mPaint.setColor(mBorderColor);
        mPaint.setStyle(Paint.Style.STROKE);//画笔空心
        mPaint.setStrokeWidth(mBorderWidth);//设置线的宽度
        canvas.drawRect(0,0,measureWidth,measureHeight,mPaint);
        mPaint.setStyle(Paint.Style.FILL); //画笔实心
        mPaint.setColor(Color.BLACK);
        if(mTextBound.width()>measureWidth-getPaddingLeft()-getPaddingBottom()-2*mBorderWidth){
            //如果文字过长，就在文字的末尾添加省略号
            mTextPaint.set(mPaint);//通过set(Paint paint),将Paint的属性传递给TextPaint
            String msg = TextUtils.ellipsize(mTitle, mTextPaint, (float) measureHeight - getPaddingLeft() - getPaddingRight()-2*mBorderWidth,
                    TextUtils.TruncateAt.END).toString();//如果文字过长，就会获取带有省略号的字符串
            canvas.drawText(msg, getPaddingLeft()+mBorderWidth, measureHeight - getPaddingBottom()-mBorderWidth, mPaint);
        }else{
            //如果文字不会过长，就将文字居中显示
            canvas.drawText(mTitle,(measureWidth-mTextBound.width())/2,measureHeight - getPaddingBottom()-mBorderWidth,mPaint);
        }


        /**
         * 初始化mBitmapRect的顶点坐标
         */
        mBitmapRect.left=mBorderWidth+getPaddingLeft();
        mBitmapRect.top=mBorderWidth+getPaddingTop();
        mBitmapRect.right=measureWidth-mBorderWidth-getPaddingRight();
        mBitmapRect.bottom=measureHeight-mBorderWidth-getPaddingBottom()-mTextBound.height();


        if(mScaleType==FILL_XY){//FILL_XY模式，直接填充满mBitmapRect，不需要改变其顶点坐标
            //drawBitmap(bitmap,src,dst,Paint); 将bitmap填充满在dst中，若src不为null时，会将src去截取填充后的dst，然后再显示在界面上
            canvas.drawBitmap(mImageBitmap,null,mBitmapRect,mPaint);
        }else if(mScaleType==CENTER){//在mBitmapRect中居中显示
            if(mImageBitmap.getWidth()>(mBitmapRect.right-mBitmapRect.left )
                    || mImageBitmap.getHeight()>(mBitmapRect.bottom-mBitmapRect.top)){//当图片过大的时候，不能居中显示，就使用FILL_XY模式
                canvas.drawBitmap(mImageBitmap,null,mBitmapRect,mPaint);
            }else{//通过改变mBitmapRect的顶点坐标，达到居中显示
                mBitmapRect.left=measureWidth/2- mImageBitmap.getWidth()/2;
                int centerGap=(measureHeight-2*mBorderWidth-getPaddingBottom()-mTextBound.height()-getPaddingTop()- mImageBitmap.getWidth())/2;//居中产生的上下间隙
                mBitmapRect.top=mBorderWidth+getPaddingTop()+centerGap;
                mBitmapRect.right=measureWidth/2+ mImageBitmap.getWidth()/2;
                mBitmapRect.bottom=measureHeight-(mBorderWidth+getPaddingBottom()+centerGap+mTextBound.height());
                canvas.drawBitmap(mImageBitmap,null,mBitmapRect,mPaint);
            }

        }

    }
}
