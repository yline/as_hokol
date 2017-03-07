package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hokol.base.application.BaseApplication;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.medium.widget.ADWidget;
import com.hokol.test.R;

/**
 * 广告
 *
 * @author yline 2017/3/8 --> 0:17
 * @version 1.0.0
 */
public class ADActivity extends BaseAppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad);
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_widget_ad);

		// 通过重写的方式,直接设定好参数
		ADWidget adWidget = new ADWidget()
		{
			@Override
			public int getViewPagerHeight()
			{
				return 360;
			}
		};
		adWidget.start(this, 5);
		adWidget.attach(linearLayout);
		adWidget.setListener(new ADWidget.OnPageListener()
		{
			@Override
			public void onPageClick(View v, int position)
			{
				BaseApplication.toast("position = " + position);
			}
			
			@Override
			public void onPageInstance(ImageView imageView, int position)
			{
				imageView.setImageResource(R.drawable.global_load_failed);
			}
		});
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, ADActivity.class));
	}
}
