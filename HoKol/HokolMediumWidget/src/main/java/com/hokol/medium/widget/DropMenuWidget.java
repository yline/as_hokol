package com.hokol.medium.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.yline.widget.menu.drop.WidgetDropMenu;

import java.util.List;

/**
 * 实现下拉菜单 的基础工具类
 *
 * @author yline 2017/3/7 --> 15:33
 * @version 1.0.0
 */
public class DropMenuWidget extends WidgetDropMenu
{
	public DropMenuWidget(Context context, TabLayout tabLayout)
	{
		super(context, tabLayout);
	}

	@Override
	public void start(List<String> header, List<View> viewList)
	{
		super.start(header, viewList);
	}

	@Override
	public void updateTitle(int index, String title)
	{
		super.updateTitle(index, title);
	}

	@Override
	public boolean isOpened()
	{
		return super.isOpened();
	}

	@Override
	public void closeMenu()
	{
		super.closeMenu();
	}

	@Override
	public void closeMenu(int position)
	{
		super.closeMenu(position);
	}

	@Override
	public int getMaskColor()
	{
		return super.getMaskColor();
	}

	@Override
	protected int getItemResourceId()
	{
		return R.layout.widget_item_drop_menu;
	}
}



