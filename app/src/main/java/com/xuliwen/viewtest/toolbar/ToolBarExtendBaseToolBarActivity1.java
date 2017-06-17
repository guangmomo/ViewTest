package com.xuliwen.viewtest.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.T;

public class ToolBarExtendBaseToolBarActivity1 extends BaseToolBarActivity {

    private Toolbar.OnMenuItemClickListener listener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_search:
                    T.toast("action_search");
                    break;
                case R.id.action_notification:
                    T.toast("action_notification");
                    break;
                case R.id.action_item1:
                    T.toast("action_item1");
                    break;
                case R.id.action_item2:
                    T.toast("action_item2");
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tool_bar_extend_base1;
    }

    @Override
    protected void handleToolbar(ToolbarHelper toolbarHelper) {
        super.handleToolbar(toolbarHelper);
        toolbarHelper
                .setTitle("Activity1")
                .setLogo(R.mipmap.ic_launcher)
                .setNavigationIcon(R.drawable.xunlei)
                .setMenu(R.menu.base_toolbar_menu)
                .setOnMenuItemClickListener(listener)
                .setCustomTitle("CustomTitle");
    }

    public void toToolBarExtendBaseActivity2(View view) {
        startActivity(new Intent(this,ToolBarExtendBaseToolBarActivity2.class));
    }


    public void toCommonActivity(View view) {
        startActivity(new Intent(this,CommonActivity.class));
    }
}
