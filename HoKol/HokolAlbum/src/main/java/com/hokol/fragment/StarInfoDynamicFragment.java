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

public class StarInfoDynamicFragment extends BaseFragment
{
	private HeadFootRecycleAdapter starInfoDynamicAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_star_info_dynamic, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_star_info_dynamic);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

		starInfoDynamicAdapter = new StarInfoDynamicAdapter();
		recyclerView.setAdapter(starInfoDynamicAdapter);

		List<String> data = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			data.add("i" + i);
		}
		starInfoDynamicAdapter.setDataList(data);
	}

	private class StarInfoDynamicAdapter extends HeadFootRecycleAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_star_info_dynamic;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
		}
	}
}






















