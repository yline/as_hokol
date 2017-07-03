package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.Calendar;

public class UserMessageDetailActivity extends BaseAppCompatActivity
{
	private static final String KeyMsgDetailUserId = "MsgDetailUserId";

	private static final String KeyMsgDetailTitle = "MsgDetailTitle";

	private static final String KeyMsgDetailContent = "MsgDetailContent";

	private static final String KeyMsgDetailDate = "MsgDetailDate";

	private ViewHolder viewHolder;

	public static void actionStart(Context context, String userId, String title, String content, long publishTime)
	{
		Intent intent = new Intent();
		intent.setClass(context, UserMessageDetailActivity.class);
		intent.putExtra(KeyMsgDetailUserId, userId);
		intent.putExtra(KeyMsgDetailTitle, title);
		intent.putExtra(KeyMsgDetailContent, content);
		intent.putExtra(KeyMsgDetailDate, publishTime);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_message_detail);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		viewHolder.setOnClickListener(R.id.iv_message_detail, new View.OnClickListener()
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
		// 标题
		String msgTitle = getIntent().getStringExtra(KeyMsgDetailTitle);
		viewHolder.setText(R.id.tv_message_detail_title, msgTitle);

		// 内容
		String msgContent = getIntent().getStringExtra(KeyMsgDetailContent);
		viewHolder.setText(R.id.tv_message_detail_content, msgContent);

		// 日期
		long msgPublishDate = getIntent().getLongExtra(KeyMsgDetailDate, System.currentTimeMillis() / 1000);
		String dateTime = HokolTimeConvertUtil.stampToFormatDate(msgPublishDate * 1000, Calendar.MINUTE);
		viewHolder.setText(R.id.tv_message_detail_date, dateTime);

		// id
		String userId = getIntent().getStringExtra(KeyMsgDetailUserId);

	}
}
