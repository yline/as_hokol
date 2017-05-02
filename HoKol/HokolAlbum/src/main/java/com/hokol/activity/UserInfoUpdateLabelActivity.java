package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hokol.R;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.base.BaseAppCompatActivity;

import java.util.Arrays;

public class UserInfoUpdateLabelActivity extends BaseAppCompatActivity
{
	private FlowAbleWidget labelClickableWidget;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update_label);
		
		labelClickableWidget = new FlowAbleWidget(this, R.id.label_clickable_layout);
		labelClickableWidget.setDataList(Arrays.asList("网红", "主播", "演员", "模特", "歌手", "体育"));
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserInfoUpdateLabelActivity.class));
	}
}
