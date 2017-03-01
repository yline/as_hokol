package com.hokol.medium.http;

import com.hokol.medium.http.bean.ResponseXBean;

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
	// 是否 输出日志
	public static final boolean isDebug = true;

	// HttpClient设置
	public static final int REQUEST_SUCCESS_CODE = 0;

	public static final int CONNECT_TIME_OUT = 10;

	public static final String MEDIA_TYPE_JSON = "application/json; charset=utf-8";

	// Http Url 信息
	private static final String HTTP_URL_HEAD = "http://192.168.0.112/index.php/Back/Api/";

	public static final String HTTP_PHONE_LOGIN_URL = HTTP_URL_HEAD + "login";

	/**
	 * 解析最外层Json
	 *
	 * @param json
	 * @return null if code is not 0
	 */
	public static final ResponseXBean getResponseXBean(String json) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(json);
		int code = jsonObject.getInt("code");
		if (REQUEST_SUCCESS_CODE == code)
		{
			String data = jsonObject.getString("data");
			return new ResponseXBean(code, data);
		}
		else
		{
			return new ResponseXBean(code, null);
		}
	}
}
