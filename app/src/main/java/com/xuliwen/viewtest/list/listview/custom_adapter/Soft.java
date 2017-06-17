package com.xuliwen.viewtest.list.listview.custom_adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by xlw on 2017/6/10.
 */

public class Soft {
    private int iconId;
    private String name;

    public Soft(@DrawableRes int id,@NonNull String name){
        iconId=id;
        this.name=name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
