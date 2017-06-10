package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.view.common.ViewHolder;
import com.yline.widget.label.FlowLayout;
import com.yline.widget.label.LabelAdapter;
import com.yline.widget.label.LabelFlowLayout;

import java.util.List;
import java.util.Map;

public class TaskPublishRightAreaFragment extends BaseFragment
{
	private ViewHolder viewHolder;

	private FlowAbleWidget ableWidget;

	private Map<String, List<String>> areaResult;

	private boolean isFirstArea;

	public static TaskPublishRightAreaFragment newInstance()
	{
		Bundle args = new Bundle();
		
		TaskPublishRightAreaFragment fragment = new TaskPublishRightAreaFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_task_publish__right_area, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		viewHolder = new ViewHolder(view);

		initView();
		initData();
	}

	private void initView()
	{
		final LabelFlowLayout areaFlowLayout = viewHolder.get(R.id.label_flow_task_publish_area);
		ableWidget = new FlowAbleWidget(getContext(), areaFlowLayout)
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.fragment_task_publish__right_area_item;
			}
		};
		ableWidget.setMaxSelectCount(1);
		ableWidget.setMinSelectCount(1);
		ableWidget.setOnLabelClickListener(new LabelAdapter.OnLabelClickListener<String>()
		{
			@Override
			public boolean onLabelClick(FlowLayout container, View view, String str, int position)
			{
				if (isFirstArea)
				{
					isFirstArea = false;

					ableWidget.clearSelectedPosition();
					List<String> secondList = areaResult.get(str);
					ableWidget.setDataList(secondList);

					viewHolder.get(R.id.rl_area_choose_back).setVisibility(View.VISIBLE);
					viewHolder.get(R.id.tv_area_choose_back).setVisibility(View.VISIBLE);
					viewHolder.setText(R.id.tv_area_choose_province, str);
				}
				else
				{
					SDKManager.toast(str);
				}
				return false;
			}
		});

		viewHolder.setOnClickListener(R.id.tv_area_choose_back, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!isFirstArea)
				{
					isFirstArea = true;
					
					ableWidget.clearSelectedPosition();
					ableWidget.setDataList(areaResult.keySet());

					viewHolder.get(R.id.rl_area_choose_back).setVisibility(View.INVISIBLE);
					viewHolder.setText(R.id.tv_area_choose_province, "");
					viewHolder.get(R.id.tv_area_choose_back).setVisibility(View.INVISIBLE);
				}
			}
		});
	}

	private void initData()
	{
		XHttpUtil.doAreaAll(new XHttpAdapter<VAreaAllBean>()
		{
			@Override
			public void onSuccess(VAreaAllBean vAreaAllBean)
			{
				areaResult = vAreaAllBean.getWidgetMap();
				if (null != areaResult)
				{
					ableWidget.clearSelectedPosition();
					ableWidget.setDataList(areaResult.keySet());
					isFirstArea = true;
				}
				else
				{
					LogFileUtil.v("area result is null");
				}
			}
		});
	}
}
