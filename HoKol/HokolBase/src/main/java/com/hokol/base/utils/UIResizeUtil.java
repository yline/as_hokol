package com.hokol.base.utils;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.log.LogFileUtil;

/**
 * 调整 UI 视图大小, 选择那个方法依据的是 其对应的上一层框体
 * 1,采用连缀的写法
 * 2,默认自动适配传入的View的父布局
 * 3,使用Apply作为结束方法
 * 4,默认适配宽度,不适配高度【都是按照设计比例适配】
 * @author yline 2017/2/9 --> 18:39
 * @version 1.0.0
 */
public class UIResizeUtil
{
	// 常量 boolean 值
	private static final int TRUE = 1;

	private static final int FALSE = 0;

	// key of array
	private static final int WIDTH = 0;

	private static final int HEIGHT = 1;

	private static final int LEFT_MARGIN = 2;

	private static final int RIGHT_MARGIN = 3;

	private static final int TOP_MARGIN = 4;

	private static final int BOTTOM_MARGIN = 5;

	private static final int IS_WIDTH_ADAPTER = 6;

	private static final int IS_HEIGHT_ADAPTER = 7;

	// type of parent view
	private static final int LINEAR_LAYOUT = 10;

	private static final int FRAME_LAYOUT = 11;

	private static final int RELATIVE_LAYOUT = 12;

	private static final int GALLERY_LAYOUT = 13;

	private static final int OTHERS_LAYOUT = 14;

	// 设计图 宽度和高度
	private static final int designWidth = 720; // 设计图宽度

	private static final int designHeight = 1080; // 设计图高度

	private int appWidth = 0; // 宽度适配

	private int appHeight = 0;    // 高度适配

	private static SparseArray<Integer> array = new SparseArray<>();

	private boolean isWidthAdapter;

	private boolean isHeightAdapter;

