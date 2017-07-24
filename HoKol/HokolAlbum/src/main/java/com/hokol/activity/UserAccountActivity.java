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
import com.hokol.application.AppStateManager;
import com.hokol.fragment.UserAccountReceiveGiftFragment;
import com.hokol.fragment.UserAccountSendGiftFragment;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class UserAccountActivity extends BaseAppCompatActivity
{
	private static final String KeyAccountUserId = "AccountUserId";

	private ViewHolder viewHolder;

	private String userId;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserAccountActivity.class).putExtra(KeyAccountUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account);

		viewHolder = new ViewHolder(this);
		initView();
		initTabView();
	}

	private void initView()
	{
		viewHolder.setOnClickListener(R.id.iv_user_account_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		viewHolder.setOnClickListener(R.id.tv_user_account_record, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserRechargeActivity.actionStart(UserAccountActivity.this, userId);
			}
		});

		float coinNum = AppStateManager.getInstance().getUserCoinNum(this);
		viewHolder.setText(R.id.tv_user_account_value, String.format("%3.2f", coinNum));
	}
	
	private void initTabView()
	{
		userId = getIntent().getStringExtra(KeyAccountUserId);

		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(UserAccountReceiveGiftFragment.newInstance(userId));
		titleList.add("收到礼物");

		fragmentList.add(UserAccountSendGiftFragment.newInstance(userId));
		titleList.add("送出礼物");
		/*
		fragmentList.add(UserAccountProfitFragment.newInstance());
		titleList.add("收益提现");
		*/
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_user_account);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_user_account);

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
		tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.hokolGrayDrak), ContextCompat.getColor(this, R.color.hokolRed));
		tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.hokolRed));
	}
}
