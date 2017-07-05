package com.hokol.medium.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.yline.view.recycler.z.secondary.WidgetSecondary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SecondaryWidget extends WidgetSecondary
{
	public static final String DefaultFirst = "不限";

	public static final String DefaultTitle = "地区";

	private Map<String, List<String>> dataMap;

	public SecondaryWidget(Context context, List<View> viewList)
	{
		super(context, viewList);
	}

	public SecondaryWidget(Context context, ViewGroup viewGroup)
	{
		super(context, viewGroup);
	}

	public void setData(Map<String, List<String>> map)
	{
		this.dataMap = map;

		List<String> provinceData = new ArrayList<>();
		provinceData.add(0, DefaultFirst);
		for (String str : dataMap.keySet())
		{
			provinceData.add(str);
		}
		super.setData(provinceData);
	}

	public void setOnConfirmListener(final OnConfirmListener onConfirmListener)
	{
		this.setOnSecondaryCallback(new OnSecondaryCallback()
		{
			@Override
			public void onFirstSelected(String firstName, int position)
			{
				List<String> choiceList = dataMap.get(firstName);
				if (null != choiceList)
				{
					choiceList = new ArrayList<>(choiceList); // 避免引用导致的错误
					choiceList.add(0, DefaultFirst);
					updateSecondList(choiceList);
				}
				else
				{
					updateSecondList(new ArrayList<String>());
				}
			}

			@Override
			public void onSecondSelected(String secondName, int position, boolean isSelected)
			{
				// 实现第一个选项的 霸道逻辑
				if (position == 0)
				{
					if (isSelected)
					{
						// 选中了第一项，取消其它所有项
						setSecondSelectOnly(0);
					}
					else
					{
						// 已经选中了第一项，再次选中第一项，则保存第一项处于选择状态
						setSecondSelect(0, true);
					}
				}
				else
				{
					// 如果第一项，是已选状态，则第一项取消选择
					if (isSecondSelect(0))
					{
						setSecondSelect(0, false);
					}
				}
			}

			@Override
			public void onSelectedConfirm(String firstName, List<String> secondList)
			{
				if (null != onConfirmListener)
				{
					onConfirmListener.onConfirmClick(firstName, secondList, getResultTitle(firstName, secondList));
				}
			}
		});

	}

	/* ---------------------------------------------------- 从这里开始设置参数；这些参数都是可以被重写的 ---------------------------------------------------- */
	protected String getResultTitle(String first, List<String> second)
	{
		if (TextUtils.isEmpty(first) || first.equals(DefaultFirst))
		{
			return DefaultTitle;
		}

		if (null == second || second.size() == 0)
		{
			return first;
		}
		else if (second.size() == 1)
		{
			if (second.get(0).equals(DefaultFirst))
			{
				return first;
			}
			return second.get(0);
		}
		else
		{
			return String.format("%s(%d)", first, second.size());
		}
	}

	@Override
	protected int getFirstItemResource()
	{
		return R.layout.widget_item_secondary_first;
	}

	@Override
	protected int getSecondItemResource()
	{
		return R.layout.widget_item_secondary_second;
	}

	public interface OnConfirmListener
	{
		void onConfirmClick(String firstName, List<String> secondNameList, String titleName);
	}
}
