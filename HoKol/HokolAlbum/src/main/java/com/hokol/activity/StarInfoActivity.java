package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.fragment.StarInfoDatumFragment;
import com.hokol.fragment.StarInfoDynamicFragment;
import com.hokol.fragment.StarInfoPrivateFragment;
import com.hokol.medium.widget.HokolGiftWidget;
import com.hokol.viewhelper.StarInfoHelper;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;
import com.yline.view.common.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 明星人物信息详情页面
 *
 * @author yline 2017/3/5 --> 15:24
 * @version 1.0.0
 */
public class StarInfoActivity extends BaseAppCompatActivity
{
	private StarInfoHelper starInfoHelper;

	private HokolGiftWidget hokolGiftWidget;

	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_info);

		viewHolder = new ViewHolder(this);
		starInfoHelper = new StarInfoHelper(this, viewHolder);
		hokolGiftWidget = new HokolGiftWidget(this);
		hokolGiftWidget.setDataList(Arrays.asList(1, 10, 66, 128, 288, 520, 666, 999, 1314, 6666, 9999, 10888));
		hokolGiftWidget.setOnSendClickListener(new HokolGiftWidget.OnSendClickListener()
		{
			@Override
			public void onSendClick(View v, int position)
			{
				SDKManager.toast("发送");
			}
		});
		hokolGiftWidget.setOnRechargeClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("充值");
			}
		});

		starInfoHelper.initHeadView();
		starInfoHelper.setHeadViewListener(new StarInfoHelper.OnHeadViewClickListener()
		{
			@Override
			public void onCareOrCancel()
			{
				IApplication.toast("点击关注");
			}

			@Override
			public void onContact()
			{
				IApplication.toast("点击联系");
			}

			@Override
			public void onGiveGift()
			{
				hokolGiftWidget.showAtLocation(viewHolder.get(R.id.tab_layout_start_info), Gravity.BOTTOM, 0, 0);

				IApplication.toast("点击送红豆");
			}
		});
		// 信用
		viewHolder.setOnClickListener(R.id.iv_star_info_credit, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserInfoCreditActivity.actionStart(StarInfoActivity.this);
			}
		});

		initView();
		starInfoHelper.initHeadData();
	}

	private void initView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(StarInfoDynamicFragment.newInstance());
		titleList.add("她的动态");
		
		fragmentList.add(StarInfoPrivateFragment.newInstance());
		titleList.add("私密空间");

		fragmentList.add(StarInfoDatumFragment.newInstance());
		titleList.add("个人资料");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_start_info);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_start_info);

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
		tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_red_light));
		tabLayout.setTabTextColors(getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_light));
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, StarInfoActivity.class));
	}
}
