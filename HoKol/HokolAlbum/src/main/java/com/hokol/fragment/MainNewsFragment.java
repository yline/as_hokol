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
import com.hokol.medium.http.bean.RequestMultiplexNewsBean;
import com.hokol.medium.http.bean.RequestSingleNewsBean;
import com.hokol.medium.http.bean.ResponseMultiplexNewsBean;
import com.hokol.medium.http.bean.ResponseSingleNewsBean;
import com.hokol.medium.http.xHttp;
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
		mainNewsHelper.initView(getContext(), view);
		mainNewsHelper.setOnRecycleItemClickListener(new CommonRecyclerAdapter.OnClickListener<ResponseSingleNewsBean>()
		{
			@Override
			public void onClick(View view, ResponseSingleNewsBean responseMultiplexNews, int position)
			{
				LogFileUtil.v("setOnRecycleItemClickListener position -> " + position);
				NewsInfoActivity.actionStart(getContext(), new RequestSingleNewsBean(responseMultiplexNews.getNews_id()));
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
					NewsInfoActivity.actionStart(getContext(), new RequestSingleNewsBean(recommendId));
				}
			}
		});
	}

	private void initData()
	{
		// 推荐
		new xHttp<ResponseSingleNewsBean>()
		{
			@Override
			public void onSuccess(ResponseSingleNewsBean responseSingleNewsBean)
			{
				super.onSuccess(responseSingleNewsBean);
				recommendId = responseSingleNewsBean.getNews_id();
				mainNewsHelper.updateRecommendData(responseSingleNewsBean);
			}
		}.doPost(HttpConstant.HTTP_MAIN_RECOMMEND_NEWS_URL, "", ResponseSingleNewsBean.class);

		// 多条新闻
		new xHttp<ResponseMultiplexNewsBean>()
		{
			@Override
			public void onSuccess(ResponseMultiplexNewsBean multiplexNewsBeen)
			{
				super.onSuccess(multiplexNewsBeen);
				List<ResponseSingleNewsBean> result = multiplexNewsBeen.getList();
				mainNewsHelper.setRecycleData(result);
			}

			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
			}

			@Override
			public void onFailure(Exception ex)
			{
				super.onFailure(ex);
			}
		}.doPost(HttpConstant.HTTP_MAIN_MULTIPLEX_NEWS_URL, new RequestMultiplexNewsBean(1, 14), ResponseMultiplexNewsBean.class);
	}
}
