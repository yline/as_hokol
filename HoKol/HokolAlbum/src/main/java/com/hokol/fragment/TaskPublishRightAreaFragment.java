package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.base.BaseFragment;
import com.yline.log.LogFileUtil;
import com.yline.view.label.FlowLayout;
import com.yline.view.label.LabelAdapter;
import com.yline.view.label.LabelFlowLayout;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskPublishRightAreaFragment extends BaseFragment
{
	public final static String ValueAllChoice = "不限";

	private ViewHolder viewHolder;

	private FlowAbleWidget ableWidget;

	private VAreaAllBean areaAllBean;

	private Map<String, List<String>> areaResult;

	private String pName, cName;

	private OnPublishRightAreaCallback onPublishAreaCallback;
	
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
		initViewClick();
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
				if (TextUtils.isEmpty(pName))
				{
					if (ValueAllChoice != str)
					{
						ableWidget.clearSelectedPosition();

						pName = str;
						List<String> cityData = new ArrayList<>(areaResult.get(str));
						cityData.add(0, ValueAllChoice);
						ableWidget.setDataList(cityData);

						viewHolder.get(R.id.rl_area_choose_back).setVisibility(View.VISIBLE);
						viewHolder.get(R.id.tv_area_choose_back).setVisibility(View.VISIBLE);
						viewHolder.setText(R.id.tv_area_choose_province, str);
					}
				}
				else
				{
					if (ValueAllChoice == str)
					{
						// do nothing
					}
					else
					{
						cName = str;
					}
				}
				return false;
			}
		});

		// 返回按钮
		viewHolder.setOnClickListener(R.id.tv_area_choose_back, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(pName))
				{
					pName = null;
					ableWidget.clearSelectedPosition();
					List<String> provinceData = new ArrayList<>();
					provinceData.add(0, ValueAllChoice);
					for (String provinceStr : areaResult.keySet())
					{
						provinceData.add(provinceStr);
					}
					ableWidget.setDataList(provinceData);

					viewHolder.get(R.id.rl_area_choose_back).setVisibility(View.GONE);
					viewHolder.setText(R.id.tv_area_choose_province, "");
					viewHolder.get(R.id.tv_area_choose_back).setVisibility(View.GONE);
				}
			}
		});
	}

	private void initViewClick()
	{
		// 取消
		viewHolder.setOnClickListener(R.id.btn_area_result_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onPublishAreaCallback)
				{
					onPublishAreaCallback.onRightAreaCancel();
				}
			}
		});
		// 完成
		viewHolder.setOnClickListener(R.id.btn_area_result_confirm, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onPublishAreaCallback)
				{
					String pCode = areaAllBean.getProvinceCode(pName);
					String cCode = areaAllBean.getCityCode(pName, cName);
					if (null != onPublishAreaCallback)
					{
						onPublishAreaCallback.onRightAreaConfirm(pCode, pName, cCode, cName);
					}
				}
			}
		});
	}

	private void initData()
	{
		XHttpUtil.doAreaAll(new HokolAdapter<VAreaAllBean>()
		{
			@Override
			public void onSuccess(VAreaAllBean vAreaAllBean)
			{
				areaAllBean = vAreaAllBean;
				areaResult = vAreaAllBean.getWidgetMap();
				if (null != areaResult)
				{
					pName = null;
					ableWidget.clearSelectedPosition();

					List<String> provinceData = new ArrayList<>();
					provinceData.add(0, ValueAllChoice);
					for (String provinceStr : areaResult.keySet())
					{
						provinceData.add(provinceStr);
					}
					ableWidget.setDataList(provinceData);
				}
				else
				{
					LogFileUtil.v("area result is null");
				}
			}
		});
	}

	public void setOnPublishAreaCallback(OnPublishRightAreaCallback onPublishAreaCallback)
	{
		this.onPublishAreaCallback = onPublishAreaCallback;
	}
	
	public interface OnPublishRightAreaCallback
	{
		void onRightAreaCancel();

		/**
		 * 获取用户选择信息
		 *
		 * @param pCode
		 * @param pName
		 * @param cCode
		 * @param cName
		 */
		void onRightAreaConfirm(String pCode, String pName, String cCode, String cName);
	}
}
