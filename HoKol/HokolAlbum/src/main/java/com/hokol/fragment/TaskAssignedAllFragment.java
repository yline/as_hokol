package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.TaskAssignedEvaluateActivity;
import com.hokol.activity.TaskAssignedSignDetailActivity;
import com.hokol.activity.TaskAssignedTradeDetailActivity;
import com.hokol.activity.TaskAssignedTradeSureDetailActivity;
import com.hokol.activity.TaskDetailActivity;
import com.hokol.adapter.TaskAssignedAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.callback.OnRecyclerDeleteCallback;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.http.bean.WTaskActionMasterCancelBean;
import com.hokol.medium.http.bean.WTaskActionMasterFinishBean;
import com.hokol.medium.http.bean.WTaskDeleteBean;
import com.hokol.medium.http.bean.WTaskUserPublishedBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class TaskAssignedAllFragment extends BaseFragment implements TaskAssignedAdapter.OnTaskAssignedRefreshListener
{
	private static final String KeyUserId = "AllUserId";

	private SuperSwipeRefreshLayout superRefreshLayout;

	private TaskAssignedAdapter taskAssignedAllAdapter;

	private WTaskUserPublishedBean userPublishedBean;

	private String userId;

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
		initViewClick();
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

			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}
		});

		taskAssignedAllAdapter = new TaskAssignedAdapter(getContext());
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

	private void initViewClick()
	{
		taskAssignedAllAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserPublishedBean.VTaskUserPublishedOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserPublishedBean.VTaskUserPublishedOneBean taskAssignedBean, int position)
			{
				TaskDetailActivity.actionStart(getContext(), taskAssignedBean.getTask_id(), true);
			}
		});
		taskAssignedAllAdapter.setOnAssignedSignCallback(new TaskAssignedAdapter.OnTaskAssignedSignCallback()
		{
			@Override
			public void onSignCancelClick(View view, String taskId)
			{
				XHttpUtil.doTaskActionMasterCancel(new WTaskActionMasterCancelBean(userId, taskId), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						if (getActivity() instanceof TaskAssignedAdapter.OnTaskAssignedRefreshCallback)
						{
							((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onAllRefresh(0, DeleteConstant.defaultNumberSuper);
							((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onSignRefresh(0, DeleteConstant.defaultNumberSuper);
						}
					}
				});
			}

			@Override
			public void onSignFinishClick(View view, String taskId)
			{
				XHttpUtil.doTaskActionMasterFinish(new WTaskActionMasterFinishBean(userId, taskId), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						if (getActivity() instanceof TaskAssignedAdapter.OnTaskAssignedRefreshCallback)
						{
							((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onAllRefresh(0, DeleteConstant.defaultNumberSuper);
							((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onSignRefresh(0, DeleteConstant.defaultNumberSuper);
						}
					}
				});
			}

			@Override
			public void onSignDetailClick(View view, String taskId)
			{
				TaskAssignedSignDetailActivity.actionStart(getContext(), taskId);
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
		// 删除任务
		taskAssignedAllAdapter.setOnRecyclerDeleteCallback(new OnRecyclerDeleteCallback<VTaskUserPublishedBean.VTaskUserPublishedOneBean>()
		{
			@Override
			public void onDelete(RecyclerViewHolder viewHolder, VTaskUserPublishedBean.VTaskUserPublishedOneBean vTaskBean, final int position)
			{
				XHttpUtil.doTaskDelete(new WTaskDeleteBean(userId, vTaskBean.getTask_id(), WTaskDeleteBean.TypeStaff), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						taskAssignedAllAdapter.remove(position);
						taskAssignedAllAdapter.notifyDataSetChanged(); // 更新数据，因为设置了 final int position的点击事件
					}
				});
			}
		});
	}

	private void initData()
	{
		userId = getArguments().getString(KeyUserId);
		onRefreshData(userId, 0, DeleteConstant.defaultNumberSuper);
	}

	@Override
	public void onRefreshData(String userId, int start, int length)
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
