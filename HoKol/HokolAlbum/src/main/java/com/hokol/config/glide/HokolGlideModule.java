package com.hokol.config.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Glide 全局 配置
 *
 * @author yline 2017/4/12 -- 9:42
 * @version 1.0.0
 */
public class HokolGlideModule implements GlideModule
{
	// 图片缓存路径
	public static final String path_glide_picture = "Glide_Picture";

	public static final int size_glide_picture = 512 * 1024 * 1024;

	@Override
	public void applyOptions(Context context, GlideBuilder builder)
	{
		// 设置内存缓存大小
		int maxMemory = (int) Runtime.getRuntime().maxMemory(); // 获取系统分配给应用的总内存大小
		int memoryCacheSize = maxMemory / 4;
		builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
		
		// 设置 磁盘缓存大小
		String path = context.getExternalFilesDir(path_glide_picture).getAbsolutePath();
		builder.setDiskCache(new DiskLruCacheFactory(path, size_glide_picture));
	}

	@Override
	public void registerComponents(Context context, Glide glide)
	{
		glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
	}
}
