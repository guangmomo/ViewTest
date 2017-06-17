package com.xuliwen.viewtest.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.xuliwen.viewtest.R;

public abstract class BaseToolBarActivity extends AppCompatActivity {

    private ToolbarHelper mToolbarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(getLayoutId());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            mToolbarHelper = new ToolbarHelper(toolbar);
            handleToolbar(mToolbarHelper);
        }
    }


    protected abstract int getLayoutId();

    protected void handleToolbar(ToolbarHelper toolbarHelper) {}

    public static class ToolbarHelper {

        private Toolbar mToolbar;

        public ToolbarHelper(Toolbar toolbar) {
            this.mToolbar = toolbar;
        }

        public Toolbar getToolbar() {
            return mToolbar;
        }

        public ToolbarHelper setTitle(String title) {
//            TextView titleTV = (TextView) mToolbar.findViewById(R.id.toolbar_title);
//            titleTV.setText(title);
            mToolbar.setTitle(title);
            return this;
        }
        public ToolbarHelper setCustomTitle(String title){
            TextView titleTV = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            titleTV.setVisibility(View.VISIBLE);
            titleTV.setText(title);
            return this;
        }

        public ToolbarHelper setLogo(int id){
            // 显示应用的Logo
            mToolbar.setLogo(id);
            return this;
        }

        public ToolbarHelper setSubTitle(String subTitle){
            mToolbar.setSubtitle(subTitle);
            return this;
        }

        public ToolbarHelper setNavigationIcon(int id){
            mToolbar.setNavigationIcon(id);
            return this;
        }

        public ToolbarHelper setMenu(int id){
            mToolbar.inflateMenu(id);
            return this;
        }

        public ToolbarHelper setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener){
            mToolbar.setOnMenuItemClickListener(listener);
            return this;
        }
    }
}
