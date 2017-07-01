package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.config.glide.HokolGlideModule;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.FileSizeUtil;
import com.yline.view.recycler.holder.ViewHolder;

import java.io.File;

public class UserSettingActivity extends BaseAppCompatActivity
{
	private static final String KeySettingUserId = "userId";

	private ViewHolder viewHolder;

	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting);

		viewHolder = new ViewHolder(this);
		initViewClick();
		initData();
	}

	private void initViewClick()
	{
		// 返回
		viewHolder.setOnClickListener(R.id.iv_user_setting_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 账号安全
		viewHolder.setOnClickListener(R.id.ll_user_setting_account, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserSettingAccountActivity.actionStart(UserSettingActivity.this);
			}
		});

		// 意见反馈
		viewHolder.setOnClickListener(R.id.ll_user_setting_feedback, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserSettingFeedbackActivity.actionStart(UserSettingActivity.this, userId);
			}
		});

		// 清理缓存
		viewHolder.setOnClickListener(R.id.ll_user_setting_cache, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new AsyncTask<Void, Void, Void>()
				{
					@Override
					protected Void doInBackground(Void... params)
					{
						Glide.get(UserSettingActivity.this).clearDiskCache();
						return null;
					}

					@Override
					protected void onPostExecute(Void aVoid)
					{
						super.onPostExecute(aVoid);
						updateCacheSize();
					}
				}.execute();
			}
		});

		// 退出登录
		viewHolder.setOnClickListener(R.id.btn_login_exit, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainActivity.actionStart(UserSettingActivity.this);
			}
		});
	}

	private void initData()
	{
		userId = getIntent().getStringExtra(KeySettingUserId);

		updateCacheSize();
	}

	private void updateCacheSize()
	{
		// 展示文件大小
		File cacheFile = getExternalFilesDir(HokolGlideModule.path_glide_picture);
		long cacheSize = FileSizeUtil.getFileOrDirAutoSize(cacheFile);
		double cacheSizeDouble = FileSizeUtil.formatFileSize(cacheSize, FileSizeUtil.getSuffixTypeMb());
		viewHolder.setText(R.id.tv_user_setting_cache_size, String.format("%3.2fMB", cacheSizeDouble));
	}
	
	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserSettingActivity.class).putExtra(KeySettingUserId, userId));
	}
}
