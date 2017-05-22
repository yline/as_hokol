package com.hokol.medium.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.yline.view.common.ViewHolder;
import com.yline.widget.dialog.WidgetDialogFoot;

import java.util.List;

/**
 * 底部出现弹框,仿IOS
 *
 * @author yline 2017/4/11 -- 10:54
 * @version 1.0.0
 */
public class DialogFootWidget extends WidgetDialogFoot
{
	public DialogFootWidget(Context context, @NonNull List<String> dataList)
	{
		super(context, dataList);
	}

	@Override
	public void setOnSelectedListener(WidgetDialogFoot.OnSelectedListener onSelectedListener)
	{
		super.setOnSelectedListener(onSelectedListener);
	}

	@Override
	public void show()
	{
		super.show();
	}

	@Override
	public void show(WidgetDialogFoot.OnSelectedListener onSelectedListener)
	{
		super.show(onSelectedListener);
	}

	@Override
	public void show(WidgetDialogFoot.OnSelectedListener onSelectedListener, DialogInterface.OnDismissListener dismissListener)
	{
		super.show(onSelectedListener, dismissListener);
	}

	@Override
	public void show(WidgetDialogFoot.OnSelectedListener onSelectedListener, DialogInterface.OnDismissListener dismissListener, DialogInterface.OnShowListener showListener)
	{
		super.show(onSelectedListener, dismissListener, showListener);
	}

	@Override
	public ViewHolder getViewHolder()
	{
		return super.getViewHolder();
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 重写 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	protected int getResourceId()
	{
		return R.layout.widget_dialog_foot_radius;
	}
}
