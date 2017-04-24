package com.hokol.test;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.hokol.test.common.BaseTestFragment;
import com.yline.base.BaseAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试入口程序
 *
 * @author yline 2017/3/3 --> 15:19
 * @version 1.0.0
 */
public class TestInnerActivity extends BaseAppCompatActivity
{
	private List<BaseTestFragment> fragmentList = new ArrayList<>();

	private HttpFragment httpFragment;

	private WidgetFragment widgetFragment;

	private static final String[] titles = {"Http", "Widget"};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_inner);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_test_inner);
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_test_inner);

		tabLayout.setupWithViewPager(viewPager);

		httpFragment = new HttpFragment();
		widgetFragment = new WidgetFragment();

		fragmentList.add(httpFragment);
		fragmentList.add(widgetFragment);

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
				return titles[position];
			}
		});
	}
}
