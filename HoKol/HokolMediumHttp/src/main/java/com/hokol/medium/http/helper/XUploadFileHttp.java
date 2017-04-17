package com.hokol.medium.http.helper;

import com.google.gson.Gson;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.XHttpAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 实现文件上传(小文件),单个图片请求
 *
 * @author yline 2017/4/14 -- 15:09
 * @version 1.0.0
 */
public abstract class XUploadFileHttp<Result>
{
	private HttpHandler httpHandler;

	private XHttpAdapter<Result> xHttpAdapter;

	public XUploadFileHttp(XHttpAdapter adapter)
	{
		this.xHttpAdapter = adapter;
		if (isResponseHandler())
		{
			this.httpHandler = HttpHandler.build();
		}
	}

	public void doPost(String httpUrl, final Class<Result> clazz)
	{
		OkHttpClient okHttpClient = getOkHttpClient();

		Request request = getRequest(httpUrl);

		okHttpClient.newCall(request).enqueue(new Callback()
		{
			@Override
			public void onFailure(Call call, IOException e)
			{
				handleFailure(e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException
			{
				handleResponse(response, clazz);
			}
		});
	}

	protected RequestBody getRequestBody()
	{
		MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();

		bodyBuilder.setType(MultipartBody.FORM);
		initRequestForm(bodyBuilder);

		MultipartBody multipartBody = bodyBuilder.build();
		if (xHttpAdapter.isDebug())
		{
			LogFileUtil.v("MultipartBody Size = " + multipartBody.size());
		}
		
		return multipartBody;
	}

	private Request getRequest(String httpUrl)
	{
		if (xHttpAdapter.isDebug())
		{
			LogFileUtil.v("httpUrl = " + httpUrl);
		}
		Request.Builder requestBuilder = new Request.Builder();

		requestBuilder.url(httpUrl);
		requestBuilder.post(getRequestBody());

		return requestBuilder.build();
	}

	private OkHttpClient getOkHttpClient()
	{
		OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();

		// 设置缓存; 略过
		// 设置超时
		okClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS);

		return okClientBuilder.build();
	}

	private void handleResponse(Response response, Class<Result> clazz) throws IOException
	{
		String jsonResult = response.body().string();

		// 入口日志
		if (xHttpAdapter.isDebug())
		{
			LogFileUtil.v("response", (null == jsonResult ? "null" : jsonResult.toString()));
		}

		// 进行code处理一次
		try
		{
			JSONObject jsonObject = new JSONObject(jsonResult);

			int code = jsonObject.getInt("code");
			if (HttpConstant.REQUEST_SUCCESS_CODE == code)
			{
				jsonResult = jsonObject.getString("data");
			}
			else
			{
				handleFailureCode(code);
				return;
			}
		} catch (JSONException ex)
		{
			handleFailure(ex);
			return;
		}

		// 响应是否 Gson 解析
		if (isResponseParse())
		{
			// 解析
			Result result = new Gson().fromJson(jsonResult, clazz);
			handleSuccess(result);
		}
		else
		{
			handleSuccess((Result) jsonResult);
		}
	}

	private void handleFailure(final Exception ex)
	{
		// 是否需要 Handler 处理一次
		if (isResponseHandler())
		{
			httpHandler.post(new Runnable()
			{
				@Override
				public void run()
				{
					xHttpAdapter.onFailure(ex);
				}
			});
		}
		else
		{
			xHttpAdapter.onFailure(ex);
		}
	}

	private void handleSuccess(final Result result)
	{
		if (isResponseHandler())
		{
			httpHandler.post(new Runnable()
			{
				@Override
				public void run()
				{
					xHttpAdapter.onSuccess(result);
				}
			});
		}
		else
		{
			xHttpAdapter.onSuccess(result);
		}
	}

	private void handleFailureCode(final int code)
	{
		if (isResponseHandler())
		{
			httpHandler.post(new Runnable()
			{
				@Override
				public void run()
				{
					xHttpAdapter.onFailureCode(code);
				}
			});
		}
		else
		{
			xHttpAdapter.onFailureCode(code);
		}
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 重写的数据 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */

	protected abstract void initRequestForm(MultipartBody.Builder bodyBuilder);

	/**
	 * 是否需要 Handler 处理一次
	 *
	 * @return
	 */
	protected boolean isResponseHandler()
	{
		return true;
	}

	/**
	 * 如果返回的,以字符串输出；则设置成true
	 * 如果需要解析,则设置成false
	 *
	 * @return
	 */
	protected boolean isResponseParse()
	{
		return true;
	}
}
