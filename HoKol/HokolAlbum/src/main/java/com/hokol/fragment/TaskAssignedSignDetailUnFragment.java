package com.hokol.fragment;

import android.os.Bundle;

import com.yline.base.BaseFragment;

public class TaskAssignedSignDetailUnFragment extends BaseFragment
{
	public static TaskAssignedSignDetailUnFragment newInstance()
	{
		Bundle args = new Bundle();

		TaskAssignedSignDetailUnFragment fragment = new TaskAssignedSignDetailUnFragment();
		fragment.setArguments(args);
		return fragment;
	}
}
