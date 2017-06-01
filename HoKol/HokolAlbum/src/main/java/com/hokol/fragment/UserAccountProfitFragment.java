package com.hokol.fragment;

import android.os.Bundle;

import com.yline.base.BaseFragment;

public class UserAccountProfitFragment extends BaseFragment
{
	public static UserAccountProfitFragment newInstance()
	{
		
		Bundle args = new Bundle();
		
		UserAccountProfitFragment fragment = new UserAccountProfitFragment();
		fragment.setArguments(args);
		return fragment;
	}
}
