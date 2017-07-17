package com.hokol.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.activity.TaskAssignedTradeDetailActivity;
import com.hokol.activity.TaskAssignedTradeSureDetailActivity;
import com.hokol.activity.TaskDetailActivity;
import com.hokol.adapter.TaskAssignedAdapter;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.http.bean.WTaskActionMasterFinishBean;
import com.hokol.medium.http.bean.WTaskUserPublishedBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.DialogIosWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class TaskAssignedTradeFragment extends BaseFragment implements TaskAssignedAdapter.OnTaskAssignedRefreshListener
{
	private static final String KeyUserId = "TradeUserId";

	private SuperSwipeRefreshLayout superRefreshLayout;

	private TaskAssignedAdapter taskAssignedTradeAdapter;

	private WTaskUserPublishedBean userPublishedBean;

	private String userId;

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

		userId = getArguments().getString(KeyUserId);
		initView(view);
		initViewClick();
	}

	@Override
	public void onStart()
	{
		super.onStart();
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

			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}
		});

		taskAssignedTradeAdapter = new TaskAssignedAdapter(getContext());
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

	private void initViewClick()
	{
		taskAssignedTradeAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserPublishedBean.VTaskUserPublishedOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserPublishedBean.VTaskUserPublishedOneBean taskAssignedBean, int position)
			{
				TaskDetailActivity.actionStart(getContext(), taskAssignedBean.getTask_id(), true);
			}
		});
		taskAssignedTradeAdapter.setOnAssignedTradeCallback(new TaskAssignedAdapter.OnTaskAssignedTradeCallback()
		{
			@Override
			public void onTradeCancelClick(View view, final String taskId, boolean hasEmploy)
			{
				if (!hasEmploy)
				{
					XHttpUtil.doTaskActionMasterFinish(new WTaskActionMasterFinishBean(userId, taskId), new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("取消任务成功");
							if (getActivity() instanceof TaskAssignedAdapter.OnTaskAssignedRefreshCallback)
							{
								((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onAllRefresh(0, DeleteConstant.defaultNumberSuper);
								((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onTradeRefresh(0, DeleteConstant.defaultNumberSuper);
							}
						}
					});
				}
				else
				{
					DialogIosWidget widgetDialogCenter = new DialogIosWidget(getContext())
					{
						@Override
						protected void initXView(TextView tvTitle, TextView tvMsg, Button btnNegative, Button btnPositive, Dialog dialog)
						{
							super.initXView(tvTitle, tvMsg, btnNegative, btnPositive, dialog);

							tvTitle.setText("已录用人员，若取消任务\n" + "将扣除5%的违约金");
							btnNegative.setText("再看看");
							btnPositive.setText("确认取消");
						}
					};
					widgetDialogCenter.setOnPositiveListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							XHttpUtil.doTaskActionMasterFinish(new WTaskActionMasterFinishBean(userId, taskId), new XHttpAdapter<String>()
							{
								@Override
								public void onSuccess(String s)
								{
									SDKManager.toast("取消任务成功");
									if (getActivity() instanceof TaskAssignedAdapter.OnTaskAssignedRefreshCallback)
									{
										((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onAllRefresh(0, DeleteConstant.defaultNumberSuper);
										((TaskAssignedAdapter.OnTaskAssignedRefreshCallback) getActivity()).onTradeRefresh(0, DeleteConstant.defaultNumberSuper);
									}
								}
							});
						}
					});
					widgetDialogCenter.show();
				}
			}

			@Override
			public void onTradeDetailClick(View view, String taskId)
			{
				TaskAssignedTradeDetailActivity.actionStart(getContext(), taskId);
			}

			@Override
			public void onTradeConfirmClick(View view, String taskId)
			{
				TaskAssignedTradeSureDetailActivity.actionStart(getContext(), taskId);
			}
		});
	}

	private void initData()
	{
		if (!TextUtils.isEmpty(userId))
		{
			onRefreshData(userId, 0, DeleteConstant.defaultNumberSuper);
		}
	}

	@Override
	public void onRefreshData(String userId, int start, int length)
	{
		userPublishedBean = new WTaskUserPublishedBean(userId, start, length);
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
