package com.hokol.medium.http.hokol;

import com.yline.http.XHttpAdapter;
import com.yline.http.request.IRequestParam;
import com.yline.http.response.XHttpResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class HokolResponse extends XHttpResponse
{
	public HokolResponse(XHttpAdapter adapter, IRequestParam iRequestParam)
	{
		super(adapter, iRequestParam);
	}

	@Override
	protected String setCache(Response response) throws IOException
	{
		return super.setCache(response);
	}

	@Override
	public <T> void handleSuccess(Call call, Response response, Class<T> clazz) throws IOException
	{
		super.handleSuccess(call, response, clazz);
	}

	@Override
	public <T> void handleFailure(Call call, IOException ex)
	{
		super.handleFailure(call, ex);
	}
}
