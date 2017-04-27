package com.hokol.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.hokol.R;
import com.yline.common.ViewHolder;

public class UserInfoUpdateNicknameActivity extends AppCompatActivity
{
	private final static String key_edit_content = "UserInfoUpdateNickname";

	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update_nickname);

		initView();
		initViewClick();
		initData();
	}

	private void initView()
	{
		viewHolder = new ViewHolder(this);
	}

	private void initViewClick()
	{
		// 返回键
		viewHolder.setOnClickListener(R.id.iv_user_info_update_nickname, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		// 完成键
		viewHolder.get(R.id.tv_user_info_update_nickname_title).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String result = viewHolder.getText(R.id.et_user_info_update_nickname_input);
				if (!TextUtils.isEmpty(result))
				{
					UserInfoActivity.actionResultUpdate(UserInfoUpdateNicknameActivity.this, result);
					finish();
				}
			}
		});
	}

	private void initData()
	{
		String oldEditContent = getIntent().getStringExtra(key_edit_content);

		EditText editText = viewHolder.get(R.id.et_user_info_update_nickname_input);
		editText.setText(oldEditContent);
		editText.setSelection(oldEditContent.length());
	}

	public static void actionStartForResult(Activity activity, int requestCode, String editContent)
	{
		Intent intent = new Intent(activity, UserInfoUpdateNicknameActivity.class);
		intent.putExtra(key_edit_content, editContent);
		activity.startActivityForResult(intent, requestCode);
	}
}
