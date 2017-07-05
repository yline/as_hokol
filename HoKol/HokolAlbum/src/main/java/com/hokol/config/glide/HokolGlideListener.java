package com.hokol.config.glide;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yline.log.LogFileUtil;

public class HokolGlideListener implements RequestListener
{
	@Override
	public boolean onException(Exception e, Object model, Target target, boolean isFirstResource)
	{
		LogFileUtil.e("onException", "isFirstResource = " + isFirstResource, e);
		return false;
	}

	@Override
	public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource)
	{
		LogFileUtil.v("onResourceReady", "isFirstResource = " + isFirstResource + ", isFromMemoryCache  = " + isFromMemoryCache);
		return false;
	}
}
