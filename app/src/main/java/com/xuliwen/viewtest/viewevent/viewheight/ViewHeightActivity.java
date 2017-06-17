package com.xuliwen.viewtest.viewevent.viewheight;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xuliwen.viewtest.Constants;
import com.xuliwen.viewtest.R;

/**
 * 获取各种高度（如状态栏）
 */
public class ViewHeightActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_height);
       // viewTree();
       // contentViewHeight();
        statusHeight();
    }

    private void viewTree(){

        TextView textView= (TextView) findViewById(R.id.viewTree_textView);
      //  Log.e(Constants.VIEWEVENT,textView.getParent().toString());//TextView的上一层View
      //  Log.e(Constants.VIEWEVENT,getWindow().getDecorView().toString());//获取到DecorView
       // Log.e(Constants.VIEWEVENT,getWindow().getDecorView().findViewById(android.R.id.content).toString());//setContentView(layout)中layout的父View
       // Log.e(Constants.VIEWEVENT,
       //         ((ViewGroup)(getWindow().getDecorView().findViewById(android.R.id.content))).getChildAt(0).toString());//setContentView(layout)中的layout
      //  Log.e(Constants.VIEWEVENT,textView.getRootView().toString());
        //View view=getLayoutInflater().inflate(R.layout.activity_view_height,(ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content));
        View view=getLayoutInflater().inflate(R.layout.activity_view_height,null);//将layout解析一个view tree，第二个参数是为这个layout解析出来的view tree设置的父view
        TextView textView1= (TextView) view.findViewById(R.id.viewTree_textView);
        Log.e(Constants.VIEWEVENT,textView1.getRootView().toString());//getRootView() 是textView1的根View

    }


    // 获取状态栏，标题栏，contentView高度 http://blog.csdn.net/a_running_wolf/article/details/50477965#


    private void statusHeight(){
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight1 = -1;
        //获取status_bar_height的系统资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取相应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        Log.e(Constants.VIEWEVENT, "状态栏-方法1:" + statusBarHeight1);


        /**
         * 获取状态栏高度——方法2
         * */
        int statusBarHeight2 = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight2 = getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(Constants.VIEWEVENT, "状态栏-方法2:" + statusBarHeight2);


        /**
         * 获取状态栏高度——方法3
         * 应用区的顶端位置即状态栏的高度
         * *注意*该方法不能在初始化的时候用
         * */
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                Rect rectangle= new Rect();//Rect表示应用的区域
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
                //高度为rectangle.top-0仍为rectangle.top
                Log.e(Constants.VIEWEVENT, "状态栏-方法3:" + rectangle.top);
            }
        });

        /**
         * 获取状态栏高度——方法4
         * 状态栏高度 = 屏幕高度 - 应用区高度
         * *注意*该方法不能在初始化的时候用
         * */

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                //屏幕
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                //应用区域
                Rect outRect1 = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
                int statusBar = dm.heightPixels - outRect1.height();  //状态栏高度=屏幕高度-应用区域高度
                Log.e(Constants.VIEWEVENT, "状态栏-方法4:" + statusBar);
            }
        });

    }

    private void contentViewHeight(){
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {

                View decorView=getWindow().getDecorView();
                View contentView=decorView.findViewById(android.R.id.content);
                Log.e(Constants.VIEWEVENT,"contentView的高度"+contentView.getHeight());//获取contentView的高度

            }
        });
    }
}
