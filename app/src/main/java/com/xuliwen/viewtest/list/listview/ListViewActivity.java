package com.xuliwen.viewtest.list.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.customview.CustomViewActivity;
import com.xuliwen.viewtest.list.listview.custom_adapter.CustomAdapterActivity;


public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
    }

    public void resArrays(View view) {
        startActivity(new Intent(this,ResArraysActivity.class));
    }

    public void arrayAdapter(View view) {
        startActivity(new Intent(this,ArrayAdapterActivity.class));
    }

    public void extendListActivity(View view) {
        startActivity(new Intent(this,ExtendListActivityActivity.class));
    }

    public void simpleAdapterActivity(View view) {
        startActivity(new Intent(this,SimpleAdapterActivity.class));
    }


    public void customAdapterActivity(View view) {
        startActivity(new Intent(this,CustomAdapterActivity.class));
    }
}
