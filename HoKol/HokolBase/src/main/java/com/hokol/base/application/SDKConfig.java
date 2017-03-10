package com.hokol.base.application;

/**
 * 在Application中配置父类所需要的配置选项
 * simple introduction
 *
 * @author YLine 2016-5-27 -> 上午7:28:17
 */
public class SDKConfig
{
	/**
	 * 打印日志工具,是否打印日志
	 */
	private boolean isUtilLog = true;

	/**
	 * 打印日志工具,是否打印日志到文件
	 */
	private boolean isUtilLogToFile = true;

	/**
	 * 打印日志工具,日志内容是否提供定位功能
	 */
	private boolean isUtilLogLocation = true;

	/**
	 * 打印日志工具,是否使用System.out.print打印日志
	 * 正常的LogCat失效时，使用
	 */
	private boolean isUtilLogBySystem = false;

	/**
	 * SDK库工程(该工程)是否打印日志
	 */
	private boolean isSDKLog = true;

	public boolean isUtilLog()
	{
		return isUtilLog;
	}

	public void setUtilLog(boolean utilLog)
	{
		isUtilLog = utilLog;
	}

	public boolean isUtilLogToFile()
	{
		return isUtilLogToFile;
	}

	public void setUtilLogToFile(boolean utilLogToFile)
	{
		isUtilLogToFile = utilLogToFile;
	}

	public boolean isUtilLogLocation()
	{
		return isUtilLogLocation;
	}

	public void setUtilLogLocation(boolean utilLogLocation)
	{
		isUtilLogLocation = utilLogLocation;
	}

	public boolean isUtilLogBySystem()
	{
		return isUtilLogBySystem;
	}

	public void setUtilLogBySystem(boolean utilLogBySystem)
	{
		isUtilLogBySystem = utilLogBySystem;
	}

	public boolean isSDKLog()
	{
		return isSDKLog;
	}

	public void setSDKLog(boolean SDKLog)
	{
		isSDKLog = SDKLog;
	}

	@Override
	public String toString()
	{
		return "SDKConfig{" +
				"isUtilLog=" + isUtilLog +
				", isUtilLogToFile=" + isUtilLogToFile +
				", isUtilLogLocation=" + isUtilLogLocation +
				", isUtilLogBySystem=" + isUtilLogBySystem +
				", isSDKLog=" + isSDKLog +
				'}';
	}
}
