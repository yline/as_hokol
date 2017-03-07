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
import com.hokol.viewhelper.MainHomeHelper;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends BaseFragment
{
	private MainHomeHelper mainHomeHelper;

	private List<BaseFragment> fragmentList;

	// 主页 tab栏目
	private TabLayout tabLayout;

	private ViewPager viewPager;

	// 下拉菜单栏
	private TabLayout tabMenuLayout;

	private String[] RES_MAIN_HOME_TAB;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_home);
		viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_home);
		tabMenuLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_home_menu);

		RES_MAIN_HOME_TAB = view.getResources().getStringArray(R.array.main_home_tab);
		
		initView(view);
		initData();
	}
	
	private void initView(View view)
	{
		mainHomeHelper = new MainHomeHelper();
		mainHomeHelper.initTabDownMenuView(getContext(), tabMenuLayout);
		mainHomeHelper.setProvinceData();
	}
	
	private void initData()
	{
		fragmentList = new ArrayList<>();
		fragmentList.add(new MainHomeRedFragment());
		fragmentList.add(new MainHomeAuthorFragment());
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
				return RES_MAIN_HOME_TAB[position];
			}
		});
		
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
	}
	/*
	public void onBackPressed()
	{
		//退出activity前关闭菜单
		if (dropDownMenu.isShowing())
		{
			dropDownMenu.closeMenu();
		}
		else
		{
			super.onBackPressed();
		}
	}*/
}
