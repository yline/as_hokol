package com.hokol.test;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.application.SDKConfig;

public class IApplication extends BaseApplication
{
	public static final String TAG = "HokolATest";
	
	@Override
	protected SDKConfig initConfig()
	{
		SDKConfig sdkConfig = new SDKConfig();
		sdkConfig.setLogFilePath(TAG);
		return sdkConfig;
	}
}
