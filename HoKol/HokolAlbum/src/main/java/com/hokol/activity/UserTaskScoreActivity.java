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
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserCreditBean;
import com.hokol.medium.http.bean.WUserCreditBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.holder.ViewHolder;

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
	private static final String KeyUserTaskScoreUserId = "UserTaskScoreUserId";

	private ViewHolder viewHolder;

	private UserTaskScoreAssignedFragment assignedFragment;

	private UserTaskScoreDeliveredFragment deliveredFragment;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserTaskScoreActivity.class).putExtra(KeyUserTaskScoreUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_task_score);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		assignedFragment = UserTaskScoreAssignedFragment.newInstance();
		fragmentList.add(assignedFragment);
		titleList.add("已发任务");

		deliveredFragment = UserTaskScoreDeliveredFragment.newInstance();
		fragmentList.add(deliveredFragment);
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

	private void initData()
	{
		String userId = getIntent().getStringExtra(KeyUserTaskScoreUserId);
		XHttpUtil.doUserCredit(new WUserCreditBean(userId), new HokolAdapter<VUserCreditBean>()
		{
			@Override
			public void onSuccess(VUserCreditBean vUserCreditBean)
			{
				// host
				VUserCreditBean.VUserCreditHostBean hostBean = vUserCreditBean.getScore2();
				assignedFragment.updateHostCredit(hostBean);

				// sub
				VUserCreditBean.VUserCreditSubBean subBean = vUserCreditBean.getScore3();
				deliveredFragment.updateSubCredit(subBean);
			}
		});
	}
}
