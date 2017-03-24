package com.hokol.medium.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.medium.widget.labellayout.LabelAdapter;
import com.hokol.medium.widget.labellayout.LabelFlowLayout;

import java.util.Deque;
import java.util.List;

/**
 * 标签
 *
 * @author yline 2017/3/8 --> 12:28
 * @version 1.0.0
 */
public class LabelWidget
{
	private Context sContext;

	private View containerView;

	private LabelFlowLayout labelFlowLayout;

	private WidgetLabelAdapter widgetLabelAdapter;

	public LabelWidget(Context context)
	{
		this.sContext = context;
		if (null != getLabelFlowLayout())
		{
			labelFlowLayout = getLabelFlowLayout();
		}
		else
		{
			containerView = LayoutInflater.from(context).inflate(R.layout.widget_label_layout, null);
			labelFlowLayout = (LabelFlowLayout) containerView.findViewById(R.id.label_layout_widget);
		}

		widgetLabelAdapter = new WidgetLabelAdapter();
		labelFlowLayout.setAdapter(widgetLabelAdapter);
	}

	public void setDataList(List<String> dataList)
	{
		widgetLabelAdapter.setDataList(dataList);
	}

	public Deque<Integer> getSelectedList()
	{
		return widgetLabelAdapter.getSelectedList();
	}

	public int getSelectedFirstPosition()
	{
		return widgetLabelAdapter.getSelectedFirst();
	}

	public String getSelectedFirstContent()
	{
		int position = widgetLabelAdapter.getSelectedFirst();
		return widgetLabelAdapter.getItem(position);
	}

	public View getView()
	{
		return containerView;
	}

	/**
	 * 设置最大显示个数
	 *
	 * @param count
	 */
	public void setMaxSelectCount(int count)
	{
		labelFlowLayout.setMaxSelectCount(count);
	}

	/**
	 * 设置最小显示个数
	 *
	 * @param count
	 */
	public void setMinSelectCount(int count)
	{
		labelFlowLayout.setMinSelectCount(count);
	}

	/**
	 * 增加一个选项
	 *
	 * @param position
	 */
	public void addSelectedPosition(int position)
	{
		widgetLabelAdapter.addSelectedPosition(position);
	}

	/**
	 * 设置每行，最大个数（如果长度，超过了也会换行）
	 *
	 * @param maxCountEachLine
	 */
	public void setMaxCountEachLine(int maxCountEachLine)
	{
		labelFlowLayout.setMaxCountEachLine(maxCountEachLine);
	}

	private class WidgetLabelAdapter extends LabelAdapter<String>
	{
		@Override
		public View getView(FlowLayout container, String s, int position)
		{
			LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(sContext).inflate(getItemResourceId(), container, false);

			TextView itemView = (TextView) linearLayout.findViewById(R.id.tv_widget_item_label);
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemView.getLayoutParams();
			layoutParams.leftMargin = getLeftMargin();
			layoutParams.rightMargin = getRightMargin();
			layoutParams.topMargin = getTopMargin();
			layoutParams.bottomMargin = getBottomMargin();

			itemView.setText(s);
			itemView.setLayoutParams(layoutParams);

			return linearLayout;
		}
	}

	/* --------------------------------------------- 修改参数 --------------------------------------------- */

	/**
	 * 返回控件自身(如果有返回这个,则修改控件资源、修改控件id无效)
	 *
	 * @return
	 */
	protected LabelFlowLayout getLabelFlowLayout()
	{
		return null;
	}

	/**
	 * 获取子控件的,layout,资源
	 *
	 * @return
	 */
	protected int getItemResourceId()
	{
		return R.layout.widget_item_label_layout;
	}

	protected int getLeftMargin()
	{
		return 0;
	}

	protected int getRightMargin()
	{
		return 0;
	}

	protected int getTopMargin()
	{
		return 0;
	}

	protected int getBottomMargin()
	{
		return 0;
	}
}
