package com.hokol.viewhelper;

import android.content.Context;
import android.view.View;

import com.hokol.view.menu.DropDownMenu;
import com.hokol.view.menu.DropDownMenuHomeHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainHomeHelper
{
	private String headers[] = {"城市"};

	private String provinceList[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州",
			"武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

	private DropDownMenuHomeHelper downMenuHomeHelper;

	/**
	 * 装饰模式,对DropDownMenu进行装饰
	 *
	 * @param context
	 * @param dropDownMenu
	 */
	public void initMenuView(Context context, final DropDownMenu dropDownMenu, View contentView)
	{
		downMenuHomeHelper = new DropDownMenuHomeHelper();

		// 初始化控件
		View provinceView = downMenuHomeHelper.initProvinceView(context);

		List<View> popupViews = new ArrayList<>();
		popupViews.add(provinceView);

		downMenuHomeHelper.setOnDropMenuClickListener(new DropDownMenuHomeHelper.OnDropMenuClickListener()
		{

			@Override
			public void onProvinceClick(int position)
			{
				dropDownMenu.setTabText(position == 0 ? headers[0] : provinceList[position]);
				dropDownMenu.closeMenu();
			}
		});

		dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
	}

	public void initMenuData()
	{
		downMenuHomeHelper.setProvinceListData(Arrays.asList(provinceList));
	}
}
