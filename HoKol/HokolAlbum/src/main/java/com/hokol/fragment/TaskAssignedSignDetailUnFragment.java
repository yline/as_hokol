package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;
import com.yline.widget.label.FlowLayout;

import java.util.Arrays;

public class TaskAssignedSignDetailUnFragment extends BaseFragment
{
	private SignDetailUnAdapter signDetailUnAdapter;

	public static TaskAssignedSignDetailUnFragment newInstance()
	{
		Bundle args = new Bundle();

		TaskAssignedSignDetailUnFragment fragment = new TaskAssignedSignDetailUnFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_assigned_sign_detail_un, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_task_assigned_sign_detail_un);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}
		});

		signDetailUnAdapter = new SignDetailUnAdapter();
		recyclerView.setAdapter(signDetailUnAdapter);

		signDetailUnAdapter.setDataList(HttpEnum.getUserTagListAll());
	}

	private class SignDetailUnAdapter extends CommonRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_sign_detail_un;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			FlowLayout flowLayout = holder.get(R.id.flow_layout_sign_detail_un);
			FlowWidget flowWidget = new FlowWidget(getContext(), flowLayout);
			flowWidget.setDataList(Arrays.asList("演员", "模特"));
		}
	}
}
