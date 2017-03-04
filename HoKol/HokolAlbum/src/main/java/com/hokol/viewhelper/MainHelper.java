package com.hokol.viewhelper;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hokol.R;

/**
 * MainActivity helper; 最好全部都用装饰模式
 *
 * @author yline 2017/3/4 --> 13:56
 * @version 1.0.0
 */
public class MainHelper
{
	private static final int DURATION_FLASH = 1500;

	/**
	 * 初始化Toolbar
	 *
	 * @param tempCompat
	 * @param toolbar
	 */
	public void initToolbar(AppCompatActivity tempCompat, Toolbar toolbar)
	{
		toolbar.setTitle("");
		tempCompat.setSupportActionBar(toolbar);
		tempCompat.getSupportActionBar().setDisplayShowHomeEnabled(false);// 去掉 默认标题;无效
		// tempCompat.getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 开启返回按钮
	}

	/**
	 * 初始化进入动画
	 *
	 * @param imageView
	 */
	public void initFlashAnimator(final ImageView imageView)
	{
		imageView.setVisibility(View.VISIBLE);
		imageView.setBackgroundResource(R.drawable.app_flash);
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
	public void initTabLayout(Context context, TabLayout tabLayout, int[] icons, String[] texts)
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
}
