package com.hokol.medium.http.hokol;

import com.yline.http.XHttpAdapter;
import com.yline.http.request.XHttp;
import com.yline.http.response.IResponse;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HokolHttp extends XHttp
{
	@Override
	protected <T> IResponse getHttpResponse(XHttpAdapter<T> adapter)
	{
		return new HokolResponse(adapter, this);
	}

	@Override
	protected Request genPostMultiRequest(String actionUrl, MultipartBody.Builder bodyBuilder)
	{
		return super.genPostMultiRequest(actionUrl, bodyBuilder);
	}

	@Override
	public boolean isResponseCodeHandle()
	{
		return super.isResponseCodeHandle();
	}

	@Override
	public boolean isResponseHandler()
	{
		return super.isResponseHandler();
	}

	@Override
	public boolean isResponseJsonType()
	{
		return super.isResponseJsonType();
	}

	@Override
	public boolean isResponseCache()
	{
		return super.isResponseCache();
	}

	@Override
	public boolean isDebug()
	{
		return super.isDebug();
	}

	@Override
	protected OkHttpClient getHttpClient()
	{
		return super.getHttpClient();
	}

	@Override
	protected void onRequestBuilder(Request.Builder builder)
	{
		super.onRequestBuilder(builder);
	}
}
