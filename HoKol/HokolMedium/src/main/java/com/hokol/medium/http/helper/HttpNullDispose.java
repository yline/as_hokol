package com.hokol.medium.http.helper;

import android.os.Handler;

import com.google.gson.Gson;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.HttpConstant;
import com.hokol.medium.http.bean.ResponseXBean;

import org.json.JSONException;

import okhttp3.Call;

/**
 * Get网络请求 处理接口
 *
 * @author yline 2017/3/1 --> 10:25
 * @version 1.0.0
 */
public class HttpNullDispose<Result> implements IHttpDispose<Result>
{
	private Handler handler;

	private IHttpResponse iHttpResponse;

	public HttpNullDispose(Handler handler, IHttpResponse iHttpResponse)
	{
		this.handler = handler;
		this.iHttpResponse = iHttpResponse;
	}

	@Override
	public void onNetSuccess(Call call, String jsonResult, Class<Result> clazz)
	{
		if (null != iHttpResponse && null != handler)
		{
			try
			{
				Gson gson = new Gson();
				final ResponseXBean responseXBean = HttpConstant.getResponseXBean(jsonResult);

				preResponse("onNetSuccess responseXBean -> " + (null == responseXBean ? "null" : responseXBean.toString()));
				// 为了放到子线程进行Gson解析
				Result result = null;
				if (HttpConstant.REQUEST_SUCCESS_CODE == responseXBean.getCode())
				{
					result = gson.fromJson(responseXBean.getData(), clazz);
				}

				final Result finalResult = result;
				handler.post(new Runnable()
				{
					@Override
					public void run()
					{
						if (HttpConstant.REQUEST_SUCCESS_CODE == responseXBean.getCode())
						{
							iHttpResponse.onSuccess(finalResult);
						}
						else
						{
							iHttpResponse.onFailureCode(responseXBean.getCode());
						}
					}
				});
			} catch (JSONException e)
			{
				onFailure(call, e, true);
			}
		}
	}

	@Override
	public void onFailure(Call call, final Exception ex, boolean isResponse)
	{
		if (null != iHttpResponse && null != handler)
		{
			handler.post(new Runnable()
			{
				@Override
				public void run()
				{
					// 网络错误,网络本身错误
					iHttpResponse.onFailure(ex);
				}
			});
		}
	}

	private void preResponse(String msg)
	{
		if (HttpConstant.isDebug)
		{
			LogFileUtil.v(msg);
		}
	}
}
