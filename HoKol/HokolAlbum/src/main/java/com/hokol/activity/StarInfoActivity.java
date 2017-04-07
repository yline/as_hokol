package com.hokol.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.common.BaseFragment;
import com.hokol.fragment.StarInfoDatumFragment;
import com.hokol.fragment.StarInfoDynamicFragment;
import com.hokol.fragment.StarInfoPrivateFragment;
import com.hokol.viewhelper.StarInfoHelper;

import java.util.ArrayList;
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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_info);

		starInfoHelper = new StarInfoHelper(this);

		View headView = findViewById(R.id.include_start_info_head);
		starInfoHelper.initHeadView(headView);
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
				IApplication.toast("点击送红豆");
				View view = LayoutInflater.from(StarInfoActivity.this).inflate(R.layout.activity_star_info_head_gift_dialog, null);

				Dialog dialog = new Dialog(StarInfoActivity.this, R.style.AppDialog_Default);// android.R.style.Theme_Holo_Light_Dialog_NoActionBar
				dialog.setContentView(view);

				Window dialogWindow = dialog.getWindow();
				dialogWindow.setGravity(Gravity.BOTTOM);

				WindowManager.LayoutParams lp = dialogWindow.getAttributes();
				lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
				lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				dialog.onWindowAttributesChanged(lp);

				dialog.show();
			}
		});

		initView();
		starInfoHelper.initHeadData();
	}

	private void initView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(new StarInfoDynamicFragment());
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
