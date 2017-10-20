package com.xuliwen.viewtest.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by xuliwen on 2017/10/16.
 */

public class FragmentAdapter1 extends FragmentPagerAdapter {
	private int[] mArrayRes;

	public FragmentAdapter1(FragmentManager fm, int[] arrayRes) {
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
