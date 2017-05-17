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
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.widget.secondary.SecondaryWidget;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		mainHomeHelper = new MainHomeHelper(getContext());

		initView(view);

		// 下拉菜单栏
		mainHomeHelper.initSecondaryView(new SecondaryWidget.OnSecondaryCallback()
		{
			@Override
			public void onSecondarySelected(String first, List<String> second, String title)
			{
				mainHomeHelper.updateProvinceTitle(title);
				mainHomeHelper.closeMenu();
			}
		});
		mainHomeHelper.initFilterView(new MainHomeHelper.OnMenuFilterCallback()
		{
			@Override
			public void onEnumFilterCommit(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
			{
				IApplication.toast("typeSex = " + typeSex + ",typeRecommend = " + typeRecommend);
				mainHomeHelper.closeMenu();
			}
		});
		TabLayout menuTabLayout = (TabLayout) view.findViewById(R.id.tab_main_home_menu);
		mainHomeHelper.initTabDownMenuView(menuTabLayout);

		initData();
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

		// 主页 tab栏目
		TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_home);
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_home);
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabTextColors(getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_light));
		tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_red_light));

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
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab)
			{

			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab)
			{

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab)
			{

			}
		});
	}

	private void initData()
	{
		XHttpUtil.doAreaAll(new XHttpAdapter<VAreaAllBean>()
		{
			@Override
			public void onSuccess(VAreaAllBean vAreaAllBean)
			{
				Map provinceMap = vAreaAllBean.getList();
				mainHomeHelper.setProvinceData(provinceMap);
			}
		});
	}
}
