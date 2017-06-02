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
import com.hokol.fragment.TaskAssignedSignDetailEdFragment;
import com.hokol.fragment.TaskAssignedSignDetailUnFragment;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 报名详情
 *
 * @author yline 2017/6/2 -- 16:37
 * @version 1.0.0
 */
public class TaskAssignedSignDetailActivity extends BaseAppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_sign_detail);

		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(TaskAssignedSignDetailUnFragment.newInstance());
		titleList.add("待录用");

		fragmentList.add(TaskAssignedSignDetailEdFragment.newInstance());
		titleList.add("已录用");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_task_assigned_sign_detail);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_task_assigned_sign_detail);

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

		findViewById(R.id.iv_task_assigned_sign_detail_cancel).setOnClickListener(new View.OnClickListener()
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
		context.startActivity(new Intent(context, TaskAssignedSignDetailActivity.class));
	}
}
