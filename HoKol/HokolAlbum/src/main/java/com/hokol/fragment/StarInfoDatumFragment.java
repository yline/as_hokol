package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.UserCareActivity;
import com.hokol.activity.UserFansActivity;
import com.yline.base.BaseFragment;

public class StarInfoDatumFragment extends BaseFragment
{
	private static final String ARG_PARAM1 = "param1";

	private String mParam1;

	public static StarInfoDatumFragment newInstance()
	{
		StarInfoDatumFragment fragment = new StarInfoDatumFragment();
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
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_star_info_datum, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		view.findViewById(R.id.ll_star_info_datum_fans).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserFansActivity.actionStart(getContext());
			}
		});

		view.findViewById(R.id.ll_star_info_datum_care_people).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserCareActivity.actionStart(getContext());
			}
		});
	}
}
