package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.hokol.R;
import com.hokol.adapter.TaskAssignedAdapter;
import com.hokol.fragment.TaskDeliveredAllFragment;
import com.hokol.fragment.TaskDeliveredEvaluateFragment;
import com.hokol.fragment.TaskDeliveredSignFragment;
import com.hokol.fragment.TaskDeliveredTradeFragment;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 已投递任务
 *
 * @author yline 2017/6/3 -- 15:12
 * @version 1.0.0
 */
public class TaskDeliveredActivity extends BaseAppCompatActivity implements TaskAssignedAdapter.OnTaskAssignedRefreshCallback
{
	private static final String KeyTaskDelivered = "KeyUserId";

	private TaskDeliveredAllFragment taskDeliveredAllFragment;

	private TaskDeliveredSignFragment taskDeliveredSignFragment;

	private TaskDeliveredTradeFragment taskDeliveredTradeFragment;

	private TaskDeliveredEvaluateFragment taskDeliveredEvaluateFragment;

	private String userId;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, TaskDeliveredActivity.class).putExtra(KeyTaskDelivered, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_delivered);

		initView();
		findViewById(R.id.iv_task_delivered_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		initData();
	}

	private void initData()
	{

	}

	private void initView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		// 用户ID
		userId = getIntent().getStringExtra(KeyTaskDelivered);

		taskDeliveredAllFragment = TaskDeliveredAllFragment.newInstance(userId);
		fragmentList.add(taskDeliveredAllFragment);
		titleList.add("全部");

		taskDeliveredSignFragment = TaskDeliveredSignFragment.newInstance(userId);
		fragmentList.add(taskDeliveredSignFragment);
		titleList.add("已报名");

		taskDeliveredTradeFragment = TaskDeliveredTradeFragment.newInstance(userId);
		fragmentList.add(taskDeliveredTradeFragment);
		titleList.add("已接单");

		taskDeliveredEvaluateFragment = TaskDeliveredEvaluateFragment.newInstance(userId);
		fragmentList.add(taskDeliveredEvaluateFragment);
		titleList.add("待评价");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_task_delivered);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_task_delivered);
		viewPager.setOffscreenPageLimit(4);

		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public Fragment getItem(int position)
			{
				return fragmentList.get(position);
			}

			@Override
			public int getCount()
			{
				return fragmentList.size();
			}

			@Override
			public CharSequence getPageTitle(int position)
			{
				return titleList.get(position);
			}
		});
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.hokolGrayHeavy), ContextCompat.getColor(this, R.color.hokolRed));
		tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.hokolRed));
	}

	@Override
	public void onAllRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(userId))
		{
			taskDeliveredAllFragment.onRefreshData(userId, start, length);
		}
	}

	@Override
	public void onSignRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(userId))
		{
			taskDeliveredSignFragment.onRefreshData(userId, start, length);
		}
	}

	@Override
	public void onTradeRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(userId))
		{
			taskDeliveredTradeFragment.onRefreshData(userId, start, length);
		}
	}

	@Override
	public void onEvaluateRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(userId))
		{
			taskDeliveredEvaluateFragment.onRefreshData(userId, start, length);
		}
	}
}
