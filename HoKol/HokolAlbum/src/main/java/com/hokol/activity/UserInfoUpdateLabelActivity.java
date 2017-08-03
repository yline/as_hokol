package com.hokol.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.label.FlowLayout;
import com.yline.view.label.LabelAdapter;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class UserInfoUpdateLabelActivity extends BaseAppCompatActivity
{
	private static final String LABEL_TEXT = "已添加(%d/2)";

	private static final String KeyInitData = "InitData";

	private FlowAbleWidget labelClickableWidget;

	private ViewHolder viewHolder;

	public static void actionStart(Activity activity, int requestCode, ArrayList<Integer> initData)
	{
		Intent intent = new Intent(activity, UserInfoUpdateLabelActivity.class);
		intent.putIntegerArrayListExtra(KeyInitData, initData);
		activity.startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_update_label);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
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
		labelClickableWidget.setDataList(HttpEnum.getUserTagList());
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
		viewHolder.setOnClickListener(R.id.tv_sava, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ArrayList<Integer> dataList = new ArrayList<>();
				Deque<Integer> selectPositionDeque = labelClickableWidget.getSelectedList();
				if (selectPositionDeque.size() > 0)
				{
					for (Integer selectPosition : selectPositionDeque)
					{
						dataList.add(selectPosition + 1);
					}
					UserInfoActivity.actionResultUpdateIntList(UserInfoUpdateLabelActivity.this, dataList);

					finish();
				}
				else
				{
					SDKManager.toast("请选择您的标签");
				}
			}
		});
	}
	
	private void initData()
	{
		List<Integer> initDataList = getIntent().getIntegerArrayListExtra(KeyInitData); // 网络传输中的 对应的 int值
		ArrayList<Integer> selectPositionList = new ArrayList<>();
		for (int i = 0; i < initDataList.size(); i++)
		{
			selectPositionList.add(initDataList.get(i) - 1);
		}
		labelClickableWidget.addAllSelectedPosition(selectPositionList); // 设置我们所需要的位置
	}
}
