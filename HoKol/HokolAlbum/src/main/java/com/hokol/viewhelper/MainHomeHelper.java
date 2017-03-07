package com.hokol.viewhelper;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.base.adapter.CommonListAdapter;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.viewhelper.global.TabDownMenuHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainHomeHelper
{
	private static final int DOWN_MENU_BACKGROUND_COLOR = android.R.color.white;

	private String headers[] = {"城市"};

	private String provinceList[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

	private TabDownMenuHelper tabDownMenuHelper;

	private ProvinceListAdapter provinceListAdapter;

	public void initTabDownMenuView(Context context, TabLayout tabLayout)
	{
		tabDownMenuHelper = new TabDownMenuHelper();

		List<View> contentViewList = new ArrayList<>();
		View provinceView = initProvinceView(context);
		contentViewList.add(provinceView);

		tabDownMenuHelper.setDropDownMenu(context, tabLayout, Arrays.asList(headers), contentViewList);
	}

	public void setProvinceData()
	{
		provinceListAdapter.set(Arrays.asList(provinceList));
	}

	private View initProvinceView(Context context)
	{
		final ListView provinceView = new ListView(context)
		{
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
			{
				heightMeasureSpec = MeasureSpec.makeMeasureSpec(500, MeasureSpec.AT_MOST);
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			}
		};
		provinceView.setDividerHeight(0);
		provinceView.setBackgroundColor(ContextCompat.getColor(context, DOWN_MENU_BACKGROUND_COLOR));
		provinceListAdapter = new ProvinceListAdapter(context);
		provinceView.setAdapter(provinceListAdapter);
		provinceView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				provinceListAdapter.setCheckItem(position);
			}
		});

		return provinceView;
	}

	private class ProvinceListAdapter extends CommonListAdapter<String>
	{
		private int checkItemPosition = 0;

		public ProvinceListAdapter(Context context)
		{
			super(context);
		}

		public void setCheckItem(int position)
		{
			checkItemPosition = position;
			notifyDataSetChanged();
		}

		@Override
		protected int getItemRes(int i)
		{
			return android.R.layout.simple_list_item_1;
		}

		@Override
		protected void setViewContent(int position, ViewGroup viewGroup, ViewHolder viewHolder)
		{
			TextView textView = viewHolder.get(android.R.id.text1);
			textView.setText(sList.get(position));
			if (checkItemPosition != -1)
			{
				if (checkItemPosition == position)
				{
					textView.setTextColor(sContext.getResources().getColor(android.R.color.holo_red_light));
					textView.setCompoundDrawablesWithIntrinsicBounds(null, null, sContext.getResources().getDrawable(R.drawable.delete_item_menu_checked), null);
				}
				else
				{
					textView.setTextColor(sContext.getResources().getColor(android.R.color.black));
					textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
				}
			}
		}
	}
}
