package com.hokol.application;

import android.content.Context;

import com.google.gson.Gson;
import com.hokol.medium.http.bean.VEnterLoginPhonePwdBean;
import com.yline.utils.LogUtil;
import com.yline.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import static com.yline.utils.SPUtil.get;

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

	private static final String KeyUserInfo = "UserInfo";

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

	public void setLoginUserInfo(Context context, AppUserInfo userInfo)
	{
		String jsonUserInfo = new Gson().toJson(userInfo);
		SPUtil.put(context, KeyUserInfo, jsonUserInfo, FileName);

		// 常用的
		SPUtil.put(context, KeyUserLogin, true, FileName);
		SPUtil.put(context, KeyUserLoginId, userInfo.getUserId(), FileName);
	}

	public boolean isUserLogin(Context context)
	{
		return (boolean) get(context, KeyUserLogin, false, FileName);
	}
	
	public String getUserLoginId(Context context)
	{
		return (String) get(context, KeyUserLoginId, null, FileName);
	}

	public VEnterLoginPhonePwdBean getUserInfo(Context context)
	{
		String jsonUserInfo = (String) get(context, KeyUserInfo, null, FileName);
		if (null != jsonUserInfo)
		{
			return new Gson().fromJson(jsonUserInfo, VEnterLoginPhonePwdBean.class);
		}
		return null;
	}

	public static class AppUserInfo
	{
		/* id */private String userId;

		/* 头像 */private String userAvatar;

		/* 昵称 */private String userNickname;

		/* 标签 */private List<String> userLabel;

		/* 省份 */private String userProvince;

		/* 城市 */private String userCity;

		public AppUserInfo(VEnterLoginPhonePwdBean vEnterLoginPhonePwdBean)
		{
			this.userId = vEnterLoginPhonePwdBean.getUser_id();
			this.userAvatar = vEnterLoginPhonePwdBean.getUser_logo();
			this.userNickname = vEnterLoginPhonePwdBean.getUser_nickname();
			this.userLabel = new ArrayList<>(vEnterLoginPhonePwdBean.getUser_tag());
			this.userProvince = vEnterLoginPhonePwdBean.getUser_province();
			this.userCity = vEnterLoginPhonePwdBean.getUser_city();
		}

		public String getUserId()
		{
			return userId;
		}

		public void setUserId(String userId)
		{
			this.userId = userId;
		}

		public String getUserAvatar()
		{
			return userAvatar;
		}

		public void setUserAvatar(String userAvatar)
		{
			this.userAvatar = userAvatar;
		}

		public String getUserNickname()
		{
			return userNickname;
		}

		public void setUserNickname(String userNickname)
		{
			this.userNickname = userNickname;
		}

		public List<String> getUserLabel()
		{
			return userLabel;
		}

		public void setUserLabel(List<String> userLabel)
		{
			this.userLabel = userLabel;
		}

		public String getUserProvince()
		{
			return userProvince;
		}

		public void setUserProvince(String userProvince)
		{
			this.userProvince = userProvince;
		}

		public String getUserCity()
		{
			return userCity;
		}

		public void setUserCity(String userCity)
		{
			this.userCity = userCity;
		}
	}

	public void logAppState(Context context)
	{
		String keyUserInfo = (String) SPUtil.get(context, KeyUserInfo, "null", FileName);
		LogUtil.v("keyUserInfo = " + keyUserInfo);

		// 常用
		boolean isUserLogin = (boolean) SPUtil.get(context, KeyUserLogin, false, FileName);
		String userId = (String) SPUtil.get(context, KeyUserLoginId, "null", FileName);
		LogUtil.v("isUserLogin = " + isUserLogin + ", userId = " + userId);
	}
}
