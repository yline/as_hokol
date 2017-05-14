package com.hokol.viewhelper;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.hokol.R;

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
		imageView.setImageResource(R.drawable.main_flash_app);
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
	 */
	public void initTabLayout(Context context, TabLayout tabLayout, int[] icons)
	{
		for (int i = 0; i < icons.length; i++)
		{
			View view = LayoutInflater.from(context).inflate(R.layout.item_main_tab, null);

			ImageView imageView = (ImageView) view.findViewById(R.id.iv_item_main_tab);
			imageView.setBackgroundResource(icons[i]);

			tabLayout.addTab(tabLayout.newTab().setCustomView(view));
		}
		tabLayout.setSelectedTabIndicatorHeight(0);
	}
	/* ---------------------------------  ---------------------------------- */
}
