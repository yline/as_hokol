package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.NewsInfoActivity;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.http.bean.WNewsSingleBean;
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
		initView(view);

		initData();
	}

	private void initView(View view)
	{
		View recommendView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_news_recommend, null);
		mainNewsHelper = new MainNewsHelper();
		mainNewsHelper.initRecycleView(getContext(), view);
		mainNewsHelper.setOnRecycleItemClickListener(new CommonRecyclerAdapter.OnClickListener<VNewsSingleBean>()
		{
			@Override
			public void onClick(View view, VNewsSingleBean responseMultiplexNews, int position)
			{
				LogFileUtil.v("setOnRecycleItemClickListener position -> " + position);
				NewsInfoActivity.actionStart(getContext(), new WNewsSingleBean(responseMultiplexNews.getNews_id()));
			}
		});
		mainNewsHelper.initRecommendView(recommendView); // 初始化控件
		mainNewsHelper.setOnRecommendClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				LogFileUtil.v("setOnRecommendClickListener");
				if (!TextUtils.isEmpty(recommendId))
				{
					NewsInfoActivity.actionStart(getContext(), new WNewsSingleBean(recommendId));
				}
			}
		});
	}

	private void initData()
	{
		// 推荐
		new XHttp<VNewsSingleBean>()
		{
			@Override
			public void onSuccess(VNewsSingleBean vNewsSingleBean)
			{
				recommendId = vNewsSingleBean.getNews_id();
				mainNewsHelper.updateRecommendData(vNewsSingleBean);
			}
		}.doRequest(HttpConstant.HTTP_MAIN_RECOMMEND_NEWS_URL, VNewsSingleBean.class);

		// 多条新闻
		new XHttp<VNewsMultiplexBean>()
		{
			@Override
			protected Object getRequestPostParam()
			{
				return new WNewsMultiplexBean(1, 14);
			}

			@Override
			public void onSuccess(VNewsMultiplexBean vNewsMultiplexBean)
			{
				List<VNewsSingleBean> result = vNewsMultiplexBean.getList();
				mainNewsHelper.setRecycleData(result);
			}
		}.doRequest(HttpConstant.HTTP_MAIN_MULTIPLEX_NEWS_URL, VNewsMultiplexBean.class);
	}
}
