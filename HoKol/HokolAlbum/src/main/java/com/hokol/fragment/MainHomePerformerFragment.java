package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class MainHomePerformerFragment extends BaseFragment
{
	private static final String ARG_PARAM1 = "param1";

	private String mParam1;

	private HeadFootRecycleAdapter mainHomePerformerAdapter;

	public static MainHomePerformerFragment newInstance()
	{
		MainHomePerformerFragment fragment = new MainHomePerformerFragment();
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
		return inflater.inflate(R.layout.fragment_main_home_performer, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recycleView = (RecyclerView) view.findViewById(R.id.recycle_main_home_performer);
		recycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
		mainHomePerformerAdapter = new MainHomePerformerFragment.MainHomePerformerAdapter();

		recycleView.setAdapter(mainHomePerformerAdapter);

		List<String> data = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			data.add("i" + i);
		}
		mainHomePerformerAdapter.setDataList(data);
	}

	private class MainHomePerformerAdapter extends HeadFootRecycleAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_performer;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder var1, int var2)
		{

		}
	}
}
