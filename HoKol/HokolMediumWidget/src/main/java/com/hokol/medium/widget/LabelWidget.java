package com.hokol.medium.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hokol.medium.widget.labellayout.FlowLayout;

import java.util.List;

/**
 * 标签控件,不可点击
 *
 * @author yline 2017/3/31 -- 9:20
 * @version 1.0.0
 */
public class LabelWidget
{
	private Context sContext;

	private FlowLayout flowLayout;

	private List<String> dataList;

	public LabelWidget(Context context, FlowLayout flowLayout)
	{
		this.sContext = context;
		this.flowLayout = flowLayout;
	}

	public void setDataList(List<String> dataList)
	{
		this.dataList = dataList;
		flowLayout.removeAllViews();
		for (int i = 0; i < dataList.size(); i++)
		{
			View itemView = LayoutInflater.from(sContext).inflate(R.layout.widget_item_label_layout, flowLayout, false);
			TextView textView = (TextView) itemView.findViewById(R.id.tv_widget_item_label);
			textView.setText(dataList.get(i));

			flowLayout.addView(itemView);
		}
	}

	public void addData(String string)
	{
		View itemView = LayoutInflater.from(sContext).inflate(R.layout.widget_item_label_layout, flowLayout, false);
		TextView textView = (TextView) itemView.findViewById(R.id.tv_widget_item_label);
		textView.setText(string);

		dataList.add(string);
		flowLayout.addView(itemView);
	}

	public void addData(int index, String string)
	{
		View itemView = LayoutInflater.from(sContext).inflate(R.layout.widget_item_label_layout, flowLayout, false);
		TextView textView = (TextView) itemView.findViewById(R.id.tv_widget_item_label);
		textView.setText(string);

		dataList.add(string);
		flowLayout.addView(itemView, index);
	}

	public void removeView(int index)
	{
		View itemView = flowLayout.getChildAt(index);

		dataList.remove(index);
		flowLayout.removeView(itemView);
	}

	public boolean removeView(String string)
	{
		int index = -1;
		for (int i = 0; i < dataList.size(); i++)
		{
			if (string.equalsIgnoreCase(dataList.get(i)))
			{
				index = i;
				break;
			}
		}

		if (-1 != index)
		{
			View itemView = flowLayout.getChildAt(index);

			dataList.remove(index);
			flowLayout.removeView(itemView);

			return true;
		}

		return false;
	}
}