	private UIResizeUtil()
	{
		/** 实例化失败 */
		// throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static UIResizeUtil build()
	{
		UIResizeUtil util = UIResizeHold.sInstance;
		array.clear();
		array.put(IS_WIDTH_ADAPTER, TRUE);
		array.put(IS_HEIGHT_ADAPTER, FALSE);
		return util;
	}

	private static class UIResizeHold
	{
		private final static UIResizeUtil sInstance = new UIResizeUtil();
	}

	public UIResizeUtil setIsWidthAdapter(boolean isWidthAdapter)
	{
		if (isWidthAdapter)
		{
			array.put(IS_WIDTH_ADAPTER, TRUE);
		}
		else
		{
			array.put(IS_WIDTH_ADAPTER, FALSE);
		}
		return this;
	}

	public UIResizeUtil setIsHeightAdapter(boolean isHeightAdapter)
	{
		if (isHeightAdapter)
		{
			array.put(IS_HEIGHT_ADAPTER, TRUE);
		}
		else
		{
			array.put(IS_HEIGHT_ADAPTER, FALSE);
		}
		return this;
	}

	public UIResizeUtil setWidth(int value)
	{
		array.put(WIDTH, value);
		return this;
	}

	public UIResizeUtil setHeight(int value)
	{
		array.put(HEIGHT, value);
		return this;
	}

	public UIResizeUtil setLeftMargin(int value)
	{
		array.put(LEFT_MARGIN, value);
		return this;
	}

	public UIResizeUtil setRightMargin(int value)
	{
		array.put(RIGHT_MARGIN, value);
		return this;
	}

	public UIResizeUtil setTopMargin(int value)
	{
		array.put(TOP_MARGIN, value);
		return this;
	}

	public UIResizeUtil setBottomMargin(int value)
	{
		array.put(BOTTOM_MARGIN, value);
		return this;
	}

	/**
	 * 实现 view 控制
	 * @param view
	 */
	public void commit(View view)
	{
		if (null != view)
		{
			ViewGroup.LayoutParams param = view.getLayoutParams();

			// 配置 type 和 param
			int type;
			if (view.getParent() instanceof FrameLayout)
			{
				type = FRAME_LAYOUT;
				if (null == param)
				{
					param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				}
			}
			else if (view.getParent() instanceof LinearLayout) // RadioGroup
			{
				type = LINEAR_LAYOUT;
				if (null == param)
				{
					param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				}
			}
			else if (view.getParent() instanceof RelativeLayout)
			{
				type = RELATIVE_LAYOUT;
				if (null == param)
				{
					param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				}
			}
			else if (view.getParent() instanceof Gallery.LayoutParams)
			{
				type = GALLERY_LAYOUT;
				if (null == param)
				{
					param = new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				}
			}
			else // ViewGroup, Gallery
			{
				LogFileUtil.e(BaseApplication.TAG, "UILayoutUtils -> setLayoutAll parent window error");
				type = OTHERS_LAYOUT;
				if (null == param)
				{
					param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				}
			}

			isWidthAdapter = array.get(IS_WIDTH_ADAPTER) == TRUE ? true : false;
			isHeightAdapter = array.get(IS_HEIGHT_ADAPTER) == TRUE ? true : false;

			apply(view, param, type);
		}
	}

	private void apply(View view, ViewGroup.LayoutParams param, int type)
	{
		// 设置值
		for (int i = 0; i < array.size(); i++)
		{
			switch (array.keyAt(i))
			{
				case WIDTH:
					setWidth(param, array.get(WIDTH));
					break;
				case HEIGHT:
					setHeight(param, array.get(HEIGHT));
					break;
				case LEFT_MARGIN:
					setLeftMargin(type, param, array.get(LEFT_MARGIN));
					break;
				case RIGHT_MARGIN:
					setRightMargin(type, param, array.get(RIGHT_MARGIN));
					break;
				case TOP_MARGIN:
					setTopMargin(type, param, array.get(TOP_MARGIN));
					break;
				case BOTTOM_MARGIN:
					setBottomMargin(type, param, array.get(BOTTOM_MARGIN));
					break;
				default:
					break;
			}
		}

		view.setLayoutParams(param);
	}

	private void setWidth(ViewGroup.LayoutParams param, int value)
	{
		if (isWidthAdapter)
		{
			value = value * getAppWidth() / designWidth;
		}
		param.width = value;
	}

	private void setHeight(ViewGroup.LayoutParams param, int value)
	{
		if (isHeightAdapter)
		{
			value = value * getAppHeight() / designHeight;
		}
		param.height = value;
	}

	private void setLeftMargin(int type, ViewGroup.LayoutParams param, int value)
	{
		if (isWidthAdapter)
		{
			value = value * getAppWidth() / designWidth;
		}

		switch (type)
		{
			case FRAME_LAYOUT:
				((FrameLayout.LayoutParams) param).leftMargin = value;
				break;
			case LINEAR_LAYOUT:
				((LinearLayout.LayoutParams) param).leftMargin = value;
				break;
			case RELATIVE_LAYOUT:
				((RelativeLayout.LayoutParams) param).leftMargin = value;
				break;
			default:
				break;
		}
	}

	private void setRightMargin(int type, ViewGroup.LayoutParams param, int value)
	{
		if (isWidthAdapter)
		{
			value = value * getAppWidth() / designWidth;
		}

		switch (type)
		{
			case FRAME_LAYOUT:
				((FrameLayout.LayoutParams) param).rightMargin = value;
				break;
			case LINEAR_LAYOUT:
				((LinearLayout.LayoutParams) param).rightMargin = value;
				break;
			case RELATIVE_LAYOUT:
				((RelativeLayout.LayoutParams) param).rightMargin = value;
				break;
			default:
				break;
		}
	}

	private void setTopMargin(int type, ViewGroup.LayoutParams param, int value)
	{
		if (isHeightAdapter)
		{
			value = value * getAppHeight() / designHeight;
		}

		switch (type)
		{
			case FRAME_LAYOUT:
				((FrameLayout.LayoutParams) param).topMargin = value;
				break;
			case LINEAR_LAYOUT:
				((LinearLayout.LayoutParams) param).topMargin = value;
				break;
			case RELATIVE_LAYOUT:
				((RelativeLayout.LayoutParams) param).topMargin = value;
				break;
			default:
				break;
		}
	}

	private void setBottomMargin(int type, ViewGroup.LayoutParams param, int value)
	{
		if (isHeightAdapter)
		{
			value = value * getAppHeight() / designHeight;
		}

		switch (type)
		{
			case FRAME_LAYOUT:
				((FrameLayout.LayoutParams) param).bottomMargin = value;
				break;
			case LINEAR_LAYOUT:
				((LinearLayout.LayoutParams) param).bottomMargin = value;
				break;
			case RELATIVE_LAYOUT:
				((RelativeLayout.LayoutParams) param).bottomMargin = value;
				break;
			default:
				break;
		}
	}

	public static int getDesignHeight()
	{
		return designHeight;
	}

	public static int getDesignWidth()
	{
		return designWidth;
	}

	/**
	 * 获取屏幕宽度,策略为先从缓存中获取
	 * @return
	 */
	public int getAppWidth()
	{
		if (appWidth == 0)
		{
			appWidth = UIScreenUtil.getAbsoluteScreenWidth(BaseApplication.getApplication());
			LogFileUtil.i(BaseApplication.TAG, "UIResizeUtils -> getAppWidth width = " + appWidth);
		}
		return appWidth;
	}

	public int getAppHeight()
	{
		if (0 == appHeight)
		{
			appHeight = UIScreenUtil.getAbsoluteScreenHeight(BaseApplication.getApplication());
			LogFileUtil.i(BaseApplication.TAG, "UIResizeUtils -> getAppHeight height = " + appHeight);
		}
		return appHeight;
	}
}
