package com.hokol.util;

import com.yline.utils.TimeConvertUtil;

import java.util.Calendar;

/**
 * 定制的单位转换类
 *
 * @author yline 2017/6/24 -- 12:23
 * @version 1.0.0
 */
public class HokolTimeConvertUtil extends TimeConvertUtil
{
	/**
	 * 计算剩余时间
	 *
	 * @param oldTime 截止 的 时间戳，单位毫秒
	 * @return
	 */
	public static String stampToRestFormatTime(long oldTime)
	{
		long diffTime = (oldTime - System.currentTimeMillis()) / 1000;
		if (diffTime < 0)
		{
			return "";
		}
		else if (diffTime < 3600)
		{
			return String.format("%d分", diffTime / 60);
		}
		else if (diffTime < 3600 * 24)
		{
			return String.format("%d时%d分", diffTime / 3600, (diffTime % 3600 / 60));
		}
		else if (diffTime < 3600 * 24 * 100)
		{
			return String.format("%d天%d时", diffTime / 86400, diffTime % 86400 / 3600);
		}
		else
		{
			return String.format("%d天", diffTime / 86400);
		}
	}

	/**
	 * @param time 时间，单位毫秒
	 * @return
	 */
	public static String stampToFormatDate(long time)
	{
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return String.format("%s.%s.%s", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * @param time     时间，单位毫秒
	 * @param minField the given calendar field.
	 * @return
	 */
	public static String stampToFormatDate(long time, int minField)
	{
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		if (minField == Calendar.DAY_OF_MONTH)
		{
			return String.format("%s.%s.%s", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));
		}
		else if (minField == Calendar.HOUR_OF_DAY)
		{
			return String.format("%s.%s.%s %s", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH), instance.get(Calendar.HOUR_OF_DAY));
		}
		else if (minField == Calendar.MINUTE)
		{
			return String.format("%s.%s.%s %s:%s", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH), instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE));
		}
		else if (minField == Calendar.SECOND)
		{
			return String.format("%s.%s.%s %s:%s:%s", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH), instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), instance.get(Calendar.SECOND));
		}
		else
		{
			return String.format("%s.%s.%s", instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));
		}
	}
}
