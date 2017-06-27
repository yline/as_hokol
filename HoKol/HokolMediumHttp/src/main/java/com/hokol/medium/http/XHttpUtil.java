package com.hokol.medium.http;

import android.content.Context;

import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.http.bean.VDynamicCareAllBean;
import com.hokol.medium.http.bean.VDynamicCareSingleBean;
import com.hokol.medium.http.bean.VDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.VDynamicUserAllBean;
import com.hokol.medium.http.bean.VDynamicUserDetailBean;
import com.hokol.medium.http.bean.VDynamicUserPrivateAllBean;
import com.hokol.medium.http.bean.VEnterLoginPhonePwdBean;
import com.hokol.medium.http.bean.VHomeMainBean;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsRecommendBean;
import com.hokol.medium.http.bean.VTaskMainAll;
import com.hokol.medium.http.bean.VTaskMainDetailBean;
import com.hokol.medium.http.bean.VTaskStaffCommentedInfoBean;
import com.hokol.medium.http.bean.VTaskUserAcceptBean;
import com.hokol.medium.http.bean.VTaskUserDeliveredBean;
import com.hokol.medium.http.bean.VTaskUserPublishedBean;
import com.hokol.medium.http.bean.VTaskUserSignUpDetailBean;
import com.hokol.medium.http.bean.VUserAvatarBean;
import com.hokol.medium.http.bean.VUserCareAllBean;
import com.hokol.medium.http.bean.VUserFansAllBean;
import com.hokol.medium.http.bean.VUserMessageSystemBean;
import com.hokol.medium.http.bean.VUserMessageSystemOutlineBean;
import com.hokol.medium.http.bean.VUserTaskCollectionBean;
import com.hokol.medium.http.bean.WDynamicCareAllBean;
import com.hokol.medium.http.bean.WDynamicCareSingleBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicUserAllBean;
import com.hokol.medium.http.bean.WDynamicUserDetailBean;
import com.hokol.medium.http.bean.WDynamicUserPrivateAllBean;
import com.hokol.medium.http.bean.WEnterCodeRegisterBean;
import com.hokol.medium.http.bean.WEnterLoginPhonePwdBean;
import com.hokol.medium.http.bean.WEnterRegisterBean;
import com.hokol.medium.http.bean.WEnterRegisterCompleteInfoBean;
import com.hokol.medium.http.bean.WEnterResetPwdBean;
import com.hokol.medium.http.bean.WHomeMainBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.http.bean.WSettingResetPhoneBean;
import com.hokol.medium.http.bean.WSettingResetPwdBean;
import com.hokol.medium.http.bean.WSettingSubmitProposalBean;
import com.hokol.medium.http.bean.WSettingUpdateInfoBean;
import com.hokol.medium.http.bean.WTaskActionMasterCommentBean;
import com.hokol.medium.http.bean.WTaskActionMasterFinishBean;
import com.hokol.medium.http.bean.WTaskActionMasterTakeOnBean;
import com.hokol.medium.http.bean.WTaskActionMasterTradeBean;
import com.hokol.medium.http.bean.WTaskActionStaffConfirmBean;
import com.hokol.medium.http.bean.WTaskActionStaffSignUpBean;
import com.hokol.medium.http.bean.WTaskActionStaffTradeBean;
import com.hokol.medium.http.bean.WTaskMainAll;
import com.hokol.medium.http.bean.WTaskMainCollectionBean;
import com.hokol.medium.http.bean.WTaskMainDetailBean;
import com.hokol.medium.http.bean.WTaskMainPublishBean;
import com.hokol.medium.http.bean.WTaskStaffCommentedInfoBean;
import com.hokol.medium.http.bean.WTaskUserAcceptBean;
import com.hokol.medium.http.bean.WTaskUserDeliveredBean;
import com.hokol.medium.http.bean.WTaskUserPublishedBean;
import com.hokol.medium.http.bean.WTaskUserSignUpDetailBean;
import com.hokol.medium.http.bean.WUserCareAllBean;
import com.hokol.medium.http.bean.WUserCareOrCancelBean;
import com.hokol.medium.http.bean.WUserCoinGiftBean;
import com.hokol.medium.http.bean.WUserFansAllBean;
import com.hokol.medium.http.bean.WUserMessageSystemBean;
import com.hokol.medium.http.bean.WUserMessageSystemOutlineBean;
import com.hokol.medium.http.bean.WUserTaskCollectionBean;
import com.yline.http.XHttpAdapter;
import com.yline.http.XHttpConfig;
import com.yline.http.XHttpConstant;
import com.yline.http.helper.HttpCacheAndNetClient;
import com.yline.http.helper.XTextHttp;
import com.yline.http.helper.XUploadFileHttp;
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
		config.setCacheDir(cacheDir);
		config.init(context);

		XHttpConstant.setIsInterceptorDebug(false);
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
	public static void doEnterLoginPhonePwd(final WEnterLoginPhonePwdBean requestBean, XHttpAdapter<VEnterLoginPhonePwdBean> adapter)
	{
		String httpUrl = HttpConstant.url_enter_login_pwd;
		new XTextHttp<VEnterLoginPhonePwdBean>(adapter).doPost(httpUrl, requestBean, VEnterLoginPhonePwdBean.class);
	}

	/**
	 * 获取 注册验证码
	 */
	public static void doEnterCodeRegister(WEnterCodeRegisterBean wEnterCodeRegisterBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_code_register;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wEnterCodeRegisterBean, String.class);
	}

	/**
	 * 用户注册
	 */
	public static void doEnterRegister(WEnterRegisterBean wEnterRegisterBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_register;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wEnterRegisterBean, String.class);
	}

	/**
	 * 用户信息完善
	 */
	public static void doEnterRegisterCompleteInfo(WEnterRegisterCompleteInfoBean completeInfoBean, XHttpAdapter<VEnterLoginPhonePwdBean> adapter)
	{
		String httpUrl = HttpConstant.url_enter_register_complete_info;
		new XTextHttp<VEnterLoginPhonePwdBean>(adapter).doPost(httpUrl, completeInfoBean, VEnterLoginPhonePwdBean.class);
	}

	/**
	 * 忘记密码之获取验证码
	 */
	public static void doEnterCodeForgetPwd(WEnterCodeRegisterBean codeRegisterBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_code_forget_pwd;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, codeRegisterBean, String.class);
	}

	/**
	 * 忘记密码之重置密码
	 */
	public static void doEnterResetPwd(WEnterResetPwdBean resetPwdBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_enter_reset_pwd;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, resetPwdBean, String.class);
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
	public static void doNewsRecommend(XHttpAdapter<VNewsRecommendBean> adapter)
	{
		String httpUrl = HttpConstant.url_news_recommend;
		new XTextHttp<VNewsRecommendBean>(adapter).doPost(httpUrl, null, VNewsRecommendBean.class);
	}

	/**
	 * 多条新闻测试
	 */
	public static void doNewsMultiplex(WNewsMultiplexBean wNewsMultiplexBean, XHttpAdapter<VNewsMultiplexBean> adapter)
	{
		String httpUrl = HttpConstant.url_news_multiplex;
		new XTextHttp<VNewsMultiplexBean>(adapter).doPost(httpUrl, wNewsMultiplexBean, VNewsMultiplexBean.class);
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
	public static void doDynamicCareAll(WDynamicCareAllBean wDynamicCareAllBean, XHttpAdapter<VDynamicCareAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_care_all;
		new XTextHttp<VDynamicCareAllBean>(adapter).doPost(httpUrl, wDynamicCareAllBean, VDynamicCareAllBean.class);
	}

	/**
	 * 请求用户详情信息
	 */
	public static void doDynamicUserDetail(WDynamicUserDetailBean wDynamicUserDetailBean, XHttpAdapter<VDynamicUserDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_detail;
		new XTextHttp<VDynamicUserDetailBean>(adapter).doPost(httpUrl, wDynamicUserDetailBean, VDynamicUserDetailBean.class);
	}

	/**
	 * 单条动态(取消)点赞功能
	 */
	public static void doDynamicPraiseSingle(WDynamicPraiseSingleBean wDynamicPraiseSingleBean, XHttpAdapter<VDynamicPraiseSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_praise_single;
		new XTextHttp<VDynamicPraiseSingleBean>(adapter).doPost(httpUrl, wDynamicPraiseSingleBean, VDynamicPraiseSingleBean.class);
	}

	/**
	 * 请求单条动态的信息
	 */
	public static void doDynamicSingle(WDynamicCareSingleBean wDynamicCareSingleBean, XHttpAdapter<VDynamicCareSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_single;
		new XTextHttp<VDynamicCareSingleBean>(adapter).doPost(httpUrl, wDynamicCareSingleBean, VDynamicCareSingleBean.class);
	}

	/**
	 * 请求用户多条动态信息
	 */
	public static void doDynamicUserAll(WDynamicUserAllBean wDynamicUserAllBean, XHttpAdapter<VDynamicUserAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_all;
		new XTextHttp<VDynamicUserAllBean>(adapter).doPost(httpUrl, wDynamicUserAllBean, VDynamicUserAllBean.class);
	}

	/**
	 * 请求用户多条私密动态信息
	 */
	public static void doDynamicUserPrivateAll(WDynamicUserPrivateAllBean wDynamicUserPrivateAllBean, XHttpAdapter<VDynamicUserPrivateAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_private_all;
		new XTextHttp<VDynamicUserPrivateAllBean>(adapter).doPost(httpUrl, wDynamicUserPrivateAllBean, VDynamicUserPrivateAllBean.class);
	}

	/**
	 * 请求关注的人的信息（多条）
	 */
	public static void doUserCareAll(WUserCareAllBean wUserCareAllBean, XHttpAdapter<VUserCareAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_care_all;
		new XTextHttp<VUserCareAllBean>(adapter).doPost(httpUrl, wUserCareAllBean, VUserCareAllBean.class);
	}

	/**
	 * 任务接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 获取任务列表(多条)--> task_index --> url_task_main_all --> WTaskMainAll - VTaskMainAll --> ok
	 * 获取任务详情(单条)--> task_detail --> url_task_main_detail --> WTaskMainDetailBean - VTaskMainDetailBean --> ok
	 * 任务发布--> 暂时不测
	 * 任务收藏/取消收藏--> task_collect_switch --> url_task_main_collection --> WTaskMainCollectionBean - null --> ok
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 任务接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 请求关注的人的信息（多条）
	 */
	public static void doTaskMainAll(WTaskMainAll wTaskMainAll, XHttpAdapter<VTaskMainAll> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_all;
		new XTextHttp<VTaskMainAll>(adapter).doPost(httpUrl, wTaskMainAll, VTaskMainAll.class);
	}

	/**
	 * 获取任务详情(单条)
	 */
	public static void doTaskMainDetail(WTaskMainDetailBean wTaskMainDetailBean, XHttpAdapter<VTaskMainDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_detail;
		new XTextHttp<VTaskMainDetailBean>(adapter).doPost(httpUrl, wTaskMainDetailBean, VTaskMainDetailBean.class);
	}

	/**
	 * 任务发布
	 */
	public static void doTaskMainPublish(WTaskMainPublishBean wTaskMainPublishBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_publish;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskMainPublishBean, String.class);
	}

	/**
	 * 任务收藏/取消收藏
	 */
	public static void doTaskMainCollection(WTaskMainCollectionBean wTaskMainCollectionBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_collection;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskMainCollectionBean, String.class);
	}

	/**
	 * 任务接单详情
	 */
	public static void doTaskUserAcceptDetail(WTaskUserAcceptBean wTaskUserAcceptBean, XHttpAdapter<VTaskUserAcceptBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_accept_detail;
		new XTextHttp<VTaskUserAcceptBean>(adapter).doPost(httpUrl, wTaskUserAcceptBean, VTaskUserAcceptBean.class);
	}

	/**
	 * 任务报名详情
	 */
	public static void doTaskUserSignUpDetail(WTaskUserSignUpDetailBean wTaskUserSignUpBean, XHttpAdapter<VTaskUserSignUpDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_signup_detail;
		new XTextHttp<VTaskUserSignUpDetailBean>(adapter).doPost(httpUrl, wTaskUserSignUpBean, VTaskUserSignUpDetailBean.class);
	}

	/**
	 * 用户已发布任务；全部
	 */
	public static void doTaskUserPublishedAll(WTaskUserPublishedBean wTaskUserPublishedBean, XHttpAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_all;
		new XTextHttp<VTaskUserPublishedBean>(adapter).doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class);
	}

	/**
	 * 用户已发布任务；待报名
	 */
	public static void doTaskUserPublishedSign(WTaskUserPublishedBean wTaskUserPublishedBean, XHttpAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_sign;
		new XTextHttp<VTaskUserPublishedBean>(adapter).doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class);
	}

	/**
	 * 用户已发布任务；待交易
	 */
	public static void doTaskUserPublishedTrade(WTaskUserPublishedBean wTaskUserPublishedBean, XHttpAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_trade;
		new XTextHttp<VTaskUserPublishedBean>(adapter).doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class);
	}

	/**
	 * 用户已发布任务；待评价
	 */
	public static void doTaskUserPublishedEvaluate(WTaskUserPublishedBean wTaskUserPublishedBean, XHttpAdapter<VTaskUserPublishedBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_published_evaluate;
		new XTextHttp<VTaskUserPublishedBean>(adapter).doPost(httpUrl, wTaskUserPublishedBean, VTaskUserPublishedBean.class);
	}

	/**
	 * 任务报名
	 */
	public static void doTaskActionStaffSignUp(WTaskActionStaffSignUpBean wTaskActionStaffSignUpBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_staff_signup;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskActionStaffSignUpBean, String.class);
	}

	/**
	 * 雇主录用报名者
	 */
	public static void doTaskActionMasterTakeOn(WTaskActionMasterTakeOnBean wTaskActionMasterTakeOnBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_takeon;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskActionMasterTakeOnBean, String.class);
	}

	/**
	 * 雇主评价雇员
	 */
	public static void doTaskActionMasterComment(WTaskActionMasterCommentBean wTaskActionMasterCommentBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_comment;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskActionMasterCommentBean, String.class);
	}

	/**
	 * 获取该任务雇员信息
	 */
	public static void doTaskStaffCommentedInfo(WTaskStaffCommentedInfoBean wTaskStaffCommentedInfoBean, XHttpAdapter<VTaskStaffCommentedInfoBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_master_comment_info;
		new XTextHttp<VTaskStaffCommentedInfoBean>(adapter).doPost(httpUrl, wTaskStaffCommentedInfoBean, VTaskStaffCommentedInfoBean.class);
	}

	/**
	 * 雇主结束报名/取消任务
	 */
	public static void doTaskActionMasterFinish(WTaskActionMasterFinishBean wTaskActionMasterFinishBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_finish;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskActionMasterFinishBean, String.class);
	}

	/**
	 * 雇主确定交易
	 */
	public static void doTaskActionMasterTrade(WTaskActionMasterTradeBean wTaskActionMasterTradeBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_master_trade;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskActionMasterTradeBean, String.class);
	}

	/**
	 * 用户已投递的任务
	 */
	public static void doTaskUserDelivered(WTaskUserDeliveredBean wTaskUserDeliveredBean, XHttpAdapter<VTaskUserDeliveredBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_user_delivered;
		new XTextHttp<VTaskUserDeliveredBean>(adapter).doPost(httpUrl, wTaskUserDeliveredBean, VTaskUserDeliveredBean.class);
	}

	/**
	 * 雇员确认、拒绝接单
	 */
	public static void doTaskActionStaffConfirm(WTaskActionStaffConfirmBean wTaskActionStaffConfirmBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_staff_confirm;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskActionStaffConfirmBean, String.class);
	}

	/**
	 * 雇员确认交易
	 */
	public static void doTaskActionStaffTrade(WTaskActionStaffTradeBean wTaskActionStaffTradeBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_action_staff_trade;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskActionStaffTradeBean, String.class);
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
	public static void doHomeMain(WHomeMainBean wHomeMainBean, XHttpAdapter<VHomeMainBean> adapter)
	{
		String httpUrl = HttpConstant.url_home_main;
		new XTextHttp<VHomeMainBean>(adapter).doPost(httpUrl, wHomeMainBean, VHomeMainBean.class);
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
	public static void doUserFansAll(WUserFansAllBean wUserFansAllBean, XHttpAdapter<VUserFansAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_fans_all;
		new XTextHttp<VUserFansAllBean>(adapter).doPost(httpUrl, wUserFansAllBean, VUserFansAllBean.class);
	}

	/**
	 * 请求用户的收藏任务
	 */
	public static void doUserCollection(WUserTaskCollectionBean wUserCollectionBean, XHttpAdapter<VUserTaskCollectionBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_collection;
		new XTextHttp<VUserTaskCollectionBean>(adapter).doPost(httpUrl, wUserCollectionBean, VUserTaskCollectionBean.class);
	}

	/**
	 * 关注/取消关注某用户
	 */
	public static void doUserCareOrCancel(WUserCareOrCancelBean wUserCareOrCancelBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_user_care_or_cancel;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wUserCareOrCancelBean, String.class);
	}

	/**
	 * 用户赠送红豆
	 */
	public static void doUserCoinGift(WUserCoinGiftBean wUserCoinGiftBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_user_coin_gift;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wUserCoinGiftBean, String.class);
	}

	/**
	 * 我的消息，所有
	 */
	public static void doUserMessageSystem(WUserMessageSystemBean wUserMessageBean, XHttpAdapter<VUserMessageSystemBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_message;
		new XTextHttp<VUserMessageSystemBean>(adapter).doPost(httpUrl, wUserMessageBean, VUserMessageSystemBean.class);
	}

	/**
	 * 我的消息，概要
	 */
	public static void doUserSystemMessageOutline(WUserMessageSystemOutlineBean outlineBean, XHttpAdapter<VUserMessageSystemOutlineBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_message_system_all;
		new XTextHttp<VUserMessageSystemOutlineBean>(adapter).doPost(httpUrl, outlineBean, VUserMessageSystemOutlineBean.class);
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
	public static void doSettingSubmitProposal(WSettingSubmitProposalBean wSettingSubmitProposalBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_setting_submit_proposal;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wSettingSubmitProposalBean, String.class);
	}

	/**
	 * 用户重置密码
	 */
	public static void doSettingResetPwd(WSettingResetPwdBean wSettingResetPwdBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_setting_reset_pwd;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wSettingResetPwdBean, String.class);
	}

	/**
	 * 用户重置手机
	 */
	public static void doSettingResetPhone(WSettingResetPhoneBean wSettingResetPhoneBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_setting_reset_phone;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wSettingResetPhoneBean, String.class);
	}

	/**
	 * 用户信息修改
	 */
	public static void doSettingUpdateInfo(WSettingUpdateInfoBean wSettingUpdateInfoBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_setting_update_info;
		new XTextHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wSettingUpdateInfoBean, String.class);
	}

	/**
	 * 修改用户头像
	 */
	public static void doSettingUpdateAvatar(final String userId, final File file, XHttpAdapter<VUserAvatarBean> adapter)
	{
		String httpUrl = HttpConstant.url_setting_update_avatar;

		if (null != file || !file.exists())
		{
			new XUploadFileHttp<VUserAvatarBean>(adapter)
			{
				@Override
				protected void initRequestForm(MultipartBody.Builder bodyBuilder)
				{
					if (isDebug())
					{
						LogFileUtil.v("user_id = " + userId + ", user_logo = " + file.getAbsolutePath());
					}
					bodyBuilder.addFormDataPart("user_id", userId);
					bodyBuilder.addFormDataPart("user_logo", file.getName(), RequestBody.create(MediaType.parse("image"), file));
				}
			}.doPost(httpUrl, VUserAvatarBean.class);
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
	public static void doAreaAll(XHttpAdapter<VAreaAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_area_all;
		new XTextHttp<VAreaAllBean>(adapter)
		{
			@Override
			protected OkHttpClient getClient()
			{
				return HttpCacheAndNetClient.getInstance();
			}
		}.doPost(httpUrl, null, VAreaAllBean.class);
	}

	private static boolean isDebug()
	{
		return XHttpConstant.isDefaultDebug();
	}
}
