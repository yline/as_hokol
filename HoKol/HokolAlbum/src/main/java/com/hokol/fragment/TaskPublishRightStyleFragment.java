package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.WTaskMainPublishBean;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.view.common.ViewHolder;
import com.yline.widget.label.LabelFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskPublishRightStyleFragment extends BaseFragment
{
	private FlowAbleWidget flowAbleWidget;

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
		initViewClick();
	}

	private void initView()
	{
		LabelFlowLayout labelFlowLayout = viewHolder.get(R.id.label_task_publish_style);
		flowAbleWidget = new FlowAbleWidget(getContext(), labelFlowLayout)
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.activity_task_publish__right_style_item;
			}
		};
		flowAbleWidget.setMaxCountEachLine(3);
		flowAbleWidget.setDataList(Arrays.asList(HttpEnum.UserTag.Red.getContent(), HttpEnum.UserTag.Author.getContent(),
				HttpEnum.UserTag.Performer.getContent(), HttpEnum.UserTag.Model.getContent(), HttpEnum.UserTag.Singer.getContent(), HttpEnum.UserTag.Sport.getContent()));
		flowAbleWidget.getSelectedList();
	}

	private void initViewClick()
	{
		// 取消
		viewHolder.setOnClickListener(R.id.btn_publish_right_style_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != rightStyleCallback)
				{
					rightStyleCallback.onRightStyleCancel();
				}
			}
		});
		// 完成
		viewHolder.setOnClickListener(R.id.btn_publish_right_style_confirm, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != rightStyleCallback)
				{
					List<Integer> taskType = new ArrayList<>();
					for (Integer integer : flowAbleWidget.getSelectedList())
					{
						taskType.add(integer);
					}

					int girlNum = parseTextInt((TextView) viewHolder.get(R.id.et_task_publish_style_girl));
					int boyNum = parseTextInt((TextView) viewHolder.get(R.id.et_task_publish_style_boy));

					if (isDataEnough(taskType, boyNum, girlNum))
					{
						rightStyleCallback.onRightStyleConfirm(taskType, boyNum, girlNum);
					}
					else
					{
						SDKManager.toast("信息填写不正确");
					}
				}
			}
		});
	}

	private int parseTextInt(TextView textView)
	{
		String result = textView.getText().toString().trim();
		if (!TextUtils.isEmpty(result))
		{
			return Integer.parseInt(result);
		}
		else
		{
			return WTaskMainPublishBean.ValueErrorInt;
		}
	}

	private boolean isDataEnough(List<Integer> taskType, int boyNum, int girlNum)
	{
		if (0 == taskType.size() || WTaskMainPublishBean.ValueErrorInt == boyNum || WTaskMainPublishBean.ValueErrorInt == girlNum)
		{
			return false;
		}
		else if (boyNum + girlNum <= 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	private OnPublishRightStyleCallback rightStyleCallback;

	public void setOnRightStyleCallback(OnPublishRightStyleCallback rightStyleCallback)
	{
		this.rightStyleCallback = rightStyleCallback;
	}

	public interface OnPublishRightStyleCallback
	{
		void onRightStyleCancel();

		void onRightStyleConfirm(List<Integer> taskType, int boyNum, int girlNum);
	}
}
