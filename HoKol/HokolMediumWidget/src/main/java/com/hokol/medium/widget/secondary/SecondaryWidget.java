package com.hokol.medium.widget.secondary;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hokol.medium.widget.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 二级列表
 * 数据后驱动型
 *
 * @author yline 2017/3/16 --> 17:05
 * @version 1.0.0
 */
public class SecondaryWidget
{
	private Context sContext;

	private Map<String, List<String>> dataMap;

	private FirstRecyclerAdapter firstListAdapter;

	private SecondRecyclerAdapter secondListAdapter;

	private OnSecondaryCallback onSecondaryCallback;

	public SecondaryWidget(Context context, List<View> viewList)
	{
		this.sContext = context;

		View view = initView();
		viewList.add(view);
	}

	public SecondaryWidget(Context context, ViewGroup viewGroup)
	{
		this.sContext = context;

		View view = initView();
		viewGroup.addView(view);
	}

	private View initView()
	{
		View parentView = LayoutInflater.from(sContext).inflate(getWidgetResourceId(), null);

		// 2
		RecyclerView secondRecyclerView = (RecyclerView) parentView.findViewById(R.id.recycler_widget_second);
		secondRecyclerView.setLayoutManager(new LinearLayoutManager(sContext));
		secondListAdapter = new SecondRecyclerAdapter(getSecondHeadItemContent())
		{
			@Override
			protected int getItemResource()
			{
				return getSecondItemResource();
			}

			@Override
			protected int getColorBgSelected()
			{
				return getSecondColorBgSelected();
			}

			@Override
			protected int getColorBgUnselected()
			{
				return getSecondColorBgUnselected();
			}

			@Override
			protected int getColorTextSelected()
			{
				return getSecondColorTextSelected();
			}

			@Override
			protected int getColorTextUnselected()
			{
				return getSecondColorTextUnselected();
			}

			@DrawableRes
			protected int getItemDrawableSelected()
			{
				return getSecondDrawableSelected();
			}

			@DrawableRes
			protected int getItemDrawableUnselected()
			{
				return getSecondDrawableUnselected();
			}
		};
		secondRecyclerView.setAdapter(secondListAdapter);

		// 1
		RecyclerView firstRecyclerView = (RecyclerView) parentView.findViewById(R.id.recycler_widget_first);
		firstRecyclerView.setLayoutManager(new LinearLayoutManager(sContext));
		firstListAdapter = new FirstRecyclerAdapter(secondListAdapter)
		{
			@Override
			protected int getItemResource()
			{
				return getFirstItemResource();
			}

			@Override
			protected int getColorBgSelected()
			{
				return getFirstColorBgSelected();
			}

			@Override
			protected int getColorBgUnselected()
			{
				return getFirstColorBgUnselected();
			}

			@Override
			protected int getColorTextSelected()
			{
				return getFirstColorTextSelected();
			}

			@Override
			protected int getColorTextUnselected()
			{
				return getFirstColorTextUnselected();
			}
		};
		firstRecyclerView.setAdapter(firstListAdapter);

		firstListAdapter.setOnFirstItemClickListener(new FirstRecyclerAdapter.OnFirstItemClickListener()
		{
			@Override
			public List<String> onFirstItemClick(String content, int position)
			{
				List<String> secondTotalList = dataMap.get(content);
				if (null != secondTotalList)
				{
					List<String> result = new ArrayList<>(secondTotalList);

					if (!TextUtils.isEmpty(getSecondHeadItemContent()))
					{
						result.add(0, getSecondHeadItemContent());
					}
					return result;
				}
				return null;
			}
		});

		// btn
		Button btnSure = (Button) parentView.findViewById(R.id.btn_widget_sure);
		btnSure.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onSecondaryCallback)
				{
					onSecondaryCallback.onSecondarySelected(firstListAdapter.getSelectedString(), secondListAdapter.getSelectedList());
				}
			}
		});

		return parentView;
	}

	public void setOnSecondaryCallback(OnSecondaryCallback onSecondaryCallback)
	{
		this.onSecondaryCallback = onSecondaryCallback;
	}

	public void setDataMap(Map<String, List<String>> dataMap)
	{
		setDataMap(dataMap, null, null);
	}

	/**
	 * @param map                数据集
	 * @param firstSelectedValue 第一列表，默认的初始值
	 * @param secondSelectedList 第二列表，默认的初始值
	 */
	public void setDataMap(Map<String, List<String>> map, String firstSelectedValue, List<String> secondSelectedList)
	{
		this.dataMap = map;

		List<String> firstTotalList = new ArrayList<>();
		for (String string : map.keySet())
		{
			firstTotalList.add(string);
		}

		if (!TextUtils.isEmpty(getFirstHeadItemContent()))
		{
			firstTotalList.add(0, getFirstHeadItemContent());
		}
		firstListAdapter.setDataList(firstTotalList);

		if (!TextUtils.isEmpty(firstSelectedValue))
		{
			List<String> secondTotalList = new ArrayList<>(map.get(firstSelectedValue));
			if (null != secondTotalList)
			{
				if (!TextUtils.isEmpty(getSecondHeadItemContent()))
				{
					secondTotalList.add(0, getSecondHeadItemContent());
				}
				firstListAdapter.initSelect(firstSelectedValue, secondTotalList, secondSelectedList);
			}
			else
			{
				firstListAdapter.initSelect(firstSelectedValue, null, null);
			}
		}
		else
		{
			firstListAdapter.initSelect(firstSelectedValue, null, null);
		}
	}

	public interface OnSecondaryCallback
	{
		void onSecondarySelected(String first, List<String> second);
	}

	/* ---------------------------------------------------- 从这里开始设置参数；这些参数都是可以被重写的 ---------------------------------------------------- */
	protected int getWidgetResourceId()
	{
		return R.layout.widget_secondary;
	}

	protected String getFirstHeadItemContent()
	{
		return "不限";
	}

	@LayoutRes
	protected int getFirstItemResource()
	{
		return R.layout.widget_secondary_item_first;
	}

	@ColorInt
	protected int getFirstColorBgSelected()
	{
		return 0xfff2f2f2;
	}

	@ColorInt
	protected int getFirstColorBgUnselected()
	{
		return 0xffffffff;
	}

	@ColorInt
	protected int getFirstColorTextSelected()
	{
		return 0xffff2742;
	}

	@ColorInt
	protected int getFirstColorTextUnselected()
	{
		return 0xff666666;
	}

	protected String getSecondHeadItemContent()
	{
		return "不限";
	}

	@LayoutRes
	protected int getSecondItemResource()
	{
		return R.layout.widget_secondary_item_second;
	}

	@ColorInt
	protected int getSecondColorBgSelected()
	{
		return 0x00000000;
	}

	@ColorInt
	protected int getSecondColorBgUnselected()
	{
		return 0x00000000;
	}

	@ColorInt
	protected int getSecondColorTextSelected()
	{
		return 0xffff2742;
	}

	@ColorInt
	protected int getSecondColorTextUnselected()
	{
		return 0xff666666;
	}

	@DrawableRes
	protected int getSecondDrawableSelected()
	{
		return R.drawable.widget_item_secondary_second_selected;
	}

	@DrawableRes
	protected int getSecondDrawableUnselected()
	{
		return R.drawable.widget_item_secondary_second_unselected;
	}
}
