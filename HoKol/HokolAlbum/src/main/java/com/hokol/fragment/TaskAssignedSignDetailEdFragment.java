package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.bean.VTaskUserSignUpDetailBean;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.base.BaseFragment;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskAssignedSignDetailEdFragment extends BaseFragment
{
	private static final String KeyDataValue = "AssignedEd";

	private SignDetailEdAdapter signDetailEdAdapter;

	public static TaskAssignedSignDetailEdFragment newInstance()
	{
		Bundle args = new Bundle();

		TaskAssignedSignDetailEdFragment fragment = new TaskAssignedSignDetailEdFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_assigned_sign_detail_ed, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_task_assigned_sign_detail_ed);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}
		});

		signDetailEdAdapter = new SignDetailEdAdapter();
		signDetailEdAdapter.setShowEmpty(false);
		recyclerView.setAdapter(signDetailEdAdapter);
	}

	private void initData()
	{
		updateData();
	}

	private void updateData()
	{
		Bundle arg = getArguments();
		if (null != arg)
		{
			signDetailEdAdapter.setDataList(arg.<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>getParcelableArrayList(KeyDataValue));
		}
	}

	public void updateData(ArrayList<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean> beanList)
	{
		if (null != signDetailEdAdapter)
		{
			signDetailEdAdapter.setDataList(beanList);
		}
		else
		{
			Bundle arg = new Bundle();
			arg.putParcelableArrayList(KeyDataValue, beanList);
			setArguments(arg);
		}
	}

	private class SignDetailEdAdapter extends WidgetRecyclerAdapter<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_sign_detail_ed;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			FlowLayout flowLayout = holder.get(R.id.flow_layout_sign_detail_ed);
			FlowWidget flowWidget = new FlowWidget(getContext(), flowLayout);
			flowWidget.setDataList(Arrays.asList("演员", "模特"));
		}
	}
}
