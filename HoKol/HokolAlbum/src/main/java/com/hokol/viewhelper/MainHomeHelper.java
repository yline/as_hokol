package com.hokol.viewhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.base.adapter.CommonListAdapter;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.SecondaryWidget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainHomeHelper
{
	private Context context;

	public MainHomeHelper(Context context)
	{
		this.context = context;
	}
	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%% TabLayout + ViewPager %%%%%%%%%%%%%%%%%%%%%%%%%%%%%*/

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%*/
	private String headers[] = {"城市", "筛选"};

	private String provinceList[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

	private DropMenuWidget dropMenuWidget;

	private ProvinceListAdapter provinceListAdapter;

	private List<View> contentViewList = new ArrayList<>();

	public void initSecondaryView(SecondaryWidget.OnSecondaryCallback listener)
	{
		SecondaryWidget secondaryWidget = new SecondaryWidget();
		View provinceView = secondaryWidget.start(context, listener);
		contentViewList.add(provinceView);
	}

	public void initTabDownMenuView(LinearLayout linearLayout)
	{
		dropMenuWidget = new DropMenuWidget();

		View provinceView = initAreaView();
		contentViewList.add(provinceView);

		View areaView = initFilterView();
		contentViewList.add(areaView);

		View dropView = dropMenuWidget.start(context, Arrays.asList(headers), contentViewList);
		linearLayout.addView(dropView);
	}

	public void setProvinceData()
	{
		provinceListAdapter.set(Arrays.asList(provinceList));
	}

	private View initAreaView()
	{
		View areaView = LayoutInflater.from(context).inflate(R.layout.fragment_main_home__menu_area, null);

		final ListView provinceView = (ListView) areaView.findViewById(R.id.lv_main_home_menu_area_province);

		provinceView.setDividerHeight(0);
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

		return areaView;
	}

	private View initFilterView()
	{
		View filterView = LayoutInflater.from(context).inflate(R.layout.fragment_main_home__menu_filter, null);

		return filterView;
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
