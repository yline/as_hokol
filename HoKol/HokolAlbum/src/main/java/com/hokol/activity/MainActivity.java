package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.fragment.MainTabFragment;

/**
 * Created by yline on 2017/2/8.
 */
public class MainActivity extends BaseAppCompatActivity
{
	private FragmentManager fragmentManager;

	private MainTabFragment mainTabFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();
	}

	private void initData()
	{
		fragmentManager = getSupportFragmentManager();

		mainTabFragment = new MainTabFragment();

		fragmentManager.beginTransaction().add(R.id.ll_main_tab, mainTabFragment).commit();
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, MainActivity.class));
	}
}
