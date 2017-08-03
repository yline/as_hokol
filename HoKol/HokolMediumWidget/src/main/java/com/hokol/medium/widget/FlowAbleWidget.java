package com.hokol.medium.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.yline.view.label.LabelFlowLayout;
import com.yline.view.label.widget.WidgetFlowAble;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	public void setDataList(Set<String> setData)
	{
		List<String> dataList = new ArrayList<>();
		for (String str : setData)
		{
			dataList.add(str);
		}
		super.setDataList(dataList);
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 提供重写的方法 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	@Override
	protected int getItemResourceId()
	{
		return super.getItemResourceId();
	}
}
