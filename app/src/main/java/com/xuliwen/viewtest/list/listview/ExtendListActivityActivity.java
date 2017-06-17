package com.xuliwen.viewtest.list.listview;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.xuliwen.viewtest.R;

public class ExtendListActivityActivity extends ListActivity {
    private String[] arrayName=new String[]{"勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗"
            ,"勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗",
            "勒布朗","勒布朗" ,"勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗","勒布朗",
            "勒布朗","勒布朗"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,R.layout.array_adapter_item,arrayName);
       setListAdapter(arrayAdapter);
    }
}
