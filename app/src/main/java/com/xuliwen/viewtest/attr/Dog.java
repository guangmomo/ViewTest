package com.xuliwen.viewtest.attr;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.L;

/**
 * Created by xlw on 2017/5/12.
 */

public class Dog extends View {
    public Dog(Context context) {
        this(context,null);
    }

    public Dog(Context context, AttributeSet attrs) {//一般第二个构造函数会被调用，其他三个构造函数不会被调用
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.dog);
        String name=typedArray.getString(R.styleable.dog_name);
        int age=typedArray.getInteger(R.styleable.dog_age,0);//获取Integer类型的值

        L.log("dong:name: "+name);
        L.log("dong:age: "+age);
        typedArray.recycle();
    }

    public Dog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dog(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
