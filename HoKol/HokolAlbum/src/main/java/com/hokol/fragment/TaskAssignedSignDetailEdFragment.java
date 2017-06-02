package com.hokol.fragment;

import android.os.Bundle;

import com.yline.base.BaseFragment;

public class TaskAssignedSignDetailEdFragment extends BaseFragment
{
	public static TaskAssignedSignDetailEdFragment newInstance()
	{
		Bundle args = new Bundle();

		TaskAssignedSignDetailEdFragment fragment = new TaskAssignedSignDetailEdFragment();
		fragment.setArguments(args);
		return fragment;
	}
}
