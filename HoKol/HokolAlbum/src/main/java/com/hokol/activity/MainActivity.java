package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.common.BaseFragment;
import com.hokol.fragment.MainCareFragment;
import com.hokol.fragment.MainHomeFragment;
import com.hokol.fragment.MainMineFragment;
import com.hokol.fragment.MainNewsFragment;
import com.hokol.fragment.MainTaskFragment;
import com.hokol.viewhelper.MainHelper;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面
 *
 * @author yline 2017/3/2 --> 10:01
 * @version 1.0.0
 */
public class MainActivity extends BaseAppCompatActivity
{
	private MainHelper mainHelper = new MainHelper();

	private FragmentManager fragmentManager = getSupportFragmentManager();

	private MainNewsFragment mainNewsFragment;

	private MainCareFragment mainCareFragment;

	private MainHomeFragment mainHomeFragment;

	private MainTaskFragment mainTaskFragment;

	private MainMineFragment mainMineFragment;

	@BindView(R.id.tab_layout_main)
	public TabLayout tabLayout;

	@BindArray(R.array.main_tab)
	public String[] RES_MAIN_TAB;

	public int[] RES_MAIN_TAB_ICON = {R.drawable.main_tab_news, R.drawable.main_tab_care, R.drawable.main_tab_home, R.drawable.main_tab_task, R.drawable.main_tab_me};

	@BindView(R.id.iv_main_logo)
	public ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_main_title);
		mainHelper.initToolbar(this, toolbar);

		ButterKnife.bind(this);

		mainHelper.initFlashAnimator(imageView);

		initView();
		initData();
	}

	private void initView()
	{
		mainHelper.initTabLayout(this, tabLayout, RES_MAIN_TAB_ICON, RES_MAIN_TAB);

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab)
			{
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
	}

	private void initData()
	{
		mainNewsFragment = new MainNewsFragment();
		mainCareFragment = new MainCareFragment();
		mainHomeFragment = new MainHomeFragment();
		mainTaskFragment = new MainTaskFragment();
		mainMineFragment = new MainMineFragment();

		fragmentManager.beginTransaction().add(R.id.fl_main_content, mainNewsFragment)// .hide(mainNewsFragment)
				.add(R.id.fl_main_content, mainCareFragment).hide(mainCareFragment)
				.add(R.id.fl_main_content, mainHomeFragment).hide(mainHomeFragment)
				.add(R.id.fl_main_content, mainTaskFragment).hide(mainTaskFragment)
				.add(R.id.fl_main_content, mainMineFragment).hide(mainMineFragment).commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == android.R.id.home)
		{
			finish();
		}
		return super.onOptionsItemSelected(item);
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

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, MainActivity.class));
	}
}
