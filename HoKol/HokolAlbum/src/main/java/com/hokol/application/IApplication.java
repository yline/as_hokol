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

		// 打印日志
		AppStateManager.getInstance().logAppState(this);

		// 初始化数据
		HokolInitManager initManager = new HokolInitManager();
		initManager.initData(this);
	}
}
