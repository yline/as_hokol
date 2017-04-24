package com.hokol.test.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.medium.widget.transform.CircleTransform;
import com.hokol.test.R;
import com.hokol.test.common.DeleteConstant;
import com.yline.base.BaseAppCompatActivity;

public class GlideActivity extends BaseAppCompatActivity
{
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glide);

		imageView = (ImageView) findViewById(R.id.iv_glide_load);
		findViewById(R.id.btn_glide_load_circle).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Glide.with(GlideActivity.this).load(DeleteConstant.getUrlSquare()).centerCrop()
						.bitmapTransform(new CircleTransform(GlideActivity.this)).into(imageView);
			}
		});

		findViewById(R.id.btn_glide_load_clear).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				imageView.setImageBitmap(null);
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, GlideActivity.class));
	}
}
