package com.hokol.medium.widget;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yline.view.dialog.ViewDialogFoot;

import java.util.List;

/**
 * 底部出现弹框,仿IOS
 *
 * @author yline 2017/4/11 -- 10:54
 * @version 1.0.0
 */
public class DialogFootWidget extends ViewDialogFoot
{
	public DialogFootWidget(Context context, @NonNull List<String> dataList)
	{
		super(context, dataList);
	}

	@Override
	protected int getXDialogStyle()
	{
		return super.getXDialogStyle();
	}
	
	@Override
	protected int getXLayoutId()
	{
		return R.layout.widget_dialog_foot_radius;
	}
}
