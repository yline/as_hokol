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
import com.hokol.view.menu.DropDownMenu;
import com.hokol.viewhelper.MainHomeHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeFragment extends BaseFragment
{
	private MainHomeHelper mainHomeHelper;

	private List<BaseFragment> fragmentList;
	
	public ViewPager viewPager;
	
	@BindView(R.id.tab_layout_main_home)
	public TabLayout tabLayout;
	
	@BindView(R.id.drop_menu_main_home)
	public DropDownMenu dropDownMenu;

	@BindArray(R.array.main_home_tab)
	public String[] RES_MAIN_HOME_TAB;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		ButterKnife.bind(this, view);
		
		initView(view);
		initData();
	}
	
	private void initView(View view)
	{
		mainHomeHelper = new MainHomeHelper();
		dropDownMenu = (DropDownMenu) view.findViewById(R.id.drop_menu_main_home);

		View contentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_home_container, null);
		mainHomeHelper.initMenuView(getContext(), dropDownMenu, contentView);

		viewPager = (ViewPager) contentView.findViewById(R.id.viewpager_main_home);
	}
	
	private void initData()
	{
		mainHomeHelper.initMenuData();

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
