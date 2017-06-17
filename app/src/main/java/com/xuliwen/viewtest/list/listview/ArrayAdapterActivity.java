package com.xuliwen.viewtest.list.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xuliwen.viewtest.R;

public class ArrayAdapterActivity extends AppCompatActivity {
    private ListView listView;
    private String[] arrayName=new String[]{"勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗"
            ,"勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗",
            "勒布朗","勒布朗" ,"勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗",
            "勒布朗","勒布朗"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        listView= (ListView) findViewById(R.id.arrayAdapter_listView);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,R.layout.array_adapter_item,arrayName);
        listView.setAdapter(arrayAdapter);
    }
}
