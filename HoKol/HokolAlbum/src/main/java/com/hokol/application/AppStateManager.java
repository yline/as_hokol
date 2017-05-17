package com.hokol.application;

import android.content.Context;

import com.yline.utils.SPUtil;

/**
 * App状态 管理
 * 1，登录、未登录
 * 2，退出登录时长、校验是否需要登录
 *
 * @author yline 2017/5/16 -- 17:00
 * @version 1.0.0
 */
public class AppStateManager
{
	private static final String FileName = "HokolState";

	private static final String KeyUserLogin = "UserLogin";

	private static final String KeyUserLoginId = "UserLoginId";

	private AppStateManager()
	{
	}

	public static AppStateManager getInstance()
	{
		return AppStateManagerHolder.appStateManager;
	}

	private static class AppStateManagerHolder
	{
		private static AppStateManager appStateManager = new AppStateManager();
	}

	public void updateUserLogin(Context context, boolean isLogin)
	{
		SPUtil.put(context, KeyUserLogin, isLogin, FileName);
	}

	public boolean isUserLogin(Context context)
	{
		return (boolean) SPUtil.get(context, KeyUserLogin, false, FileName);
	}
	
	public String getUserLoginId(Context context)
	{
		if (isUserLogin(context))
		{
			return (String) SPUtil.get(context, KeyUserLoginId, null, FileName);
		}
		else
		{
			return null;
		}
	}
}
