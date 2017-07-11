package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.StarInfoActivity;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserSignUpDetailBean;
import com.hokol.medium.http.bean.WTaskActionMasterFinishBean;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.ArrayList;

public class TaskAssignedSignDetailEdFragment extends BaseFragment
{
	private static final String KeyDataValue = "AssignedEd";

	private static final String KeyTaskId = "TaskId";

	private SignDetailEdAdapter signDetailEdAdapter;

	private String taskId;

	public static TaskAssignedSignDetailEdFragment newInstance(String taskId)
	{
		Bundle args = new Bundle();

		TaskAssignedSignDetailEdFragment fragment = new TaskAssignedSignDetailEdFragment();
		args.putString(KeyTaskId, taskId);

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

		taskId = getArguments().getString(KeyTaskId);

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

		signDetailEdAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean taskBean, int position)
			{
				StarInfoActivity.actionStart(getContext(), taskBean.getUser_id());
			}
		});

		view.findViewById(R.id.tv_task_assigned_sign_detail_ed).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(getContext());
				XHttpUtil.doTaskActionMasterFinish(new WTaskActionMasterFinishBean(userId, taskId), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("结束报名成功");
						getActivity().finish(); // 结束该Activity
					}
				});
			}
		});
	}

	private void initData()
	{
		onRefreshData();
	}

	private void onRefreshData()
	{
		Bundle arg = getArguments();
		if (null != arg)
		{
			signDetailEdAdapter.setDataList(arg.<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>getParcelableArrayList(KeyDataValue));
		}
	}

	public void onRefreshData(ArrayList<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean> beanList)
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
			super.onBindViewHolder(holder, position);

			VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean taskBean = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_detail_ed_avatar);
			Glide.with(TaskAssignedSignDetailEdFragment.this).load(taskBean.getUser_logo()).error(R.drawable.global_load_avatar).into(avatarImageView);

			// 昵称
			holder.setText(R.id.tv_sign_detail_ed_name, taskBean.getUser_nickname());

			// 性别
			if (taskBean.getUser_sex().equals(HttpEnum.UserSex.Boy))
			{
				holder.setImageResource(R.id.iv_user_info_sex, R.drawable.global_sex_boy).setVisibility(View.VISIBLE);
			}
			else if (taskBean.getUser_sex().equals(HttpEnum.UserSex.Girl))
			{
				holder.setImageResource(R.id.iv_user_info_sex, R.drawable.global_sex_girl).setVisibility(View.VISIBLE);
			}
			else
			{
				holder.get(R.id.iv_user_info_sex).setVisibility(View.GONE);
			}

			// 等级图标
			ImageView levelImageView = holder.get(R.id.iv_user_info_level);
			Glide.with(TaskAssignedSignDetailEdFragment.this).load(taskBean.getLevel_url()).into(levelImageView);

			// 简介
			holder.setText(R.id.tv_sign_detail_ed_brief, taskBean.getUser_sign());

			// 录用按钮
			holder.get(R.id.tv_detail_ed_employ).setVisibility(View.GONE);

			// 标签
			FlowLayout flowLayout = holder.get(R.id.flow_layout_sign_detail_ed);
			FlowWidget flowWidget = new FlowWidget(getContext(), flowLayout);
			flowWidget.setDataList(taskBean.getUser_tag());
		}
	}
}
