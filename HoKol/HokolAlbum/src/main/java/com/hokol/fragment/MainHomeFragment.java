package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends BaseFragment
{
	private List<BaseFragment> fragmentList;

	private ViewPager viewPager;

	private TabLayout tabLayout;

	private static final int[] IDS = new int[]{
			R.string.main_home_title_one,
			R.string.main_home_title_two,
			R.string.main_home_title_three,
			R.string.main_home_title_four,
			R.string.main_home_title_five,
			R.string.main_home_title_six
	};

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_news);
		tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_news);
	}

	private void initData()
	{
		fragmentList = new ArrayList<>();
		fragmentList.add(new MainHomeRedFragment());
		fragmentList.add(new MainHomeAuchorFragment());
		fragmentList.add(new MainHomePerformerFragment());
		fragmentList.add(new MainHomeModelFragment());
		fragmentList.add(new MainHomeSingerFragment());
		fragmentList.add(new MainHomeSportFragment());

		// viewPager.setOffscreenPageLimit(fragmentList.size());
		viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager())
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
				return getResources().getString(IDS[position]);
			}
		});
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{

			}

			@Override
			public void onPageSelected(int position)
			{

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});

		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
	}
}
