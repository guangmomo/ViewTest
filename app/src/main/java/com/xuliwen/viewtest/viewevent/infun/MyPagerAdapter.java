package com.xuliwen.viewtest.viewevent.infun;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xlw on 2017/5/18.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<View> mItemList;

   public MyPagerAdapter(List<View> mItemList){
       this.mItemList=mItemList;
   }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mItemList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView(mItemList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(mItemList.get(position));


        return mItemList.get(position);
    }
}
