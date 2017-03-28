package com.hokol.medium.http.helper;

import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttp;
import com.hokol.medium.http.XHttpAdapter;
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
import com.hokol.medium.http.bean.WDynamicCareAllBean;
import com.hokol.medium.http.bean.WDynamicCareSingleBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicUserAllBean;
import com.hokol.medium.http.bean.WDynamicUserDetailBean;
import com.hokol.medium.http.bean.WHomeMainBean;
import com.hokol.medium.http.bean.WLoginPhonePwdBean;
import com.hokol.medium.http.bean.WNewsMultiplexBean;
import com.hokol.medium.http.bean.WTaskMainAll;
import com.hokol.medium.http.bean.WTaskMainCollectionBean;
import com.hokol.medium.http.bean.WTaskMainDetailBean;
import com.hokol.medium.http.bean.WUserCareAllBean;

public class XHttpUtil
{
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 登录接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 手机号 + 密码登陆
	 */
	public static void doLoginPhonePwd(final WLoginPhonePwdBean requestBean, XHttpAdapter<VLoginPhonePwdBean> adapter)
	{
		String httpUrl = HttpConstant.url_login_pwd;
		new XHttp<VLoginPhonePwdBean>(adapter).doPost(httpUrl, requestBean, VLoginPhonePwdBean.class);
	}

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

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 主页接口 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	/**
	 * 请求主页动态数据
	 */
	public static void doHomeMain(WHomeMainBean wHomeMainBean, XHttpAdapter<VHomeMainBean> adapter)
	{
		String httpUrl = HttpConstant.url_home_main;
		new XHttp<VHomeMainBean>(adapter).doPost(httpUrl, wHomeMainBean, VHomeMainBean.class);
	}


}
