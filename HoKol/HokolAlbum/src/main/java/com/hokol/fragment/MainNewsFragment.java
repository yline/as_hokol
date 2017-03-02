package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.adapter.HeadFootWrapperAdapter;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.medium.http.bean.ResponseMainMultiplexNewsBean;
import com.hokol.medium.http.bean.ResponseMainSingleNewsBean;
import com.hokol.viewhelper.MainNewsHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainNewsFragment extends BaseFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_news, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		ButterKnife.bind(this, view);
		initView(view);
	}
	
	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_news);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		
		CommonRecyclerAdapter commonListAdapter = new CommonNewsAdapter();
		HeadFootWrapperAdapter recyclerAdapter = new HeadFootWrapperAdapter(commonListAdapter);
		
		initRecycleView(recyclerAdapter);

		List<ResponseMainMultiplexNewsBean> dataList = new ArrayList<>();
		for (int i = 0; i < 40; i++)
		{
			dataList.add(new ResponseMainMultiplexNewsBean("", "", "origin " + i, "time " + i, "title " + i, ""));
		}
		recyclerAdapter.addAll(dataList);

		recyclerView.setAdapter(recyclerAdapter);
	}
	
	private void initRecycleView(HeadFootWrapperAdapter adapter)
	{
		ResponseMainSingleNewsBean singleNewsBean = new ResponseMainSingleNewsBean();
		singleNewsBean.setNews_source("origin");
		singleNewsBean.setNews_time("time");
		singleNewsBean.setNews_title("title");

		// 推荐
		View recommendView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_news_recommend, null);
		MainNewsHelper mainNewsHelper = new MainNewsHelper();
		mainNewsHelper.initRecommendView(recommendView); // 初始化控件
		mainNewsHelper.setRecommendData(singleNewsBean); // 设置数据

		adapter.addHeaderView(recommendView);
	}
	
	private class CommonNewsAdapter extends CommonRecyclerAdapter<ResponseMainMultiplexNewsBean>
	{
		
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_news;
		}
		
		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			viewHolder.setImageBackground(R.id.iv_main_news, R.mipmap.ic_launcher);
			viewHolder.setText(R.id.tv_main_news_title, sList.get(position).getNews_title());
			viewHolder.setText(R.id.tv_main_news_origin, sList.get(position).getNews_source());
			viewHolder.setText(R.id.tv_main_news_time, sList.get(position).getNews_time());
		}
	}
}
