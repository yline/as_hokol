package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.StarDynamicActivity;
import com.hokol.activity.StarInfoActivity;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseFragment;
import com.hokol.viewhelper.MainCareHelper;

import java.util.ArrayList;
import java.util.List;

public class MainCareFragment extends BaseFragment
{
	private MainCareHelper mainCareHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_care, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		mainCareHelper = new MainCareHelper();
		mainCareHelper.initRecycleView(getContext(), view);
		mainCareHelper.setOnRecycleItemClickListener(new MainCareHelper.OnCareRecycleClickListener()
		{
			@Override
			public void onAvatarClick()
			{
				StarInfoActivity.actionStart(getContext());
			}

			@Override
			public void onPictureClick()
			{
				StarDynamicActivity.actionStart(getContext());
			}
		});

		final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_main_care);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
		{
			@Override
			public void onRefresh()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("刷新结束");
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
	}

	private void initData()
	{
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			list.add("" + i);
		}
		mainCareHelper.setRecycleData(list);
	}
}
