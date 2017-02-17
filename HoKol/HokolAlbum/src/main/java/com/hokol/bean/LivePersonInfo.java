package com.hokol.bean;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播 的人物信息(仅仅展示图片)
 * @author yline 2017/2/17 --> 11:23
 * @version 1.0.0
 */
public class LivePersonInfo
{
	/** 人物图片 */
	private Drawable personImage = null;

	/** 人物名称 */
	private String personName = "girl";

	/** 观看人数 */
	private int watchingNumber = 0;

	/** 是否正在直播 */
	private boolean isLiving = false;

	/** 人物地区 */
	private String personArea = String.format("%s-%s", "浙江", "杭州");

	/** 人物距离 */
	private String distance = "14km";

	/** 人物标签 */
	private List<String> personTag = new ArrayList<>();

	public LivePersonInfo(Drawable personImage)
	{
		this.personImage = personImage;
	}

	public Drawable getPersonImage()
	{
		return personImage;
	}

	public void setPersonImage(Drawable personImage)
	{
		this.personImage = personImage;
	}

	public String getPersonName()
	{
		return personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}

	public int getWatchingNumber()
	{
		return watchingNumber;
	}

	public void setWatchingNumber(int watchingNumber)
	{
		this.watchingNumber = watchingNumber;
	}

	public boolean isLiving()
	{
		return isLiving;
	}

	public void setLiving(boolean living)
	{
		isLiving = living;
	}

	public String getPersonArea()
	{
		return personArea;
	}

	public void setPersonArea(String personArea)
	{
		this.personArea = personArea;
	}

	public String getDistance()
	{
		return distance;
	}

	public void setDistance(String distance)
	{
		this.distance = distance;
	}

	public List<String> getPersonTag()
	{
		return personTag;
	}

	public void setPersonTag(List<String> personTag)
	{
		this.personTag = personTag;
	}
}
