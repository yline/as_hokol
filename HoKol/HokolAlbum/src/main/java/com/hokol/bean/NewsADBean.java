package com.hokol.bean;

import android.content.Context;
import android.widget.ImageView;

import com.hokol.R;

/**
 * Created by yline on 2017/2/13.
 */
public class NewsADBean
{
	// 图片大小
	private int viewWidth = -1; // match

	private int viewHeight = -2; // wrap

	// 自动播放
	private boolean isRecycle = true; // 是否轮回

	private boolean isAutoRecycle = true; // 是否自动播放(必须轮回才能自动播放)

	private boolean isAutoRecycleToRight = true; // 向右滑动

	private int timeAutoRecycle = 4500; // 自动播放

	private int timeUpRecycle = 8000; // 用户手离开之后暂停的时间(滑动长时间),滑动短时间则范围是:（8000-5000/3,8000+5000/3）

	// 资源有关
	private int pointStartPosition = 0; // 开始数

	private int pointCount = 5; // 总数,默认,设置图片资源的时候赋值

	private int pointResId = R.drawable.delete_ad_point; // 点资源  selected

	private int[] resInt; // 图片资源

	public void setResInt(int[] resInt)
	{
		this.resInt = resInt;
		this.pointCount = resInt.length;
	}

	/**
	 * 图片循环设置
	 * @param isRecycle     是否循环
	 * @param isAutoRecycle 是否自动播放
	 */
	public void setRecycleSetting(boolean isRecycle, boolean isAutoRecycle)
	{
		this.isRecycle = isRecycle;
		this.isAutoRecycle = isAutoRecycle;
	}

	public ImageView getViewRes(Context context, int position)
	{
		ImageView imageView = new ImageView(context);

		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageResource(resInt[position]);

		return imageView;
	}

	public int getRecycleCount()
	{
		if (isAutoRecycleToRight)
		{
			return pointCount * 3 + 2;
		}
		else
		{
			return pointCount * 3 - 1;
		}
	}

	public int getViewWidth()
	{
		return viewWidth;
	}

	public void setViewWidth(int viewWidth)
	{
		this.viewWidth = viewWidth;
	}

	public int getViewHeight()
	{
		return viewHeight;
	}

	public void setViewHeight(int viewHeight)
	{
		this.viewHeight = viewHeight;
	}

	public boolean isRecycle()
	{
		return isRecycle;
	}

	public void setRecycle(boolean recycle)
	{
		isRecycle = recycle;
	}

	public boolean isAutoRecycle()
	{
		return isAutoRecycle;
	}

	public void setAutoRecycle(boolean autoRecycle)
	{
		isAutoRecycle = autoRecycle;
	}

	public boolean isAutoRecycleToRight()
	{
		return isAutoRecycleToRight;
	}

	public void setAutoRecycleToRight(boolean autoRecycleToRight)
	{
		isAutoRecycleToRight = autoRecycleToRight;
	}

	public int getTimeAutoRecycle()
	{
		return timeAutoRecycle;
	}

	public void setTimeAutoRecycle(int timeAutoRecycle)
	{
		this.timeAutoRecycle = timeAutoRecycle;
	}

	public int getTimeUpRecycle()
	{
		return timeUpRecycle;
	}

	public void setTimeUpRecycle(int timeUpRecycle)
	{
		this.timeUpRecycle = timeUpRecycle;
	}

	public int getPointStartPosition()
	{
		return pointStartPosition;
	}

	public void setPointStartPosition(int pointStartPosition)
	{
		this.pointStartPosition = pointStartPosition;
	}

	public int getPointCount()
	{
		return pointCount;
	}

	public void setPointCount(int pointCount)
	{
		this.pointCount = pointCount;
	}

	public int getPointResId()
	{
		return pointResId;
	}

	public void setPointResId(int pointResId)
	{
		this.pointResId = pointResId;
	}

	public int[] getResInt()
	{
		return resInt;
	}
}
