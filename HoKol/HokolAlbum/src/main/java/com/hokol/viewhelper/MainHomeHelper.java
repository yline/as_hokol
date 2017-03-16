package com.hokol.viewhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.medium.widget.DropMenuWidget;
import com.hokol.medium.widget.SecondaryWidget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainHomeHelper
{
	private Context context;

	public MainHomeHelper(Context context)
	{
		this.context = context;
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%% 下拉菜单 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%*/
	private String headers[] = {"城市", "筛选"};

	private DropMenuWidget dropMenuWidget;

	private List<View> contentViewList = new ArrayList<>();

	private SecondaryWidget secondaryWidget;

	public void initSecondaryView(SecondaryWidget.OnSecondaryCallback listener)
	{
		secondaryWidget = new SecondaryWidget();
		View provinceView = secondaryWidget.start(context, listener);
		contentViewList.add(provinceView);
	}

	public void initTabDownMenuView(LinearLayout linearLayout)
	{
		dropMenuWidget = new DropMenuWidget();

		View areaView = initFilterView();
		contentViewList.add(areaView);

		View dropView = dropMenuWidget.start(context, Arrays.asList(headers), contentViewList);
		linearLayout.addView(dropView);
	}

	public void setProvinceData(Map<String, List<String>> map)
	{
		secondaryWidget.setDataMap(map);
	}

	public void closeMenu()
	{
		dropMenuWidget.closeMenu();
	}

	private View initFilterView()
	{
		View filterView = LayoutInflater.from(context).inflate(R.layout.fragment_main_home__menu_filter, null);

		return filterView;
	}
}
