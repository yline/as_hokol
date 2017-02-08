package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.fragment.MainDeleteFragment;
import com.hokol.fragment.MainNewsFragment;
import com.hokol.fragment.MainTabFragment;
import com.hokol.fragment.MainTitleFragment;

/**
 * Created by yline on 2017/2/8.
 */
public class MainActivity extends BaseAppCompatActivity implements MainTabFragment.OnTabClickListener, MainTitleFragment.OnTitleClickListener
{
	private FragmentManager fragmentManager = getSupportFragmentManager();

	private MainNewsFragment mainNewsFragment;

	private MainDeleteFragment mainDeleteFragment;

	private int lastTabPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();
	}

	private void initData()
	{
		mainNewsFragment = new MainNewsFragment();
		mainDeleteFragment = new MainDeleteFragment();

		lastTabPosition = 0;
		fragmentManager.beginTransaction().add(R.id.fl_main_content, mainNewsFragment).commit();
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, MainActivity.class));
	}

	@Override
	public void onTabClick(int position)
	{
		if (lastTabPosition != position)
		{
			Fragment showFragment = getFragmentByPosition(position);
			if (null != showFragment)
			{
				hide(lastTabPosition).show(showFragment).commit();
			}

			lastTabPosition = position;
		}
	}

	private Fragment getFragmentByPosition(int newPosition)
	{
		Fragment fragment = null;
		switch (newPosition)
		{
			case 0:
				fragment = mainNewsFragment;
				break;
			case 1:
				fragment = mainDeleteFragment;
				break;
			case 2:
				fragment = mainDeleteFragment;
				break;
			case 3:
				fragment = mainDeleteFragment;
				break;
			case 4:
				fragment = mainDeleteFragment;
				break;
		}
		return fragment;
	}

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
		}
		return fragmentTransaction;
	}

	@Override
	public void onTitleClick(MainTitleFragment.TITLE_TYPE type)
	{

	}
}
