package com.xuliwen.viewtest.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by xuliwen on 2017/10/16.
 */

public class FragmentAdapter2 extends FragmentStatePagerAdapter {
	
	private int[] mArrayRes;
	
	public FragmentAdapter2(FragmentManager fm, int[] arrayRes) {
		super(fm);
		mArrayRes = arrayRes;
	}

	@Override
	public Fragment getItem(int position) {
		return StateFragment.newInstance(mArrayRes[position]);
	}

	@Override
	public int getCount() {
		if (mArrayRes == null) {
			return 0;
		}
		return mArrayRes.length;
	}
}
