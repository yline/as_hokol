package com.hokol.application;

import com.hokol.medium.http.XHttpUtil;
import com.hokol.wxapi.WXEntryBean;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yline.application.BaseApplication;
import com.yline.utils.LogUtil;

/**
 * @author yline 2017/2/8 --> 10:48
 * @version 1.0.0
 */
public class IApplication extends BaseApplication
{
	private static IWXAPI iwxApi;

	public static IWXAPI getIwxApi()
	{
		return iwxApi;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		initWeChat();

		XHttpUtil.init(this);

		// 初始化数据
		HokolInitManager initManager = new HokolInitManager();
		initManager.initData(this);

		// 打印日志
		AppStateManager.getInstance().logAppState(this);
	}

	private void initWeChat()
	{
		iwxApi = WXAPIFactory.createWXAPI(this, WXEntryBean.APP_ID, true);
		boolean isRegister = iwxApi.registerApp(WXEntryBean.APP_ID);
		LogUtil.v("isRegister " + isRegister);
	}
}
