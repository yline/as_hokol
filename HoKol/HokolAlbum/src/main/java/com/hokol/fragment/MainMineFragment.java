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
import com.hokol.activity.EnterChoiceActivity;
import com.hokol.activity.UserInfoActivity;
import com.hokol.application.AppStateManager;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.widget.DialogFootWidget;
import com.hokol.medium.widget.FlowWidget;
import com.yline.base.BaseFragment;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMineFragment extends BaseFragment
{
	private List<BaseFragment> fragmentList = new ArrayList<>();

	private static final String[] RES_TITLE = {"动态", "私密空间", "我的主页"};

	private ViewHolder viewHolder;

	public static MainMineFragment newInstance()
	{
		Bundle args = new Bundle();

		MainMineFragment fragment = new MainMineFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_mine, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		viewHolder = new ViewHolder(view);
		initView(view);

		initData();
	}
	
	private void initView(View view)
	{
		fragmentList.add(MainMineDynamicFragment.newInstance());
		fragmentList.add(MainMinePrivateFragment.newInstance());
		fragmentList.add(MainMineHomeFragment.newInstance());

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

	private void initData()
	{
		// 头部数据
		final AppStateManager appStateManager = AppStateManager.getInstance();
		boolean isLogin = appStateManager.isUserLogin(getContext());
		if (isLogin)
		{
			viewHolder.get(R.id.ll_main_mine_head).setVisibility(View.VISIBLE);
			viewHolder.get(R.id.tv_main_mine_head).setVisibility(View.INVISIBLE);
			viewHolder.setOnClickListener(R.id.ll_main_mine_head, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					UserInfoActivity.actionStart(getContext(), appStateManager.getUserLoginId(getContext()));
				}
			});
			viewHolder.setOnClickListener(R.id.circle_main_mine_avatar, new View.OnClickListener()
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

			// 头像
			ImageView imageView = viewHolder.get(R.id.circle_main_mine_avatar);
			Glide.with(getContext()).load(appStateManager.getUserLoginAvatar(getContext())).into(imageView);

			// 昵称
			viewHolder.setText(R.id.tv_main_mine_head_name, appStateManager.getUserLoginNickName(getContext()));

			// 性别
			if (HttpEnum.UserSex.Boy.equals(appStateManager.getUserLoginSex(getContext())))
			{
				viewHolder.get(R.id.iv_main_mine_head_sex).setVisibility(View.VISIBLE);
				viewHolder.setImageResource(R.id.iv_main_mine_head_sex, R.drawable.global_sex_boy);
			}
			else if (HttpEnum.UserSex.Girl.equals(appStateManager.getUserLoginSex(getContext())))
			{
				viewHolder.get(R.id.iv_main_mine_head_sex).setVisibility(View.VISIBLE);
				viewHolder.setImageResource(R.id.iv_main_mine_head_sex, R.drawable.global_sex_girl);
			}
			else
			{
				viewHolder.get(R.id.iv_main_mine_head_sex).setVisibility(View.GONE);
			}

			// 签名
			viewHolder.setText(R.id.tv_main_mine_sign, appStateManager.getUserSign(getContext()));

			// 标签
			FlowLayout flowLayout = viewHolder.get(R.id.flow_layout_main_mine_head_label);
			FlowWidget flowWidget = new FlowWidget(getContext(), flowLayout);
			flowWidget.setDataList(appStateManager.getUserLoginLabel(getContext()));
		}
		else
		{
			viewHolder.get(R.id.ll_main_mine_head).setVisibility(View.INVISIBLE);
			viewHolder.get(R.id.tv_main_mine_head).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.include_main_mine_head, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					EnterChoiceActivity.actionStart(getContext());
				}
			});
		}
	}
}
