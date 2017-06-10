package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.TaskAssignedTradeDetailActivity;
import com.hokol.activity.TaskAssignedTradeSureDetailActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.http.bean.WTaskUserPublishedBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.List;

public class TaskAssignedTradeFragment extends BaseFragment
{
	private static final String KeyUserId = "TradeUserId";

	private SuperSwipeRefreshLayout superRefreshLayout;

	private TaskAssignedTradeAdapter taskAssignedTradeAdapter;

	private WTaskUserPublishedBean userPublishedBean;

	public static TaskAssignedTradeFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyUserId, userId);
		TaskAssignedTradeFragment fragment = new TaskAssignedTradeFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_assigned_trade, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		// 内容
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_task_assigned_trade);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}
		});

		taskAssignedTradeAdapter = new TaskAssignedTradeAdapter();
		recyclerView.setAdapter(taskAssignedTradeAdapter);

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_task_assigned_trade);
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

	private void initData()
	{
		String userId = getArguments().getString(KeyUserId);
		if (!TextUtils.isEmpty(userId))
		{
			userPublishedBean = new WTaskUserPublishedBean(userId, 0, DeleteConstant.defaultNumberSuper);
			XHttpUtil.doTaskUserPublishedTrade(userPublishedBean, new XHttpAdapter<VTaskUserPublishedBean>()
			{
				@Override
				public void onSuccess(VTaskUserPublishedBean vTaskUserPublishedBean)
				{
					List<VTaskUserPublishedBean.VTaskUserPublishedOneBean> result = vTaskUserPublishedBean.getList();
					if (null != result)
					{
						taskAssignedTradeAdapter.setDataList(result);
					}
				}
			});
		}
	}

	private class TaskAssignedTradeAdapter extends CommonRecyclerAdapter<VTaskUserPublishedBean.VTaskUserPublishedOneBean>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			viewHolder.get(R.id.ll_task_assigned_start).setVisibility(View.INVISIBLE);
			viewHolder.get(R.id.ll_task_assigned_trade).setVisibility(View.VISIBLE);
			viewHolder.get(R.id.ll_task_assigned_finish).setVisibility(View.INVISIBLE);

			viewHolder.setOnClickListener(R.id.tv_task_assigned_trade_detail, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					TaskAssignedTradeDetailActivity.actionStart(getContext());
				}
			});

			viewHolder.setOnClickListener(R.id.tv_task_assigned_trade_sure_detail, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					TaskAssignedTradeSureDetailActivity.actionStart(getContext());
				}
			});
		}
	}
}
