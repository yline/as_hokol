package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.TaskScoreDeliveredDetailActivity;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VUserCreditBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;
import com.yline.view.recycler.holder.ViewHolder;

/**
 * 已投任务
 *
 * @author yline 2017/6/3 -- 14:54
 * @version 1.0.0
 */
public class UserTaskScoreDeliveredFragment extends BaseFragment
{
	private static final String ScoreOutlook = "outlook"; // 外貌相符

	private static final String ScoreAction = "action"; // 活动能力

	private static final String ScoreServer = "server"; // 服务态度

	private ViewHolder headViewHolder;

	private UserTaskScoreDeliveredAdapter taskScoreDeliveredAdapter;

	public static UserTaskScoreDeliveredFragment newInstance()
	{
		Bundle args = new Bundle();
		UserTaskScoreDeliveredFragment fragment = new UserTaskScoreDeliveredFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_user_task_score_delivered, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}
	
	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_task_score_delivered);
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

		taskScoreDeliveredAdapter = new UserTaskScoreDeliveredAdapter();
		recyclerView.setAdapter(taskScoreDeliveredAdapter);

		// 头部
		View headView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_task_score_delivered_head, null);
		taskScoreDeliveredAdapter.addHeadView(headView);
		headViewHolder = new ViewHolder(headView);
		updateSubCredit();

		taskScoreDeliveredAdapter.setDataList(HttpEnum.getUserTagListAll());
	}

	public void updateSubCredit()
	{
		Bundle bundle = getArguments();
		if (null != bundle)
		{
			headViewHolder.setProgress(R.id.progress_user_task_score_delivered_sub_outlook, (int) (10 * bundle.getFloat(ScoreOutlook)));
			headViewHolder.setText(R.id.tv_user_task_score_delivered_sub_outlook, String.format("%3.1f", bundle.getFloat(ScoreOutlook)));

			headViewHolder.setProgress(R.id.progress_user_task_score_delivered_sub_action, (int) (10 * bundle.getFloat(ScoreAction)));
			headViewHolder.setText(R.id.tv_user_task_score_delivered_sub_action, String.format("%3.1f", bundle.getFloat(ScoreAction)));

			headViewHolder.setProgress(R.id.progress_user_task_score_delivered_sub_server, (int) (10 * bundle.getFloat(ScoreServer)));
			headViewHolder.setText(R.id.tv_user_task_score_delivered_sub_server, String.format("%3.1f", bundle.getFloat(ScoreServer)));
		}
	}

	public void updateSubCredit(VUserCreditBean.VUserCreditSubBean subBean)
	{
		if (null != headViewHolder)
		{
			headViewHolder.setProgress(R.id.progress_user_task_score_delivered_sub_outlook, (int) (10 * subBean.getConformity_score()));
			headViewHolder.setText(R.id.tv_user_task_score_delivered_sub_outlook, String.format("%3.1f", subBean.getConformity_score()));

			headViewHolder.setProgress(R.id.progress_user_task_score_delivered_sub_action, (int) (10 * subBean.getAction_capacity_score()));
			headViewHolder.setText(R.id.tv_user_task_score_delivered_sub_action, String.format("%3.1f", subBean.getAction_capacity_score()));

			headViewHolder.setProgress(R.id.progress_user_task_score_delivered_sub_server, (int) (10 * subBean.getAttitude_score()));
			headViewHolder.setText(R.id.tv_user_task_score_delivered_sub_server, String.format("%3.1f", subBean.getAttitude_score()));
		}
		else
		{
			Bundle args = new Bundle();
			args.putFloat(ScoreOutlook, subBean.getConformity_score());
			args.putFloat(ScoreAction, subBean.getAction_capacity_score());
			args.putFloat(ScoreServer, subBean.getAttitude_score());
			setArguments(args);
		}
	}

	private class UserTaskScoreDeliveredAdapter extends HeadFootRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_task_score_delivered;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			holder.setOnClickListener(R.id.tv_item_task_score_delivered_more, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					TaskScoreDeliveredDetailActivity.actionStart(getContext());
				}
			});
		}
	}
}
