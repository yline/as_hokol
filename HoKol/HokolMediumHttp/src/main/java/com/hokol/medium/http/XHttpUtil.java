package com.hokol.medium.http;

import com.hokol.medium.http.bean.VAreaAllBean;
import com.hokol.medium.http.bean.VDynamicCareAllBean;
import com.hokol.medium.http.bean.VDynamicCareSingleBean;
import com.hokol.medium.http.bean.VDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.VDynamicUserAllBean;
import com.hokol.medium.http.bean.VDynamicUserDetailBean;
import com.hokol.medium.http.bean.VHomeMainBean;
import com.hokol.medium.http.bean.VLoginPhonePwdBean;
import com.hokol.medium.http.bean.VNewsMultiplexBean;
import com.hokol.medium.http.bean.VNewsSingleBean;
import com.hokol.medium.http.bean.VTaskMainAll;
import com.hokol.medium.http.bean.VTaskMainDetailBean;
import com.hokol.medium.http.bean.VUserCareAllBean;
import com.hokol.medium.http.bean.VUserCollectionBean;
import com.hokol.medium.http.bean.VUserFansAllBean;
import com.hokol.medium.http.bean.WDynamicCareAllBean;
import com.hokol.medium.http.bean.WDynamicCareSingleBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicUserAllBean;
import com.hokol.medium.http.bean.WDynamicUserDetailBean;
import com.hokol.medium.http.bean.WHomeMainBean;
import com.hokol.medium.http.bean.WLoginPhonePwdBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.http.bean.WSettingResetPhoneBean;
import com.hokol.medium.http.bean.WSettingResetPwdBean;
import com.hokol.medium.http.bean.WSettingSubmitProposalBean;
import com.hokol.medium.http.bean.WSettingUpdateInfoBean;
import com.hokol.medium.http.bean.WTaskMainAll;
import com.hokol.medium.http.bean.WTaskMainCollectionBean;
import com.hokol.medium.http.bean.WTaskMainDetailBean;
import com.hokol.medium.http.bean.WUserCareAllBean;
import com.hokol.medium.http.bean.WUserCareOrCancelBean;
import com.hokol.medium.http.bean.WUserCoinGiftBean;
import com.hokol.medium.http.bean.WUserCollectionBean;
import com.hokol.medium.http.bean.WUserFansAllBean;

public class XHttpUtil
{

	/**
	 * 登录接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 手机号+密码登陆 --> login --> url_login_pwd --> WLoginPhonePwdBean - VLoginPhonePwdBean --> OK
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 登录接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 手机号 + 密码登陆
	 */
	public static void doLoginPhonePwd(final WLoginPhonePwdBean requestBean, XHttpAdapter<VLoginPhonePwdBean> adapter)
	{
		String httpUrl = HttpConstant.url_login_pwd;
		new XHttp<VLoginPhonePwdBean>(adapter).doPost(httpUrl, requestBean, VLoginPhonePwdBean.class);
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
	public static void doNewsRecommend(XHttpAdapter<VNewsSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_news_recommend;
		new XHttp<VNewsSingleBean>(adapter).doPost(httpUrl, null, VNewsSingleBean.class);
	}

	/**
	 * 多条新闻测试
	 */
	public static void doNewsMultiplex(WNewsMultiplexBean wNewsMultiplexBean, XHttpAdapter<VNewsMultiplexBean> adapter)
	{
		String httpUrl = HttpConstant.url_news_multiplex;
		new XHttp<VNewsMultiplexBean>(adapter).doPost(httpUrl, wNewsMultiplexBean, VNewsMultiplexBean.class);
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
		new XHttp<VDynamicCareAllBean>(adapter).doPost(httpUrl, wDynamicCareAllBean, VDynamicCareAllBean.class);
	}

	/**
	 * 请求关注的人的多条动态
	 */
	public static void doDynamicUserDetail(WDynamicUserDetailBean wDynamicUserDetailBean, XHttpAdapter<VDynamicUserDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_detail;
		new XHttp<VDynamicUserDetailBean>(adapter).doPost(httpUrl, wDynamicUserDetailBean, VDynamicUserDetailBean.class);
	}

	/**
	 * 单条动态(取消)点赞功能
	 */
	public static void doDynamicPraiseSingle(WDynamicPraiseSingleBean wDynamicPraiseSingleBean, XHttpAdapter<VDynamicPraiseSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_praise_single;
		new XHttp<VDynamicPraiseSingleBean>(adapter).doPost(httpUrl, wDynamicPraiseSingleBean, VDynamicPraiseSingleBean.class);
	}

	/**
	 * 请求单条动态的信息
	 */
	public static void doDynamicSingle(WDynamicCareSingleBean wDynamicCareSingleBean, XHttpAdapter<VDynamicCareSingleBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_single;
		new XHttp<VDynamicCareSingleBean>(adapter).doPost(httpUrl, wDynamicCareSingleBean, VDynamicCareSingleBean.class);
	}

	/**
	 * 请求用户多条动态信息
	 */
	public static void doDynamicUserAll(WDynamicUserAllBean wDynamicUserAllBean, XHttpAdapter<VDynamicUserAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_dynamic_user_all;
		new XHttp<VDynamicUserAllBean>(adapter).doPost(httpUrl, wDynamicUserAllBean, VDynamicUserAllBean.class);
	}

	/**
	 * 请求关注的人的信息（多条）
	 */
	public static void doUserCareAll(WUserCareAllBean wUserCareAllBean, XHttpAdapter<VUserCareAllBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_care_all;
		new XHttp<VUserCareAllBean>(adapter).doPost(httpUrl, wUserCareAllBean, VUserCareAllBean.class);
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
		new XHttp<VTaskMainAll>(adapter).doPost(httpUrl, wTaskMainAll, VTaskMainAll.class);
	}

	/**
	 * 获取任务详情(单条)
	 */
	public static void doTaskMainDetail(WTaskMainDetailBean wTaskMainDetailBean, XHttpAdapter<VTaskMainDetailBean> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_detail;
		new XHttp<VTaskMainDetailBean>(adapter).doPost(httpUrl, wTaskMainDetailBean, VTaskMainDetailBean.class);
	}

	/**
	 * 请求关注的人的信息（多条）
	 */
	public static void doTaskMainCollection(WTaskMainCollectionBean wTaskMainCollectionBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_task_main_collection;
		new XHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wTaskMainCollectionBean, String.class);
	}

