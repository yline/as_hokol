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

public class MainMineFragment extends BaseFragment
{
	private List<BaseFragment> fragmentList = new ArrayList<>();

	private static final String[] RES_TITLE = {"动态", "私密空间", "我的主页"};

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_mine, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		initView(view);
	}
	
	private void initView(View view)
	{
		fragmentList.add(new MainMineDynamicFragment());
		fragmentList.add(new MainMinePrivateFragment());
		fragmentList.add(new MainMineHomeFragment());

		TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_mine);
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_mine);

		tabLayout.setupWithViewPager(viewPager);
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
				return RES_TITLE[position];
			}
		});
	}
}
