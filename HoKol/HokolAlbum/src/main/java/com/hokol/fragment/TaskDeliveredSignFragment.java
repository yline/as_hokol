package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.yline.base.BaseFragment;

public class TaskDeliveredSignFragment extends BaseFragment
{
	public static TaskDeliveredSignFragment newInstance()
	{
		Bundle args = new Bundle();

		TaskDeliveredSignFragment fragment = new TaskDeliveredSignFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_delivered_sign, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View view)
	{

	}
}
