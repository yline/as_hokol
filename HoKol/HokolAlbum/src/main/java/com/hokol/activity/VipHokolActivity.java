package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

public class VipHokolActivity extends BaseAppCompatActivity
{

	private ViewHolder viewHolder;

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, VipHokolActivity.class));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vip_hokol);

		viewHolder = new ViewHolder(this);
	}

	private void initView()
	{
		viewHolder.setOnClickListener(R.id.iv_vip_singlle_hokol_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
}
