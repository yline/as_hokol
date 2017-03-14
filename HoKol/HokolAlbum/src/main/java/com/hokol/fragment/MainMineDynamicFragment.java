package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.adapter.HeadFootRecycleAdapter;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.custom.DefaultLinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainMineDynamicFragment extends BaseFragment
{
	private HeadFootRecycleAdapter recyclerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_mine_dynamic, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_mine_dynamic);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext(), DefaultLinearItemDecoration.VERTICAL_LIST));

		recyclerAdapter = new DynamicRecycleAdapter();
		recyclerView.setAdapter(recyclerAdapter);

		List dataList = new ArrayList();
		for (int i = 0; i < 10; i++)
		{
			dataList.add("i" + i);
		}
		recyclerAdapter.setDataList(dataList);
	}

	private class DynamicRecycleAdapter extends HeadFootRecycleAdapter
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_care;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, final int position)
		{
			viewHolder.get(R.id.iv_item_main_care_content).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					IApplication.toast("position = " + position);
				}
			});
		}
	}
}
