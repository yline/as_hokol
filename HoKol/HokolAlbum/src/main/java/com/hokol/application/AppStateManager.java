package com.hokol.application;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VEnterLoginPhonePwdBean;
import com.hokol.medium.http.bean.WSettingUpdateInfoBean;
import com.yline.log.LogFileUtil;
import com.yline.utils.SPUtil;

import java.util.ArrayList;
import java.util.Arrays;
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

	/* ------------------------------------ 用户信息 ------------------------------------- */
	/* 是否登陆 */private static final String KeyUserLogin = "IsUserLogin";

	/* userId */private static final String KeyUserLoginId = "UserLoginId";

	/* 头像 */private static final String KeyUserLoginAvatar = "UserLoginAvatar";

	/* 性别 */private static final String KeyUserLoginSex = "UserSex";

	/* 昵称 */private static final String KeyUserLoginNickname = "UserNickname";

	/* 标签 */private static final String KeyUserLoginLabel = "UserLabel";

	/* 省份 */private static final String KeyUserProvinceName = "UserProvinceName";

	/* 省份 */private static final String KeyUserProvinceCode = "UserProvinceCode";

	/* 城市 */private static final String KeyUserCityName = "UserCityName";

	/* 城市 */private static final String KeyUserCityCode = "UserCityCode";

	/* 签名 */private static final String KeyUserSign = "UserSign";

	/* 用户获奖 */private static final String KeyUserPrice = "UserPrice";

	/* 用户星座 */private static final String KeyUserConstell = "UserSignConstell";

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

	public void setLoginUserInfo(Context context, VEnterLoginPhonePwdBean loginPhonePwdBean)
	{
		if (null != loginPhonePwdBean)
		{
			SPUtil.put(context, KeyUserLogin, true, FileName);
			SPUtil.put(context, KeyUserLoginId, loginPhonePwdBean.getUser_id(), FileName);
			SPUtil.put(context, KeyUserLoginAvatar, loginPhonePwdBean.getUser_logo(), FileName);
			SPUtil.put(context, KeyUserLoginSex, loginPhonePwdBean.getUser_sex(), FileName);
			SPUtil.put(context, KeyUserLoginNickname, loginPhonePwdBean.getUser_nickname(), FileName);
			SPUtil.put(context, KeyUserLoginLabel, new Gson().toJson(loginPhonePwdBean.getUser_tag()), FileName);
			SPUtil.put(context, KeyUserProvinceName, loginPhonePwdBean.getProvince().get(0), FileName);
			SPUtil.put(context, KeyUserProvinceCode, loginPhonePwdBean.getProvince().get(1), FileName);
			SPUtil.put(context, KeyUserCityName, loginPhonePwdBean.getCity().get(0), FileName);
			SPUtil.put(context, KeyUserCityCode, loginPhonePwdBean.getCity().get(1), FileName);
			SPUtil.put(context, KeyUserSign, loginPhonePwdBean.getUser_sign(), FileName);
			SPUtil.put(context, KeyUserPrice, loginPhonePwdBean.getUser_prize(), FileName);
			SPUtil.put(context, KeyUserConstell, loginPhonePwdBean.getUser_constell(), FileName);
		}
		else
		{
			LogFileUtil.v("setLoginUserInfo loginPhonePwdBean is null");
		}
	}

	// 更新 用户头像
	public void updateKeyUserLoginAvatar(Context context, String avatarUrl)
	{
		SPUtil.put(context, KeyUserLoginAvatar, avatarUrl, FileName);
	}

	public boolean isUserLogin(Context context)
	{
		return (boolean) SPUtil.get(context, KeyUserLogin, false, FileName);
	}
	
	public String getUserLoginId(Context context)
	{
		return (String) SPUtil.get(context, KeyUserLoginId, "", FileName);
	}

	public String getUserLoginAvatar(Context context)
	{
		return (String) SPUtil.get(context, KeyUserLoginAvatar, "", FileName);
	}

	public String getUserLoginSex(Context context)
	{
		return (String) SPUtil.get(context, KeyUserLoginSex, HttpEnum.UserSex.All.getContent(), FileName);
	}

	public int getUserLoginSexInt(Context context)
	{
		String userSex = (String) SPUtil.get(context, KeyUserLoginSex, HttpEnum.UserSex.All.getContent(), FileName);
		return HttpEnum.getUserSex(userSex).getIndex();
	}

	public String getUserLoginNickName(Context context)
	{
		return (String) SPUtil.get(context, KeyUserLoginNickname, "", FileName);
	}

	public List<String> getUserLoginLabel(Context context)
	{
		String jsonList = (String) SPUtil.get(context, KeyUserLoginLabel, "", FileName);
		if (!TextUtils.isEmpty(jsonList))
		{
			return new Gson().fromJson(jsonList, new TypeToken<List<String>>()
			{
			}.getType());
		}

		return null;
	}

	public List<Integer> getUserLoginLabelInt(Context context)
	{
		List<Integer> intUserTagList = new ArrayList<>();

		List<String> userTagList = getUserLoginLabel(context);
		if (null == userTagList)
		{
			return intUserTagList;
		}

		for (String userTag : userTagList)
		{
			intUserTagList.add(HttpEnum.getUserTag(userTag).getIndex());
		}

		return intUserTagList;
	}

	public String getUserLoginLabelString(Context context)
	{
		return (String) SPUtil.get(context, KeyUserLoginLabel, "", FileName);
	}

	public String getUserProvinceName(Context context)
	{
		return (String) SPUtil.get(context, KeyUserProvinceName, "", FileName);
	}

	public String getUserProvinceCode(Context context)
	{
		return (String) SPUtil.get(context, KeyUserProvinceCode, "", FileName);
	}

	public String getUserCityName(Context context)
	{
		return (String) SPUtil.get(context, KeyUserCityName, "", FileName);
	}

	public String getUserCityCode(Context context)
	{
		return (String) SPUtil.get(context, KeyUserCityCode, "", FileName);
	}

	public String getUserSign(Context context)
	{
		return (String) SPUtil.get(context, KeyUserSign, "", FileName);
	}

	public String getUserPrice(Context context)
	{
		return (String) SPUtil.get(context, KeyUserPrice, "", FileName);
	}

	public String getUserConstell(Context context)
	{
		return (String) SPUtil.get(context, KeyUserConstell, "", FileName);
	}

	public int getUserConstellInt(Context context)
	{
		String userConstell = (String) SPUtil.get(context, KeyUserConstell, "", FileName);
		return HttpEnum.getUserConstell(userConstell).getIndex();
	}

	public WSettingUpdateInfoBean getUserInfoBean(Context context, String userId)
	{
		String nickname = AppStateManager.getInstance().getUserLoginNickName(context);
		int userSex = AppStateManager.getInstance().getUserLoginSexInt(context);

		String cCode = AppStateManager.getInstance().getUserCityCode(context);
		String pCode = AppStateManager.getInstance().getUserProvinceCode(context);

		String userSign = AppStateManager.getInstance().getUserSign(context);
		List<Integer> userTagList = AppStateManager.getInstance().getUserLoginLabelInt(context);

		String userPrice = AppStateManager.getInstance().getUserPrice(context);
		int userConstell = AppStateManager.getInstance().getUserConstellInt(context);

		return new WSettingUpdateInfoBean(userId, nickname, userSex, cCode, pCode, userSign, userTagList, userPrice, userConstell);
	}

	public void logAppState(Context context)
	{
		String[] appState = new String[20];

		appState[0] = String.valueOf(isUserLogin(context));
		appState[1] = getUserLoginId(context);
		appState[2] = getUserLoginAvatar(context);
		appState[3] = getUserLoginSex(context);
		appState[4] = getUserLoginNickName(context);
		appState[5] = getUserLoginLabelString(context);
		appState[6] = getUserProvinceName(context);
		appState[7] = getUserProvinceCode(context);
		appState[8] = getUserCityName(context);
		appState[9] = getUserCityCode(context);
		appState[10] = getUserSign(context);
		appState[11] = getUserPrice(context);
		appState[12] = getUserConstell(context);

		LogFileUtil.v("appState =》 " + Arrays.toString(appState));
	}
}
