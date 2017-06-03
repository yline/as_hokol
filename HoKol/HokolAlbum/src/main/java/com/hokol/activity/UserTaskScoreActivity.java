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
import com.hokol.fragment.UserTaskScoreAssignedFragment;
import com.hokol.fragment.UserTaskScoreDeliveredFragment;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;
import com.yline.view.common.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的评分
 *
 * @author yline 2017/6/3 -- 13:44
 * @version 1.0.0
 */
public class UserTaskScoreActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_task_score);

		viewHolder = new ViewHolder(this);
		initView();
	}

	private void initView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(UserTaskScoreAssignedFragment.newInstance());
		titleList.add("已发任务");

		fragmentList.add(UserTaskScoreDeliveredFragment.newInstance());
		titleList.add("已投任务");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_user_task_score);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_user_task_score);

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
		tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.hokolRed));
		tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.hokolGrayDrak), ContextCompat.getColor(this, R.color.hokolRed));

		viewHolder.setOnClickListener(R.id.iv_task_score_cancel, new View.OnClickListener()
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
		context.startActivity(new Intent(context, UserTaskScoreActivity.class));
	}
}
