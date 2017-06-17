package com.xuliwen.viewtest.drawable;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.L;

public class DrawableTestActivity extends AppCompatActivity implements View.OnTouchListener{

    private ImageView drawableImageView;
    private EditText drawableEditText;
    private ImageView levelListImageView;
    private ImageView transitionImageView;
    private ImageView scaleImageView;
    private ImageView clipImageView;
    private ImageView customImageView;
    private Handler uiHandler=new Handler();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_test);

        drawableImageView= (ImageView) findViewById(R.id.drawable_imageView);

        drawableEditText= (EditText) findViewById(R.id.drawable_editText);
        drawableEditText.setOnTouchListener(this);

        levelListImageView= (ImageView) findViewById(R.id.level_list_imageVew);

        transitionImageView= (ImageView) findViewById(R.id.transition_imageView);

        scaleImageView= (ImageView) findViewById(R.id.scale_imageView);

        clipImageView= (ImageView) findViewById(R.id.clip_imageView);

        customImageView= (ImageView) findViewById(R.id.custom_imageView);

        logBitmapDrawableIntrinsic();
        startTransition();
        setScaleLevel();
        setClipLevel();
        setCustomImageViewBackground();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setCustomImageViewBackground(){
        CustomDrawable customDrawable=new CustomDrawable(Color.BLUE);
        customImageView.setBackground(customDrawable);
    }

    private void setClipLevel(){
        ClipDrawable clipDrawable= (ClipDrawable) clipImageView.getDrawable();
        clipDrawable.setLevel(4000);

    }

    private void setScaleLevel(){
        Drawable drawable=scaleImageView.getDrawable();
        drawable.setLevel(1);
    }


    private void startTransition(){
        TransitionDrawable transitionDrawable= (TransitionDrawable) transitionImageView.getBackground();
        transitionDrawable.startTransition(3*1000);
    }

    private void logBitmapDrawableIntrinsic(){
        BitmapDrawable drawable= (BitmapDrawable) drawableImageView.getDrawable();
        //获取图片的固定大小，单位是dp
        L.log("drawableImageView width:"+drawable.getIntrinsicWidth());
        L.log("drawableImageView height:"+drawable.getIntrinsicHeight());
    }


    //处理滑动冲突
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
        if ((view.getId() == R.id.drawable_editText && canVerticalScroll(drawableEditText))) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }


    /**
     * EditText竖直方向是否可以滚动
     * @param editText  需要判断的EditText
     * @return  true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if(scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    public void levelListDrawableTest(View view) {
        new Thread(){
            @Override
            public void run() {
               for(int i=0; i<=100;i++){
                   final int finalI = i;
                   uiHandler.post(new Runnable() {
                       @Override
                       public void run() {
                           levelListImageView.getBackground().setLevel(finalI);
                       }
                   });
                   try {
                       Thread.sleep(50);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }.start();
    }
}
