package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.medium.http.bean.ResponseMainMultiplexNewsBean;
import com.hokol.medium.http.bean.ResponseMainSingleNewsBean;

import java.util.List;

/**
 * 新闻页面的,view帮助类
 *
 * @author yline 2017/3/2 --> 14:42
 * @version 1.0.0
 */
public class MainNewsHelper
{
	private ViewHolder recommendViewHolder;

	private HeadFootRecycleAdapter recyclerAdapter;

	/**
	 * 初始化Recycle控件
	 *
	 * @param context
	 * @param parentView
	 */
	public void initView(Context context, View parentView)
	{
		RecyclerView recyclerView = (RecyclerView) parentView.findViewById(R.id.recycle_main_news);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));

		recyclerAdapter = new CommonNewsAdapter();

		recyclerView.setAdapter(recyclerAdapter);
	}

	/**
	 * 初始化推荐控件
	 *
	 * @param recommendView
	 */
	public void initRecommendView(View recommendView)
	{
		recommendViewHolder = new ViewHolder(recommendView);

		recyclerAdapter.addHeaderView(recommendView);
	}

	/**
	 * 更新推荐显示内容
	 *
	 * @param singleNewsBean
	 */
	public void updateRecommendData(ResponseMainSingleNewsBean singleNewsBean)
	{
		recommendViewHolder.setImageBackgroundResource(R.id.iv_main_news_recommend, R.drawable.delete_ad_img1);
		recommendViewHolder.setText(R.id.tv_main_news_recommend_title, singleNewsBean.getNews_title());
		recommendViewHolder.setText(R.id.tv_main_news_recommend_origin, singleNewsBean.getNews_source());
		recommendViewHolder.setText(R.id.tv_main_news_recommend_time, singleNewsBean.getNews_time());
	}

	/**
	 * 更新Recycle数据
	 *
	 * @param dataList
	 */
	public void setRecycleData(List<ResponseMainMultiplexNewsBean> dataList)
	{
		recyclerAdapter.addAll(dataList);
	}

	public void setOnRecycleItemClickListener(CommonRecyclerAdapter.OnClickListener listener)
	{
		recyclerAdapter.setOnClickListener(listener);
	}

	public void setOnRecommendClickListener(View.OnClickListener listener)
	{
		recommendViewHolder.get(R.id.rl_main_news_recommend).setOnClickListener(listener);
	}

	private class CommonNewsAdapter extends HeadFootRecycleAdapter<ResponseMainMultiplexNewsBean>
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
