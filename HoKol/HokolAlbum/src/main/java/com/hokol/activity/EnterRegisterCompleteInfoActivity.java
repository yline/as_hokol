package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.common.ViewHolder;
import com.yline.widget.label.FlowLayout;
import com.yline.widget.label.LabelAdapter;

import java.util.Arrays;
import java.util.Deque;

public class EnterRegisterCompleteInfoActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_register_complete_info);

		viewHolder = new ViewHolder(this);

		FlowAbleWidget flowAbleWidget = new FlowAbleWidget(this, R.id.label_flow_enter_register_complete_info)
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.activity_user_info__flow_able;
			}
		};
		flowAbleWidget.setMaxCountEachLine(4); // 每行4个
		flowAbleWidget.setMaxSelectCount(2); // 最多选择两个
		flowAbleWidget.setLabelGravity(FlowLayout.LabelGravity.EQUIDISTANT);
		flowAbleWidget.setDataList(Arrays.asList("网红", "主播", "演员", "模特", "歌手", "体育", "其它"));
		flowAbleWidget.addSelectedPosition(0);
		flowAbleWidget.setOnLabelSelectListener(new LabelAdapter.OnLabelSelectListener()
		{
			@Override
			public void onLabelSelected(Deque selectedDeque)
			{
				int length = selectedDeque.size();
				viewHolder.setText(R.id.tv_enter_register_complete_info_label, String.format("我的标签(%d/2)", length));
			}
		});

		viewHolder.setOnClickListener(R.id.btn_enter_register_complete_info_commit, new View.OnClickListener()
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
