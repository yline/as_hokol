package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.NewsInfoActivity;
import com.hokol.medium.http.XHttpAdapter;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.http.bean.WNewsSingleBean;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainNewsHelper;

import java.util.List;

public class MainNewsFragment extends BaseFragment
{
	private MainNewsHelper mainNewsHelper;

	private String recommendId;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_news, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		mainNewsHelper = new MainNewsHelper(getContext());

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		// recycleView
		mainNewsHelper.initRecycleView(view);
		mainNewsHelper.setOnRecycleItemClickListener(new CommonRecyclerAdapter.OnClickListener<VNewsSingleBean>()
		{
			@Override
			public void onClick(View view, VNewsSingleBean responseMultiplexNews, int position)
			{
				NewsInfoActivity.actionStart(getContext(), new WNewsSingleBean(responseMultiplexNews.getNews_id()));
			}
		});

		// 推荐
		View recommendView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_news_recommend, null);
		mainNewsHelper.initRecommendView(recommendView);
		mainNewsHelper.setOnRecommendClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(recommendId))
				{
					NewsInfoActivity.actionStart(getContext(), new WNewsSingleBean(recommendId));
				}
			}
		});

		// 刷新
		SuperSwipeRefreshLayout superSwipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_news);
		mainNewsHelper.initRefreshLayout(superSwipeRefreshLayout);
	}

	private void initData()
	{
		// 推荐
		XHttpUtil.doNewsRecommend(new XHttpAdapter<VNewsSingleBean>()
		{
			@Override
			public void onSuccess(VNewsSingleBean vNewsSingleBean)
			{
				recommendId = vNewsSingleBean.getNews_id();
				mainNewsHelper.updateRecommendData(vNewsSingleBean);
			}
		});

		// 多条新闻
		XHttpUtil.doNewsMultiplex(new WNewsMultiplexBean(1, 14), new XHttpAdapter<VNewsMultiplexBean>()
		{
			@Override
			public void onSuccess(VNewsMultiplexBean vNewsMultiplexBean)
			{
				List<VNewsSingleBean> result = vNewsMultiplexBean.getList();
				mainNewsHelper.setRecycleData(result);
			}
		});
	}
}
