package com.hokol.medium.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.medium.widget.labellayout.LabelAdapter;
import com.hokol.medium.widget.labellayout.LabelFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签
 *
 * @author yline 2017/3/8 --> 12:28
 * @version 1.0.0
 */
public class LabelWidget
{
	private static final int ERROR = -1;

	private LabelFlowLayout labelFlowLayout;

	private WidgetLabelAdapter widgetLabelAdapter;

	public View start(Context context, List<String> strList)
	{
		View view = null;
		if (null != getLabelFlowLayout())
		{
			labelFlowLayout = getLabelFlowLayout();
		}
		else
		{
			view = LayoutInflater.from(context).inflate(R.layout.widget_label_layout, null);
			labelFlowLayout = (LabelFlowLayout) view.findViewById(R.id.label_layout_widget);
		}

		widgetLabelAdapter = new WidgetLabelAdapter(context, strList);
		labelFlowLayout.setAdapter(widgetLabelAdapter);

		return view;
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
	 * 设置已选择项
	 *
	 * @param data
	 */
	public void setSelectedList(int... data)
	{
		widgetLabelAdapter.setSelectedList(data);
	}

	/**
	 * 设置点击事件
	 *
	 * @param onTagClickListener
	 */
	public void setOnTagClickListener(LabelFlowLayout.OnTagClickListener onTagClickListener)
	{
		labelFlowLayout.setOnTagClickListener(onTagClickListener);
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

	/**
	 * 判断，被选择的项，被选择之后的操作
	 *
	 * @param onSelectListener
	 */
	public void setOnSelectListener(LabelFlowLayout.OnSelectListener onSelectListener)
	{
		labelFlowLayout.setOnSelectListener(onSelectListener);
	}

	/**
	 * 返回 被选择的项
	 *
	 * @return
	 */
	public List<Integer> getSelectedList()
	{
		List<Integer> list = new ArrayList<>();
		for (Integer position : labelFlowLayout.getSelectedList())
		{
			list.add(position);
		}
		return list;
	}

	private class WidgetLabelAdapter extends LabelAdapter<String>
	{
		private Context sContext;

		public WidgetLabelAdapter(Context context, List<String> data)
		{
			super(data);
			this.sContext = context;
		}

		@Override
		public View getView(FlowLayout parent, int position, String s)
		{
			TextView itemView = (TextView) LayoutInflater.from(sContext).inflate(getItemResourceId(), parent, false);
			itemView.setText(slist.get(position));
			return itemView;
		}

		@Override
		public boolean setSelected(int position, String s)
		{
			return getSelected(position, s);
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

	/**
	 * 默认选择项(每一项都会单独判断)
	 *
	 * @param position 位置
	 * @param s        字符
	 * @return false, 不选择, true,选择
	 */
	protected boolean getSelected(int position, String s)
	{
		return false;
	}
}
