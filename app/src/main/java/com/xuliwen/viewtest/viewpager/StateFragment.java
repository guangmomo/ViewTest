package com.xuliwen.viewtest.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xuliwen.viewtest.R;

/**
 * Created by xuliwen on 2017/10/16.
 */

public class StateFragment extends Fragment {
	private ImageView mImageView;

	/**
	 * 正确的创建Fragment的方式：可以保证Fragment在被系统重新创建的时候，可以通过getArguments获取到setArguments设置的数据
	 * @param resId
	 * @return
	 */
	public static StateFragment newInstance(int resId){
		StateFragment stateFragment = new StateFragment();
		Bundle args = new Bundle();
		args.putInt("resId", resId);
		stateFragment.setArguments(args);
		return stateFragment;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * Fragment被添加到Activity上面
	 * @param context
	 */
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	/**
	 * 创建Fragment的View
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_state_layout, container, false);
		ImageView mImageView = (ImageView) view.findViewById(R.id.fragment_state_imageView);
//		mImageView = (ImageView) view.findViewById(R.id.fragment_state_imageView);
		int resId = getArguments().getInt("resId", R.mipmap.ic_launcher);
		mImageView.setImageResource(resId);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	

	/**
	 * onDestroyView之前Fragment的状态被保存了，onDestroyView之后View会立马从Fragment中移除
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	/**
	 * Fragment从Activity中移除
	 */
	@Override
	public void onDetach() {
		super.onDetach();
	}
}
