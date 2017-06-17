package com.xuliwen.viewtest.toolbar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.T;

public class ToolBarExtendBaseToolBarActivity2 extends BaseToolBarActivity {


    private Toolbar.OnMenuItemClickListener listener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.toolbar_action1:
                    T.toast("toolbar_action1");
                    break;
                case R.id.toolbar_action2:
                    T.toast("toolbar_action2");
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
        return R.layout.activity_tool_bar_extend_base2;
    }

    @Override
    protected void handleToolbar(ToolbarHelper toolbarHelper) {
        super.handleToolbar(toolbarHelper);
        toolbarHelper
                .setTitle("Activity2")
                .setLogo(R.mipmap.ic_launcher)
                .setNavigationIcon(R.drawable.xunlei)
                .setSubTitle("SubTitle")
                .setMenu(R.menu.menu_toolbar_demo)
                .setOnMenuItemClickListener(listener);
    }
}
