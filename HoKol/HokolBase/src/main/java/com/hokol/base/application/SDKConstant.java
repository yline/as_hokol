package com.hokol.base.application;

/**
 * 底层库专用的常量
 * simple introduction
 */
public class SDKConstant
{
	// application 功能

	/** 应用心跳 */
	public static final int HANDLER_PALPITATION = -2;

	/** 应用心跳频率 */
	public static final int APPLICATION_TIME = 2 * 60 * 1000;

	/** handler 吐丝 */
	public static final int HANDLER_TOAST = -1;

	// 其他

	/** 用于请求权限 */
	public static final int REQUEST_CODE_PERMISSION = 1025;

	public static final String TAG_HANDLE_PERMISSION = "Deny permissions : ";
}
