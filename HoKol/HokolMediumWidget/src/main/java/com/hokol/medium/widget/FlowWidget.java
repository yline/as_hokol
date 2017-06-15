package com.hokol.medium.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.yline.view.layout.label.FlowLayout;
import com.yline.view.layout.label.WidgetFlow;

/**
 * 动态添加 控件
 * 不支持点击
 *
 * @author yline 2017/4/28 -- 11:03
 * @version 1.0.0
 */
public class FlowWidget extends WidgetFlow
{
	public FlowWidget(Activity activity, @IdRes int widgetLayoutId)
	{
		super(activity, widgetLayoutId);
	}

	public FlowWidget(Context context, @NonNull FlowLayout flowLayout)
	{
		super(context, flowLayout);
	}
	
	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 提供重写的方法 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	@Override
	protected int getItemResourceId()
	{
		return super.getItemResourceId();
	}
}
