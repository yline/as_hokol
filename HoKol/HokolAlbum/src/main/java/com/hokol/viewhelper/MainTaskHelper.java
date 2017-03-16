package com.hokol.viewhelper;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.HeadFootRecycleAdapter;
import com.hokol.medium.widget.transform.CircleTransform;

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
	private Context context;

	public MainTaskHelper(Context context)
	{
		this.context = context;
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	private String headers[] = {"分类", "金额", "地区"};

	private String provinceList[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

	private DropMenuWidget dropMenuWidget;

	public void initTabDownMenuView(LinearLayout linearLayout)
	{
		dropMenuWidget = new DropMenuWidget();

		List<View> contentViewList = new ArrayList<>();

		View classifyView = initClassifyView();
		contentViewList.add(classifyView);

		View moneyView = initClassifyView();
		contentViewList.add(moneyView);

		View areaView = initFilterView();
		contentViewList.add(areaView);

		View dropMenuView = dropMenuWidget.start(context, Arrays.asList(headers), contentViewList);
		linearLayout.addView(dropMenuView);
	}

	private View initClassifyView()
	{
		View areaView = LayoutInflater.from(context).inflate(R.layout.fragment_main_task__menu_classify, null);

		return areaView;
	}

	private View initFilterView()
	{
		View filterView = LayoutInflater.from(context).inflate(R.layout.fragment_main_task__menu_filter, null);

		return filterView;
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Refresh %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
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
						IApplication.toast("刷新结束");
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% RecyclerView %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	private TaskRecycleAdapter taskRecycleAdapter;

	public void initRecycleView(RecyclerView recycleView)
	{
		recycleView.setLayoutManager(new LinearLayoutManager(context));
		recycleView.addItemDecoration(new DefaultLinearItemDecoration(context));
		taskRecycleAdapter = new TaskRecycleAdapter();
		
		recycleView.setAdapter(taskRecycleAdapter);
	}

	public void setOnRecyclerClickListener(CommonRecyclerAdapter.OnClickListener listener)
	{
		taskRecycleAdapter.setOnClickListener(listener);
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
			ImageView imageView = viewHolder.get(R.id.iv_item_main_task_avatar);
			Glide.with(context).load(DeleteConstant.url_default_avatar).centerCrop()
					.transform(new CircleTransform(context)).placeholder(R.mipmap.ic_launcher)
					.into(imageView);
		}
	}
}
