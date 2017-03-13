package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;
import com.hokol.fragment.StarInfoDatumFragment;
import com.hokol.fragment.StarInfoDynamicFragment;
import com.hokol.fragment.StarInfoPrivateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 明星人物信息详情页面
 *
 * @author yline 2017/3/5 --> 15:24
 * @version 1.0.0
 */
public class StarInfoActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_info);

		initView();
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
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, StarInfoActivity.class));
	}
}
