package com.hokol.activity;

import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;

/**
 * 首个欢迎界面
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class WelcomeActivity extends BaseAppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		initView();
	}

	private void initView()
	{
		findViewById(R.id.btn_action_main).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainActivity.actionStart(WelcomeActivity.this);
			}
		});
	}
}
