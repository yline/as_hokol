package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecyclerAdapter;
import com.hokol.medium.widget.recycler.OnRecyclerItemClickListener;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.yline.common.CommonRecyclerViewHolder;
import com.yline.common.ViewHolder;
import com.yline.log.LogFileUtil;
import com.yline.utils.TimeConvertUtil;
import com.yline.utils.UIScreenUtil;

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
	private HeadFootRecyclerAdapter recyclerAdapter;

	/**
	 * 初始化Recycle控件
	 *
	 * @param parentView
	 */
	public void initRecycleView(View parentView)
	{
		RecyclerView recyclerView = (RecyclerView) parentView.findViewById(R.id.recycle_main_news);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(context)
		{
			@Override
			protected int getVerticalDividePaddingLeft()
			{
				return UIScreenUtil.dp2px(context, 10);
			}
		});

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

	public void setOnRecycleItemClickListener(OnRecyclerItemClickListener listener)
	{
		recyclerAdapter.setOnRecyclerItemClickListener(listener);
	}

	public void setOnRecommendClickListener(View.OnClickListener listener)
	{
		recommendViewHolder.get(R.id.rl_main_news_recommend).setOnClickListener(listener);
	}

	private class CommonNewsAdapter extends HeadFootRecyclerAdapter<VNewsSingleBean>
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

			Glide.with(context).load(sList.get(position).getNews_img()).placeholder(R.mipmap.global_load_failed).into(imageView);

			viewHolder.setText(R.id.tv_item_main_news_title, sList.get(position).getNews_title());
			viewHolder.setText(R.id.tv_item_main_news_origin, sList.get(position).getNews_source());

			long stampTime = sList.get(position).getNews_time(); //  / 1000
			String showTime = TimeConvertUtil.stamp2FormatTime(stampTime * 1000);
			viewHolder.setText(R.id.tv_item_main_news_time, showTime);
		}
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 头布局 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private ViewHolder recommendViewHolder;

	/**
	 * 初始化推···荐控件
	 *
	 * @param recommendView
	 */
	public void initRecommendView(View recommendView)
	{
		recommendViewHolder = new ViewHolder(recommendView);

		recyclerAdapter.addHeadView(recommendView);
	}

	/**
	 * 更新推荐显示内容
	 *
	 * @param singleNewsBean
	 */
	public void updateRecommendData(VNewsSingleBean singleNewsBean)
	{
		ImageView imageView = recommendViewHolder.get(R.id.iv_main_news_recommend);
		Glide.with(context).load(singleNewsBean.getNews_img()).placeholder(R.mipmap.global_load_failed).priority(Priority.HIGH).into(imageView);

		recommendViewHolder.setText(R.id.tv_main_news_recommend_title, singleNewsBean.getNews_title());
		recommendViewHolder.setText(R.id.tv_main_news_recommend_origin, singleNewsBean.getNews_source());

		long stampTime = singleNewsBean.getNews_time();
		LogFileUtil.v("Recommend stampTime = " + stampTime);
		String showTime = TimeConvertUtil.stamp2FormatTime(stampTime * 1000);
		recommendViewHolder.setText(R.id.tv_main_news_recommend_time, showTime);
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 刷新布局 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	public void initRefreshLayout(final SuperSwipeRefreshLayout superSwipeRefreshLayout)
	{
		superSwipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("加载结束");
						superSwipeRefreshLayout.setRefreshing(false);
					}
				}, 2000);
			}
		});
		superSwipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("加载结束");
						superSwipeRefreshLayout.setLoadMore(false);
					}
				}, 2000);
			}
		});
	}

}
