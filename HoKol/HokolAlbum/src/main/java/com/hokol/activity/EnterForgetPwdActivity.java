package com.hokol.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.common.BaseAppCompatActivity;

public class EnterForgetPwdActivity extends BaseAppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_forget_pwd);

		findViewById(R.id.tv_enter_forget_pwd_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		findViewById(R.id.btn_enter_forget_pwd_commit).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				showDialog();
			}
		});
	}

	private void showDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.enter_forget_pwd_dialog_hint);
		builder.setPositiveButton(R.string.enter_forget_pwd_dialog_commit, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				IApplication.toast("ok");
				EnterForgetPwdNewActivity.actionStart(EnterForgetPwdActivity.this);
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterForgetPwdActivity.class));
	}
}
