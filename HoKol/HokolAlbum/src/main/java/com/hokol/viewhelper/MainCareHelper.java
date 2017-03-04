package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.custom.DefaultLinearItemDecoration;
import com.hokol.medium.http.bean.ResponseSingleNewsBean;

import java.util.List;

public class MainCareHelper
{
	private HeadFootRecycleAdapter recyclerAdapter;

	private RequestManager glideManager;

	/**
	 * 初始化Recycle控件
	 *
	 * @param context
	 * @param parentView
	 */
	public void initRecycleView(Context context, View parentView)
	{
		RecyclerView recyclerView = (RecyclerView) parentView.findViewById(R.id.recycle_main_care);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(context, DefaultLinearItemDecoration.VERTICAL_LIST));

		this.glideManager = Glide.with(context);

		recyclerAdapter = new MainCareHelper.CommonNewsAdapter();

		recyclerView.setAdapter(recyclerAdapter);
	}

	public void setOnRecycleItemClickListener(CommonRecyclerAdapter.OnClickListener listener)
	{
		recyclerAdapter.setOnClickListener(listener);
	}

	public void setRecycleData(List<Object> dataList)
	{
		recyclerAdapter.setDataList(dataList);
	}

	private class CommonNewsAdapter extends HeadFootRecycleAdapter<ResponseSingleNewsBean>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_care;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{/*
			ImageView imageView = viewHolder.get(R.id.iv_main_news);

			glideManager.load(sList.get(position).getNews_img()).placeholder(R.drawable.load_failed).into(imageView);

			viewHolder.setText(R.id.tv_main_news_title, sList.get(position).getNews_title());
			viewHolder.setText(R.id.tv_main_news_origin, sList.get(position).getNews_source());
			viewHolder.setText(R.id.tv_main_news_time, sList.get(position).getNews_time());*/
		}
	}
}
