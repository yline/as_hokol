package com.nhjh7.e.http;

import android.os.Handler;

import com.yline.log.LogFileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUtils
{
	private static final String TAG = "HttpUtils";

	private static final int CONNNECT_TIMEOUT = 5000; // 连接超时时间 ms

	private static final int READ_TIMEOUT = 5000; // 读取超时时间 ms

	private static final int HTTPCONNECT_SUCCESS_CODE = 200; // 网络请求,正确返回码

	private static Handler mHandler = new Handler();

	public static void doGetAsync(final String url, final Callback callback)
	{
		String httpUrl = String.format("http://%s", url);
		doGet(httpUrl, callback);
	}

	private static void doGet(final String httpUrl, final Callback callback)
	{
		LogFileUtil.v("url :" + httpUrl);

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				URL url = null;
				HttpURLConnection httpURLConnection = null;

				BufferedReader bufferedReader = null;

				StringBuffer result = new StringBuffer();

				try
				{
					url = new URL(httpUrl); // 创建一个URL对象
					LogFileUtil.v(TAG, "doGetAsyn -> new url over");

					// 调用URL的openConnection()方法,获取HttpURLConnection对象
					httpURLConnection = (HttpURLConnection) url.openConnection();

					// HttpURLConnection默认就是用GET发送请求,因此也可省略
					httpURLConnection.setRequestMethod("GET");
					initHttpConnection(httpURLConnection);
					/** 请求头,日志 */
					String requestHeader = HttpHelperUtils.getHttpRequestHeader(httpURLConnection);
					LogFileUtil.i(TAG, "doGetAsyn -> initHttpConnection over requestHeader\n" + requestHeader);

					int responseCode = httpURLConnection.getResponseCode();
					LogFileUtil.i(TAG, "doGetAsyn -> responseCode = " + responseCode);

					if (HTTPCONNECT_SUCCESS_CODE == responseCode || responseCode == 201) // 连接成功
					{
						bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
						String line = null;
						while (null != (line = bufferedReader.readLine())) // 即:有数据时,一直在读取
						{
							result.append(line);
						}
						
						/** 响应头,日志 */
						String responseHeader = HttpHelperUtils.getHttpResponseHeader(httpURLConnection);
						LogFileUtil.i(TAG, "doGetAsyn -> responseHeader\n" + responseHeader);

						handleSuccess(callback, result.toString());
					}
					else
					{
						LogFileUtil.e(TAG, "doGetAsyn -> responseCode error");
						handleError(callback, new Exception("doGetAsyn -> responseCode error code = " + responseCode));
					}
				}
				catch (MalformedURLException e)
				{
					LogFileUtil.e(TAG, "doGetAsyn -> MalformedURLException", e);
					handleError(callback, e);
				}
				catch (ProtocolException e)
				{
					LogFileUtil.e(TAG, "doGetAsyn -> ProtocolException", e);
					handleError(callback, e);
				}
				catch (IOException e)
				{
					LogFileUtil.e(TAG, "doGetAsyn -> IOException", e);
					handleError(callback, e);
				}
				finally
				{
					if (null != bufferedReader)
					{
						try
						{
							bufferedReader.close();
						}
						catch (IOException e)
						{
							LogFileUtil.e(TAG, "doGetAsyn -> close IOException", e);
							handleError(callback, e);
						}
					}
					if (null != httpURLConnection)
					{
						httpURLConnection.disconnect();
					}
				}
			}
		}).start();
	}

	/**
	 * 回调,抛出异常到主线程
	 * @param callback
	 * @param e
	 */

	private static void handleError(final Callback callback, final Exception e)
	{
		mHandler.post(new Runnable()
		{

			@Override
			public void run()
			{
				if (null != callback)
				{
					callback.onError(e);
				}
			}
		});
	}

	/**
	 * 回调,抛出网络请求结果到主线程
	 * @param callback
	 * @param result
	 */
	private static void handleSuccess(final Callback callback, final String result)
	{
		mHandler.post(new Runnable()
		{

			@Override
			public void run()
			{
				if (null != callback)
				{
					callback.onSuccess(result);
				}
			}
		});
	}

	/**
	 * http 链接  设置
	 * RequestHeader : setRequestProperty
	 * parameter     : 直接在后面加的参数
	 * @param connection
	 */
	private static void initHttpConnection(HttpURLConnection connection)
	{
		// 用setRequestProperty方法设置多个自定义的请求头:action，用于后端判断
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("action", "post");

		// 禁用网络缓存
		connection.setUseCaches(false);
		// 定制
		connection.setDoOutput(true); // 设置此方法,允许向服务器输出内容

		// HttpURLConnection默认也支持从服务端读取结果流，因此也可省略
		connection.setDoInput(true);
		connection.setReadTimeout(READ_TIMEOUT); // 设置读取超时为5秒
		connection.setConnectTimeout(CONNNECT_TIMEOUT); // 设置连接网络超时为5秒
	}

	/**
	 * 设置请求体
	 * @param connection
	 * @param json       json字符串
	 */
	private static void initHttpConnectionBody(HttpURLConnection connection, String json)
	{
		OutputStream outputStream = null;
		try
		{
			outputStream = connection.getOutputStream();
			byte[] bytes = HttpHelperUtils.getByteFromString(json);
			outputStream.write(bytes);
		}
		catch (IOException e)
		{
			LogFileUtil.e(TAG, "initHttpConnectionBody -> IOException", e);
		}
		finally
		{
			try
			{
				outputStream.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public interface Callback
	{
		void onSuccess(String result);

		void onError(Exception ex);
	}
}
