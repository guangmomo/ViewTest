package com.xuliwen.viewtest.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xuliwen on 2017/10/16.
 */

public class FragmentAdapter3 extends FragmentPagerAdapter {

	private List<Fragment> mFragmentList;

	public FragmentAdapter3(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		mFragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	@Override
	public int getCount() {
		if (mFragmentList == null) {
			return 0;
		}
		return mFragmentList.size();
	}
}
