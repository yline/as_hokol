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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.MineInfoActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.base.common.BaseFragment;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.transform.CircleTransform;

import java.util.ArrayList;
import java.util.Arrays;
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

		View headView = view.findViewById(R.id.include_main_mine_head);
		initHeaderView(headView);
		headView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MineInfoActivity.actionStart(getContext());
			}
		});
	}
	
	private void initView(View view)
	{
		fragmentList.add(new MainMineDynamicFragment());
		fragmentList.add(new MainMinePrivateFragment());
		fragmentList.add(new MainMineHomeFragment());

		TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_main_mine);
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_main_mine);

		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabTextColors(getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_light));
		tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_red_light));
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

	private void initHeaderView(View parentView)
	{
		ImageView imageView = (ImageView) parentView.findViewById(R.id.iv_main_mine_avatar);
		Glide.with(getContext()).load(DeleteConstant.url_default_avatar).centerCrop()
				.bitmapTransform(new CircleTransform(getContext())).placeholder(R.mipmap.ic_launcher)
				.into(imageView);

		LinearLayout labelLayout = (LinearLayout) parentView.findViewById(R.id.ll_main_mine_label);
		LabelWidget labelWidget = new LabelWidget();
		View labelView = labelWidget.start(getContext(), Arrays.asList("网红", "模特"));
		labelLayout.addView(labelView);
	}
}
