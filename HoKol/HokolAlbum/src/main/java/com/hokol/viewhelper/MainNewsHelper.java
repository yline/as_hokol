package com.hokol.viewhelper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.hokol.R;
import com.hokol.medium.http.bean.VNewsRecommendBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.utils.TimeConvertUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;
import com.yline.view.recycler.holder.ViewHolder;

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
	private CommonNewsAdapter recyclerAdapter;

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
			public void drawVerticalDivider(Canvas c, Drawable divide, int currentPosition, int childLeft, int childTop, int childRight, int childBottom)
			{
				childLeft = childLeft + UIScreenUtil.dp2px(context, 10);
				super.drawVerticalDivider(c, divide, currentPosition, childLeft, childTop, childRight, childBottom);
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
	public void setRecyclerData(List<VNewsSingleBean> dataList)
	{
		recyclerAdapter.setDataList(dataList);
	}

	public void setOnRecycleItemClickListener(OnRecyclerItemClickListener listener)
	{
		recyclerAdapter.setOnRecyclerItemClickListener(listener);
	}

	public void addRecyclerData(List<VNewsSingleBean> dataList)
	{
		recyclerAdapter.addAll(dataList);
	}

	public void setOnRecommendClickListener(View.OnClickListener listener)
	{
		recommendViewHolder.get(R.id.rl_main_news_recommend).setOnClickListener(listener);
	}

	private class CommonNewsAdapter extends HeadFootRecyclerAdapter<VNewsSingleBean>
	{
		private OnRecyclerItemClickListener listener;

		public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener)
		{
			this.listener = listener;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_news;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			viewHolder.getItemView().setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != listener)
					{
						listener.onItemClick(viewHolder, sList.get(position), position);
					}
				}
			});

			ImageView imageView = viewHolder.get(R.id.iv_item_main_news);

			Glide.with(context).load(sList.get(position).getNews_img()).placeholder(R.drawable.global_load_failed).into(imageView);

			viewHolder.setText(R.id.tv_item_main_news_title, sList.get(position).getNews_title());
			viewHolder.setText(R.id.tv_item_main_news_origin, sList.get(position).getNews_source());

			long stampTime = sList.get(position).getNews_time(); //  / 1000
			String showTime = TimeConvertUtil.stamp2FormatTime(stampTime * 1000);
			viewHolder.setText(R.id.tv_item_main_news_time, showTime);
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			// super.onBindEmptyViewHolder(viewHolder, position);
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
	 * @param recommendBean
	 */
	public void updateRecommendData(VNewsRecommendBean recommendBean)
	{
		ImageView imageView = recommendViewHolder.get(R.id.iv_main_news_recommend);
		Glide.with(context).load(recommendBean.getBanner_img()).placeholder(R.drawable.global_load_failed).priority(Priority.HIGH).into(imageView);
	}
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 刷新布局 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
}
