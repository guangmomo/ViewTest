package com.xuliwen.viewtest.utils;

import android.widget.Toast;

import com.xuliwen.viewtest.App;

/**
 * Created by xlw on 2017/6/7.
 */

public class T {
    public static void toast(String content){
        Toast.makeText(App.getContext(),content,Toast.LENGTH_SHORT).show();
    }
}
