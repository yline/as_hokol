package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserTaskCommentAssignedBean;
import com.hokol.medium.http.bean.WUserTaskCommentAssignedBean;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class TaskScoreAssignedDetailActivity extends BaseAppCompatActivity
{
	private static final String KeyAssignedUserId = "UserId";

	private static final String KeyAssignedTaskId = "TaskId";

	private ScoreAssignedDetailAdapter assignedDetailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_score_assigned_detail);

		initView();
		initData();
	}

	private void initView()
	{
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_score_assigned_detail);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		assignedDetailAdapter = new ScoreAssignedDetailAdapter();
		recyclerView.setAdapter(assignedDetailAdapter);

		View headView = new View(this);
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(this, 4)));
		headView.setBackgroundColor(ContextCompat.getColor(this, R.color.hokolGrayLight));
		assignedDetailAdapter.addHeadView(headView);

		findViewById(R.id.iv_task_score_assigned_detail_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private void initData()
	{
		String userId = getIntent().getStringExtra(KeyAssignedUserId);
		String taskId = getIntent().getStringExtra(KeyAssignedTaskId);

		XHttpUtil.doUserTaskCommentAssigned(new WUserTaskCommentAssignedBean(userId, taskId), new XHttpAdapter<VUserTaskCommentAssignedBean>()
		{
			@Override
			public void onSuccess(VUserTaskCommentAssignedBean vUserTaskCommentAssignedBean)
			{
				// 后台提供的数据 是单条
				List<VUserTaskCommentAssignedBean.VUserTaskCommentAssignedOneBean> resultList = vUserTaskCommentAssignedBean.getList();
				if (null != resultList)
				{
					assignedDetailAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class ScoreAssignedDetailAdapter extends HeadFootRecyclerAdapter<VUserTaskCommentAssignedBean.VUserTaskCommentAssignedOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_task_score_assigned_detail;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			VUserTaskCommentAssignedBean.VUserTaskCommentAssignedOneBean assignedBean = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_task_score_delivered_detail);
			Glide.with(TaskScoreAssignedDetailActivity.this).load(assignedBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

			// 昵称
			holder.setText(R.id.circle_task_score_delivered_nickname, assignedBean.getUser_nickname());

			// 时间
			String timeStr = HokolTimeConvertUtil.stampToFormatDate(assignedBean.getComment_pub_time() * 1000);
			holder.setText(R.id.circle_task_score_delivered_time, timeStr);

			// 信息
			holder.setText(R.id.circle_task_score_delivered_content, assignedBean.getUser_comment());
		}
	}

	public static void actionStart(Context context, String userId, String taskId)
	{
		context.startActivity(new Intent(context, TaskScoreAssignedDetailActivity.class).putExtra(KeyAssignedUserId, userId).putExtra(KeyAssignedTaskId, taskId));
	}
}
