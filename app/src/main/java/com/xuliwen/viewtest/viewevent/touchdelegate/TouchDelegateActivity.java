package com.xuliwen.viewtest.viewevent.touchdelegate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuliwen.viewtest.R;

public class TouchDelegateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_delegate);
    }

    public void toTouchDelegateViewEventTest(View view) {
        startActivity(new Intent(this,TouchDelegateViewEventTestActivity.class));
    }

    public void toTouchStandard(View view) {
        startActivity(new Intent(this,StandardTouchActivity1.class));
    }
}
