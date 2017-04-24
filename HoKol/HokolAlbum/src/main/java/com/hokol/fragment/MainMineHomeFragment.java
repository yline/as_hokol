package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.TaskAssignedActivity;
import com.hokol.activity.TaskDeliveredActivity;
import com.hokol.activity.UserAccountActivity;
import com.hokol.activity.UserCareActivity;
import com.hokol.activity.UserFansActivity;
import com.hokol.activity.UserRechargeActivity;
import com.hokol.activity.UserSettingActivity;
import com.hokol.activity.UserTaskCollectionActivity;
import com.hokol.activity.UserTaskMessageActivity;
import com.hokol.activity.UserVIPActivity;

public class MainMineHomeFragment extends BaseFragment
{
	private ViewHolder homeViewHolder;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_mine_home, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		homeViewHolder = new ViewHolder(view);

		// 任务
		homeViewHolder.get(R.id.ll_main_min_home_task_assigned).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TaskAssignedActivity.actionStart(getContext());
			}
		});

		homeViewHolder.get(R.id.ll_main_min_home_task_delivered).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				TaskDeliveredActivity.actionStart(getContext());
			}
		});

		homeViewHolder.get(R.id.ll_main_min_home_task_collection).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserTaskCollectionActivity.actionStart(getContext());
			}
		});

		homeViewHolder.get(R.id.ll_main_min_home_task_message).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserTaskMessageActivity.actionStart(getContext());
			}
		});

		// 粉丝
		homeViewHolder.get(R.id.ll_main_mine_home_fans).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserFansActivity.actionStart(getContext());
			}
		});

		homeViewHolder.get(R.id.ll_main_mine_home_care).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserCareActivity.actionStart(getContext());
			}
		});

		// 充值
		homeViewHolder.get(R.id.ll_main_min_home_account).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserAccountActivity.actionStart(getContext());
			}
		});

		homeViewHolder.get(R.id.ll_main_min_home_vip).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserVIPActivity.actionStart(getContext());
			}
		});

		homeViewHolder.get(R.id.ll_main_min_home_recharge).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserRechargeActivity.actionStart(getContext());
			}
		});

		// 设置
		homeViewHolder.get(R.id.ll_main_min_home_setting).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserSettingActivity.actionStart(getContext());
			}
		});


	}
}
