package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.yline.base.BaseFragment;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskAssignedSignFragment extends BaseFragment
{
	private SuperSwipeRefreshLayout superRefreshLayout;

	private TaskAssignedSignAdapter taskAssignedSignAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_assigned_sign, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		// 内容
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_task_assigned_sign);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_recycler_divider_gray_light_normal;
			}
		});

		taskAssignedSignAdapter = new TaskAssignedSignAdapter();
		recyclerView.setAdapter(taskAssignedSignAdapter);

		List<String> data = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			data.add("" + i);
		}
		taskAssignedSignAdapter.setDataList(data);

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_task_assigned_sign);
		superRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("刷新结束");
						superRefreshLayout.setRefreshing(false);
					}
				}, 2000);
			}
		});
		superRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("刷新结束");
						superRefreshLayout.setLoadMore(false);
					}
				}, 2000);
			}
		});
	}

	private class TaskAssignedSignAdapter extends CommonRecyclerAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			FlowLayout flowLayout = viewHolder.get(R.id.flow_task_assigned);
			FlowWidget labelWidget = new FlowWidget(getContext(), flowLayout);
			labelWidget.setDataList(Arrays.asList("报名详情", "结束报名", "取消交易"));
		}
	}
}
