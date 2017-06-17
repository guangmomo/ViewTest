package com.xuliwen.viewtest.attr;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.L;

/**
 * Created by xlw on 2017/5/11.
 */

public class People extends View {

    private static final int  HANDSOME=0;
    private static final int  CLOWN=1;



    private static final int CHINESE=0x1;
    private static final int ENGLISH=0x2;
    private static final int JAPANESE=0x4;

    private static final int MATCH_PARENT=-1;
    private static final int WRAP_CONTENT=-2;



    public People(Context context) {
        this(context,null);
    }

    public People(Context context, AttributeSet attrs) {//默认调用的构造方法
       this(context,attrs,0);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.people);
        String name=typedArray.getString(R.styleable.people_name);
        int age=typedArray.getInteger(R.styleable.people_age,0);//获取Integer类型的值
       // int age=typedArray.getInt(R.styleable.people_age,0); //获取Integer类型的值
        float peopleHeight=typedArray.getFloat(R.styleable.people_peopleHeight,0);
        int appearanceFlag=typedArray.getInt(R.styleable.people_appearance,0);//获取enum的值用getInt
        String appearance=null;
        if(appearanceFlag==HANDSOME) appearance="帅气";
        if(appearanceFlag==CLOWN) appearance="丑陋";
        int color=typedArray.getColor(R.styleable.people_skinColor, Color.WHITE);//获取color值
        int head=typedArray.getResourceId(R.styleable.people_head,R.mipmap.ic_launcher);//获取reference的值
        boolean isStudent=typedArray.getBoolean(R.styleable.people_isStudent,false);
        int languageFlag=typedArray.getInt(R.styleable.people_language,0);
        String language="";
        boolean isChinese=(languageFlag & CHINESE)==CHINESE;
        boolean isEnglish=(languageFlag & ENGLISH)==ENGLISH;
        boolean isJapanese=(languageFlag & JAPANESE)==JAPANESE;
        if(isChinese){
            language+="chinese,";
        }
        if(isEnglish){
            language+="english,";
        }
        if(isJapanese){
            language+="japanese";
        }
        int height=typedArray.getLayoutDimension(R.styleable.people_android_height,"height");  //获取Dimension的值，单位是px

        String  text=typedArray.getString(R.styleable.people_android_text);

        String headWidth="";
        int headWidthPx=typedArray.getLayoutDimension(R.styleable.people_headWidth,-1);
        if(headWidthPx==MATCH_PARENT){
            headWidth="match_parent";
        }else if(headWidthPx==WRAP_CONTENT){
            headWidth="wrap_content";
        }else {
            headWidth=String.valueOf(headWidthPx)+"px";
        }


        L.log("name: "+name);
        L.log("age: "+age);
        L.log("peopleHeight: "+peopleHeight);
        L.log("appearance: "+appearance);
        L.log("color: "+color);
        L.log("head: "+head);
        L.log("isStudent: "+isStudent);
        L.log("language: "+language);
        L.log("height: "+height+"px");
        L.log("text: "+text);
        L.log("headWidth: "+headWidth);


        //最后要调用recycle
        typedArray.recycle();
    }

    public People(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


}
