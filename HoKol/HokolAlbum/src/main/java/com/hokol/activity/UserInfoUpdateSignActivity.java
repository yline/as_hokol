package com.hokol.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.medium.widget.EditTextWidget;
import com.yline.base.BaseAppCompatActivity;
import com.yline.common.ViewHolder;

public class UserInfoUpdateSignActivity extends BaseAppCompatActivity
{
	private final static String key_edit_content = "UserInfoUpdateSign";

	private final static int max_input_size = 242;

	private ViewHolder viewHolder;

	private EditTextWidget editTextWidget;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update_sign);

		initView();
		initViewClick();
		initData();
	}

	private void initView()
	{
		viewHolder = new ViewHolder(this);

		editTextWidget = new EditTextWidget((EditText) viewHolder.get(R.id.et_user_info_update_sign_input),
				(TextView) viewHolder.get(R.id.tv_user_info_update_sign_remainder))
		{
			@Override
			protected int getMaxInputSize()
			{
				return max_input_size;
			}
		};
	}

	private void initViewClick()
	{
		// 返回键
		viewHolder.setOnClickListener(R.id.iv_user_info_update_sign, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		// 完成键
		viewHolder.get(R.id.tv_user_info_update_sign_title).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String result = editTextWidget.getEditResult();
				UserInfoActivity.actionResultUpdate(UserInfoUpdateSignActivity.this, result);
				finish();
			}
		});
	}

	private void initData()
	{
		String oldEditContent = getIntent().getStringExtra(key_edit_content);
		editTextWidget.init(oldEditContent);
	}

	public static void actionStart(Context context)
	{
		Intent intent = new Intent(context, UserInfoUpdateSignActivity.class);
		context.startActivity(intent);
	}

	public static void actionStartForResult(Activity activity, int requestCode, String editContent)
	{
		Intent intent = new Intent(activity, UserInfoUpdateSignActivity.class);
		intent.putExtra(key_edit_content, editContent);
		activity.startActivityForResult(intent, requestCode);
	}
}
