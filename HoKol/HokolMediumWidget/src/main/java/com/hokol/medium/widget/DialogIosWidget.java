package com.hokol.medium.widget;

import android.content.Context;

import com.yline.widget.dialog.WidgetDialogCenter;

public class DialogIosWidget extends WidgetDialogCenter
{
	public DialogIosWidget(Context context)
	{
		super(context);
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&提供重写的数据&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */

	/**
	 * 最外层资源文件
	 *
	 * @return
	 */
	protected int getLayoutResourceId()
	{
		return R.layout.widget_dialog_ios;
	}
}
