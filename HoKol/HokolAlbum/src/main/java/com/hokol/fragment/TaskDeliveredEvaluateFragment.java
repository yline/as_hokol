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
import com.hokol.activity.TaskDeliveredEvaluateActivity;
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

public class TaskDeliveredEvaluateFragment extends BaseFragment
{
	private static final String KeyUserId = "TaskId";

	private TaskDeliveredAdapter deliveredEvaluateAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private WTaskUserDeliveredBean deliveredEvaluateBean;

	public static TaskDeliveredEvaluateFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyUserId, userId);
		TaskDeliveredEvaluateFragment fragment = new TaskDeliveredEvaluateFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_delivered_evaluate, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_task_delivered_evaluate);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}
		});

		deliveredEvaluateAdapter = new TaskDeliveredAdapter();
		deliveredEvaluateAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserDeliveredBean.VTaskUserDeliveredOneBean deliveredOneBean, int position)
			{
				TaskDetailActivity.actionStart(getContext(), deliveredOneBean.getTask_id(), false);
			}
		});
		deliveredEvaluateAdapter.setOnDeliveredEvaluateCallback(new TaskDeliveredAdapter.OnTaskDeliveredEvaluateCallback()
		{
			@Override
			public void onEvaluateDeleteClick(View view)
			{
				SDKManager.toast("删除任务");
			}

			@Override
			public void onEvaluateAppealClick(View view)
			{
				SDKManager.toast("维权申诉");
			}

			@Override
			public void onEvaluateClick(View view)
			{
				TaskDeliveredEvaluateActivity.actionStart(getContext());
			}
		});
		recyclerView.setAdapter(deliveredEvaluateAdapter);

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_task_delivered_evaluate);
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
			deliveredEvaluateAdapter.setShowEmpty(false);
			deliveredEvaluateBean = new WTaskUserDeliveredBean(userId, WTaskUserDeliveredBean.TypeEvaluate, 0, DeleteConstant.defaultNumberSuper);
			XHttpUtil.doTaskUserDelivered(deliveredEvaluateBean, new XHttpAdapter<VTaskUserDeliveredBean>()
			{
				@Override
				public void onSuccess(VTaskUserDeliveredBean vTaskUserDeliveredBean)
				{
					deliveredEvaluateAdapter.setShowEmpty(true);
					List<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean> resultList = vTaskUserDeliveredBean.getList();
					if (null != resultList)
					{
						deliveredEvaluateAdapter.setDataList(resultList);
					}
				}
			});
		}
	}
}
