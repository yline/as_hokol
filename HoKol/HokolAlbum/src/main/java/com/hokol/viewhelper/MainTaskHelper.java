package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.custom.DefaultLinearItemDecoration;
import com.hokol.medium.widget.DropMenuWidget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Task helper
 *
 * @author yline 2017/3/7 --> 16:02
 * @version 1.0.0
 */
public class MainTaskHelper
{
	private String headers[] = {"分类", "金额", "地区"};

	private String provinceList[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

	private DropMenuWidget dropMenuWidget;

	private TaskRecycleAdapter taskRecycleAdapter;

	private OnTaskClickListener onRecycleClickListener;

	public void initTabDownMenuView(Context context, LinearLayout linearLayout)
	{
		dropMenuWidget = new DropMenuWidget();

		List<View> contentViewList = new ArrayList<>();

		View classifyView = initClassifyView(context);
		contentViewList.add(classifyView);

		View moneyView = initClassifyView(context);
		contentViewList.add(moneyView);

		View areaView = initFilterView(context);
		contentViewList.add(areaView);

		dropMenuWidget.start(context, Arrays.asList(headers), contentViewList);
		dropMenuWidget.attach(linearLayout);
	}

	public void initRecycleView(Context context, RecyclerView recycleView)
	{
		recycleView.setLayoutManager(new LinearLayoutManager(context));
		recycleView.addItemDecoration(new DefaultLinearItemDecoration(context, LinearLayoutManager.VERTICAL));
		taskRecycleAdapter = new TaskRecycleAdapter();
		
		recycleView.setAdapter(taskRecycleAdapter);
	}

	public void setOnRecycleClickListener(OnTaskClickListener onTaskClickListener)
	{
		this.onRecycleClickListener = onTaskClickListener;
	}

	public void setRecycleData()
	{
		List list = new ArrayList();
		for (int i = 0; i < 20; i++)
		{
			list.add("i");
		}
		taskRecycleAdapter.setDataList(list);
	}

	private View initClassifyView(Context context)
	{
		View areaView = LayoutInflater.from(context).inflate(R.layout.fragment_main_task__menu_classify, null);
		
		return areaView;
	}

	private View initFilterView(Context context)
	{
		View filterView = LayoutInflater.from(context).inflate(R.layout.fragment_main_task__menu_filter, null);

		return filterView;
	}

	private class TaskRecycleAdapter extends HeadFootRecycleAdapter
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_task;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, final int position)
		{
			// 设置点击时间
			viewHolder.get(R.id.ll_main_task_container).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onRecycleClickListener)
					{
						onRecycleClickListener.onTaskClick(position);
					}
				}
			});
		}
	}

	public interface OnTaskClickListener
	{
		/**
		 * Recycle item 被点击时，相应
		 *
		 * @param position
		 */
		void onTaskClick(int position);
	}
}
