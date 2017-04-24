package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.fragment.TaskAssignedAllFragment;
import com.hokol.fragment.TaskAssignedEvaluateFragment;
import com.hokol.fragment.TaskAssignedSignFragment;
import com.hokol.fragment.TaskAssignedTradeFragment;

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
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned);

		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(new TaskAssignedAllFragment());
		titleList.add("全部");

		fragmentList.add(new TaskAssignedSignFragment());
		titleList.add("待报名");

		fragmentList.add(new TaskAssignedTradeFragment());
		titleList.add("待交易");

		fragmentList.add(new TaskAssignedEvaluateFragment());
		titleList.add("待评价");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_task_assigned);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_task_assigned);

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
		tabLayout.setTabTextColors(getResources().getColor(android.R.color.darker_gray), getResources().getColor(android.R.color.holo_red_light));
		tabLayout.setSelectedTabIndicatorHeight(0);

		findViewById(R.id.tv_task_assigned_action_back).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskAssignedActivity.class));
	}
}
