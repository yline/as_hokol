package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.application.IApplication;
import com.hokol.wxapi.WXEntryBean;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.log.LogFileUtil;

/**
 * 登入流程；登录和注册选择界面
 *
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class EnterChoiceActivity extends BaseAppCompatActivity
{
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, EnterChoiceActivity.class));
	}
	
	public static void actionStartJump(Context context)
	{
		AppStateManager.getInstance().clearLoginUserInfo(context); // 清除本地数据
		SDKManager.finishActivity();

		// 开启本Activity
		context.startActivity(new Intent(context, EnterChoiceActivity.class));

		// 开启登录页面
		EnterLoginPhonePwdActivity.actionStart(context);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_choice);

		initView();
	}

	private void initView()
	{
		findViewById(R.id.btn_enter_choice_register).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterRegisterPhoneActivity.actionStart(EnterChoiceActivity.this);
			}
		});

		findViewById(R.id.btn_enter_choice_login).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EnterLoginPhonePwdActivity.actionStart(EnterChoiceActivity.this);
			}
		});

		findViewById(R.id.circle_enter_choice_wechat).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				boolean isSuccess = doWcRequest();
				LogFileUtil.v("是否能够拉起来呢 = " + isSuccess);
			}
		});
	}

	/**
	 * 拉起微信授权登录页面
	 */
	private boolean doWcRequest()
	{
		IWXAPI iwxApi = IApplication.getIwxApi();
		if (null != iwxApi && iwxApi.isWXAppInstalled())
		{
			SendAuth.Req req = new SendAuth.Req();
			req.scope = WXEntryBean.APP_SCOPE;
			req.state = WXEntryBean.APP_STATE;
			iwxApi.sendReq(req);

			return true;
		}
		else
		{
			return false;
		}
	}
}
