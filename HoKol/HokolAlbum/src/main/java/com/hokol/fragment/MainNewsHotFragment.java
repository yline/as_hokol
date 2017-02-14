package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;

/**
 * 热点
 * @author yline 2017/2/13 --> 17:36
 * @version 1.0.0
 */
public class MainNewsHotFragment extends BaseFragment
{
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		if (view != null)
		{
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
			{
				parent.removeView(view);
			}
			return view;
		}
		view = inflater.inflate(R.layout.fragment_news_hot, container, false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		if (null != view)
		{

		}
		super.onViewCreated(view, savedInstanceState);
		initView();
	}
	
	private void initView()
	{
		NewsADFragment newsAdFragment = new NewsADFragment();
		getChildFragmentManager().beginTransaction().add(R.id.ll_news_hot_ad, newsAdFragment).commit();
	}
}
