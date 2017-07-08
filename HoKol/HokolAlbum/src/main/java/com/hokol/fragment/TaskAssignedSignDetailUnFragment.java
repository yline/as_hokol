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
import com.hokol.medium.http.bean.WTaskActionMasterTakeOnBean;
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

public class TaskAssignedSignDetailUnFragment extends BaseFragment
{
	private static final String KeyDataValue = "AssignedUn";

	private static final String KeyTaskId = "TaskId";

	private static final String KeyIsNegotiable = "IsNegotiable";

	private SignDetailUnAdapter signDetailUnAdapter;

	private String taskId;

	private boolean isNegotiable;

	public static TaskAssignedSignDetailUnFragment newInstance(String taskId, boolean isNegotiable)
	{
		Bundle args = new Bundle();
		TaskAssignedSignDetailUnFragment fragment = new TaskAssignedSignDetailUnFragment();
		args.putString(KeyTaskId, taskId);
		args.putBoolean(KeyIsNegotiable, isNegotiable);
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

		taskId = getArguments().getString(KeyTaskId);
		isNegotiable = getArguments().getBoolean(KeyIsNegotiable);
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

		signDetailUnAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean taskBean, int position)
			{
				StarInfoActivity.actionStart(getContext(), taskBean.getUser_id());
			}
		});
		signDetailUnAdapter.setOnTaskEmployCallback(new OnTaskEmployCallback()
		{
			@Override
			public void onEmployClick(RecyclerViewHolder viewHolder, VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean taskBean, int position)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(getContext());
				String otherUserId = taskBean.getUser_id();
				if (!isNegotiable)
				{
					WTaskActionMasterTakeOnBean takeOnBean = new WTaskActionMasterTakeOnBean(taskId, otherUserId, userId);
					XHttpUtil.doTaskActionMasterTakeOn(takeOnBean, new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("录用成功");
						}
					});
				}
				else
				{
					SDKManager.toast("还未担保，请输入价格");
				}
			}
		});
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

	public interface OnTaskEmployCallback
	{
		void onEmployClick(RecyclerViewHolder viewHolder, VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean taskBean, int position);
	}

	private class SignDetailUnAdapter extends WidgetRecyclerAdapter<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean>
	{
		private OnTaskEmployCallback onTaskEmployCallback;

		public void setOnTaskEmployCallback(OnTaskEmployCallback onTaskEmployCallback)
		{
			this.onTaskEmployCallback = onTaskEmployCallback;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_sign_detail_un;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder holder, final int position)
		{
			super.onBindViewHolder(holder, position);

			final VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean taskBean = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_detail_un_avatar);
			Glide.with(TaskAssignedSignDetailUnFragment.this).load(taskBean.getUser_logo()).error(R.drawable.global_load_avatar).into(avatarImageView);

			// 昵称
			holder.setText(R.id.tv_sign_detail_un_name, taskBean.getUser_nickname());

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
			Glide.with(TaskAssignedSignDetailUnFragment.this).load(taskBean.getLevel_url()).into(levelImageView);

			// 简介
			holder.setText(R.id.tv_sign_detail_un_brief, taskBean.getUser_sign());

			// 录用按钮
			holder.get(R.id.tv_detail_un_employ).setVisibility(View.VISIBLE);

			// 标签
			FlowLayout flowLayout = holder.get(R.id.flow_layout_sign_detail_un);
			FlowWidget flowWidget = new FlowWidget(getContext(), flowLayout);
			flowWidget.setDataList(taskBean.getUser_tag());

			// 录用
			holder.setOnClickListener(R.id.tv_detail_un_employ, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onTaskEmployCallback)
					{
						onTaskEmployCallback.onEmployClick(holder, taskBean, position);
					}
				}
			});
		}
	}
}
