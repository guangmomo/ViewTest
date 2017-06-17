package com.xuliwen.viewtest.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.list.gridview.GridViewActivity;
import com.xuliwen.viewtest.list.listview.ListViewActivity;
import com.xuliwen.viewtest.list.recycleview.RecycleViewActivity;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public void listViewTest(View view) {
        startActivity(new Intent(ListActivity.this,ListViewActivity.class));
    }

    public void recycleViewTest(View view) {
        startActivity(new Intent(ListActivity.this,RecycleViewActivity.class));
    }

    public void gridViewTest(View view) {
        startActivity(new Intent(ListActivity.this,GridViewActivity.class));
    }
}
