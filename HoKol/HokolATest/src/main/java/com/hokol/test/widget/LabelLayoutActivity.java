package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.LabelWidget;

import java.util.Arrays;

public class LabelLayoutActivity extends BaseAppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		
		LabelWidget labelWidget = new LabelWidget();
		labelWidget.start(this, Arrays.asList("标签 -> ", "网红", "模特", "歌手"));
		labelWidget.attach(linearLayout);
		
		setContentView(linearLayout);
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, LabelLayoutActivity.class));
	}
}
