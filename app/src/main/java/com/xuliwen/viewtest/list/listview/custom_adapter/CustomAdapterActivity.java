package com.xuliwen.viewtest.list.listview.custom_adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.xuliwen.viewtest.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {

    private List<Soft> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);

        initData();
        CustomAdapter customAdapter=new CustomAdapter(datas);
        ListView listView= (ListView) findViewById(R.id.custom_adapter_listView);
        listView.setAdapter(customAdapter);
    }

    private void initData(){
        datas=new ArrayList<>();
        for(int i=0; i<100; i++){
            Soft soft=new Soft(R.drawable.ic_chrome,"谷歌");
            datas.add(soft);
        }
    }
}
