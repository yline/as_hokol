package com.hokol.application;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.application.SDKConfig;

/**
 * Created by yline on 2017/2/7.
 */
public class IApplication extends BaseApplication
{
	public static final String TAG = "DebugLog";

	@Override
	protected SDKConfig initConfig()
	{
		SDKConfig sdkConfig = new SDKConfig();
		sdkConfig.setLogFilePath(TAG);
		return sdkConfig;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
	}
}
