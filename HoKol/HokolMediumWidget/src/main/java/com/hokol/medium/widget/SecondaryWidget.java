package com.hokol.medium.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yline.common.CommonListAdapter;
import com.yline.common.ViewHolder;
import com.yline.log.LogFileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 二级列表
 * 数据后驱动型
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

	private CommonListAdapter firstListAdapter, secondListAdapter;

	private Button btnSure;

	private String firstString, secondString;

	public View start(Context context, final OnSecondaryCallback listener)
	{
		this.parentView = LayoutInflater.from(context).inflate(getResourceId(), null);

		initListView(context);

		btnSure = (Button) parentView.findViewById(getSureBtnId());
		btnSure.setVisibility(View.GONE);
		btnSure.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != listener)
				{
					listener.onSecondarySelected(firstString, secondString);
				}
			}
		});

		return parentView;
	}

	public void setDataMap(Map<String, List<String>> map)
	{
		this.dataMap = map;
		initListData(dataMap.keySet());
		btnSure.setVisibility(View.VISIBLE);
	}

	private void initListView(Context context)
	{
		ListView firstListView = (ListView) parentView.findViewById(getFirstListViewId());
		firstListAdapter = new FirstListAdapter(context);
		firstListView.setAdapter(firstListAdapter);

		ListView secondListView = (ListView) parentView.findViewById(getSecondListViewId());
		secondListAdapter = new SecondListAdapter(context);
		secondListView.setAdapter(secondListAdapter);
	}

	private void initListData(Set<String> firstSet)
	{
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

		firstListAdapter.setDataList(provinceList);
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
		protected void onBindViewHolder(ViewGroup parent, ViewHolder viewHolder, final int position)
		{
			TextView textView = viewHolder.get(android.R.id.text1);
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
						secondListAdapter.setDataList(selectList);
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
		protected void onBindViewHolder(ViewGroup parent, ViewHolder viewHolder, final int position)
		{
			TextView textView = viewHolder.get(android.R.id.text1);
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
