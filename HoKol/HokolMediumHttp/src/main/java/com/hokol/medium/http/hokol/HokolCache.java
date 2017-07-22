package com.hokol.medium.http.hokol;

import com.yline.http.cache.XCache;

import java.io.File;
import java.io.IOException;

import okhttp3.Request;

public class HokolCache extends XCache
{
	protected HokolCache(File dir, long maxSize)
	{
		super(dir, maxSize);
	}

	@Override
	protected String key(Request request) throws IOException
	{
		return super.key(request);
	}
}
