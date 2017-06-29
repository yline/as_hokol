package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.TaskScoreAssignedDetailActivity;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VUserCreditBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;
import com.yline.view.recycler.holder.ViewHolder;

/**
 * 已发任务
 *
 * @author yline 2017/6/3 -- 14:54
 * @version 1.0.0
 */
public class UserTaskScoreAssignedFragment extends BaseFragment
{
	private static final String ScoreAction = "action"; // 活动相符

	private static final String ScoreCommunication = "communication"; // 交流态度

	private static final String ScoreSincerity = "sincerity"; // 诚信经营

	private UserTaskScoreAssignedAdapter taskScoreAssignedAdapter;

	private ViewHolder headViewHolder;

	public static UserTaskScoreAssignedFragment newInstance()
	{
		Bundle args = new Bundle();

		UserTaskScoreAssignedFragment fragment = new UserTaskScoreAssignedFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_user_task_score_assigned, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_task_score_assigned);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_medium;
			}

			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}

			@Override
			protected int getHeadNumber()
			{
				return 1;
			}
		});

		taskScoreAssignedAdapter = new UserTaskScoreAssignedAdapter();
		recyclerView.setAdapter(taskScoreAssignedAdapter);

		// 头部
		View headView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_task_score_assigned_head, null);
		taskScoreAssignedAdapter.addHeadView(headView);
		headViewHolder = new ViewHolder(headView);
		updateHostCredit();

		taskScoreAssignedAdapter.setDataList(HttpEnum.getUserTagListAll());
	}

	public void updateHostCredit()
	{
		Bundle bundle = getArguments();
		if (null != bundle)
		{
			// 活动相符
			headViewHolder.setProgress(R.id.progress_user_task_score_assigned_host_action, (int) (10 * bundle.getFloat(ScoreAction)));
			headViewHolder.setText(R.id.tv_user_task_score_assigned_host_action, String.format("%3.1f", bundle.getFloat(ScoreAction)));

			// 交流态度
			headViewHolder.setProgress(R.id.progress_user_task_score_assigned_host_communication, (int) (10 * bundle.getFloat(ScoreCommunication)));
			headViewHolder.setText(R.id.tv_user_task_score_assigned_host_communication, String.format("%3.1f", bundle.getFloat(ScoreCommunication)));

			// 诚信经营
			headViewHolder.setProgress(R.id.progress_user_task_score_assigned_host_sincerity, (int) (10 * bundle.getFloat(ScoreSincerity)));
			headViewHolder.setText(R.id.tv_user_task_score_assigned_host_sincerity, String.format("%3.1f", bundle.getFloat(ScoreSincerity)));
		}
	}

	public void updateHostCredit(VUserCreditBean.VUserCreditHostBean hostBean)
	{
		if (null != headViewHolder)
		{
			// 活动相符
			headViewHolder.setProgress(R.id.progress_user_task_score_assigned_host_action, (int) (10 * hostBean.getConformity_score()));
			headViewHolder.setText(R.id.tv_user_task_score_assigned_host_action, String.format("%3.1f", hostBean.getConformity_score()));

			// 交流态度
			headViewHolder.setProgress(R.id.progress_user_task_score_assigned_host_communication, (int) (10 * hostBean.getCommunion_score()));
			headViewHolder.setText(R.id.tv_user_task_score_assigned_host_communication, String.format("%3.1f", hostBean.getCommunion_score()));

			// 诚信经营
			headViewHolder.setProgress(R.id.progress_user_task_score_assigned_host_sincerity, (int) (10 * hostBean.getCredibility_score()));
			headViewHolder.setText(R.id.tv_user_task_score_assigned_host_sincerity, String.format("%3.1f", hostBean.getCredibility_score()));
		}
		else
		{
			Bundle args = new Bundle();
			args.putFloat(ScoreAction, hostBean.getConformity_score());
			args.putFloat(ScoreCommunication, hostBean.getCommunion_score());
			args.putFloat(ScoreSincerity, hostBean.getCredibility_score());
			setArguments(args);
		}
	}

	private class UserTaskScoreAssignedAdapter extends HeadFootRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_task_score_assigned;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			holder.setOnClickListener(R.id.tv_item_user_task_score_assigned_more, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					TaskScoreAssignedDetailActivity.actionStart(getContext());
				}
			});
		}
	}
}
