package com.hokol.medium.http.helper;


import android.os.Handler;

/**
 * 提供,统一处理Http的handler
 *
 * @author yline 2017/2/23 --> 10:26
 * @version 1.0.0
 */
public class HttpTextHandler extends Handler
{
	private HttpTextHandler()
	{
	}

	public static HttpTextHandler build()
	{
		return HttpHandlerHold.sInstance;
	}

	private static class HttpHandlerHold
	{
		private static HttpTextHandler sInstance = new HttpTextHandler();
	}
}
