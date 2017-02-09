package com.hokol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.utils.UIResizeUtil;

/**
 * 首个欢迎界面
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class WelcomeActivity extends BaseAppCompatActivity
{
	private Button btnActionMain;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		initView();
	}

	private void initView()
	{
		btnActionMain = (Button) findViewById(R.id.btn_action_main);
		btnActionMain.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MainActivity.actionStart(WelcomeActivity.this);
			}
		});

		UIResizeUtil.build().setIsWidthAdapter(false).setWidth(300).setHeight(300).setRightMargin(300).setTopMargin(300).commit(btnActionMain);
	}
}
