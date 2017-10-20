package com.xuliwen.viewtest.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xuliwen.viewtest.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentStateActivity extends AppCompatActivity {
	
	private ViewPager mViewPager;
	private List<Fragment> mFragmentList;
	private int[] mArrayRes = new int[]{R.drawable.big, R.drawable.big1, R.drawable.big2, R.drawable.big3, R.drawable.big4,
			R.drawable.big5, R.drawable.big6, R.drawable.big7, R.drawable.big8, R.drawable.big9};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_state);
		mViewPager = (ViewPager) findViewById(R.id.activity_fragment_state_viewPager);
//		initFragmentList();
		FragmentAdapter1 fragmentAdapter1 = new FragmentAdapter1(getSupportFragmentManager(), mArrayRes);
//		FragmentAdapter2 fragmentAdapter2 = new FragmentAdapter2(getSupportFragmentManager(), mArrayRes);
//		FragmentAdapter3 fragmentAdapter3 = new FragmentAdapter3(getSupportFragmentManager(), mFragmentList);
//		FragmentAdapter4 fragmentAdapter4 = new FragmentAdapter4(getSupportFragmentManager(), mFragmentList);
		mViewPager.setAdapter(fragmentAdapter1);
//		mViewPager.setAdapter(fragmentAdapter2);
//		mViewPager.setAdapter(fragmentAdapter3);
//		mViewPager.setAdapter(fragmentAdapter4);
	}
	
	private void initFragmentList(){
		mFragmentList = new ArrayList<>();
		for (int resId : mArrayRes) {
			mFragmentList.add(StateFragment.newInstance(resId));
		}
	}
}
