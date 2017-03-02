package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;
import com.hokol.medium.http.bean.ResponseMainMultiplexNewsBean;
import com.hokol.medium.http.bean.ResponseMainSingleNewsBean;
import com.hokol.viewhelper.MainNewsHelper;

import java.util.ArrayList;
import java.util.List;

public class MainNewsFragment extends BaseFragment
{
	private MainNewsHelper mainNewsHelper;

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
		mainNewsHelper.initRecommendView(recommendView); // 初始化控件
	}

	private void initData()
	{
		List<ResponseMainMultiplexNewsBean> dataList = new ArrayList<>();
		for (int i = 0; i < 40; i++)
		{
			dataList.add(new ResponseMainMultiplexNewsBean("", "", "origin " + i, "time " + i, "title " + i, ""));
		}
		mainNewsHelper.setRecycleData(dataList);

		// 设置数据
		ResponseMainSingleNewsBean singleNewsBean = new ResponseMainSingleNewsBean();
		singleNewsBean.setNews_source("origin");
		singleNewsBean.setNews_time("time");
		singleNewsBean.setNews_title("title");
		mainNewsHelper.updateRecommendData(singleNewsBean);
	}
}
