package com.hokol.base.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hokol.base.log.LogFileUtil;


public class BaseReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		LogFileUtil.m("BaseReceiver -> " + this.getClass().getSimpleName());
	}

}
