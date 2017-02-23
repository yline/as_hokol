package com.hokol.application;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.application.SDKConfig;

/**
 * @author yline 2017/2/8 --> 10:48
 * @version 1.0.0
 */
public class IApplication extends BaseApplication
{
	public static final String TAG = "DebugLog";
	
	@Override
	protected SDKConfig initConfig()
	{
		SDKConfig sdkConfig = new SDKConfig();
		sdkConfig.setLogFilePath(TAG);
		sdkConfig.setLogSystem(true);
		sdkConfig.setLog(false);
		return sdkConfig;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
}
