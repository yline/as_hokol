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
	private static final String url_head = "http://120.92.35.211/wanghong/wh/index.php/Api/";
	
	/* --------------------------- 注册、登录、忘记密码 --------------------------- */
	// 手机+密码登陆
	public static final String url_login_pwd = url_head + "ApiLogin/login";

	/* --------------------------- 新闻 --------------------------- */
	// 多条新闻获取
	public static final String url_news_multiplex = url_head + "ApiNews/news";

	// 单条新闻获取
	public static final String url_news_single = url_head + "ApiNews/new_one";

	// 推荐新闻
	public static final String url_news_recommend = url_head + "ApiNews/new_tui";

	/* --------------------------- 关注 --------------------------- */
	// 关注主页面
	public static final String url_dynamic_care_all = url_head + "ApiDongtai/dongtai";

	// 请求用户详情信息；点击关注的人头像
	public static final String url_dynamic_user_detail = url_head + "ApiDongtai/user_info";

	// 请求关注的人的信息; 我的界面查看我关注的人
	public static final String url_user_care_all = url_head + "ApiDongtai/care_peo_info";

	// 单条动态(取消)点赞
	public static final String url_dynamic_praise_single = url_head + "ApiDongtai/dt_zan";

	// 请求单条动态信息
	public static final String url_dynamic_single = url_head + "ApiDongtai/dt_one";

	// 请求用户自己的，多条动态信息
	public static final String url_dynamic_user_all = url_head + "ApiDongtai/dt_nums";


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
