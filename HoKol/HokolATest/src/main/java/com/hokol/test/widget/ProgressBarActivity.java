package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.progressbar.CircleProgressBar;
import com.hokol.test.R;

public class ProgressBarActivity extends BaseAppCompatActivity
{
	CircleProgressBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar);

		progress = (CircleProgressBar) findViewById(R.id.progress);
		progress.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				progress.setVisibility(View.GONE);
			}
		}, 6000);
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, ProgressBarActivity.class));
	}
}
