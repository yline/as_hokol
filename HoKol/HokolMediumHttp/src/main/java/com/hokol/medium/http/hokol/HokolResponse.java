package com.hokol.medium.http.hokol;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yline.http.XHttpAdapter;
import com.yline.http.request.IRequestParam;
import com.yline.http.response.XHttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HokolResponse extends XHttpResponse
{
	public HokolResponse(XHttpAdapter adapter, IRequestParam iRequestParam)
	{
		super(adapter, iRequestParam);
	}

	@Override
	protected <T> void handleSuccess(String responseData, Class<T> clazz) throws IOException
	{
		if (iRequestParam.isResponseCodeHandle() && adapter instanceof HokolAdapter)
		{
			try
			{
				JSONObject jsonObject = new JSONObject(responseData);
				int code = jsonObject.getInt("code");
				String data = jsonObject.getString("data");

				if (null == clazz)
				{
					handlerHokolAdapter((HokolAdapter) adapter, Integer.MIN_VALUE, null);
				}
				else if (clazz == String.class)
				{
					handlerHokolAdapter((HokolAdapter) adapter, code, data);
				}
				else
				{
					// code -> json 解析 -> 返回数据
					T result = new Gson().fromJson(data, clazz);
					handlerHokolAdapter((HokolAdapter) adapter, code, result);
					handleAdapter(result);
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			catch (JsonSyntaxException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			super.handleSuccess(responseData, clazz);
		}
	}

	private <T> void handlerHokolAdapter(final HokolAdapter hokolAdapter, final int code, final T result)
	{
		if (iRequestParam.isResponseHandler())
		{
			httpHandler.post(new Runnable()
			{
				@Override
				public void run()
				{
					hokolAdapter.onSuccess(code, result);
				}
			});
		}
		else
		{
			hokolAdapter.onSuccess(code, result);
		}
	}
}
