package com.hokol.base.common;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.View;

import com.hokol.base.application.BaseApplication;

/**
 * @author yline 2017/2/28 --> 15:48
 * @version 1.0.0
 */
public class BasePreferenceFragment extends PreferenceFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		BaseApplication.addFragmentForRecordFew(this);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		BaseApplication.removeFragmentForRecordFew(this);
	}
}
