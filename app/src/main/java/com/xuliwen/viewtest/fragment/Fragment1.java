package com.xuliwen.viewtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuliwen.viewtest.R;

/**
 * Created by xuliwen on 2017/10/27.
 */

public class Fragment1 extends Fragment {
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1_layout, null);
	}
}
