package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserMessageSystemOutlineBean;
import com.hokol.medium.http.bean.WUserMessageSystemOutlineBean;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

public class UserTaskMessageActivity extends BaseAppCompatActivity
{
	private static final String KeyMessageUserId = "MessageUserId";

	private ViewHolder viewHolder;

	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_task_message);

		viewHolder = new ViewHolder(this);

		initView();
		initData();
	}

	private void initView()
	{
		viewHolder.setOnClickListener(R.id.rl_task_message, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(userId))
				{
					UserMessageSystemActivity.actionStart(UserTaskMessageActivity.this, userId);
				}

			}
		});

		viewHolder.setOnClickListener(R.id.iv_task_message, new View.OnClickListener()
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
		userId = getIntent().getStringExtra(KeyMessageUserId);
		XHttpUtil.doUserSystemMessageOutline(new WUserMessageSystemOutlineBean(userId), new XHttpAdapter<VUserMessageSystemOutlineBean>()
		{
			@Override
			public void onSuccess(VUserMessageSystemOutlineBean outlineBean)
			{
				if (outlineBean.getUnread_num() <= 0)
				{
					viewHolder.get(R.id.tv_task_message_bubble).setVisibility(View.INVISIBLE);
					viewHolder.setText(R.id.tv_task_message_content, "暂无消息");
					viewHolder.setText(R.id.tv_task_message_time, "");
				}
				else
				{
					// 气泡
					viewHolder.setText(R.id.tv_task_message_bubble, "" + outlineBean.getUnread_num()).setVisibility(View.VISIBLE);
					// 标题和时间
					viewHolder.setText(R.id.tv_task_message_content, outlineBean.getMess_title());
					String timeStr = HokolTimeConvertUtil.stamp2FormatTime(outlineBean.getPub_time() * 1000);
					viewHolder.setText(R.id.tv_task_message_time, timeStr);
				}
			}
		});
	}
	
	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserTaskMessageActivity.class).putExtra(KeyMessageUserId, userId));
	}
}
