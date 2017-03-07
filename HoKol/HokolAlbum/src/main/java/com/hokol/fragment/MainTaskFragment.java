package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseFragment;
import com.hokol.viewhelper.MainTaskHelper;
import com.hokol.viewhelper.global.ADHelper;

import java.util.ArrayList;
import java.util.List;

public class MainTaskFragment extends BaseFragment implements ADHelper.OnPageClickListener
{
	private MainTaskHelper mainTaskHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_task, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View parentView)
	{
		List<Integer> data = new ArrayList<>();
		data.add(R.drawable.delete_ad_img1);
		data.add(R.drawable.delete_ad_img2);
		data.add(R.drawable.delete_ad_img3);
		data.add(R.drawable.delete_ad_img4);
		data.add(R.drawable.delete_ad_img5);

		ADHelper adHelper = new ADHelper();
		adHelper.build().setResource(data).setViewPagerHeight(300).commit(getContext());
		adHelper.initPoint((LinearLayout) parentView.findViewById(R.id.ll_main_task_ad));
		adHelper.initViewPagerView((ViewPager) parentView.findViewById(R.id.viewpager_main_task_ad));
		adHelper.startAutoRecycle();
		adHelper.setListener(this);

		TabLayout tabLayout = (TabLayout) parentView.findViewById(R.id.tab_layout_main_task_menu);
		mainTaskHelper = new MainTaskHelper();
		mainTaskHelper.initTabDownMenuView(getContext(), tabLayout);

		RecyclerView recycleView = (RecyclerView) parentView.findViewById(R.id.recycle_main_task);
		mainTaskHelper.initRecycleView(getContext(), recycleView);
		mainTaskHelper.setRecycleData();
	}

	@Override
	public void onPagerClick(View v, int position)
	{
		IApplication.toast("position = " + position);
	}
}
