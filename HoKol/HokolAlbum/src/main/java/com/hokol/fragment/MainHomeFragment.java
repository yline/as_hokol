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
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;
import com.hokol.viewhelper.MainHomeHelper;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends BaseFragment
{
	private MainHomeHelper mainHomeHelper;

	private List<BaseFragment> fragmentList;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		mainHomeHelper = new MainHomeHelper();

		// 主页 tab栏目
		TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_home);
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_home);
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabTextColors(getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_light));
		tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_red_light));

		// 下拉菜单栏
		LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_main_home_menu);
		mainHomeHelper.initTabDownMenuView(getContext(), linearLayout);
		mainHomeHelper.setProvinceData();

		initView(view);

		final String[] RES_MAIN_HOME_TAB = view.getResources().getStringArray(R.array.main_home_tab);
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
				return RES_MAIN_HOME_TAB[position];
			}
		});
	}
	
	private void initView(View view)
	{
		fragmentList = new ArrayList<>();
		fragmentList.add(new MainHomeRedFragment());
		fragmentList.add(new MainHomeAuthorFragment());
		fragmentList.add(new MainHomePerformerFragment());
		fragmentList.add(new MainHomeModelFragment());
		fragmentList.add(new MainHomeSingerFragment());
		fragmentList.add(new MainHomeSportFragment());
	}
}
