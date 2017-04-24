package com.hokol.fragment;

import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.UserInfoActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.widget.DialogFootWidget;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;
import com.yline.base.BaseFragment;

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
		headView.findViewById(R.id.circle_main_mine_avatar).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DialogFootWidget dialogFootWidget = new DialogFootWidget(getContext(), Arrays.asList("相册", "拍照"));
				dialogFootWidget.show(new DialogFootWidget.OnSelectedListener()
				{
					@Override
					public void onCancelSelected(DialogInterface dialog)
					{
						IApplication.toast("DisAppear");
						dialog.dismiss();
					}

					@Override
					public void onOptionSelected(DialogInterface dialog, int position, String content)
					{
						IApplication.toast("position = " + position + ", content = " + content);
						dialog.dismiss();
					}
				});
			}
		});
		headView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserInfoActivity.actionStart(getContext());
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
		tabLayout.setTabTextColors(getResources().getColor(R.color.hokolGrayDrak), getResources().getColor(R.color.hokolRed));
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
		ImageView imageView = (ImageView) parentView.findViewById(R.id.circle_main_mine_avatar);
		Glide.with(getContext()).load(DeleteConstant.url_default_avatar).centerCrop().into(imageView);

		FlowLayout flowLayout = (FlowLayout) parentView.findViewById(R.id.flow_layout_main_mine_head_label);
		LabelWidget labelWidget = new LabelWidget(getContext(), flowLayout);
		labelWidget.setDataList(Arrays.asList("网红", "模特"));
	}
}
