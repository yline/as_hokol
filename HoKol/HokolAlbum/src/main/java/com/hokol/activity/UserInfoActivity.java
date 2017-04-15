package com.hokol.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.DialogFootWidget;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;

import java.util.Arrays;

public class UserInfoActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		initView();
	}

	private void initView()
	{
		viewHolder = new ViewHolder(this);

		FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flow_layout_user_info);
		LabelWidget labelWidget = new LabelWidget(this, flowLayout);
		labelWidget.setDataList(Arrays.asList("网红", "模特"));

		// title
		viewHolder.setOnClickListener(R.id.iv_user_info, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 头像
		viewHolder.setOnClickListener(R.id.circle_user_info_avatar, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DialogFootWidget dialogFootWidget = new DialogFootWidget(UserInfoActivity.this, Arrays.asList("相册", "拍照"))
				{
					@Override
					protected int getResourceId()
					{
						return R.layout.widget_dialog_foot_rec;
					}
				};
				dialogFootWidget.show(new DialogFootWidget.OnSelectedListener()
				{
					@Override
					public void onCancelSelected(DialogInterface dialog)
					{
						dialog.dismiss();
					}

					@Override
					public void onOptionSelected(DialogInterface dialog, int position, String content)
					{
						dialog.dismiss();
					}
				});
			}
		});

		// 修改昵称
		viewHolder.setOnClickListener(R.id.ll_user_info_nickname, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String content = viewHolder.getText(R.id.tv_user_info_nickname);
				UserInfoUpdateNicknameActivity.actionStartForResult(UserInfoActivity.this, request_code_nickname, content);
			}
		});

		// 修改个性签名
		viewHolder.setOnClickListener(R.id.ll_user_info_sign, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String content = viewHolder.getText(R.id.tv_user_info_sign);
				UserInfoUpdateSignActivity.actionStartForResult(UserInfoActivity.this, request_code_sign, content);
			}
		});

		// 修改获奖经历
		viewHolder.setOnClickListener(R.id.ll_user_info_award, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String content = viewHolder.getText(R.id.tv_user_info_award);
				UserInfoUpdateAwardActivity.actionStartForResult(UserInfoActivity.this, request_code_award, content);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (null != data)
		{
			if (request_code_sign == requestCode)
			{
				String resultContent = data.getStringExtra(request_key);
				viewHolder.setText(R.id.tv_user_info_sign, resultContent);
			}
			else if (request_code_award == requestCode)
			{
				String resultContent = data.getStringExtra(request_key);
				viewHolder.setText(R.id.tv_user_info_award, resultContent);
			}
			else if (request_code_nickname == requestCode)
			{
				String resultContent = data.getStringExtra(request_key);
				viewHolder.setText(R.id.tv_user_info_nickname, resultContent);
			}
		}
	}

	private static final String request_key = "UserInfo";

	private static final int request_code_nickname = 100;

	private static final int request_code_sign = 101;

	private static final int request_code_award = 102;


	public static void actionResultUpdate(Activity activity, String result)
	{
		Intent intent = new Intent();
		intent.putExtra(request_key, result);
		activity.setResult(request_code_sign, intent);
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserInfoActivity.class));
	}
}
