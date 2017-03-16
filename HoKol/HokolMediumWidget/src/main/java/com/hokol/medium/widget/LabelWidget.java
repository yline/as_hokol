package com.hokol.medium.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hokol.medium.widget.labellayout.FlowLayout;
import com.hokol.medium.widget.labellayout.LabelAdapter;
import com.hokol.medium.widget.labellayout.LabelFlowLayout;

import java.util.List;

/**
 * 标签
 *
 * @author yline 2017/3/8 --> 12:28
 * @version 1.0.0
 */
public class LabelWidget
{
	private View view;

	public View start(Context context, List<String> strList)
	{
		LabelFlowLayout labelFlowLayout;
		if (null != getLabelFlowLayout())
		{
			labelFlowLayout = getLabelFlowLayout();
		}
		else
		{
			view = LayoutInflater.from(context).inflate(getResourceId(), null);
			labelFlowLayout = (LabelFlowLayout) view.findViewById(getLabelLayoutId());
		}

		labelFlowLayout.setAdapter(new WidgetLabelAdapter(context, strList));

		return view;
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
			TextView tvItem = (TextView) LayoutInflater.from(sContext).inflate(R.layout.widget_item_label_layout, parent, false);
			tvItem.setText(slist.get(position));
			return tvItem;
		}
	}

	// 修改参数
	protected int getLabelLayoutId()
	{
		return R.id.label_layout_widget;
	}

	protected int getResourceId()
	{
		return R.layout.widget_label_layout;
	}

	protected LabelFlowLayout getLabelFlowLayout()
	{
		return null;
	}
}
