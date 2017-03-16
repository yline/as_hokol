package com.hokol.viewhelper;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;

import java.util.List;

/**
 * 新闻页面的,view帮助类
 *
 * @author yline 2017/3/2 --> 14:42
 * @version 1.0.0
 */
public class MainNewsHelper
{
	private Context context;

	public MainNewsHelper(Context context)
	{
		this.context = context;
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% RecyclerView 主布局 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private HeadFootRecycleAdapter recyclerAdapter;

	/**
	 * 初始化Recycle控件
	 *
	 * @param parentView
	 */
	public void initRecycleView(View parentView)
	{
		RecyclerView recyclerView = (RecyclerView) parentView.findViewById(R.id.recycle_main_news);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(context));

		recyclerAdapter = new CommonNewsAdapter();

		recyclerView.setAdapter(recyclerAdapter);
	}

	/**
	 * 更新Recycle数据
	 *
	 * @param dataList
	 */
	public void setRecycleData(List<VNewsSingleBean> dataList)
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

	private class CommonNewsAdapter extends HeadFootRecycleAdapter<VNewsSingleBean>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_news;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			ImageView imageView = viewHolder.get(R.id.iv_item_main_news);

			Glide.with(context).load(sList.get(position).getNews_img()).placeholder(R.drawable.global_load_failed).into(imageView);

			viewHolder.setText(R.id.tv_item_main_news_title, sList.get(position).getNews_title());
			viewHolder.setText(R.id.tv_item_main_news_origin, sList.get(position).getNews_source());
			viewHolder.setText(R.id.tv_item_main_news_time, sList.get(position).getNews_time());
		}
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 头布局 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private ViewHolder recommendViewHolder;

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
	public void updateRecommendData(VNewsSingleBean singleNewsBean)
	{
		ImageView imageView = recommendViewHolder.get(R.id.iv_main_news_recommend);
		Glide.with(context).load(singleNewsBean.getNews_img()).placeholder(R.drawable.global_load_failed).priority(Priority.HIGH).into(imageView);

		recommendViewHolder.setText(R.id.tv_main_news_recommend_title, singleNewsBean.getNews_title());
		recommendViewHolder.setText(R.id.tv_main_news_recommend_origin, singleNewsBean.getNews_source());
		recommendViewHolder.setText(R.id.tv_main_news_recommend_time, singleNewsBean.getNews_time());
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 刷新布局 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	public void initRefreshLayout(final SwipeRefreshLayout swipeRefreshLayout)
	{
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
						IApplication.toast("加载结束");
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
	}

}
