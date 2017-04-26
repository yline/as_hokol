package com.hokol.application;

import com.hokol.medium.http.XHttpUtil;
import com.yline.application.BaseApplication;

/**
 * @author yline 2017/2/8 --> 10:48
 * @version 1.0.0
 */
public class IApplication extends BaseApplication
{
	@Override
	public void onCreate()
	{
		super.onCreate();

		XHttpUtil.init(this);
	}
}
