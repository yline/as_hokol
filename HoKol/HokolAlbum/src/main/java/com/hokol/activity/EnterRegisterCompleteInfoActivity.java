package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.LabelClickableWidget;
import com.hokol.medium.widget.labellayout.LabelFlowLayout;
import com.hokol.medium.widget.labellayout.LabelView;

import java.util.Arrays;

public class EnterRegisterCompleteInfoActivity extends BaseAppCompatActivity
{
	private Button btnCommit;

	private LabelFlowLayout labelFlowLayout;

	private LabelView labelView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_register_complete_info);

		btnCommit = (Button) findViewById(R.id.btn_enter_register_complete_info_commit);
		labelFlowLayout = (LabelFlowLayout) findViewById(R.id.label_flow_enter_register_complete_info);
		labelView = (LabelView) findViewById(R.id.label_enter_register_complete_info);

		LabelClickableWidget labelClickableWidget = new LabelClickableWidget(this)
		{
			@Override
			protected LabelFlowLayout getLabelFlowLayout()
			{
				return labelFlowLayout;
			}
		};
		String[] dataList = new String[]{"网红", "主播", "演员", "模特", "歌手", "体育"};
		labelClickableWidget.setDataList(Arrays.asList(dataList));
		labelClickableWidget.setMaxCountEachLine(3);

		labelView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				labelView.setChecked(!labelView.isChecked());
			}
		});

		btnCommit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("进入主页，登入成功");
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterRegisterCompleteInfoActivity.class));
	}
}
