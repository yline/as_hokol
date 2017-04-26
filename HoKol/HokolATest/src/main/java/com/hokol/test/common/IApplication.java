package com.hokol.test.common;

import com.hokol.medium.http.XHttpUtil;
import com.yline.application.BaseApplication;

public class IApplication extends BaseApplication
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		XHttpUtil.init(this);
	}
}
