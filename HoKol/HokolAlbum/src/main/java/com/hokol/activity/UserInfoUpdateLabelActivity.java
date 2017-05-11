package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.widget.FlowAbleWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.medium.widget.labellayout.LabelAdapter;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.ViewHolder;

import java.util.Arrays;
import java.util.Deque;

public class UserInfoUpdateLabelActivity extends BaseAppCompatActivity
{
	private FlowAbleWidget labelClickableWidget;

	private ViewHolder viewHolder;

	private static final String LABEL_TEXT = "已添加(%d/2)";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update_label);

		viewHolder = new ViewHolder(this);

		viewHolder.setText(R.id.tv_label_info, String.format(LABEL_TEXT, 1));
		
		labelClickableWidget = new FlowAbleWidget(this, R.id.label_clickable_layout)
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.activity_user_info__flow_able;
			}
		};
		labelClickableWidget.setMaxCountEachLine(4); // 每行4个
		labelClickableWidget.setMaxSelectCount(2); // 最多选择两个
		labelClickableWidget.setLabelGravity(FlowLayout.LabelGravity.EQUIDISTANT);
		labelClickableWidget.setDataList(Arrays.asList("网红", "主播", "演员", "模特", "歌手", "体育"));
		labelClickableWidget.setOnLabelSelectListener(new LabelAdapter.OnLabelSelectListener()
		{
			@Override
			public void onLabelSelected(Deque selectedDeque)
			{
				viewHolder.setText(R.id.tv_label_info, String.format(LABEL_TEXT, selectedDeque.size()));
			}
		});

		viewHolder.setOnClickListener(R.id.iv_back, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		viewHolder.setOnClickListener(R.id.tv_finish, new View.OnClickListener()
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
		context.startActivity(new Intent(context, UserInfoUpdateLabelActivity.class));
	}
}
