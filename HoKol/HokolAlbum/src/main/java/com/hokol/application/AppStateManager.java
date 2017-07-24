package com.hokol.application;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hokol.activity.UserInfoActivity;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VEnterLoginPhonePwdBean;
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

	/* 用户星座 */private static final String KeyUserConstell = "UserConstell";

	/* 用户手机号 */private static final String KeyUserTel = "UserTel";

	// 其他数据，分割线
	/* 用户粉丝数 */private static final String KeyUserFansNum = "UserFansNum";

	/* 用户关注数 */private static final String KeyUserCareNum = "UserCareNum";

	/* 用户点赞数 */private static final String KeyUserPraiseNum = "UserPraiseNum";

	/* 用户红豆数 */private static final String KeyUserCoinNum = "UserCoinNum";

	/* 用户等级 */private static final String KeyUserVipLevel = "UserVipLevel";

	/* 等级图标链接 */private static final String KeyUserVipUrl = "UserVipUrl";

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 储存非 用户的数据 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	private boolean isFirstFlash = true;

	private AppStateManager()
	{
	}

	public static AppStateManager getInstance()
	{
		return AppStateManagerHolder.appStateManager;
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

			List<String> provinceList = loginPhonePwdBean.getProvince();
			if (null != provinceList && provinceList.size() >= 2)
			{
				SPUtil.put(context, KeyUserProvinceName, provinceList.get(0), FileName);
				SPUtil.put(context, KeyUserProvinceCode, provinceList.get(1), FileName);
			}

			List<String> cityList = loginPhonePwdBean.getCity();
			if (null != cityList && cityList.size() >= 2)
			{
				SPUtil.put(context, KeyUserCityName, cityList.get(0), FileName);
				SPUtil.put(context, KeyUserCityCode, cityList.get(1), FileName);
			}

			SPUtil.put(context, KeyUserSign, loginPhonePwdBean.getUser_sign(), FileName);
			SPUtil.put(context, KeyUserPrice, loginPhonePwdBean.getUser_prize(), FileName);
			SPUtil.put(context, KeyUserConstell, loginPhonePwdBean.getUser_constell(), FileName);

			SPUtil.put(context, KeyUserTel, loginPhonePwdBean.getUser_tel(), FileName);

			// 其他数据
			SPUtil.put(context, KeyUserFansNum, loginPhonePwdBean.getUser_fans_num(), FileName);
			SPUtil.put(context, KeyUserCareNum, loginPhonePwdBean.getUser_care_num(), FileName);
			SPUtil.put(context, KeyUserPraiseNum, loginPhonePwdBean.getUser_zan(), FileName);
			SPUtil.put(context, KeyUserCoinNum, loginPhonePwdBean.getUser_coin(), FileName);
			SPUtil.put(context, KeyUserVipLevel, loginPhonePwdBean.getUser_level(), FileName);
			SPUtil.put(context, KeyUserVipUrl, loginPhonePwdBean.getLevel_url(), FileName);
		}
		else
		{
			LogFileUtil.v("setLoginUserInfo loginPhonePwdBean is null");
		}
	}

	public void updateUserInfo(Context context, UserInfoActivity.UserInfo updateInfoBean)
	{
		if (null != updateInfoBean)
		{
			SPUtil.put(context, KeyUserLoginNickname, updateInfoBean.getUser_nickname(), FileName);
			int intSex = updateInfoBean.getUser_sex();
			String strSex = HttpEnum.getUserSex(intSex).getContent();
			SPUtil.put(context, KeyUserLoginSex, strSex, FileName);

			int intConstell = updateInfoBean.getUser_constell();
			String strConstell = HttpEnum.getUserConstell(intConstell).getContent();
			SPUtil.put(context, KeyUserConstell, strConstell, FileName);

			SPUtil.put(context, KeyUserProvinceCode, updateInfoBean.getP_code(), FileName);
			SPUtil.put(context, KeyUserProvinceName, updateInfoBean.getP_name(), FileName);
			SPUtil.put(context, KeyUserCityCode, updateInfoBean.getC_code(), FileName);
			SPUtil.put(context, KeyUserCityName, updateInfoBean.getC_name(), FileName);

			SPUtil.put(context, KeyUserCityName, updateInfoBean.getC_name(), FileName);
			SPUtil.put(context, KeyUserSign, updateInfoBean.getUser_sign(), FileName);

			List<Integer> intTagList = updateInfoBean.getUser_tag();
			List<String> strTagList = new ArrayList<>();
			for (Integer tagIndex : intTagList)
			{
				strTagList.add(HttpEnum.getUserTag(tagIndex).getContent());
			}
			SPUtil.put(context, KeyUserLoginLabel, new Gson().toJson(strTagList), FileName);
			SPUtil.put(context, KeyUserPrice, updateInfoBean.getUser_prize(), FileName);
		}
		else
		{
			LogFileUtil.v("updateUserInfo updateInfoBean is null");
		}
	}

	/**
	 * 更新 用户头像
	 */
	public void updateKeyUserLoginAvatar(Context context, String avatarUrl)
	{
		SPUtil.put(context, KeyUserLoginAvatar, avatarUrl, FileName);
	}

	/**
	 * 更新用户的coin数目
	 */
	public void updateKeyUserCoinNum(Context context, float coinNum)
	{
		SPUtil.put(context, KeyUserCoinNum, coinNum, FileName);
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

	public ArrayList<Integer> getUserLoginLabelInt(Context context)
	{
		ArrayList<Integer> intUserTagList = new ArrayList<>();

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

	public String getUserTel(Context context)
	{
		String userTel = (String) SPUtil.get(context, KeyUserTel, "", FileName);
		return userTel;
	}

	public UserInfoActivity.UserInfo getUserInfoBean(Context context, String userId)
	{
		String nickname = AppStateManager.getInstance().getUserLoginNickName(context);
		int userSex = AppStateManager.getInstance().getUserLoginSexInt(context);

		String cCode = AppStateManager.getInstance().getUserCityCode(context);
		String cName = AppStateManager.getInstance().getUserCityName(context);
		String pCode = AppStateManager.getInstance().getUserProvinceCode(context);
		String pName = AppStateManager.getInstance().getUserProvinceName(context);

		String userSign = AppStateManager.getInstance().getUserSign(context);
		ArrayList<Integer> userTagList = AppStateManager.getInstance().getUserLoginLabelInt(context);

		String userPrice = AppStateManager.getInstance().getUserPrice(context);
		int userConstell = AppStateManager.getInstance().getUserConstellInt(context);

		return new UserInfoActivity.UserInfo(userId, nickname, userSex, cCode, pCode, userSign, userTagList, userPrice, userConstell, cName, pName);
	}

	// 其他数据，分割线
	public int getUserFansNum(Context context)
	{
		Object object = SPUtil.get(context, KeyUserFansNum, 0, FileName);
		if (null == object)
		{
			return 0;
		}
		return (int) object;
	}

	public int getUserCareNum(Context context)
	{
		Object object = SPUtil.get(context, KeyUserCareNum, 0, FileName);
		if (null == object)
		{
			return 0;
		}
		return (int) object;
	}

	public int getUserPraiseNum(Context context)
	{
		Object object = SPUtil.get(context, KeyUserPraiseNum, 0, FileName);
		if (null == object)
		{
			return 0;
		}
		return (int) object;
	}

	public float getUserCoinNum(Context context)
	{
		Object object = SPUtil.get(context, KeyUserCoinNum, 0f, FileName);
		if (null == object)
		{
			return 0f;
		}
		return (float) object;
	}

	public int getUserVipLevel(Context context)
	{
		Object object = SPUtil.get(context, KeyUserVipLevel, 0, FileName);
		if (null == object)
		{
			return 0;
		}
		return (int) object;
	}

	public String getUserVipUrl(Context context)
	{
		Object object = SPUtil.get(context, KeyUserVipUrl, "", FileName);
		if (null == object)
		{
			return "";
		}
		return (String) object;
	}

	public void clearLoginUserInfo(Context context)
	{
		SPUtil.put(context, KeyUserLogin, false, FileName);
		SPUtil.put(context, KeyUserLoginId, "", FileName);
		SPUtil.put(context, KeyUserLoginAvatar, "", FileName);
		SPUtil.put(context, KeyUserLoginSex, "", FileName);
		SPUtil.put(context, KeyUserLoginNickname, "", FileName);
		SPUtil.put(context, KeyUserLoginLabel, "", FileName);

		SPUtil.put(context, KeyUserProvinceName, "", FileName);
		SPUtil.put(context, KeyUserProvinceCode, "", FileName);
		SPUtil.put(context, KeyUserCityName, "", FileName);
		SPUtil.put(context, KeyUserCityCode, "", FileName);

		SPUtil.put(context, KeyUserSign, "", FileName);
		SPUtil.put(context, KeyUserPrice, "", FileName);
		SPUtil.put(context, KeyUserConstell, "", FileName);

		SPUtil.put(context, KeyUserTel, "", FileName);

		// 其他数据
		SPUtil.put(context, KeyUserFansNum, 0, FileName);
		SPUtil.put(context, KeyUserCareNum, 0, FileName);
		SPUtil.put(context, KeyUserPraiseNum, 0, FileName);
		SPUtil.put(context, KeyUserCoinNum, 0, FileName);
		SPUtil.put(context, KeyUserVipLevel, 0, FileName);
		SPUtil.put(context, KeyUserVipUrl, "", FileName);
	}

	public void logAppState(Context context)
	{
		String[] appState = new String[20];

		appState[0] = "0-" + String.valueOf(isUserLogin(context));
		appState[1] = "1-" + getUserLoginId(context);
		appState[2] = "2-" + getUserLoginAvatar(context);
		appState[3] = "3-" + getUserLoginSex(context);
		appState[4] = "4-" + getUserLoginNickName(context);
		appState[5] = "5-" + getUserLoginLabelString(context);
		appState[6] = "6-" + getUserProvinceName(context);
		appState[7] = "7-" + getUserProvinceCode(context);
		appState[8] = "8-" + getUserCityName(context);
		appState[9] = "9-" + getUserCityCode(context);
		appState[10] = "10-" + getUserSign(context);
		appState[11] = "11-" + getUserPrice(context);
		appState[12] = "12-" + getUserConstell(context);
		// 分割线
		appState[13] = "13-" + getUserFansNum(context) + "";
		appState[14] = "14-" + getUserCareNum(context) + "";
		appState[15] = "15-" + getUserPraiseNum(context) + "";
		appState[16] = "16-" + getUserCoinNum(context) + "";
		appState[17] = "17-" + getUserVipLevel(context) + "";
		appState[18] = "18-" + getUserVipUrl(context);

		LogFileUtil.v("appState =》 " + Arrays.toString(appState));
	}

	public boolean isFirstFlash()
	{
		return isFirstFlash;
	}

	public void setFirstFlash(boolean firstFlash)
	{
		isFirstFlash = firstFlash;
	}

	private static class AppStateManagerHolder
	{
		private static AppStateManager appStateManager = new AppStateManager();
	}
}
