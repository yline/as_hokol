package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.common.BaseFragment;
import com.hokol.fragment.MainCareFragment;
import com.hokol.fragment.MainHomeFragment;
import com.hokol.fragment.MainMineFragment;
import com.hokol.fragment.MainNewsFragment;
import com.hokol.fragment.MainTaskFragment;
import com.hokol.viewhelper.MainTitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面
 *
 * @author yline 2017/3/2 --> 10:01
 * @version 1.0.0
 */
public class MainActivity extends BaseAppCompatActivity implements MainTitleHelper.OnTitleClickListener
{
	private FragmentManager fragmentManager = getSupportFragmentManager();

	private MainNewsFragment mainNewsFragment;

	private MainCareFragment mainCareFragment;

	private MainHomeFragment mainHomeFragment;

	private MainTaskFragment mainTaskFragment;

	private MainMineFragment mainMineFragment;

	@BindView(R.id.tab_layout_main)
	public TabLayout tabLayout;

	private static final int[] RES = {R.string.main_tab_one, R.string.main_tab_two, R.string.main_tab_three, R.string.main_tab_four, R.string.main_tab_five};

	private static final int COLOR_BEFORE = Color.BLACK;

	private static final int COLOR_AFTER = Color.GREEN;

	private int lastTabPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		initView();
		initData();
	}
	
	private void initView()
	{
		// tabLayout = (TabLayout) findViewById(R.id.tab_layout_main);
		tabLayout.addTab(tabLayout.newTab().setText(RES[0]));
		tabLayout.addTab(tabLayout.newTab().setText(RES[1]));
		tabLayout.addTab(tabLayout.newTab().setText(RES[2]));
		tabLayout.addTab(tabLayout.newTab().setText(RES[3]));
		tabLayout.addTab(tabLayout.newTab().setText(RES[4]));
		tabLayout.setSelectedTabIndicatorHeight(0);
		tabLayout.setTabTextColors(COLOR_BEFORE, COLOR_AFTER);

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab)
			{/*
				int position = tab.getPosition();
				if (lastTabPosition != position)
				{
					Fragment showFragment = getFragmentByPosition(position);
					if (null != showFragment)
					{
						hide(lastTabPosition).show(showFragment).commit();
					}

					lastTabPosition = position;
				}*/
				int position = tab.getPosition();
				fragmentManager.beginTransaction().show(getFragmentByPosition(position)).commit();
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab)
			{
				int position = tab.getPosition();
				fragmentManager.beginTransaction().hide(getFragmentByPosition(position)).commit();
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab)
			{

			}
		});

		MainTitleHelper mainTitleHelper = new MainTitleHelper();
		mainTitleHelper.initTitleView(findViewById(R.id.include_main_title));
		mainTitleHelper.setListener(this);
	}

	private void initData()
	{
		mainNewsFragment = new MainNewsFragment();
		mainCareFragment = new MainCareFragment();
		mainHomeFragment = new MainHomeFragment();
		mainTaskFragment = new MainTaskFragment();
		mainMineFragment = new MainMineFragment();

		lastTabPosition = 0;

		fragmentManager.beginTransaction().add(R.id.fl_main_content, mainNewsFragment).hide(mainNewsFragment)
				.add(R.id.fl_main_content, mainCareFragment).hide(mainCareFragment)
				.add(R.id.fl_main_content, mainHomeFragment).hide(mainHomeFragment)
				.add(R.id.fl_main_content, mainTaskFragment).hide(mainTaskFragment)
				.add(R.id.fl_main_content, mainMineFragment).hide(mainMineFragment).commit();
	}

	@Override
	public void onTitleClick(MainTitleHelper.TITLE_TYPE type)
	{

	}

	/**
	 * 依据位置，获取到相应的 Fragment
	 *
	 * @param newPosition
	 * @return
	 */
	private Fragment getFragmentByPosition(int newPosition)
	{
		BaseFragment fragment = null;
		switch (newPosition)
		{
			case 0:
				fragment = mainNewsFragment;
				break;
			case 1:
				fragment = mainCareFragment;
				break;
			case 2:
				fragment = mainHomeFragment;
				break;
			case 3:
				fragment = mainTaskFragment;
				break;
			case 4:
				fragment = mainMineFragment;
				break;
			default:
				break;
		}
		return fragment;
	}
	/*
	private FragmentTransaction hide(int oldPosition)
	{
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		switch (oldPosition)
		{
			case 0:
				fragmentTransaction.hide(mainNewsFragment);
				break;
			case 1:
				fragmentTransaction.hide(mainDeleteFragment);
				break;
			case 2:
				fragmentTransaction.hide(mainDeleteFragment);
				break;
			case 3:
				fragmentTransaction.hide(mainDeleteFragment);
				break;
			case 4:
				fragmentTransaction.hide(mainDeleteFragment);
				break;
			default:
				break;
		}
		return fragmentTransaction;
	}*/

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, MainActivity.class));
	}
}
