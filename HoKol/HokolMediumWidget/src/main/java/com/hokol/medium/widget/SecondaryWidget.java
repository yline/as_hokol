package com.hokol.medium.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hokol.base.adapter.CommonListAdapter;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.base.log.LogFileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 二级列表
 *
 * @author yline 2017/3/16 --> 17:05
 * @version 1.0.0
 */
public class SecondaryWidget
{
	public static final String First_First_Data = "不限";

	public static final String Second_First_Data = "不限";

	private View parentView;

	private Map<String, List<String>> dataMap;

	private CommonListAdapter secondListAdapter;

	private String firstString, secondString;

	public View start(Context context, Map<String, List<String>> map, final OnSecondaryCallback listener)
	{
		this.dataMap = map;
		this.parentView = LayoutInflater.from(context).inflate(getResourceId(), null);

		initFirstList(context, map.keySet());
		initSecondList(context);

		Button btnSure = (Button) parentView.findViewById(getSureBtnId());
		btnSure.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				listener.onSecondarySelected(firstString, secondString);
			}
		});

		return parentView;
	}

	private void initFirstList(Context context, Set<String> firstSet)
	{
		ListView firstListView = (ListView) parentView.findViewById(getFirstListViewId());
		CommonListAdapter firstListAdapter = new FirstListAdapter(context);
		firstListView.setAdapter(firstListAdapter);

		List<String> provinceList = new ArrayList<>();
		for (String string : firstSet)
		{
			provinceList.add(string);
		}
		
		String insertData = getFirstFirstData();
		if (!TextUtils.isEmpty(insertData))
		{
			firstString = insertData;
			provinceList.add(0, insertData);
		}

		firstListAdapter.set(provinceList);
	}

	private void initSecondList(Context context)
	{
		ListView secondListView = (ListView) parentView.findViewById(getSecondListViewId());
		secondListAdapter = new SecondListAdapter(context);
		secondListView.setAdapter(secondListAdapter);
	}

	private class FirstListAdapter extends CommonListAdapter<String>
	{

		public FirstListAdapter(Context context)
		{
			super(context);
		}

		@Override
		protected int getItemRes(int position)
		{
			return android.R.layout.simple_list_item_1;
		}

		@Override
		protected void setViewContent(final int position, ViewGroup parent, ViewHolder item)
		{
			TextView textView = item.get(android.R.id.text1);
			textView.setText(sList.get(position));
			textView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					firstString = sList.get(position);
					secondString = "";

					List<String> tempSelectList = dataMap.get(firstString);

					LogFileUtil.v("first selected = " + firstString + ", list = " + tempSelectList);
					if (null != tempSelectList)
					{
						List<String> selectList = new ArrayList<>(tempSelectList);
						String secondFirstString = getSecondFirstData();
						if (!TextUtils.isEmpty(secondFirstString))
						{
							selectList.add(0, secondFirstString);
						}
						secondListAdapter.set(selectList);
					}
					else
					{
						secondListAdapter.clear();
					}
				}
			});
		}
	}

	private class SecondListAdapter extends CommonListAdapter<String>
	{

		public SecondListAdapter(Context context)
		{
			super(context);
		}

		@Override
		protected int getItemRes(int position)
		{
			return android.R.layout.simple_list_item_1;
		}

		@Override
		protected void setViewContent(final int position, ViewGroup parent, ViewHolder item)
		{
			TextView textView = item.get(android.R.id.text1);
			textView.setText(sList.get(position));
			textView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					secondString = sList.get(position);
					LogFileUtil.v("second selected = " + secondString);
				}
			});
		}
	}

	public interface OnSecondaryCallback
	{
		void onSecondarySelected(String first, String second);
	}

	/* ---------------------------------------------------- 从这里开始设置参数；这些参数都是可以被重写的 ---------------------------------------------------- */
	protected int getResourceId()
	{
		return R.layout.widget_secondary;
	}

	protected int getFirstListViewId()
	{
		return R.id.list_widget_first;
	}

	protected String getFirstFirstData()
	{
		return First_First_Data;
	}

	protected int getSecondListViewId()
	{
		return R.id.list_widget_second;
	}

	protected String getSecondFirstData()
	{
		return Second_First_Data;
	}

	protected int getSureBtnId()
	{
		return R.id.btn_widget_sure;
	}
}
