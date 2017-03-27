package com.hokol.base.utils;

import java.util.Locale;

/**
 * 时间单位转换,转换类
 *
 * @author YLine
 *         2016年8月3日 下午7:50:16
 */
public class TimeConvertUtil
{
	/**
	 * @param time 毫秒  12223000
	 * @return 3:23:43
	 */
	public static String ms2FormatMinute(int time)
	{
		int seconds = time / 1000;
		if (seconds < 3600)
		{
			return String.format(Locale.CHINA, "%02d:%02d", seconds / 60, seconds % 60);
		}
		else
		{
			return String.format(Locale.CHINA, "%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
		}
	}

	/**
	 * 15分钟内 刚刚
	 * 60分钟内 多少分钟前
	 * 昨天
	 * 几天前
	 * 日期
	 * 几个小时前
	 *
	 * @param oldTime 时间戳，单位毫秒
	 * @return
	 */
	public static String stamp2FormatTime(long oldTime)
	{
		long newTime = System.currentTimeMillis();
		int durationTime = (int) ((newTime - oldTime) / 1000);
		if (durationTime < 900)
		{
			return "刚刚";
		}
		else if (durationTime < 3600)
		{
			return (durationTime / 60 + "分钟前");
		}
		else // 昨天,多少天前,日期,小时
		{
			int oldYear = TimeStampUtil.getYear(oldTime);
			int oldMonth = TimeStampUtil.getMonth(oldTime);
			int oldDay = TimeStampUtil.getDay(oldTime);
			int durationYear = TimeStampUtil.getYear(newTime) - oldYear;
			int durationMonth = TimeStampUtil.getMonth(newTime) - oldMonth;
			int durationDay = TimeStampUtil.getDay(newTime) - oldDay;
			if (durationYear > 0 || durationMonth > 0 || durationDay > 9)
			{
				return (String.format("%d-%d-%d", oldYear, oldMonth, oldDay));
			}
			else if (durationDay == 0)
			{
				return (durationTime / 3600 + "小时前");
			}
			else if (durationDay == 1)
			{
				return "昨天";
			}
			else
			{
				return durationDay + "天前";
			}
		}
	}
}
