package com.hokol.medium.http;

import com.hokol.medium.http.bean.VXBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Http常量库
 *
 * @author yline 2017/2/28 --> 23:54
 * @version 1.0.0
 */
public class HttpConstant
{
	// HttpClient设置
	public static final int REQUEST_SUCCESS_CODE = 0;

	public static final int CONNECT_TIME_OUT = 10;

	public static final String MEDIA_TYPE_JSON = "application/json; charset=utf-8";

	// Http Url 信息
	private static final String HTTP_URL_HEAD = "http://120.92.35.211/wanghong/wh/index.php/Back/Api/";

	/* --------------------------- 注册、登录、忘记密码 --------------------------- */
	// 手机+密码登陆
	public static final String HTTP_PHONE_LOGIN_URL = HTTP_URL_HEAD + "login";

	/* --------------------------- 新闻 --------------------------- */
	// 多条新闻获取
	public static final String HTTP_MAIN_MULTIPLEX_NEWS_URL = HTTP_URL_HEAD + "news";

	// 单条新闻获取
	public static final String HTTP_MAIN_SINGLE_NEWS_URL = HTTP_URL_HEAD + "new_1";

	// 推荐新闻
	public static final String HTTP_MAIN_RECOMMEND_NEWS_URL = HTTP_URL_HEAD + "new_tui";

	/* --------------------------- 关注 --------------------------- */
	// 关注主页面
	public static final String HTTP_MAIN_CARE_STAR_URL = HTTP_URL_HEAD + "dongtai";

	// 单条动态(取消)点赞
	public static final String HTTP_MAIN_DYNAMIC_PRAISE_URL = HTTP_URL_HEAD + "dt_zan";

	/* ------------------------------ 控制 -------------------------------- */
	// 是否 输出日志
	private static boolean isDefaultDebug = true;

	public static boolean isDefaultDebug()
	{
		return isDefaultDebug;
	}

	public static void setIsDefaultDebug(boolean isDefaultDebug)
	{
		HttpConstant.isDefaultDebug = isDefaultDebug;
	}

	/**
	 * 解析最外层Json
	 *
	 * @param json
	 * @return null if code is not 0
	 */
	public static final VXBean getResponseXBean(String json) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(json);
		int code = jsonObject.getInt("code");
		if (REQUEST_SUCCESS_CODE == code)
		{
			String data = jsonObject.getString("data");
			return new VXBean(code, data);
		}
		else
		{
			return new VXBean(code, null);
		}
	}
}
