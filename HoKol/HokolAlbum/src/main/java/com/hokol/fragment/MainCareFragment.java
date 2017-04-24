package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.StarDynamicActivity;
import com.hokol.activity.StarInfoActivity;
import com.hokol.application.IApplication;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainCareHelper;
import com.yline.base.BaseFragment;

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

		mainCareHelper = new MainCareHelper(getContext());

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		// 内容
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_care);
		mainCareHelper.initRecycleView(recyclerView);
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

		// 刷新
		final SuperSwipeRefreshLayout swipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_care);
		swipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
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
				}, 2000);
			}
		});
		swipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("刷新结束");
						swipeRefreshLayout.setLoadMore(false);
					}
				}, 2000);
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
