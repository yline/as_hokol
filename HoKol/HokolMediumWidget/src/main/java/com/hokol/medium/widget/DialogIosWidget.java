package com.hokol.medium.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yline.view.dialog.ViewDialogCenter;

public class DialogIosWidget extends ViewDialogCenter
{
	public DialogIosWidget(Context context)
	{
		super(context);
	}

	@Override
	protected void initXView(TextView tvTitle, TextView tvMsg, Button btnNegative, Button btnPositive, Dialog dialog)
	{
		super.initXView(tvTitle, tvMsg, btnNegative, btnPositive, dialog);
		tvMsg.setVisibility(View.GONE);
	}

	@Override
	protected int getXLayoutId()
	{
		return R.layout.widget_dialog_ios;
	}

	@Override
	protected int getXDialogStyle()
	{
		return super.getXDialogStyle();
	}
}
