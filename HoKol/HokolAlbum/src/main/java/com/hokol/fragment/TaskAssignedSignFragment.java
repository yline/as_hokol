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
import com.hokol.activity.TaskAssignedSignDetailActivity;
import com.hokol.activity.TaskDetailActivity;
import com.hokol.adapter.TaskAssignedAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.http.bean.WTaskActionMasterCancelBean;
import com.hokol.medium.http.bean.WTaskActionMasterFinishBean;
import com.hokol.medium.http.bean.WTaskUserPublishedBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class TaskAssignedSignFragment extends BaseFragment implements TaskAssignedAdapter.OnTaskAssignedRefreshListener
{
	private static final String KeyUserId = "SignUserId";

	private SuperSwipeRefreshLayout superRefreshLayout;

	private TaskAssignedAdapter taskAssignedSignAdapter;

	private WTaskUserPublishedBean userPublishedBean;

	private String userId;

	public static TaskAssignedSignFragment newInstance(String userId)
	{
		Bundle args = new Bundle();
		args.putString(KeyUserId, userId);
		TaskAssignedSignFragment fragment = new TaskAssignedSignFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_assigned_sign, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_task_assigned_sign);
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

		taskAssignedSignAdapter = new TaskAssignedAdapter(getContext());
		recyclerView.setAdapter(taskAssignedSignAdapter);

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

	private void initViewClick()
	{
		taskAssignedSignAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserPublishedBean.VTaskUserPublishedOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserPublishedBean.VTaskUserPublishedOneBean taskAssignedBean, int position)
			{
				TaskDetailActivity.actionStart(getContext(), taskAssignedBean.getTask_id(), true);
			}
		});
		// 待报名
		taskAssignedSignAdapter.setOnAssignedSignCallback(new TaskAssignedAdapter.OnTaskAssignedSignCallback()
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
							((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onTradeRefresh(0, DeleteConstant.defaultNumberSuper);
						}
					}
				});
			}

			@Override
			public void onSignDetailClick(View view, String taskId, boolean isGuarantee)
			{
				TaskAssignedSignDetailActivity.actionStart(getContext(), taskId, isGuarantee);
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
		userPublishedBean = new WTaskUserPublishedBean(userId, start, length);
		XHttpUtil.doTaskUserPublishedSign(userPublishedBean, new XHttpAdapter<VTaskUserPublishedBean>()
		{
			@Override
			public void onSuccess(VTaskUserPublishedBean vTaskUserPublishedBean)
			{
				List<VTaskUserPublishedBean.VTaskUserPublishedOneBean> result = vTaskUserPublishedBean.getList();
				if (null != result)
				{
					taskAssignedSignAdapter.setDataList(result);
				}
			}
		});
	}
}
