package com.xuliwen.viewtest.list.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xuliwen.viewtest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterActivity extends AppCompatActivity {

    private ListView listView;
    private int[] headerIds=new int[]{R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome
    ,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome
            ,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome
            ,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome
            ,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome,R.drawable.ic_chrome};

    private String[] names=new String[]{"谷歌","谷歌","谷歌","谷歌",
            "谷歌","谷歌","谷歌","谷歌",
            "谷歌","谷歌","谷歌","谷歌",
            "谷歌","谷歌","谷歌","谷歌",
            "谷歌","谷歌","谷歌","谷歌"};

    private List<Map<String,Object>> datas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        initData();
        listView= (ListView) findViewById(R.id.simpleAdapter_listView);
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,datas,R.layout.simple_adapter_item,new String[]{"headerId","name"},new int[]{R.id.simpleAdapter_imageView, R.id.simpleAdapter_textView});
        listView.setAdapter(simpleAdapter);
    }

    private void initData(){
        int length=headerIds.length;
        for(int i=0;i<length;i++){
            Map<String , Object> map=new HashMap<>();
            map.put("headerId",headerIds[i]);
            map.put("name",names[i]);
            datas.add(map);
        }
    }
}
