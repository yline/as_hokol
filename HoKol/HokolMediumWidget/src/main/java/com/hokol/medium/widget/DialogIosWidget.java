package com.hokol.medium.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.yline.widget.dialog.WidgetDialogIos;

public class DialogIosWidget extends WidgetDialogIos
{
	public DialogIosWidget(Context context)
	{
		super(context);
	}

	@Override
	public void show()
	{
		super.show();
	}

	@Override
	protected void initBuilder(WidgetDialogIos.Builder builder)
	{
		super.initBuilder(builder);
	}

	@Override
	protected void initMessageTextView(TextView textView, Builder builder)
	{
		super.initMessageTextView(textView, builder);
	}

	@Override
	protected void initTitleTextView(TextView textView, Builder builder)
	{
		super.initTitleTextView(textView, builder);
	}

	@Override
	protected void initDialog(Dialog dialog)
	{
		super.initDialog(dialog);
	}

	@Override
	protected void initNegativeButton(Button negativeButton, Builder builder)
	{
		super.initNegativeButton(negativeButton, builder);
	}

	@Override
	protected void initPositiveButton(Button positiveButton, Builder builder)
	{
		super.initPositiveButton(positiveButton, builder);
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 提供重写的数据 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */

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
