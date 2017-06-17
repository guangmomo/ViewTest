package com.xuliwen.viewtest.toolbar.custom_title;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuliwen.viewtest.R;

public class BaseCustomTitleActivity extends AppCompatActivity {

    private RelativeLayout mTitleLayout;
    private TitleHelper mTitleHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitleLayout= (RelativeLayout) findViewById(R.id.custom_title);
        if(mTitleLayout!=null){
            mTitleHelper=new TitleHelper(mTitleLayout);
            handlerCustomTitle(mTitleHelper);
        }

    }

    protected void handlerCustomTitle(TitleHelper mTitleHelper ){}

    public static class TitleHelper{
        private RelativeLayout titleLayout;

        private TitleHelper(RelativeLayout titleLayout){
            this.titleLayout=titleLayout;
        }

        public TitleHelper setLeftImage(int id){
            ImageView imageView= (ImageView) titleLayout.findViewById(R.id.left_imageView);
            imageView.setImageResource(id);
            return this;
        }

        public TitleHelper setCenterTitle(String centerTitle){
            TextView textView= (TextView) titleLayout.findViewById(R.id.center_textView);
            textView.setText(centerTitle);
            return this;
        }

        public TitleHelper setRightTitle(String rightTitle){
            TextView textView= (TextView) titleLayout.findViewById(R.id.right_textView);
            textView.setText(rightTitle);
            return this;
        }

    }
}
