package com.hokol.wxapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.hokol.application.IApplication;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.yline.application.BaseApplication;
import com.yline.log.LogFileUtil;

/**
 * 05:53:35:28:A5:7A:E6:35:20:F8:B2:99:4F:3D:73:69
 *
 * @author yline 2017/7/13 -- 22:26
 * @version 1.0.0
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler
{
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		BaseApplication.addActivity(this);

		// 通过WXAPIFactory工厂，获取IWXAPI的实例
		IApplication.getIwxApi().handleIntent(getIntent(), this);
		// progressDialog = new ProgressDialog(this);
	}

	/**
	 * 请求前，执行
	 * 发送的请求将回调到onReq方法
	 *
	 * @param baseReq
	 */
	@Override
	public void onReq(BaseReq baseReq)
	{
		LogFileUtil.v("onReq openId = " + baseReq.openId + ", transaction = " + baseReq.transaction + ", Type = " + baseReq.getType());
	}

	/**
	 * 返回结果，执行
	 * 发送到微信请求的响应结果将回调到onResp方法
	 *
	 * @param baseResp
	 */
	@Override
	public void onResp(BaseResp baseResp)
	{
		LogFileUtil.v("onResp Type = " + baseResp.getType() + ", errCode = " + baseResp.errCode + ". errStr = " + baseResp.errStr + ", openId = " + baseResp.openId + ", transaction = " + baseResp.transaction);
		// progressDialog.show();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		BaseApplication.removeActivity(this);
	}
}
