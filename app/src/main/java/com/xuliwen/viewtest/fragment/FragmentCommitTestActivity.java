package com.xuliwen.viewtest.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.L;


/**
 * 这个类用于探索Activity的生命周期：onSaveInstanceState，onDestroy对
 * Fragment的commit，commitAllowingStateLoss 和 Activity的 onBackPressed，finish 的影响
 * 
 */
public class FragmentCommitTestActivity extends AppCompatActivity {

	private static final String TAG = "FragmentCommitTestActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_commit_test);
	}

	public void commitFragment1Delay5s(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				FragmentCommitTestActivity.this.getSupportFragmentManager().beginTransaction().add(R.id.fragment1_layout, new Fragment1()).commit();
			}
		}, 5 * 1000);
	}

	public void commitAllowingStateLossFragment1Delay5s(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				FragmentCommitTestActivity.this.getSupportFragmentManager().beginTransaction().add(R.id.fragment1_layout, new Fragment1()).commitAllowingStateLoss();
			}
		}, 5 * 1000);
	}

	public void onBackPressDelay5s(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				FragmentCommitTestActivity.this.onBackPressed();
			}
		}, 5 * 1000);
	}
	

	public void finishDelay5s(View view) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				FragmentCommitTestActivity.this.finish();
			}
		}, 5 * 1000);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		L.log(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		L.log(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		L.log(TAG, "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		L.log(TAG, "onDestroy");
		super.onDestroy();
	}


}
