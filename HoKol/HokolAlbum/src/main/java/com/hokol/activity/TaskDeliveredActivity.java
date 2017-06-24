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
public class TaskDeliveredActivity extends BaseAppCompatActivity
{
	private static final String KeyTaskDelivered = "KeyUserId";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_delivered);

		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		// 用户ID
		String userId = getIntent().getStringExtra(KeyTaskDelivered);

		fragmentList.add(TaskDeliveredAllFragment.newInstance(userId));
		titleList.add("全部");

		fragmentList.add(TaskDeliveredSignFragment.newInstance(userId));
		titleList.add("已报名");

		fragmentList.add(TaskDeliveredTradeFragment.newInstance(userId));
		titleList.add("已接单");
		
		fragmentList.add(TaskDeliveredEvaluateFragment.newInstance(userId));
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

		findViewById(R.id.iv_task_delivered_cancel).setOnClickListener(new View.OnClickListener()
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
		context.startActivity(new Intent(context, TaskDeliveredActivity.class).putExtra(KeyTaskDelivered, userId));
	}
}
