package com.hokol.medium.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.yline.widget.label.LabelFlowLayout;
import com.yline.widget.label.WidgetFlowAble;

/**
 * 流动添加控件，
 *
 * @author yline 2017/4/28 -- 11:06
 * @version 1.0.0
 */
public class FlowAbleWidget extends WidgetFlowAble
{
	public FlowAbleWidget(Activity activity, @IdRes int widgetLayoutId)
	{
		super(activity, widgetLayoutId);
	}

	public FlowAbleWidget(Context context, @NonNull LabelFlowLayout flowLayout)
	{
		super(context, flowLayout);
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 提供重写的方法 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	protected int getItemResourceId()
	{
		return R.layout.widget_item_flow_able;
	}
}
