package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;

import java.util.Arrays;

public class UserInfoActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		initView();
	}

	private void initView()
	{
		FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flow_layout_user_info);
		LabelWidget labelWidget = new LabelWidget(this, flowLayout);
		labelWidget.setDataList(Arrays.asList("网红", "模特"));

		findViewById(R.id.iv_user_info).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserInfoActivity.class));
	}
}
