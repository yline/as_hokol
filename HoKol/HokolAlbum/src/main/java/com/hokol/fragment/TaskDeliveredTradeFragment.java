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
import com.hokol.activity.TaskDetailActivity;
import com.hokol.adapter.TaskDeliveredAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserDeliveredBean;
import com.hokol.medium.http.bean.WTaskUserDeliveredBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class TaskDeliveredTradeFragment extends BaseFragment
{
	private static final String KeyUserId = "TaskId";

	private TaskDeliveredAdapter deliveredTradeAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private WTaskUserDeliveredBean deliveredTradeBean;

	public static TaskDeliveredTradeFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyUserId, userId);
		TaskDeliveredTradeFragment fragment = new TaskDeliveredTradeFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_delivered_trade, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_task_delivered_trade);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}
		});

		deliveredTradeAdapter = new TaskDeliveredAdapter();
		deliveredTradeAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserDeliveredBean.VTaskUserDeliveredOneBean deliveredOneBean, int position)
			{
				TaskDetailActivity.actionStart(getContext(), deliveredOneBean.getTask_id(), false);
			}
		});
		deliveredTradeAdapter.setOnDeliveredTradeCallback(new TaskDeliveredAdapter.OnTaskDeliveredTradeCallback()
		{
			@Override
			public void onTradeFailedClick(View view)
			{
				SDKManager.toast("任务未完成");
			}

			@Override
			public void onTradeFinishedClick(View view)
			{
				SDKManager.toast("任务完成");
			}
		});
		recyclerView.setAdapter(deliveredTradeAdapter);

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_task_delivered_trade);
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
			deliveredTradeAdapter.setShowEmpty(false);
			deliveredTradeBean = new WTaskUserDeliveredBean(userId, WTaskUserDeliveredBean.TypeTraded, 0, DeleteConstant.defaultNumberSuper);
			XHttpUtil.doTaskUserDelivered(deliveredTradeBean, new XHttpAdapter<VTaskUserDeliveredBean>()
			{
				@Override
				public void onSuccess(VTaskUserDeliveredBean vTaskUserDeliveredBean)
				{
					deliveredTradeAdapter.setShowEmpty(true);
					List<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean> resultList = vTaskUserDeliveredBean.getList();
					if (null != resultList)
					{
						deliveredTradeAdapter.setDataList(resultList);
					}
				}
			});
		}
	}
}
