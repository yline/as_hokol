package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.fragment.StarDynamicGiftFragment;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicCareSingleBean;
import com.hokol.medium.http.bean.WDynamicCareSingleBean;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.common.ViewHolder;

/**
 * 动态信息详情界面
 *
 * @author yline 2017/3/5 --> 15:12
 * @version 1.0.0
 */
public class StarDynamicActivity extends BaseAppCompatActivity
{
	private static final String KeyDynamicId = "StarKeyDynamicId";

	private ViewHolder starDynamicViewHolder;

	private StarDynamicGiftFragment starDynamicGiftFragment;

	private ImageView contentImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_dynamic);

		starDynamicViewHolder = new ViewHolder(this);

		initView();
		initData();
	}

	private void initView()
	{
		starDynamicViewHolder.get(R.id.iv_star_dynamic_care).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("点击关注");
			}
		});
		starDynamicViewHolder.get(R.id.iv_star_dynamic_give_gift).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("点击送礼物");
				getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fl_star_dynamic_gift, starDynamicGiftFragment).commit();
			}
		});
		starDynamicViewHolder.get(R.id.iv_star_dynamic_praise).setOnClickListener(new View.OnClickListener()
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

		// 送礼物 Fragment
		starDynamicGiftFragment = new StarDynamicGiftFragment();

		// 背景大小
		contentImageView = starDynamicViewHolder.get(R.id.iv_star_dynamic_content);
		int width = UIScreenUtil.getScreenWidth(this);
		UIResizeUtil.build().setHeight(width).commit(contentImageView);
	}

	private void initData()
	{
		String dynamicId = getIntent().getStringExtra(KeyDynamicId);
		if (!TextUtils.isEmpty(dynamicId))
		{
			WDynamicCareSingleBean wDynamicSingleBean = new WDynamicCareSingleBean(dynamicId);
			XHttpUtil.doDynamicSingle(wDynamicSingleBean, new XHttpAdapter<VDynamicCareSingleBean>()
			{
				@Override
				public void onSuccess(VDynamicCareSingleBean vDynamicCareSingleBean)
				{
					String contentUrl = vDynamicCareSingleBean.getDt_img();
					Glide.with(StarDynamicActivity.this).load(contentUrl).placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).into(contentImageView);

					String avatarUrl = vDynamicCareSingleBean.getUser_logo();
					ImageView avatarImageView = starDynamicViewHolder.get(R.id.circle_star_dynamic);
					Glide.with(StarDynamicActivity.this).load(avatarUrl).placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).into(avatarImageView);
				}
			});
		}
		else
		{
			LogFileUtil.e("Procedure Error", "Dynamic is Empty");
		}
	}

	public static void actionStart(Context context, String dynamicId)
	{
		Intent intent = new Intent(context, StarDynamicActivity.class);
		intent.putExtra(KeyDynamicId, dynamicId);
		context.startActivity(intent);
	}
}
