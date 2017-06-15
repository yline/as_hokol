package com.hokol.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.medium.widget.EtMaxNumWidget;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

public class UserInfoUpdateAwardActivity extends BaseAppCompatActivity
{
	private final static String key_edit_content = "UserInfoUpdateAward";

	private final static int max_input_size = 1000;

	private ViewHolder viewHolder;

	private EtMaxNumWidget editTextWidget;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update_award);

		initView();
		initViewClick();
		initData();
	}

	private void initView()
	{
		viewHolder = new ViewHolder(this);

		editTextWidget = new EtMaxNumWidget((EditText) viewHolder.get(R.id.et_user_info_update_award_input),
				(TextView) viewHolder.get(R.id.tv_user_info_update_award_remainder))
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
		viewHolder.setOnClickListener(R.id.iv_user_info_update_award, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		// 完成键
		viewHolder.get(R.id.tv_user_info_update_award_title).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String result = editTextWidget.getEditResult();
				UserInfoActivity.actionResultUpdate(UserInfoUpdateAwardActivity.this, result);
				finish();
			}
		});
	}

	private void initData()
	{
		String oldEditContent = getIntent().getStringExtra(key_edit_content);
		editTextWidget.init(oldEditContent);
	}

	public static void actionStartForResult(Activity activity, int requestCode, String editContent)
	{
		Intent intent = new Intent(activity, UserInfoUpdateAwardActivity.class);
		intent.putExtra(key_edit_content, editContent);
		activity.startActivityForResult(intent, requestCode);
	}
}
