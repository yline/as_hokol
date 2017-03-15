package com.hokol.viewhelper;

import android.support.v4.widget.SwipeRefreshLayout;

import com.hokol.application.IApplication;

public class MainHomeRedRefreshHelper
{
	private static final int[] COLOR = new int[]{android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light};

	public void init(final SwipeRefreshLayout swipeRefreshLayout)
	{
		swipeRefreshLayout.setColorSchemeResources(COLOR);
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
}
