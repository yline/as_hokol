package com.hokol.medium.http;

import android.content.Context;

import com.hokol.medium.http.bean.*;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.http.hokol.HokolHttp;
import com.yline.http.XHttpConfig;
import com.yline.http.client.HttpCachePriorClient;
import com.yline.log.LogFileUtil;
import com.yline.utils.FileUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class XHttpUtil
{
	private static final String TAG = "XHttpUtil";

	/**
	 * 完成Http的全局初始化
	 */
	public static void init(Context context)
	{
		File cacheDir = FileUtil.getFileExternalDir(context, "Text");

		XHttpConfig config = XHttpConfig.getInstance();
		config.setCacheDir(cacheDir).init(context);
	}

	/**
	 * 网络请求，用户初始化
	 */
	public static void doInitHokol(WInitHokolBean hokolBean, HokolAdapter<VInitHokolBean> adapter)
	{
		String httpUrl = HttpConstant.url_enter_hokol_init;
		new HokolHttp().doPost(httpUrl, hokolBean, VInitHokolBean.class, adapter);
	}

	/**
	 * 登录接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 手机号+密码登陆 --> login --> url_login_pwd --> WEnterLoginPhonePwdBean - VEnterLoginPhonePwdBean --> OK
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 登录接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 手机号 + 密码登陆
	 */
	public static void doEnterLoginPhonePwd(final WEnterLoginPhonePwdBean requestBean, HokolAdapter<VEnterLoginPhonePwdBean> adapter)
	{
		String httpUrl = HttpConstant.url_enter_login_pwd;
		new HokolHttp().doPost(httpUrl, requestBean, VEnterLoginPhonePwdBean.class, adapter);
	}

	/**
	 * 获取 注册验证码
	 */
	public static void doEnterCodeRegister(WEnterCodeRegisterBean wEnterCodeRegisterBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_code_register;
		new HokolHttp().doPost(httpUrl, wEnterCodeRegisterBean, String.class, adapter);
	}

	/**
	 * 忘记密码之获取验证码
	 */
	public static void doEnterCodeForgetPwd(WEnterCodeRegisterBean codeRegisterBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_code_forget_pwd;
		new HokolHttp().doPost(httpUrl, codeRegisterBean, String.class, adapter);
	}

	/**
	 * 修改号码 获取验证码
	 */
	public static void doEnterCodeUpdatePhone(WEnterCodeUpdatePhoneBean codeUpdateBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_code_update_phone;
		new HokolHttp().doPost(httpUrl, codeUpdateBean, String.class, adapter);
	}

	/**
	 * 修改号码
	 */
	public static void doEnterPhoneUpdate(WEnterPhoneUpdateBean updateBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_update_phone;
		new HokolHttp().doPost(httpUrl, updateBean, String.class, adapter);
	}

	/**
	 * 用户注册
	 */
	public static void doEnterRegister(WEnterRegisterBean wEnterRegisterBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_register;
		new HokolHttp().doPost(httpUrl, wEnterRegisterBean, String.class, adapter);
	}
	
	/**
	 * 用户信息完善
	 */
	public static void doEnterRegisterCompleteInfo(WEnterRegisterCompleteInfoBean completeInfoBean, HokolAdapter<VEnterRegisterCompleteInfoBean> adapter)
	{
		String httpUrl = HttpConstant.url_enter_register_complete_info;
		new HokolHttp().doPost(httpUrl, completeInfoBean, VEnterRegisterCompleteInfoBean.class, adapter);
	}

	/**
	 * 忘记密码之重置密码
	 */
	public static void doEnterResetPwd(WEnterResetPwdBean resetPwdBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_reset_pwd;
		new HokolHttp().doPost(httpUrl, resetPwdBean, String.class, adapter);
	}

	/**
	 * 新闻接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称/Bean名称 --> 情况
	 * 推荐新闻获取 --> new_tui --> url_news_recommend --> null - VNewsSingleBean --> 还差 Html返回值
	 * 多条新闻获取 --> news --> url_news_multiplex --> WNewsMultiplexBean - VNewsMultiplexBean --> 还差 Html返回值 + 待处理长度<=0的情况
	 * 单条新闻获取 --> 等待Html5,未提供接口
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 新闻接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 推荐新闻获取
	 */
	public static void doNewsRecommend(HokolAdapter<VNewsRecommendBean> adapter)
	{
		String httpUrl = HttpConstant.url_news_recommend;
		new HokolHttp().doPost(httpUrl, "", VNewsRecommendBean.class, adapter);
	}

	/**
	 * 多条新闻测试
	 */
	public static void doNewsMultiplex(WNewsMultiplexBean wNewsMultiplexBean, HokolAdapter<VNewsMultiplexBean> adapter)
	{
		String httpUrl = HttpConstant.url_news_multiplex;
		new HokolHttp().doPost(httpUrl, wNewsMultiplexBean, VNewsMultiplexBean.class, adapter);
	}

	/**
	 * 动态接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 请求关注的人的多条动态 --> dongtai --> url_dynamic_care_all --> WDynamicCareAllBean - VDynamicCareAllBean + VDynamicCareBean --> 长度<=0情况未处理、
	 * 请求用户详情信息 --> user_info --> url_dynamic_user_detail --> WDynamicUserDetailBean - VDynamicUserDetailBean --> ok + 没有参数也能请求到数据
	 * 请求关注的人的信息（多条） --> care_peo_info --> url_user_care_all --> WUserCareAllBean - VUserCareAllBean --> ok
	 * 单条动态(取消)点赞功能 --> dt_zan_switch --> url_dynamic_praise_single --> WDynamicPraiseSingleBean - VDynamicPraiseSingleBean --> ok
	 * 请求单条动态的信息 --> dt_one --> url_dynamic_single --> WDynamicCareSingleBean - VDynamicCareSingleBean --> ok
	 * 请求用户多条动态信息 --> dt_nums --> url_dynamic_user_all --> WDynamicUserAllBean - VDynamicUserAllBean --> ok
	 * 发布动态 --> dt_pub --> url_dynamic_publish --> ？？？ - ？？？ --> 稍后测试
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 动态接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 请求关注的人的多条动态
	 */
	public static void doDynamicCareAll(WDynamicCareAllBean wDynamicCareAllBean, HokolAdapter<VDynamicCareAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_care_all;
		new HokolHttp().doPost(httpUrl, wDynamicCareAllBean, VDynamicCareAllBean.class, adapter);
	}

	/**
	 * 请求用户详情信息
	 */
	public static void doDynamicUserDetail(WDynamicUserDetailBean wDynamicUserDetailBean, HokolAdapter<VDynamicUserDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_detail;
		new HokolHttp().doPost(httpUrl, wDynamicUserDetailBean, VDynamicUserDetailBean.class, adapter);
	}

	/**
	 * 单条动态(取消)点赞功能
	 */
	public static void doDynamicPraiseSingle(WDynamicPraiseSingleBean wDynamicPraiseSingleBean, HokolAdapter<VDynamicPraiseSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_praise_single;
		new HokolHttp().doPost(httpUrl, wDynamicPraiseSingleBean, VDynamicPraiseSingleBean.class, adapter);
	}

	/**
	 * 请求单条动态的信息
	 */
	public static void doDynamicSingle(WDynamicCareSingleBean wDynamicCareSingleBean, HokolAdapter<VDynamicCareSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_single;
		new HokolHttp().doPost(httpUrl, wDynamicCareSingleBean, VDynamicCareSingleBean.class, adapter);
	}

	/**
	 * 请求单条 私密动态 的信息
	 */
	public static void doDynamicPrivateSingle(WDynamicPrivateSingleBean privateSingleBean, HokolAdapter<VWDynamicPrivateSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_private_single;
		new HokolHttp().doPost(httpUrl, privateSingleBean, VWDynamicPrivateSingleBean.class, adapter);
	}

	/**
	 * 请求用户多条动态信息
	 */
	public static void doDynamicUserAll(WDynamicUserAllBean wDynamicUserAllBean, HokolAdapter<VDynamicUserAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_all;
		new HokolHttp().doPost(httpUrl, wDynamicUserAllBean, VDynamicUserAllBean.class, adapter);
	}

	/**
	 * 请求用户多条私密动态信息
	 */
	public static void doDynamicUserPrivateAll(WDynamicUserPrivateAllBean wDynamicUserPrivateAllBean, HokolAdapter<VDynamicUserPrivateAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_private_all;
		new HokolHttp().doPost(httpUrl, wDynamicUserPrivateAllBean, VDynamicUserPrivateAllBean.class, adapter);
	}

	/**
	 * 请求关注的人的信息（多条）
	 */
	public static void doUserCareAll(WUserCareAllBean wUserCareAllBean, HokolAdapter<VUserCareAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_care_all;
		new HokolHttp().doPost(httpUrl, wUserCareAllBean, VUserCareAllBean.class, adapter);
	}

	/**
	 * 发布动态
	 *
	 * @param userId  用户id
	 * @param content 发布内容
	 * @param file    图片的文件
	 * @param adapter
	 */
	public static void doDynamicPublish(final String userId, final String content, final File file, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_publish;

		if (null != file || !file.exists())
		{
			HokolHttp hokolHttp = new HokolHttp();

			MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
			if (hokolHttp.isDebug())
			{
				LogFileUtil.v("user_id = " + userId + ", content = " + content + ", user_logo = " + file.getAbsolutePath());
			}
			bodyBuilder.addFormDataPart("dt_user_id", userId);
			bodyBuilder.addFormDataPart("dt_content", content);
			bodyBuilder.addFormDataPart("dt_img", file.getName(), RequestBody.create(MediaType.parse("image"), file));

			hokolHttp.doPost(httpUrl, bodyBuilder, String.class, adapter);
		}
		else
		{
			LogFileUtil.e(TAG, "Dynamic Publish file is null or not exists");
		}
	}

	/**
	 * 发布私密空间
	 *
	 * @param userId  用户id
	 * @param content 发布内容
	 * @param file    图片的文件
	 * @param adapter
	 */
	public static void doDynamicPrivatePublish(final String userId, final String content, final File file, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_private_publish;

		if (null != file || !file.exists())
		{
			HokolHttp hokolHttp = new HokolHttp();

			MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
			if (hokolHttp.isDebug())
			{
				LogFileUtil.v("user_id = " + userId + ", content = " + content + ", user_logo = " + file.getAbsolutePath());
			}
			bodyBuilder.addFormDataPart("pri_user_id", userId);
			bodyBuilder.addFormDataPart("pri_content", content);
			bodyBuilder.addFormDataPart("pri_img", file.getName(), RequestBody.create(MediaType.parse("image"), file));

			hokolHttp.doPost(httpUrl, bodyBuilder, String.class, adapter);
		}
		else
		{
			LogFileUtil.e(TAG, "Dynamic Private Publish  file is null or not exists");
		}
	}

	/**
	 * 删除动态
	 */
	public static void doDynamicDelete(WDynamicDeleteBean deleteBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_delete;
		new HokolHttp().doPost(httpUrl, deleteBean, String.class, adapter);
	}

	/**
	 * 删除私密动态
	 */
	public static void doDynamicPrivateDelete(WDynamicPrivateDeleteBean deleteBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_private_delete;
		new HokolHttp().doPost(httpUrl, deleteBean, String.class, adapter);
	}

	/**
	 * 任务接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 获取任务列表(多条)--> task_index --> url_task_main_all --> WTaskMainAllBean - VTaskMainAllBean --> ok
	 * 获取任务详情(单条)--> task_detail --> url_task_main_detail --> WTaskMainDetailBean - VTaskMainDetailBean --> ok
	 * 任务发布--> 暂时不测
	 * 任务收藏/取消收藏--> task_collect_switch --> url_task_main_collection --> WTaskMainCollectionBean - null --> ok
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 任务接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 请求关注的人的信息（多条）
	 */
	public static void doTaskMainAll(WTaskMainAllBean wTaskMainAll, HokolAdapter<VTaskMainAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_all;
		new HokolHttp().doPost(httpUrl, wTaskMainAll, VTaskMainAllBean.class, adapter);
	}

	/**
	 * 获取任务详情(单条)
	 */
	public static void doTaskMainDetail(WTaskMainDetailBean wTaskMainDetailBean, HokolAdapter<VTaskMainDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_detail;
		new HokolHttp().doPost(httpUrl, wTaskMainDetailBean, VTaskMainDetailBean.class, adapter);
	}

	/**
	 * 任务发布
	 */
	public static void doTaskMainPublish(WTaskMainPublishBean wTaskMainPublishBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_publish;
		new HokolHttp().doPost(httpUrl, wTaskMainPublishBean, String.class, adapter);
	}

	/**
	 * 任务收藏/取消收藏
	 */
	public static void doTaskMainCollection(WTaskMainCollectionBean wTaskMainCollectionBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_collection;
		new HokolHttp().doPost(httpUrl, wTaskMainCollectionBean, String.class, adapter);
	}

	/**
	 * 任务接单详情
	 */
	public static void doTaskUserAcceptDetail(WTaskUserAcceptBean wTaskUserAcceptBean, HokolAdapter<VTaskUserAcceptBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_accept_detail;
		new HokolHttp().doPost(httpUrl, wTaskUserAcceptBean, VTaskUserAcceptBean.class, adapter);
	}

	/**
	 * 任务报名详情
	 */
	public static void doTaskUserSignUpDetail(WTaskUserSignUpDetailBean wTaskUserSignUpBean, HokolAdapter<VTaskUserSignUpDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_signup_detail;
		new HokolHttp().doPost(httpUrl, wTaskUserSignUpBean, VTaskUserSignUpDetailBean.class, adapter);
	}

	/**
	 * 用户已发布任务；全部
	 */
	public static void doTaskUserPublishedAll(WTaskUserPublishedBean wTaskUserPublishedBean, HokolAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_all;
		new HokolHttp().doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class, adapter);
	}

	/**
	 * 用户已发布任务；待报名
	 */
	public static void doTaskUserPublishedSign(WTaskUserPublishedBean wTaskUserPublishedBean, HokolAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_sign;
		new HokolHttp().doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class, adapter);
	}

	/**
	 * 用户已发布任务；待交易
	 */
	public static void doTaskUserPublishedTrade(WTaskUserPublishedBean wTaskUserPublishedBean, HokolAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_trade;
		new HokolHttp().doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class, adapter);
	}

	/**
	 * 用户已发布任务；待评价
	 */
	public static void doTaskUserPublishedEvaluate(WTaskUserPublishedBean wTaskUserPublishedBean, HokolAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_evaluate;
		new HokolHttp().doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class, adapter);
	}

	/**
	 * 任务报名
	 */
	public static void doTaskActionStaffSignUp(WTaskActionStaffSignUpBean wTaskActionStaffSignUpBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_staff_signup;
		new HokolHttp().doPost(httpUrl, wTaskActionStaffSignUpBean, String.class, adapter);
	}

	/**
	 * 雇主录用报名者
	 */
	public static void doTaskActionMasterTakeOn(WTaskActionMasterTakeOnBean wTaskActionMasterTakeOnBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_takeon;
		new HokolHttp().doPost(httpUrl, wTaskActionMasterTakeOnBean, String.class, adapter);
	}

	/**
	 * 雇主评价雇员
	 */
	public static void doTaskActionMasterComment(WTaskActionMasterCommentBean wTaskActionMasterCommentBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_comment;
		new HokolHttp().doPost(httpUrl, wTaskActionMasterCommentBean, String.class, adapter);
	}

	/**
	 * 获取该任务雇员信息
	 */
	public static void doTaskStaffCommentedInfo(WTaskStaffCommentedInfoBean wTaskStaffCommentedInfoBean, HokolAdapter<VTaskStaffCommentedInfoBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_master_comment_info;
		new HokolHttp().doPost(httpUrl, wTaskStaffCommentedInfoBean, VTaskStaffCommentedInfoBean.class, adapter);
	}

	/**
	 * 雇主结束任务
	 */
	public static void doTaskActionMasterFinish(WTaskActionMasterFinishBean wTaskActionMasterFinishBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_finish;
		new HokolHttp().doPost(httpUrl, wTaskActionMasterFinishBean, String.class, adapter);
	}

	/**
	 * 雇主结束任务
	 */
	public static void doTaskActionMasterCancel(WTaskActionMasterCancelBean wTaskActionMasterCancelBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_cancel;
		new HokolHttp().doPost(httpUrl, wTaskActionMasterCancelBean, String.class, adapter);
	}

	/**
	 * 雇主确定交易
	 */
	public static void doTaskActionMasterTrade(WTaskActionMasterTradeBean wTaskActionMasterTradeBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_trade;
		new HokolHttp().doPost(httpUrl, wTaskActionMasterTradeBean, String.class, adapter);
	}

	/**
	 * 用户已投递的任务
	 */
	public static void doTaskUserDelivered(WTaskUserDeliveredBean wTaskUserDeliveredBean, HokolAdapter<VTaskUserDeliveredBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_delivered;
		new HokolHttp().doPost(httpUrl, wTaskUserDeliveredBean, VTaskUserDeliveredBean.class, adapter);
	}

	/**
	 * 雇员确认、拒绝接单
	 */
	public static void doTaskActionStaffConfirm(WTaskActionStaffConfirmBean wTaskActionStaffConfirmBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_staff_confirm;
		new HokolHttp().doPost(httpUrl, wTaskActionStaffConfirmBean, String.class, adapter);
	}

	/**
	 * 雇员确认交易
	 */
	public static void doTaskActionStaffTrade(WTaskActionStaffTradeBean wTaskActionStaffTradeBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_staff_trade;
		new HokolHttp().doPost(httpUrl, wTaskActionStaffTradeBean, String.class, adapter);
	}

	/**
	 * 雇员确认交易
	 */
	public static void doTaskActionStaffComment(WTaskActionStaffCommentBean wTaskActionStaffCommentBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_staff_comment;
		new HokolHttp().doPost(httpUrl, wTaskActionStaffCommentBean, String.class, adapter);
	}

	/**
	 * 删除任务，仅仅有删除记录的功能
	 */
	public static void doTaskDelete(WTaskDeleteBean taskDeleteBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_delete;
		new HokolHttp().doPost(httpUrl, taskDeleteBean, String.class, adapter);
	}

	/**
	 * 主页接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 请求主页动态数据 --> index --> url_home_main --> WHomeMainBean - VHomeMainBean --> OK
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 主页接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 进行了数据过滤
	 * 请求主页动态数据
	 */
	public static void doHomeMain(WHomeMainBean wHomeMainBean, HokolAdapter<VHomeMainBean> adapter)
	{
		String httpUrl = HttpConstant.url_home_main;
		new HokolHttp().doPost(httpUrl, wHomeMainBean, VHomeMainBean.class, adapter);
	}

	/**
	 * 主页推荐
	 */
	public static void doRecommendHome(HokolAdapter<VRecommendHomeBean> adapter)
	{
		String httpUrl = HttpConstant.url_recommend_home;
		new HokolHttp().doPost(httpUrl, "", VRecommendHomeBean.class, adapter);
	}

	/**
	 * 任务推荐
	 */
	public static void doRecommendTask(HokolAdapter<VRecommendTaskBean> adapter)
	{
		String httpUrl = HttpConstant.url_recommend_task;
		new HokolHttp().doPost(httpUrl, "", VRecommendTaskBean.class, adapter);
	}

	/**
	 * 我的页面 接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 请求用户粉丝的信息（多条） --> user_fans_info --> user_fans_info --> WUserFansAllBean - VUserFansAllBean -->
	 * 请求用户的收藏 --> user_collect_task --> url_user_collection --> WUserTaskCollectionBean - VUserTaskCollectionBean -->
	 * 关注/取消关注某用户 --> user_care_switch --> url_user_care_or_cancel --> WUserCareOrCancelBean - null -->
	 * 用户赠送红豆 --> present_coin --> url_user_coin_gift --> WUserCoinGiftBean - null -->
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 我的页面 接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 请求用户粉丝的信息（多条）
	 */
	public static void doUserFansAll(WUserFansAllBean wUserFansAllBean, HokolAdapter<VUserFansAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_fans_all;
		new HokolHttp().doPost(httpUrl, wUserFansAllBean, VUserFansAllBean.class, adapter);
	}

	/**
	 * 请求用户的收藏任务
	 */
	public static void doUserCollection(WUserTaskCollectionBean wUserCollectionBean, HokolAdapter<VUserTaskCollectionBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_collection;
		new HokolHttp().doPost(httpUrl, wUserCollectionBean, VUserTaskCollectionBean.class, adapter);
	}

	/**
	 * 关注/取消关注某用户
	 */
	public static void doUserCareOrCancel(WUserCareOrCancelBean wUserCareOrCancelBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_user_care_or_cancel;
		new HokolHttp().doPost(httpUrl, wUserCareOrCancelBean, String.class, adapter);
	}

	/**
	 * 用户赠送红豆
	 */
	public static void doUserCoinGift(WUserCoinGiftBean wUserCoinGiftBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_user_coin_gift;
		new HokolHttp().doPost(httpUrl, wUserCoinGiftBean, String.class, adapter);
	}

	/**
	 * 我的消息，所有
	 */
	public static void doUserMessageSystem(WUserMessageSystemBean wUserMessageBean, HokolAdapter<VUserMessageSystemBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_message;
		new HokolHttp().doPost(httpUrl, wUserMessageBean, VUserMessageSystemBean.class, adapter);
	}

	/**
	 * 我的消息，概要
	 */
	public static void doUserSystemMessageOutline(WUserMessageSystemOutlineBean outlineBean, HokolAdapter<VUserMessageSystemOutlineBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_message_system_all;
		new HokolHttp().doPost(httpUrl, outlineBean, VUserMessageSystemOutlineBean.class, adapter);
	}

	/**
	 * 我的消息，读取
	 */
	public static void doUserSystemMessageSignRead(WUserSystemMessageSignReadBean signReadBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_user_message_system_readed;
		new HokolHttp().doPost(httpUrl, signReadBean, String.class, adapter);
	}

	/**
	 * 我的消息，删除
	 */
	public static void doUserSystemMessageSignDelete(WUserSystemMessageSignDeleteBean signDeleteBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_user_message_system_delete;
		new HokolHttp().doPost(httpUrl, signDeleteBean, String.class, adapter);
	}

	/**
	 * 接收的礼物
	 */
	public static void doUserGiftReceive(WUserGiftReceiveBean receiveBean, HokolAdapter<VUserGiftReceiveBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_gift_receivve;
		new HokolHttp().doPost(httpUrl, receiveBean, VUserGiftReceiveBean.class, adapter);
	}
	
	/**
	 * 送出的红豆
	 */
	public static void doUserGiftSend(WUserGiftSendBean sendBean, HokolAdapter<VUserGiftSendBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_gift_send;
		new HokolHttp().doPost(httpUrl, sendBean, VUserGiftSendBean.class, adapter);
	}

	/**
	 * 充值记录
	 */
	public static void doUserRechargeRecord(WUserRechargeRecordBean recordBean, HokolAdapter<VUserRechargeRecordBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_recharge_record;
		new HokolHttp().doPost(httpUrl, recordBean, VUserRechargeRecordBean.class, adapter);
	}

	/**
	 * 我的会员
	 */
	public static void doUserVipInfo(WUserVipInfoBean vipInfoBean, HokolAdapter<VUserVipInfoBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_vip_info;
		new HokolHttp().doPost(httpUrl, vipInfoBean, VUserVipInfoBean.class, adapter);
	}

	/**
	 * 会员充值记录
	 */
	public static void doUserVipRechargeRecord(WUserVipRechargeRecordBean recordBean, HokolAdapter<VUserVipRechargeRecordBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_vip_recharge_record;
		new HokolHttp().doPost(httpUrl, recordBean, VUserVipRechargeRecordBean.class, adapter);
	}

	/**
	 * 未使用的交流卷
	 */
	public static void doUserContactVolumeUnapply(WUserContactVolumeBean bean, HokolAdapter<VUserContactVolumeBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_contact_volume_unapply;
		new HokolHttp().doPost(httpUrl, bean, VUserContactVolumeBean.class, adapter);
	}

	/**
	 * 已使用的交流卷
	 */
	public static void doUserContactVolumeApplied(WUserContactVolumeBean bean, HokolAdapter<VUserContactVolumeBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_vip_recharge_applied;
		new HokolHttp().doPost(httpUrl, bean, VUserContactVolumeBean.class, adapter);
	}

	/**
	 * 已过期的交流卷
	 */
	public static void doUserContactVolumePassed(WUserContactVolumeBean bean, HokolAdapter<VUserContactVolumeBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_vip_recharge_expired;
		new HokolHttp().doPost(httpUrl, bean, VUserContactVolumeBean.class, adapter);
	}

	/**
	 * 用户信用
	 */
	public static void doUserCredit(WUserCreditBean creditBean, HokolAdapter<VUserCreditBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_credit;
		new HokolHttp().doPost(httpUrl, creditBean, VUserCreditBean.class, adapter);
	}
	
	/**
	 * 我的评分(已发任务)
	 */
	public static void doUserTaskScoreAssigned(WUserTaskScoreAssignedBean assignedBean, HokolAdapter<VUserTaskScoreAssignedBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_task_score_assigned;
		new HokolHttp().doPost(httpUrl, assignedBean, VUserTaskScoreAssignedBean.class, adapter);
	}

	/**
	 * 我的评分(已投任务)
	 */
	public static void doUserTaskScoreDelivered(WUserTaskScoreDeliveredBean deliveredBean, HokolAdapter<VUserTaskScoreDeliveredBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_task_score_delivered;
		new HokolHttp().doPost(httpUrl, deliveredBean, VUserTaskScoreDeliveredBean.class, adapter);
	}

	/**
	 * 查看评论(已发任务)
	 */
	public static void doUserTaskCommentAssigned(WUserTaskCommentAssignedBean assignedBean, HokolAdapter<VUserTaskCommentAssignedBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_task_comment_assigned;
		new HokolHttp().doPost(httpUrl, assignedBean, VUserTaskCommentAssignedBean.class, adapter);
	}

	/**
	 * 查看评论(已投任务)
	 */
	public static void doUserTaskCommentDelivered(WUserTaskCommentDeliveredBean creditBean, HokolAdapter<VUserTaskCommentDeliveredBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_task_comment_delivered;
		new HokolHttp().doPost(httpUrl, creditBean, VUserTaskCommentDeliveredBean.class, adapter);
	}

	/**
	 * 设置页面
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 用户发表意见 --> user_advice --> url_setting_submit_proposal --> WSettingSubmitProposalBean - null --> ok
	 * 用户重置密码 --> reset_pwd --> url_setting_reset_pwd --> WSettingResetPwdBean - null --> ok
	 * 用户重置手机 --> 接口废弃
	 * 用户信息修改 --> reset_user_info --> url_setting_update_info --> WSettingUpdateInfoBean - null -->
	 * 用户头像修改 --> reset_user_logo -->  暂时不测
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 设置页面 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 用户发表意见
	 */
	public static void doSettingSubmitProposal(WSettingSubmitProposalBean wSettingSubmitProposalBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_setting_submit_proposal;
		new HokolHttp().doPost(httpUrl, wSettingSubmitProposalBean, String.class, adapter);
	}

	/**
	 * 用户重置密码
	 */
	public static void doSettingResetPwd(WSettingResetPwdBean wSettingResetPwdBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_setting_reset_pwd;
		new HokolHttp().doPost(httpUrl, wSettingResetPwdBean, String.class, adapter);
	}

	/**
	 * 用户信息修改
	 */
	public static void doSettingUpdateInfo(WSettingUpdateInfoBean wSettingUpdateInfoBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_setting_update_info;
		new HokolHttp().doPost(httpUrl, wSettingUpdateInfoBean, String.class, adapter);
	}

	/**
	 * 修改用户头像
	 */
	public static void doSettingUpdateAvatar(final String userId, final File file, HokolAdapter<VUserAvatarBean> adapter)
	{
		String httpUrl = HttpConstant.url_setting_update_avatar;

		if (null != file || !file.exists())
		{
			HokolHttp hokolHttp = new HokolHttp();

			MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
			if (hokolHttp.isDebug())
			{
				LogFileUtil.v("user_id = " + userId + ", user_logo = " + file.getAbsolutePath());
			}
			bodyBuilder.addFormDataPart("user_id", userId);
			bodyBuilder.addFormDataPart("user_logo", file.getName(), RequestBody.create(MediaType.parse("image"), file));

			hokolHttp.doPost(httpUrl, bodyBuilder, VUserAvatarBean.class, adapter);
		}
		else
		{
			LogFileUtil.e(TAG, "Setting Update Avatar file is null or not exists");
		}
	}
	
	/**
	 * 设置页面
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 获取地区 --> get_area --> url_area_all --> null - VAreaAllBean --> ok
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 设置页面 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	public static void doAreaAll(HokolAdapter<VAreaAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_area_all;
		new HokolHttp()
		{
			@Override
			protected OkHttpClient getHttpClient()
			{
				return HttpCachePriorClient.getInstance();
			}
		}.doPost(httpUrl, "", VAreaAllBean.class, adapter);
	}

	/**
	 * 获取支付宝签名数据
	 */
	public static void doAliPayOrderInfo(WAliPayOrderInfoBean infoBean, HokolAdapter<VAliPayOrderInfoBean> adapter)
	{
		String httpUrl = HttpConstant.url_ali_pay_order_info;
		new HokolHttp().doPost(httpUrl, infoBean, VAliPayOrderInfoBean.class, adapter);
	}

	/**
	 * 微信注册/登录
	 */
	public static void doWeChatLogin(WWeChatLoginBean loginBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_we_chat_login;
		new HokolHttp().doPost(httpUrl, loginBean, String.class, adapter);
	}

	/**
	 * 微信注册 填写信息
	 */
	public static void doWeChatRegisterInfo(WWeChatRegisterInfoBean infoBean, HokolAdapter<VWeChatRegisterInfoBean> adapter)
	{
		String httpUrl = HttpConstant.url_we_chat_register;
		new HokolHttp().doPost(httpUrl, infoBean, VWeChatRegisterInfoBean.class, adapter);
	}

	/**
	 * 微信注册 填写信息
	 */
	public static void doWeChatRegisterICode(WWeChatRegisterICodeBean codeBean, HokolAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_we_chat_i_code;
		new HokolHttp().doPost(httpUrl, codeBean, String.class, adapter);
	}
}
