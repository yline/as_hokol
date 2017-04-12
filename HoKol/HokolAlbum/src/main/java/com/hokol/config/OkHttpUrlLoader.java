package com.hokol.config;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * 迁徙 glide-okhttp3-integration-1.4.0.jar的
 * 不修改
 *
 * @author yline 2017/4/12 -- 9:58
 * @version 1.0.0
 */
public class OkHttpUrlLoader implements ModelLoader<GlideUrl, InputStream>
{
	private final okhttp3.Call.Factory client;

	public OkHttpUrlLoader(okhttp3.Call.Factory client)
	{
		this.client = client;
	}

	public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height)
	{
		return new OkHttpStreamFetcher(this.client, model);
	}

	public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream>
	{
		private static volatile okhttp3.Call.Factory internalClient;

		private okhttp3.Call.Factory client;

		public Factory()
		{
			this(getInternalClient());
		}

		public Factory(okhttp3.Call.Factory client)
		{
			this.client = client;
		}

		private static okhttp3.Call.Factory getInternalClient()
		{
			if (internalClient == null)
			{
				Class var0 = OkHttpUrlLoader.Factory.class;
				synchronized (OkHttpUrlLoader.Factory.class)
				{
					if (internalClient == null)
					{
						internalClient = new OkHttpClient();
					}
				}
			}

			return internalClient;
		}

		public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories)
		{
			return new OkHttpUrlLoader(this.client);
		}

		public void teardown()
		{
		}
	}
}

