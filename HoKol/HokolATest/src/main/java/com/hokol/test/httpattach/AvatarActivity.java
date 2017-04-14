package com.hokol.test.httpattach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.log.LogFileUtil;
import com.hokol.test.R;

/**
 * 测试头像展示
 *
 * @author yline 2017/4/14 -- 14:16
 * @version 1.0.0
 */
public class AvatarActivity extends BaseAppCompatActivity
{
	private static final String Path = "avatarPath";

	private ImageView imageView;

	private String avatarPath;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar);

		imageView = (ImageView) findViewById(R.id.iv_avatar);
		findViewById(R.id.btn_avatar).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				avatarPath = getIntent().getStringExtra(Path);
				LogFileUtil.v("avatarPath = " + avatarPath);

				Glide.with(AvatarActivity.this).load(avatarPath).error(R.mipmap.ic_launcher)
						.into(imageView);
			}
		});
	}

	public static void actionStart(Context context, String path)
	{
		Intent intent = new Intent();
		intent.setClass(context, AvatarActivity.class);
		intent.putExtra(Path, path);
		context.startActivity(intent);
	}
}
