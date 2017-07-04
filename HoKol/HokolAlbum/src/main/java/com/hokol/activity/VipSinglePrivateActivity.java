package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.UIScreenUtil;

public class VipSinglePrivateActivity extends BaseAppCompatActivity
{
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, VipSinglePrivateActivity.class));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vip_single_private);

		int width = UIScreenUtil.getScreenWidth(this);
		// UIResizeUtil.build()
		// RadioButton radioButton;
		// radioButton.setForeground();
	}
}
