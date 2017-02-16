package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseFragment;
import com.hokol.viewhelper.MainNewsHotADHelper;
import com.hokol.viewhelper.MainNewsHotPointHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 热点
 * @author yline 2017/2/13 --> 17:36
 * @version 1.0.0
 */
public class MainNewsHotFragment extends BaseFragment implements MainNewsHotADHelper.OnPageClickListener, MainNewsHotPointHelper.OnItemClickListener
{
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_news_hot, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}
	
	private void initView(View view)
	{
		List<Integer> data = new ArrayList<>();
		data.add(R.drawable.delete_ad_img1);
		data.add(R.drawable.delete_ad_img2);
		data.add(R.drawable.delete_ad_img3);
		data.add(R.drawable.delete_ad_img4);
		data.add(R.drawable.delete_ad_img5);

		MainNewsHotADHelper mainNewsHotADHelper = new MainNewsHotADHelper();
		mainNewsHotADHelper.build().setResource(data).commit(getContext());
		mainNewsHotADHelper.initPoint((LinearLayout) view.findViewById(R.id.ll_main_hot_news_ad));
		mainNewsHotADHelper.initViewPagerView((ViewPager) view.findViewById(R.id.viewpager_main_hot_news_ad));
		mainNewsHotADHelper.startAutoRecycle();
		mainNewsHotADHelper.setListener(this);

		MainNewsHotPointHelper mainNewsHotPointHelper = new MainNewsHotPointHelper();
		mainNewsHotPointHelper.init(view.findViewById(R.id.include_main_news_hot_point));
		mainNewsHotPointHelper.setListener(this);
	}

	@Override
	public void onPagerClick(View v, int position)
	{
		IApplication.toast("ad position = " + position);
	}

	@Override
	public void onItemClick(int position)
	{
		IApplication.toast("point position = " + position);
	}
}
