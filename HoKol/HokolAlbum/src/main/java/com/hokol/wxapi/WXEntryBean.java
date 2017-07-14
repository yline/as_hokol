package com.hokol.wxapi;

/**
 * 1，需要用户安装微信客户端才能使用
 */
public class WXEntryBean
{
	// 直接使用到的  关键字
	/* 应用唯一标识，在微信开放平台提交应用审核通过后获得 */
	public static final String APP_ID = "wxa16045aae728f8b6";

	public static final String APP_SECRET = "26b54ea2a3eaa432cdbf54e6ec011dcd";

	/* 置为简单的随机数加session进行校验; 固定的自己知道的字符串 */
	private static final String KeyWcEntry = "HokolMediumModule_For_Wechat_Login";

	public static final String APP_STATE = KeyWcEntry;

	private static final String KeyTypeUserInfo = "snsapi_userinfo";

	/* 应用授权作用域，如获取用户个人信息则填写 snsapi_userinfo */
	public static final String APP_SCOPE = KeyTypeUserInfo;
}
