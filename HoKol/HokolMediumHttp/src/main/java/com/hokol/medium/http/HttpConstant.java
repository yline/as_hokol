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

	// 单条新闻获取；废弃
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
	public static final String url_dynamic_praise_single = url_head + "ApiDongtai/dt_zan_switch";

	// 请求单条动态信息
	public static final String url_dynamic_single = url_head + "ApiDongtai/dt_one";

	// 请求用户自己的，多条动态信息
	public static final String url_dynamic_user_all = url_head + "ApiDongtai/dt_nums";

	// 发布动态
	public static final String url_dynamic_publish = url_head + "ApiDongtai/dt_pub";

	/* --------------------------- 任务 --------------------------- */
	// 获取任务列表 (多条)
	public static final String url_task_main_all = url_head + "ApiTask/task_index";

	// 获取任务详情 (单条)
	public static final String url_task_main_detail = url_head + "ApiTask/task_detail";

	// 任务收藏/取消收藏
	public static final String url_task_main_collection = url_head + "ApiTask/task_collect_switch";

	/* --------------------------- 主页 --------------------------- */
	// 请求主页动态数据
	public static final String url_home_main = url_head + "ApiIndex/index";

	/* --------------------------- 我的页面 --------------------------- */
	// 请求用户粉丝的信息（多条）
	public static final String url_user_fans_all = url_head + "ApiMine/user_fans_info";

	// 请求用户的收藏
	public static final String url_user_collection = url_head + "ApiMine/user_collect_task";

	// 关注/取消关注某用户
	public static final String url_user_care_or_cancel = url_head + "ApiMine/user_care_switch";

	// 用户赠送红豆
	public static final String url_user_coin_gift = url_head + "ApiMine/present_coin";

	/* --------------------------- 设置页面 --------------------------- */
	// 用户发表意见
	public static final String url_setting_submit_proposal = url_head + "ApiSetting/user_advice";

	// 用户重置密码
	public static final String url_setting_reset_pwd = url_head + "ApiSetting/reset_pwd";

	// 用户重置手机
	public static final String url_setting_reset_phone = url_head + "ApiSetting/reset_tel";

	// 用户信息修改
	public static final String url_setting_update_info = url_head + "ApiSetting/reset_user_info";

	// 用户头像修改
	public static final String url_setting_update_avatar = url_head + "ApiSetting/reset_user_logo";

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
