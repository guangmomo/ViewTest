package com.xuliwen.viewtest.customview.customviewgroup.flowlayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.DensityUtils;

public class FlowLayoutActivity extends AppCompatActivity {
    private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        flowLayout= (FlowLayout) findViewById(R.id.flow1);
       // flowLayout();
    }

    /**
     * 动态设置FlowLayout的属性
     */
    private void flowLayout(){
        flowLayout= (FlowLayout) findViewById(R.id.flow1);
        flowLayout.setDividerColor(Color.BLACK);
        flowLayout.setHorizontalSpacing(DensityUtils.dp2px(this,2));
        flowLayout.setDividerWidth(DensityUtils.dp2px(this,10));
    }

    public void toRequestLayout(View view) {
        flowLayout.setHorizontalSpacing(DensityUtils.dp2px(this,2));
    }

    public void toInvalidate(View view) {
        flowLayout.setDividerWidth(DensityUtils.dp2px(this,10));
    }
}
