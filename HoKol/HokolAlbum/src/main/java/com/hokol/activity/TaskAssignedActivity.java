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
import com.hokol.fragment.TaskAssignedAllFragment;
import com.hokol.fragment.TaskAssignedEvaluateFragment;
import com.hokol.fragment.TaskAssignedSignFragment;
import com.hokol.fragment.TaskAssignedTradeFragment;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 已发布任务
 *
 * @author yline 2017/4/1 -- 17:54
 * @version 1.0.0
 */
public class TaskAssignedActivity extends BaseAppCompatActivity implements TaskAssignedAdapter.OnTaskAssignedRefreshCallback
{
	private static final String KeyTaskAssigned = "KeyTaskAssigned";

	private TaskAssignedAllFragment taskAssignedAllFragment;

	private TaskAssignedSignFragment taskAssignedSignFragment;

	private TaskAssignedTradeFragment taskAssignedTradeFragment;

	private TaskAssignedEvaluateFragment taskAssignedEvaluateFragment;

	private String userId;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, TaskAssignedActivity.class).putExtra(KeyTaskAssigned, userId));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned);

		// 用户ID
		userId = getIntent().getStringExtra(KeyTaskAssigned);
		initTabView();

		findViewById(R.id.iv_task_assigned_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private void initTabView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		taskAssignedAllFragment = TaskAssignedAllFragment.newInstance(userId);
		fragmentList.add(taskAssignedAllFragment);
		titleList.add("全部");

		taskAssignedSignFragment = TaskAssignedSignFragment.newInstance(userId);
		fragmentList.add(taskAssignedSignFragment);
		titleList.add("待报名");

		taskAssignedTradeFragment = TaskAssignedTradeFragment.newInstance(userId);
		fragmentList.add(taskAssignedTradeFragment);
		titleList.add("待交易");

		taskAssignedEvaluateFragment = TaskAssignedEvaluateFragment.newInstance(userId);
		fragmentList.add(taskAssignedEvaluateFragment);
		titleList.add("待评价");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_task_assigned);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_task_assigned);
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
			taskAssignedAllFragment.onRefreshData(userId, start, length);
		}
	}

	@Override
	public void onSignRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(userId))
		{
			taskAssignedSignFragment.onRefreshData(userId, start, length);
		}
	}

	@Override
	public void onTradeRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(userId))
		{
			taskAssignedTradeFragment.onRefreshData(userId, start, length);
		}
	}

	@Override
	public void onEvaluateRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(userId))
		{
			taskAssignedEvaluateFragment.onRefreshData(userId, start, length);
		}
	}
}
