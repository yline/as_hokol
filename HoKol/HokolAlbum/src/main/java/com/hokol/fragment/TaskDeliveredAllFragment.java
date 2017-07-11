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
import com.hokol.adapter.TaskAssignedAdapter;
import com.hokol.adapter.TaskDeliveredAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserDeliveredBean;
import com.hokol.medium.http.bean.WTaskActionMasterTradeBean;
import com.hokol.medium.http.bean.WTaskActionStaffConfirmBean;
import com.hokol.medium.http.bean.WTaskActionStaffTradeBean;
import com.hokol.medium.http.bean.WTaskDeleteBean;
import com.hokol.medium.http.bean.WTaskUserDeliveredBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class TaskDeliveredAllFragment extends BaseFragment implements TaskAssignedAdapter.OnTaskAssignedRefreshListener
{
	private static final String KeyUserId = "UserId";

	private TaskDeliveredAdapter deliveredAllAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private WTaskUserDeliveredBean deliveredAllBean;

	private String userId;

	public static TaskDeliveredAllFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyUserId, userId);
		TaskDeliveredAllFragment fragment = new TaskDeliveredAllFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_delivered_all, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_task_delivered_all);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}
		});

		deliveredAllAdapter = new TaskDeliveredAdapter(getContext());
		deliveredAllAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserDeliveredBean.VTaskUserDeliveredOneBean deliveredOneBean, int position)
			{
				TaskDetailActivity.actionStart(getContext(), deliveredOneBean.getTask_id(), false);
			}
		});
		deliveredAllAdapter.setOnDeliveredSignCallback(new TaskDeliveredAdapter.OnTaskDeliveredSignCallback()
		{
			@Override
			public void onSignCancelClick(View view, String taskId)
			{
				XHttpUtil.doTaskActionStaffConfirm(new WTaskActionStaffConfirmBean(userId, taskId, WTaskActionStaffConfirmBean.ActionRefuse), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("取消接单成功");
					}
				});
			}

			@Override
			public void onSignConfirmClick(View view, String taskId)
			{
				XHttpUtil.doTaskActionStaffConfirm(new WTaskActionStaffConfirmBean(userId, taskId, WTaskActionStaffConfirmBean.ActionAccept), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("确认接单成功");
					}
				});
			}
		});
		deliveredAllAdapter.setOnDeliveredTradeCallback(new TaskDeliveredAdapter.OnTaskDeliveredTradeCallback()
		{
			@Override
			public void onTradeFailedClick(View view, String taskId)
			{
				XHttpUtil.doTaskActionStaffTrade(new WTaskActionStaffTradeBean(userId, taskId, WTaskActionMasterTradeBean.ActionFailed), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("任务未完成");
					}
				});
			}

			@Override
			public void onTradeFinishedClick(View view, String taskId)
			{
				XHttpUtil.doTaskActionStaffTrade(new WTaskActionStaffTradeBean(userId, taskId, WTaskActionMasterTradeBean.ActionFinished), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("任务完成");
					}
				});
			}
		});
		deliveredAllAdapter.setOnDeliveredEvaluateCallback(new TaskDeliveredAdapter.OnTaskDeliveredEvaluateCallback()
		{
			@Override
			public void onEvaluateDeleteClick(View view, String taskId)
			{
				XHttpUtil.doTaskDelete(new WTaskDeleteBean(userId, taskId, WTaskDeleteBean.TypeStaff), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("删除任务");
					}
				});
			}

			@Override
			public void onEvaluateAppealClick(View view, String taskId)
			{
				// 界面上删除了
				// SDKManager.toast("维权申诉");
			}

			@Override
			public void onEvaluateClick(View view, VTaskUserDeliveredBean.VTaskUserDeliveredOneBean bean)
			{
				TaskDeliveredEvaluateActivity.actionStart(getContext(), bean.getTask_id(), bean.getTask_user_id(), bean.getUser_logo(), bean.getUser_nickname());
			}
		});
		recyclerView.setAdapter(deliveredAllAdapter);

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_task_delivered_all);
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
		userId = getArguments().getString(KeyUserId);
		if (!TextUtils.isEmpty(userId))
		{
			onRefreshData(userId, 0, DeleteConstant.defaultNumberSuper);
		}
	}

	@Override
	public void onRefreshData(String userId, int start, int length)
	{
		deliveredAllAdapter.setShowEmpty(false);
		deliveredAllBean = new WTaskUserDeliveredBean(userId, WTaskUserDeliveredBean.TypeAll, start, length);
		XHttpUtil.doTaskUserDelivered(deliveredAllBean, new XHttpAdapter<VTaskUserDeliveredBean>()
		{
			@Override
			public void onSuccess(VTaskUserDeliveredBean vTaskUserDeliveredBean)
			{
				deliveredAllAdapter.setShowEmpty(true);
				List<VTaskUserDeliveredBean.VTaskUserDeliveredOneBean> resultList = vTaskUserDeliveredBean.getList();
				if (null != resultList)
				{
					deliveredAllAdapter.setDataList(resultList);
				}
			}
		});
	}
}
