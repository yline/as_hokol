package com.hokol.medium.widget.secondary;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hokol.medium.widget.R;
import com.yline.view.common.RecyclerViewHolder;

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
	public static final String DefaultFirst = "不限";

	public static final String DefaultTitle = "地区";

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

			@Override
			public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
			{
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

			@Override
			public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
			{
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
					List<String> secondResultList = secondListAdapter.getSelectedList();
					onSecondaryCallback.onSecondarySelected(firstListAdapter.getSelectedString(), secondResultList,
							getResultTitle(firstListAdapter.getSelectedString(), secondResultList));
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
		/**
		 * 选择之后，的回调
		 *
		 * @param first  第一列数据
		 * @param second 第二列数据
		 * @param title  应该显示的标题
		 */
		void onSecondarySelected(String first, List<String> second, String title);
	}

	/* ---------------------------------------------------- 从这里开始设置参数；这些参数都是可以被重写的 ---------------------------------------------------- */
	protected int getWidgetResourceId()
	{
		return R.layout.widget_secondary;
	}

	protected String getFirstHeadItemContent()
	{
		return DefaultFirst;
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
		return ContextCompat.getColor(sContext, android.R.color.white);
	}

	@ColorInt
	protected int getFirstColorTextSelected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolRed);
	}

	@ColorInt
	protected int getFirstColorTextUnselected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolGrayDrak);
	}

	protected String getSecondHeadItemContent()
	{
		return DefaultFirst;
	}

	@LayoutRes
	protected int getSecondItemResource()
	{
		return R.layout.widget_secondary_item_second;
	}

	@ColorInt
	protected int getSecondColorBgSelected()
	{
		return ContextCompat.getColor(sContext, android.R.color.transparent);
	}

	@ColorInt
	protected int getSecondColorBgUnselected()
	{
		return ContextCompat.getColor(sContext, android.R.color.transparent);
	}

	@ColorInt
	protected int getSecondColorTextSelected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolRed);
	}

	@ColorInt
	protected int getSecondColorTextUnselected()
	{
		return ContextCompat.getColor(sContext, R.color.hokolGrayDrak);
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

	protected String getResultTitle(String first, List<String> second)
	{
		if (TextUtils.isEmpty(first) || first.equals(getSecondHeadItemContent()))
		{
			return DefaultTitle;
		}

		if (null == second || second.size() == 0)
		{
			return first;
		}
		else if (second.size() == 1)
		{
			if (second.get(0).equals(getSecondHeadItemContent()))
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
}
