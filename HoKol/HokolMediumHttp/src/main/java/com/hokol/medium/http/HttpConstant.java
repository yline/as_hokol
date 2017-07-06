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

	// 手机+ 密码登陆
	public static final String url_enter_hokol_init = url_head + "ApiLogin/init";

	/* --------------------------- 注册、登录、忘记密码 --------------------------- */
	// 手机+ 密码登陆
	public static final String url_enter_login_pwd = url_head + "ApiLogin/login";

	// 获取 注册验证码
	public static final String url_enter_code_register = url_head + "ApiLogin/get_register_code";

	// 用户注册
	public static final String url_enter_register = url_head + "ApiLogin/register";

	// 用户信息完善
	public static final String url_enter_register_complete_info = url_head + "ApiLogin/add_info";

	// 忘记密码之获取验证码
	public static final String url_enter_code_forget_pwd = url_head + "ApiLogin/get_lose_pwd_code";
	
	// 忘记密码之重置密码
	public static final String url_enter_reset_pwd = url_head + "ApiLogin/lose_pwd";

	// 修改号码 获取验证码
	public static final String url_enter_code_update_phone = url_head + "ApiSetting/get_reset_tel_code";

	// 修改号码
	public static final String url_enter_update_phone = url_head + "ApiSetting/reset_tel";

	/* --------------------------- 新闻 --------------------------- */
	// 多条新闻获取
	public static final String url_news_multiplex = url_head + "ApiNews/news";

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

	// 请求用户多条私密动态信息
	public static final String url_dynamic_user_private_all = url_head + "ApiDongtai/private_nums";

	// 发布动态
	public static final String url_dynamic_publish = url_head + "ApiDongtai/dt_pub";

	// 发布私密动态
	public static final String url_dynamic_private_publish = url_head + "ApiDongtai/private_space_pub";

	// 删除动态
	public static final String url_dynamic_delete = url_head + "ApiDongtai/dt_del";
	
	// 删除私密动态
	public static final String url_dynamic_private_delete = url_head + "ApiDongtai/private_del";

	/* --------------------------- 任务 --------------------------- */
	// 获取任务列表 (多条)；任务主页
	public static final String url_task_main_all = url_head + "ApiTask/task_index";

	// 获取任务详情 (单条)
	public static final String url_task_main_detail = url_head + "ApiTask/task_detail";

	// 任务发布
	public static final String url_task_main_publish = url_head + "ApiTask/task_pub";

	// 任务收藏/取消收藏
	public static final String url_task_main_collection = url_head + "ApiTask/task_collect_switch";

	// 任务接单详情
	public static final String url_task_user_accept_detail = url_head + "ApiTask/task_confirm_detail";

	// 任务报名详情
	public static final String url_task_user_signup_detail = url_head + "ApiTask/task_join_detail";

	// 用户已发布任务；全部
	public static final String url_task_user_published_all = url_head + "ApiTask/task_published_all";

	// 用户已发布任务；待报名
	public static final String url_task_user_published_sign = url_head + "ApiTask/task_published_join";

	// 用户已发布任务；待交易
	public static final String url_task_user_published_trade = url_head + "ApiTask/task_published_confirm";

	// 用户已发布任务；待评价
	public static final String url_task_user_published_evaluate = url_head + "ApiTask/task_published_comment";

	// 任务报名
	public static final String url_task_action_staff_signup = url_head + "ApiTask/task_join";

	// 雇主录用报名者
	public static final String url_task_action_master_takeon = url_head + "ApiTask/task_employe";

	// 雇主评价雇员
	public static final String url_task_action_master_comment = url_head + "ApiTask/task_comment_employer";

	// 获取被评价雇员信息
	public static final String url_task_master_comment_info = url_head + "ApiTask/task_comment_peo_info";

	// 雇主结束报名/取消任务
	public static final String url_task_action_master_finish = url_head + "ApiTask/finish_task_employe";

	// 雇主确定交易
	public static final String url_task_action_master_trade = url_head + "ApiTask/confirm_task";

	// 用户已投递的任务
	public static final String url_task_user_delivered = url_head + "ApiTask/post_task";
	
	// 雇员确认、拒绝接单
	public static final String url_task_action_staff_confirm = url_head + "ApiTask/is_confirm_task";

	// 雇员确认交易
	public static final String url_task_action_staff_trade = url_head + "ApiTask/is_success_task";

	// 删除任务
	public static final String url_task_action_delete = url_head + "ApiTask/del_task";

	/* --------------------------- 主页 --------------------------- */
	// 请求主页动态数据
	public static final String url_home_main = url_head + "ApiIndex/index";

	// 主页推荐
	public static final String url_recommend_home = url_head + "ApiIndex/index_recommend";

	// 任务推荐
	public static final String url_recommend_task = url_head + "ApiTask/task_recommend";

	/* --------------------------- 我的页面 --------------------------- */
	// 请求用户粉丝的信息（多条）
	public static final String url_user_fans_all = url_head + "ApiMine/user_fans_info";

	// 请求用户的收藏任务
	public static final String url_user_collection = url_head + "ApiMine/user_collect_task";

	// 关注/取消关注某用户
	public static final String url_user_care_or_cancel = url_head + "ApiMine/user_care_switch";

	// 用户赠送红豆
	public static final String url_user_coin_gift = url_head + "ApiMine/present_coin";

	// 我的消息, 单条
	public static final String url_user_message = url_head + "ApiMine/mine_message";

	// 我的系统消息
	public static final String url_user_message_system_all = url_head + "ApiMine/message_sys";

	// 我的系统消息，读取
	public static final String url_user_message_system_readed = url_head + "ApiMine/message_read";

	// 我的系统消息，删除
	public static final String url_user_message_system_delete = url_head + "ApiMine/message_delete";

	// 接收的礼物
	public static final String url_user_gift_receivve = url_head + "ApiMine/recive_gift";

	// 送出的红豆
	public static final String url_user_gift_send = url_head + "ApiMine/post_gift";

	// 充值记录
	public static final String url_user_recharge_record = url_head + "ApiMine/recharge_record";

	// 我的会员
	public static final String url_user_vip_info = url_head + "ApiMine/mine_member";

	// 我的会员
	public static final String url_user_vip_recharge_record = url_head + "ApiMine/recharge_vip_record";

	// 获取未使用的交流券
	public static final String url_user_contact_volume_unapply = url_head + "ApiMine/rem_call_card";

	// 获取已使用的交流券
	public static final String url_user_vip_recharge_applied = url_head + "ApiMine/used_call_card";

	// 获取已过期的交流券
	public static final String url_user_vip_recharge_expired = url_head + "ApiMine/expire_call_card";

	// 用户信用
	public static final String url_user_credit = url_head + "ApiMine/user_credit";

	// 我的评分(已发任务)
	public static final String url_user_task_score_assigned = url_head + "ApiMine/mine_score_pub";

	// 我的评分(已投任务)
	public static final String url_user_task_score_delivered = url_head + "ApiMine/mine_score_post";

	// 查看评论(已发任务)
	public static final String url_user_task_comment_assigned = url_head + "ApiMine/find_comment_pub";

	// 查看评论(已投任务)
	public static final String url_user_task_comment_delivered = url_head + "ApiMine/find_comment_post";


	/* --------------------------- 设置接口 --------------------------- */
	// 用户发表意见
	public static final String url_setting_submit_proposal = url_head + "ApiSetting/user_advice";

	// 用户重置密码
	public static final String url_setting_reset_pwd = url_head + "ApiSetting/reset_pwd";

	// 用户信息修改
	public static final String url_setting_update_info = url_head + "ApiSetting/reset_user_info";

	// 用户头像修改
	public static final String url_setting_update_avatar = url_head + "ApiSetting/reset_user_logo";

	/* --------------------------- 地区接口 --------------------------- */

	// 获取地区
	public static final String url_area_all = url_head + "ApiArea/get_area";
	
	/* ------------------------------ 控制 -------------------------------- */

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
