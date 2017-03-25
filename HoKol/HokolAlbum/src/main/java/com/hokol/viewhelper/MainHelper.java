package com.hokol.viewhelper;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.base.utils.UIResizeUtil;
import com.hokol.base.utils.UIScreenUtil;

/**
 * MainActivity helper; 最好全部都用装饰模式
 *
 * @author yline 2017/3/4 --> 13:56
 * @version 1.0.0
 */
public class MainHelper
{
	private static final int DURATION_FLASH = 1000;

	/**
	 * 初始化进入动画
	 *
	 * @param imageView
	 */
	public void initFlashAnimator(final ImageView imageView)
	{
		imageView.setVisibility(View.VISIBLE);
		imageView.setBackgroundResource(R.drawable.main_flash_app);
		ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 1f);
		animator.setDuration(DURATION_FLASH);
		animator.addListener(new Animator.AnimatorListener()
		{
			@Override
			public void onAnimationStart(Animator animation)
			{

			}

			@Override
			public void onAnimationEnd(Animator animation)
			{
				imageView.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationCancel(Animator animation)
			{

			}

			@Override
			public void onAnimationRepeat(Animator animation)
			{

			}
		});
		animator.start();
	}

	/**
	 * 初始化TabLayout,不考虑进入参数
	 *
	 * @param context
	 * @param tabLayout
	 * @param icons
	 * @param texts
	 */
	public void initTabLayout(Context context, TabLayout tabLayout, int[] icons, int[] texts)
	{
		for (int i = 0; i < icons.length; i++)
		{
			View view = LayoutInflater.from(context).inflate(R.layout.item_main_tab, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_item_main_tab);
			imageView.setBackgroundResource(icons[i]);

			TextView textView = (TextView) view.findViewById(R.id.tv_item_main_tab);
			textView.setText(texts[i]);

			tabLayout.addTab(tabLayout.newTab().setCustomView(view));
		}
		tabLayout.setSelectedTabIndicatorHeight(0);
	}

	/* --------------------------------- ToolBar 管理 ---------------------------------- */
	private Toolbar toolbar;

	private LinearLayout barTaskMenu;

	private TextView tvBarTitle;

	public void initToolbar(Toolbar toolbar)
	{
		this.toolbar = toolbar;
		this.barTaskMenu = (LinearLayout) toolbar.findViewById(R.id.ll_main_action_task);
		this.barTaskMenu.setVisibility(View.GONE);

		this.tvBarTitle = (TextView) toolbar.findViewById(R.id.tv_main_title);
	}

	/**
	 * @param visibility One of {@link #View.VISIBLE}, {@link #View.INVISIBLE}, or {@link #View.GONE}.
	 */
	public void setBarTaskMenuVisibility(int visibility)
	{
		this.barTaskMenu.setVisibility(visibility);
	}

	/**
	 * 设置背景颜色；同时把背景控件给出去，以便可能需要设置其他参数
	 *
	 * @param color
	 */
	public void setBarBackground(Context context, int color, int height)
	{
		toolbar.setBackgroundResource(color);
		UIResizeUtil.build().setHeight(UIScreenUtil.dp2px(context, height)).commit(toolbar);
	}

	/**
	 * 设置标题内容；同时把标题控件给出去，以便可能需要设置其它参数
	 *
	 * @param contentId
	 * @return
	 */
	public TextView setBarTextContent(int contentId)
	{
		tvBarTitle.setText(contentId);
		return tvBarTitle;
	}

	public View getBarTaskMenu()
	{
		return barTaskMenu;
	}

	/* ---------------------------------  ---------------------------------- */
}
