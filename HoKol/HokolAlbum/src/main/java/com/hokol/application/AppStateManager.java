package com.hokol.application;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.hokol.medium.http.bean.VEnterLoginPhonePwdBean;
import com.yline.utils.LogUtil;
import com.yline.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

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
		return (boolean) SPUtil.get(context, KeyUserLogin, false, FileName);
	}
	
	public String getUserLoginId(Context context)
	{
		return (String) SPUtil.get(context, KeyUserLoginId, "", FileName);
	}

	public AppUserInfo getUserInfo(Context context)
	{
		String jsonUserInfo = (String) SPUtil.get(context, KeyUserInfo, "", FileName);
		if (!TextUtils.isEmpty(jsonUserInfo))
		{
			return new Gson().fromJson(jsonUserInfo, AppUserInfo.class);
		}
		return null;
	}

	public static class AppUserInfo
	{
		/* id */private String userId;

		/* 性别 */private String userSex;

		/* 头像 */private String userAvatar;

		/* 昵称 */private String userNickname;

		/* 标签 */private List<String> userLabel;

		/* 省份 */private String userProvinceName;

		/* 省份 */private String userProvinceCode;

		/* 城市 */private String userCityName;

		/* 城市 */private String userCityCode;

		/* 签名 */private String userSign;

		public AppUserInfo(VEnterLoginPhonePwdBean vEnterLoginPhonePwdBean)
		{
			this.userSex = vEnterLoginPhonePwdBean.getUser_sex();
			this.userId = vEnterLoginPhonePwdBean.getUser_id();
			this.userAvatar = vEnterLoginPhonePwdBean.getUser_logo();
			this.userNickname = vEnterLoginPhonePwdBean.getUser_nickname();
			this.userLabel = new ArrayList<>(vEnterLoginPhonePwdBean.getUser_tag());
			this.userProvinceName = vEnterLoginPhonePwdBean.getProvince().get(0);
			this.userProvinceCode = vEnterLoginPhonePwdBean.getCity().get(1);
			this.userCityName = vEnterLoginPhonePwdBean.getCity().get(0);
			this.userCityCode = vEnterLoginPhonePwdBean.getCity().get(1);
			this.userSign = vEnterLoginPhonePwdBean.getUser_sign();
		}

		public String getUserId()
		{
			return userId;
		}

		public void setUserId(String userId)
		{
			this.userId = userId;
		}

		public String getUserSex()
		{
			return userSex;
		}

		public void setUserSex(String userSex)
		{
			this.userSex = userSex;
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

		public String getUserProvinceName()
		{
			return userProvinceName;
		}

		public void setUserProvinceName(String userProvinceName)
		{
			this.userProvinceName = userProvinceName;
		}

		public String getUserProvinceCode()
		{
			return userProvinceCode;
		}

		public void setUserProvinceCode(String userProvinceCode)
		{
			this.userProvinceCode = userProvinceCode;
		}

		public String getUserCityName()
		{
			return userCityName;
		}

		public void setUserCityName(String userCityName)
		{
			this.userCityName = userCityName;
		}

		public String getUserCityCode()
		{
			return userCityCode;
		}

		public void setUserCityCode(String userCityCode)
		{
			this.userCityCode = userCityCode;
		}

		public String getUserSign()
		{
			return userSign;
		}

		public void setUserSign(String userSign)
		{
			this.userSign = userSign;
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
