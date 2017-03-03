package com.hokol.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;

public class FlashActivity extends BaseAppCompatActivity
{
	private static final int DURATION_FLASH = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flash);

		ImageView imageView = (ImageView) findViewById(R.id.iv_flash_logo);

		ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f);
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
				MainActivity.actionStart(FlashActivity.this);
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
}
