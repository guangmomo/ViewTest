package com.xuliwen.viewtest.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xuliwen on 2017/10/16.
 */

public class FragmentAdapter4 extends FragmentStatePagerAdapter {


	private List<Fragment> mFragmentList;

	public FragmentAdapter4(FragmentManager fm, List<Fragment> fragmentList) {
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
