package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.fragment.StarDynamicGiftFragment;

/**
 * 动态信息详情界面
 *
 * @author yline 2017/3/5 --> 15:12
 * @version 1.0.0
 */
public class StarDynamicActivity extends BaseAppCompatActivity
{
	private ViewHolder starDynamicViewHolder;

	private StarDynamicGiftFragment starDynamicGiftFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_dynamic);

		starDynamicViewHolder = new ViewHolder(this.getWindow().getDecorView());
		starDynamicGiftFragment = new StarDynamicGiftFragment();

		starDynamicViewHolder.get(R.id.tv_star_dynamic_care_or_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("点击关注");
			}
		});
		starDynamicViewHolder.get(R.id.tv_star_dynamic_give_gift).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("点击送礼物");
				getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fl_star_dynamic_gift, starDynamicGiftFragment).commit();
			}
		});
		starDynamicViewHolder.get(R.id.tv_star_dynamic_praise).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("点击点赞");
			}
		});

		starDynamicViewHolder.get(R.id.ll_star_dynamic_user).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				StarInfoActivity.actionStart(StarDynamicActivity.this);
			}
		});
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, StarDynamicActivity.class));
	}
}
