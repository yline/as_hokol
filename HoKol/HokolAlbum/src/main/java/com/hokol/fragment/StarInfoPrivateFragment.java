package com.hokol.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseFragment;

public class StarInfoPrivateFragment extends BaseFragment
{
	private static final String ARG_PARAM1 = "param1";

	private static final String ARG_PARAM2 = "param2";

	private String mParam1;

	private String mParam2;

	public static StarInfoPrivateFragment newInstance()
	{
		StarInfoPrivateFragment fragment = new StarInfoPrivateFragment();
		/*Bundle args = new Bundle();
		fragment.setArguments(args);*/
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_star_info_private, container, false);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		view.findViewById(R.id.rl_star_info_private_lock).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("已经点击");
				// 弹框, popWindow

				View contentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_star_info_pop_window, null);

				Dialog dialog = new Dialog(getContext());
				dialog.setContentView(contentView);
				dialog.show();

				/*
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				builder.setView(contentView);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();*/
			}
		});
	}
}
