package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.test.R;

import java.util.Arrays;

public class LabelLayoutActivity extends BaseAppCompatActivity
{
	private LabelFlowLayout labelFlowLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_label_layout);

		labelFlowLayout = (LabelFlowLayout) findViewById(R.id.label_layout);
		
		LabelWidget labelWidget = new LabelWidget()
		{
			@Override
			protected LabelFlowLayout getLabelFlowLayout()
			{
				return labelFlowLayout;
			}
		};
		labelWidget.start(this, Arrays.asList("标签", "网红", "模特", "歌手", "体育", "咸蛋", "金刚", "二货", "逼哥", "主宰", "修补", "天空", "海水"));
		labelWidget.setMaxSelectCount(3);
		labelWidget.setMaxCountEachLine(3);
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, LabelLayoutActivity.class));
	}
}