	/**
	 * 主页接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 请求主页动态数据 --> index --> url_home_main --> WHomeMainBean - VHomeMainBean --> OK
	 */
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 主页接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 请求主页动态数据
	 */
	public static void doHomeMain(WHomeMainBean wHomeMainBean, XHttpAdapter<VHomeMainBean> adapter)
	{
		String httpUrl = HttpConstant.url_home_main;
		new XHttp<VHomeMainBean>(adapter).doPost(httpUrl, wHomeMainBean, VHomeMainBean.class);
	}

	/**
	 * 我的页面 接口
	 * Button名称 --> API后缀 --> HttpConstant --> Bean名称 - Bean名称 --> 情况
	 * 请求用户粉丝的信息（多条） --> user_fans_info --> user_fans_info --> WUserFansAllBean - VUserFansAllBean -->
	 * 请求用户的收藏 --> user_collect_task --> url_user_collection --> WUserCollectionBean - VUserCollectionBean -->
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
		new XHttp<VUserFansAllBean>(adapter).doPost(httpUrl, wUserFansAllBean, VUserFansAllBean.class);
	}

	/**
	 * 请求用户的收藏
	 */
	public static void doUserCollection(WUserCollectionBean wUserCollectionBean, XHttpAdapter<VUserCollectionBean> adapter)
	{
		String httpUrl = HttpConstant.url_user_collection;
		new XHttp<VUserCollectionBean>(adapter).doPost(httpUrl, wUserCollectionBean, VUserCollectionBean.class);
	}

	/**
	 * 关注/取消关注某用户
	 */
	public static void doUserCareOrCancel(WUserCareOrCancelBean wUserCareOrCancelBean, XHttpAdapter<String> adapter)
	{
		String httpUrl = HttpConstant.url_user_care_or_cancel;
		new XHttp<String>(adapter)
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
		new XHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wUserCoinGiftBean, String.class);
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
		new XHttp<String>(adapter)
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
		new XHttp<String>(adapter)
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
		new XHttp<String>(adapter)
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
		new XHttp<String>(adapter)
		{
			@Override
			protected boolean isResponseParse()
			{
				return false;
			}
		}.doPost(httpUrl, wSettingUpdateInfoBean, String.class);
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
		new XHttp<VAreaAllBean>(adapter).doPost(httpUrl, null, VAreaAllBean.class);
	}
}
