package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hokol.R;
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
public class TaskAssignedActivity extends BaseAppCompatActivity
{
	private static final String KeyTaskAssigned = "KeyTaskAssigned";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned);

		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		// 用户ID
		String userId = getIntent().getStringExtra(KeyTaskAssigned);

		fragmentList.add(TaskAssignedAllFragment.newInstance(userId));
		titleList.add("全部");
		
		fragmentList.add(TaskAssignedSignFragment.newInstance(userId));
		titleList.add("待报名");

		fragmentList.add(TaskAssignedTradeFragment.newInstance(userId));
		titleList.add("待交易");

		fragmentList.add(TaskAssignedEvaluateFragment.newInstance(userId));
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

		findViewById(R.id.iv_task_assigned_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, TaskAssignedActivity.class).putExtra(KeyTaskAssigned, userId));
	}
}
