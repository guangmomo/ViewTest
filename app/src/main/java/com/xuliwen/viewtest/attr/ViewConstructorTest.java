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

public class ViewConstructorTest extends View {
    public ViewConstructorTest(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewConstructorTest(Context context, AttributeSet attrs) {
        this(context,attrs,R.attr.defStyleAttrTest);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewConstructorTest(Context context, AttributeSet attrs, int defStyleAttr) {
       this(context,attrs,defStyleAttr,R.style.defStyleResStyle);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewConstructorTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.ViewConstructorTest,0,defStyleRes);
        L.log("attr1： "+typedArray.getString(R.styleable.ViewConstructorTest_attr1));
        L.log("attr2： "+typedArray.getString(R.styleable.ViewConstructorTest_attr2));
        L.log("attr3： "+typedArray.getString(R.styleable.ViewConstructorTest_attr3));
        L.log("attr4： "+typedArray.getString(R.styleable.ViewConstructorTest_attr4));
        L.log("attr5： "+typedArray.getString(R.styleable.ViewConstructorTest_attr5));
        L.log("attr6： "+typedArray.getString(R.styleable.ViewConstructorTest_attr6));

        typedArray.recycle();
    }
}
