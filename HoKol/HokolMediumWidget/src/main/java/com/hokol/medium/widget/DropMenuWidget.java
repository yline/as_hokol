package com.hokol.medium.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.yline.view.pop.ViewDropMenu;

import java.util.List;

/**
 * 实现下拉菜单 的基础工具类
 *
 * @author yline 2017/3/7 --> 15:33
 * @version 1.0.0
 */
public class DropMenuWidget extends ViewDropMenu
{
	public DropMenuWidget(Context context, TabLayout tabLayout)
	{
		super(context, tabLayout);
	}

	@Override
	public void show(List<String> header, List<View> viewList)
	{
		super.show(header, viewList);
	}

	@Override
	public void updateTitle(int index, String title)
	{
		super.updateTitle(index, title);
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
	public int getXMaskColor()
	{
		return super.getXMaskColor();
	}

	@Override
	protected int getXItemId()
	{
		return super.getXItemId();
	}
}



