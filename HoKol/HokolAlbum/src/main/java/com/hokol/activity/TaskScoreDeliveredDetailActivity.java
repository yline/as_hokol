package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserTaskCommentDeliveredBean;
import com.hokol.medium.http.bean.WUserTaskCommentDeliveredBean;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

public class TaskScoreDeliveredDetailActivity extends BaseAppCompatActivity
{
	private static final String KeyDeliveredUserId = "userId";

	private static final String KeyDeliveredTaskId = "taskId";

	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_score_delivered_detail);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		findViewById(R.id.iv_task_score_delivered_detail_cancel).setOnClickListener(new View.OnClickListener()
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
		String userId = getIntent().getStringExtra(KeyDeliveredUserId);
		String taskId = getIntent().getStringExtra(KeyDeliveredTaskId);
		XHttpUtil.doUserTaskCommentDelivered(new WUserTaskCommentDeliveredBean(userId, taskId), new XHttpAdapter<VUserTaskCommentDeliveredBean>()
		{
			@Override
			public void onSuccess(VUserTaskCommentDeliveredBean vUserTaskCommentDeliveredBean)
			{
				// 头像
				ImageView avatarImageView = viewHolder.get(R.id.circle_task_score_delivered_detail);
				Glide.with(TaskScoreDeliveredDetailActivity.this).load(vUserTaskCommentDeliveredBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

				// 昵称
				viewHolder.setText(R.id.circle_task_score_delivered_nickname, vUserTaskCommentDeliveredBean.getUser_nickname());

				// 时间
				String timeStr = HokolTimeConvertUtil.stampToFormatDate(vUserTaskCommentDeliveredBean.getComment_pub_time() * 1000);
				viewHolder.setText(R.id.circle_task_score_delivered_time, timeStr);

				// 信息
				viewHolder.setText(R.id.circle_task_score_delivered_content, vUserTaskCommentDeliveredBean.getUser_comment());
			}
		});
	}

	public static void actionStart(Context context, String userId, String taskId)
	{
		context.startActivity(new Intent(context, TaskScoreDeliveredDetailActivity.class).putExtra(KeyDeliveredUserId, userId).putExtra(KeyDeliveredTaskId, taskId));
	}
}
