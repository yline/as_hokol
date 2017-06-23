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
import com.hokol.activity.TaskAssignedEvaluateActivity;
import com.hokol.activity.TaskAssignedSignDetailActivity;
import com.hokol.activity.TaskAssignedTradeDetailActivity;
import com.hokol.activity.TaskAssignedTradeSureDetailActivity;
import com.hokol.adapter.TaskAssignedAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.http.bean.WTaskUserPublishedBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;

import java.util.List;

public class TaskAssignedAllFragment extends BaseFragment
{
	private static final String KeyUserId = "AllUserId";

	private SuperSwipeRefreshLayout superRefreshLayout;

	private TaskAssignedAdapter taskAssignedAllAdapter;

	private WTaskUserPublishedBean userPublishedBean;

	public static TaskAssignedAllFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyUserId, userId);
		TaskAssignedAllFragment fragment = new TaskAssignedAllFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_assigned_all, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_task_assigned_all);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}
		});

		taskAssignedAllAdapter = new TaskAssignedAdapter(getContext());
		taskAssignedAllAdapter.setOnAssignedSignCallback(new TaskAssignedAdapter.OnTaskAssignedSignCallback()
		{
			@Override
			public void onSignCancelClick(View view)
			{
				SDKManager.toast("取消任务");
			}

			@Override
			public void onSignFinishClick(View view)
			{
				SDKManager.toast("结束报名");
			}

			@Override
			public void onSignDetailClick(View view)
			{
				TaskAssignedSignDetailActivity.actionStart(getContext());
			}
		});
		taskAssignedAllAdapter.setOnAssignedTradeCallback(new TaskAssignedAdapter.OnTaskAssignedTradeCallback()
		{
			@Override
			public void onTradeCancelClick(View view)
			{
				SDKManager.toast("取消交易");
			}

			@Override
			public void onTradeDetailClick(View view)
			{
				TaskAssignedTradeDetailActivity.actionStart(getContext());
			}

			@Override
			public void onTradeConfirmClick(View view)
			{
				TaskAssignedTradeSureDetailActivity.actionStart(getContext());
			}
		});
		taskAssignedAllAdapter.setOnAssignedEvaluateCallback(new TaskAssignedAdapter.OnTaskAssignedEvaluateCallback()
		{
			@Override
			public void onEvaluateClick(View view)
			{
				TaskAssignedEvaluateActivity.actionStart(getContext());
			}
		});
		recyclerView.setAdapter(taskAssignedAllAdapter);

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_task_assigned_all);
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
			taskAssignedAllAdapter.setShowEmpty(false);
			userPublishedBean = new WTaskUserPublishedBean(userId, 0, DeleteConstant.defaultNumberSuper);
			XHttpUtil.doTaskUserPublishedAll(userPublishedBean, new XHttpAdapter<VTaskUserPublishedBean>()
			{
				@Override
				public void onSuccess(VTaskUserPublishedBean vTaskUserPublishedBean)
				{
					taskAssignedAllAdapter.setShowEmpty(true);
					List<VTaskUserPublishedBean.VTaskUserPublishedOneBean> result = vTaskUserPublishedBean.getList();
					if (null != result)
					{
						taskAssignedAllAdapter.setDataList(result);
					}
				}
			});
		}
	}
}
