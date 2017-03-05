package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.custom.DefaultLinearItemDecoration;
import com.hokol.medium.http.bean.ResponseSingleNewsBean;
import com.hokol.view.labellayout.FlowLayout;
import com.hokol.view.labellayout.LabelFlowLayout;

import java.util.Arrays;
import java.util.List;

public class MainCareHelper
{
	private HeadFootRecycleAdapter recyclerAdapter;

	private RequestManager glideManager;

	private LayoutInflater inflate;

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
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(context, DefaultLinearItemDecoration.VERTICAL_LIST, R.drawable.main_care_divider));

		this.glideManager = Glide.with(context);
		this.inflate = LayoutInflater.from(context);

		recyclerAdapter = new MainCareHelper.RecycleAdapter();

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

	private class RecycleAdapter extends HeadFootRecycleAdapter<ResponseSingleNewsBean>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_care;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			LabelFlowLayout labelFlowLayout = viewHolder.get(R.id.label_item_main_care_label);
			labelFlowLayout.setAdapter(new CareLabelAdapter(Arrays.asList("标签 -> ", "网红", "模特", "歌手")));
			/*
			ImageView imageView = viewHolder.get(R.id.iv_main_news);

			glideManager.load(sList.get(position).getNews_img()).placeholder(R.drawable.global_load_failed).into(imageView);

			viewHolder.setText(R.id.tv_main_news_title, sList.get(position).getNews_title());
			viewHolder.setText(R.id.tv_main_news_origin, sList.get(position).getNews_source());
			viewHolder.setText(R.id.tv_main_news_time, sList.get(position).getNews_time());
			*/
		}
	}
	
	private class CareLabelAdapter extends com.hokol.view.labellayout.LabelAdapter<String>
	{
		public CareLabelAdapter(List<String> data)
		{
			super(data);
		}

		@Override
		public View getView(FlowLayout parent, int position, String s)
		{
			TextView tvItem = (TextView) inflate.inflate(R.layout.global_item_label, parent, false);
			tvItem.setText(slist.get(position));
			return tvItem;
		}
	}
}
