package com.hokol.base.common;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.hokol.base.application.BaseApplication;


/**
 * Created by yline on 2017/1/25.
 */
public class BaseListFragment extends ListFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		BaseApplication.addFragmentForRecord(this);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		BaseApplication.removeFragmentForRecord(this);
	}
}
