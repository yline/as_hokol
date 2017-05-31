package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.base.BaseFragment;
import com.yline.view.common.ViewHolder;
import com.yline.widget.label.LabelFlowLayout;

import java.util.Arrays;

public class TaskPublishRightStyleFragment extends BaseFragment
{
	private ViewHolder viewHolder;

	public static TaskPublishRightStyleFragment newInstance()
	{
		Bundle args = new Bundle();
		TaskPublishRightStyleFragment fragment = new TaskPublishRightStyleFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.activity_task_publish__right_style, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		viewHolder = new ViewHolder(view);
		initView();
	}
	
	private void initView()
	{
		LabelFlowLayout labelFlowLayout = viewHolder.get(R.id.label_task_publish_style);
		FlowAbleWidget flowAbleWidget = new FlowAbleWidget(getContext(), labelFlowLayout);
		flowAbleWidget.setDataList(Arrays.asList(HttpEnum.UserTag.Red.getContent(), HttpEnum.UserTag.Author.getContent(),
				HttpEnum.UserTag.Performer.getContent(), HttpEnum.UserTag.Model.getContent(), HttpEnum.UserTag.Singer.getContent(), HttpEnum.UserTag.Sport.getContent()));
	}
}
