package com.xuliwen.viewtest.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.xuliwen.viewtest.R;

public class WindowTestActivity extends AppCompatActivity {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private Button button;
    private boolean flag;// true--已添加  false--已删除

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_test);

        initWindowManger();

    }

    private void initWindowManger(){
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mParams=new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 系统提示window
        mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
        //mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;// 焦点
        mParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;//窗口的宽和高
        mParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mParams.gravity= Gravity.LEFT|Gravity.TOP;
        mParams.x = 300;//窗口位置的偏移量
        mParams.y = 300;
    }

    public void addView(View view) {
        if(!flag){
            button=new Button(this);
            button.setText("windowButton");
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int rawX = (int) event.getRawX();
                    int rawY = (int) event.getRawY();
                    if(event.getAction()==MotionEvent.ACTION_MOVE){
                        mParams.x=rawX;
                        mParams.y=rawY;
                        mWindowManager.updateViewLayout(button,mParams);
                    }
                    return false;
                }
            });
            mWindowManager.addView(button,mParams);
            flag=true;
        }
    }


    public void deleteView(View view) {
            if(flag) {
                mWindowManager.removeView(button);
                flag = false;
            }
    }
}
