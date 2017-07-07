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

public class TaskAssignedSignDetailUnFragment extends BaseFragment
{
	private static final String KeyDataValue = "AssignedUn";

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
		initData();
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
		signDetailUnAdapter.setShowEmpty(false);
		recyclerView.setAdapter(signDetailUnAdapter);
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
			signDetailUnAdapter.setDataList(arg.<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>getParcelableArrayList(KeyDataValue));
		}
	}

	public void updateData(ArrayList<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean> beanList)
	{
		if (null != signDetailUnAdapter)
		{
			signDetailUnAdapter.setDataList(beanList);
		}
		else
		{
			Bundle arg = new Bundle();
			arg.putParcelableArrayList(KeyDataValue, beanList);
			setArguments(arg);
		}
	}

	private class SignDetailUnAdapter extends WidgetRecyclerAdapter<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>
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
