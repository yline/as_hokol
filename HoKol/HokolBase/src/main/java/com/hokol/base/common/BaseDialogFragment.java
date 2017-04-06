package com.hokol.base.common;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.hokol.base.application.BaseApplication;

public class BaseDialogFragment extends DialogFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		BaseApplication.addFragmentForRecordV4(this);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		BaseApplication.removeFragmentForRecordV4(this);
	}
}
