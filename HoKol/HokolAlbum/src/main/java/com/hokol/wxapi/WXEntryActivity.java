package com.hokol.wxapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.hokol.activity.EnterLoginThirdActivity;
import com.hokol.activity.MainActivity;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VWeChatLoginBean;
import com.hokol.medium.http.bean.VWeChatLoginFirstBean;
import com.hokol.medium.http.bean.WWeChatLoginBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.yline.application.BaseApplication;
import com.yline.application.SDKManager;
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

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, WXEntryActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		BaseApplication.addActivity(this);

		// 通过WXAPIFactory工厂，获取IWXAPI的实例
		IApplication.getIwxApi().handleIntent(getIntent(), this);
		progressDialog = new ProgressDialog(this); // R.style.Widget_Dialog_Default
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage("正在加载...");
		progressDialog.show();
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
		switch (baseResp.errCode)
		{
			case 0:
				final SendAuth.Resp resp = (SendAuth.Resp) baseResp;
				LogFileUtil.v("onResp resp code = " + resp.code + ", state = " + resp.state + ", lang = " + resp.lang + ", country = " + resp.country);
				XHttpUtil.doWeChatLogin(new WWeChatLoginBean(resp.code), new HokolAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						VWeChatLoginBean vWeChatLoginBean = new Gson().fromJson(s, VWeChatLoginBean.class);
						MainActivity.actionStart(WXEntryActivity.this, vWeChatLoginBean);
						IApplication.finishActivity();
					}

					@Override
					public void onSuccess(int code, String s)
					{
						super.onSuccess(code, s);
						// 是注册的逻辑
						if (WWeChatLoginBean.TypeRegister == code)
						{
							VWeChatLoginFirstBean result = new Gson().fromJson(s, VWeChatLoginFirstBean.class);
							if (null != result)
							{
								EnterLoginThirdActivity.actionStart(WXEntryActivity.this, result.getUser_id());
							}
						}
						dismissDialog();
					}

					@Override
					public void onFailure(Exception ex, boolean isDebug)
					{
						super.onFailure(ex, isDebug);
						SDKManager.toast("登录失败，请检查网络");
						dismissDialog();
					}
				});
				break;
			case -2: // -2 用户取消
				LogFileUtil.v("onResp Type = -2, canceled");
				SDKManager.toast("已取消登录");
				finish();
				break;
			case -4: // -4 用户拒绝授权
				LogFileUtil.v("onResp Type = -4, refused");
				SDKManager.toast("已拒接授权");
				finish();
				break;
		}
	}

	private void dismissDialog()
	{
		if (null != progressDialog && progressDialog.isShowing())
		{
			progressDialog.dismiss();
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		dismissDialog();
		BaseApplication.removeActivity(this);
	}
}
