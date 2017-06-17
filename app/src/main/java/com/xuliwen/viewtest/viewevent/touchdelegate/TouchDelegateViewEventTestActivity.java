package com.xuliwen.viewtest.viewevent.touchdelegate;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;

import com.xuliwen.viewtest.Constants;
import com.xuliwen.viewtest.R;

/***
 * 研究TouchDelegate影响下的点击事件传递
 */
public class TouchDelegateViewEventTestActivity extends AppCompatActivity {
    private View view1;
    private View view2;
    private View view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_delegate_view_event_test);

        view1=findViewById(R.id.activity_touch_delegate);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Constants.VIEWEVENT,"view1+OnClickListener");
            }
        });

        view2 = findViewById(R.id.view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Constants.VIEWEVENT,"view2+OnClickListener");
            }
        });
        view3 =findViewById(R.id.view3);

        view3.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                view3.getHitRect(delegateArea);
                delegateArea.left -= 30;
                delegateArea.top -= 30;
                delegateArea.right += 30;
                delegateArea.bottom += 30;
                TouchDelegate td = new TouchDelegate(delegateArea, view3);
                view2.setTouchDelegate(td);
            }
        });
    }
}
