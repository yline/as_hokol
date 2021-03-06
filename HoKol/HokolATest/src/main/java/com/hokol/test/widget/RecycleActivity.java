package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.test.R;
import com.hokol.test.common.BaseTestFragment;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends BaseAppCompatActivity
{
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, RecycleActivity.class));
	}

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
			recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
			{
				/*@Override
				protected int getDivideResourceId()
				{
					return R.drawable.widget_solid_graylight_size_medium;
				}

				@Override
				protected int getHeadNumber()
				{
					return 2;
				}*/
			});
			HeadFootRecyclerAdapter recyclerAdapter = getRecycleAdapter();
			recyclerView.setAdapter(recyclerAdapter);

			List<String> dataList = new ArrayList<>();
			for (int i = 0; i < 35; i++)
			{
				dataList.add("One " + i);
			}
			recyclerAdapter.setDataList(dataList);

			View grayView = new View(getContext());
			grayView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 30)));
			grayView.setBackgroundResource(android.R.color.holo_green_light);
			recyclerAdapter.addHeadView(grayView);

			View redView = new View(getContext());
			redView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 30)));
			redView.setBackgroundResource(android.R.color.holo_red_light);
			recyclerAdapter.addHeadView(redView);
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
			recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
			recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
			{
				@Override
				protected int getDivideResourceId()
				{
					return R.drawable.widget_solid_white_size_little;
				}

				@Override
				protected int getHeadNumber()
				{
					return 0;
				}
			});
			HeadFootRecyclerAdapter recyclerAdapter = getRecycleAdapter();
			recyclerView.setAdapter(recyclerAdapter);

			List<String> dataList = new ArrayList<>();
			for (int i = 0; i < 35; i++)
			{
				dataList.add("Two " + i);
			}
			recyclerAdapter.setDataList(dataList);

			View grayView = new View(getContext());
			grayView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 30)));
			grayView.setBackgroundResource(android.R.color.holo_green_light);
			recyclerAdapter.addHeadView(grayView);

			View redView = new View(getContext());
			redView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 30)));
			redView.setBackgroundResource(android.R.color.holo_red_light);
			recyclerAdapter.addHeadView(redView);
		}
	}
}
