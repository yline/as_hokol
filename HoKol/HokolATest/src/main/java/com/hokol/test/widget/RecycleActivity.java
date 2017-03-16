package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.common.BaseFragment;
import com.hokol.test.BaseTestFragment;
import com.hokol.test.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends BaseAppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycle);
		
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();
		
		fragmentList.add(new RecycleOneFragment());
		titleList.add("Linear 分割");
		
		fragmentList.add(new RecycleTwoFragment());
		titleList.add("Grid 分割");
		
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_recycle);
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_recycle);
		
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

	public static class RecycleOneFragment extends BaseTestFragment
	{
		@Override
		protected int getResourceId()
		{
			return R.layout.fragment_test_base_special;
		}

		@Override
		protected void test()
		{
			RecyclerView recyclerView = addRecycleView();
			recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
			CommonRecyclerAdapter recyclerAdapter = getRecycleAdapter();

			List<String> dataList = new ArrayList<>();
			for (int i = 0; i < 15; i++)
			{
				dataList.add("One " + i);
			}
			recyclerAdapter.setDataList(dataList);
		}
	}

	public static class RecycleTwoFragment extends BaseTestFragment
	{
		@Override
		protected int getResourceId()
		{
			return R.layout.fragment_test_base_special;
		}

		@Override
		protected void test()
		{
			RecyclerView recyclerView = addRecycleView();
			recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
			CommonRecyclerAdapter recyclerAdapter = getRecycleAdapter();

			List<String> dataList = new ArrayList<>();
			for (int i = 0; i < 15; i++)
			{
				dataList.add("Two " + i);
			}
			recyclerAdapter.setDataList(dataList);
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, RecycleActivity.class));
	}
}
