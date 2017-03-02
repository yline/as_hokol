package com.hokol.viewhelper;

import android.view.View;

import com.hokol.R;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.medium.http.bean.ResponseMainSingleNewsBean;

/**
 * 新闻页面的,view帮助类
 *
 * @author yline 2017/3/2 --> 14:42
 * @version 1.0.0
 */
public class MainNewsHelper
{
	private ViewHolder recommendViewHolder;

	public void initRecommendView(View view)
	{
		recommendViewHolder = new ViewHolder(view);
	}

	public void setRecommendData(ResponseMainSingleNewsBean singleNewsBean)
	{
		recommendViewHolder.setImageBackgroundResource(R.id.iv_main_news_recommend, R.drawable.delete_ad_img1);
		recommendViewHolder.setText(R.id.tv_main_news_recommend_title, singleNewsBean.getNews_title());
		recommendViewHolder.setText(R.id.tv_main_news_recommend_origin, singleNewsBean.getNews_source());
		recommendViewHolder.setText(R.id.tv_main_news_recommend_time, singleNewsBean.getNews_time());
	}
}
