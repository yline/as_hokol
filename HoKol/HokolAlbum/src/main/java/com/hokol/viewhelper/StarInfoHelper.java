package com.hokol.viewhelper;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.widget.FlowWidget;
import com.yline.view.common.ViewHolder;
import com.yline.widget.label.FlowLayout;

import java.util.Arrays;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;

public class StarInfoHelper
{
	private Context sContext;

	private ViewHolder viewHolder;

	public StarInfoHelper(Context context, ViewHolder viewHolder)
	{
		this.sContext = context;
		this.viewHolder = viewHolder;
	}

	private OnHeadViewClickListener onHeadViewClickListener;

	public void initHeadView()
	{
		// 背景
		ImageView bgImageView = viewHolder.get(R.id.iv_star_info_bg);
		Glide.with(sContext).load(DeleteConstant.getUrlSquare()).placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed)
				.bitmapTransform(new KuwaharaFilterTransformation(sContext, 25)).bitmapTransform(new ColorFilterTransformation(sContext, 0xc0000000)).into(bgImageView); //  Color.argb(99, )

		// 标签
		FlowLayout flowLayout = viewHolder.get(R.id.label_flow_star_info);
		FlowWidget labelWidget = new FlowWidget(sContext, flowLayout);
		labelWidget.setDataList(Arrays.asList("网红", "模特"));

		// 关注、联系、送红豆
		viewHolder.get(R.id.iv_star_info_head_care_or_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onHeadViewClickListener)
				{
					onHeadViewClickListener.onCareOrCancel();
				}
			}
		});
		viewHolder.get(R.id.iv_star_info_head_contact).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onHeadViewClickListener)
				{
					onHeadViewClickListener.onContact();
				}
			}
		});
		viewHolder.get(R.id.iv_star_info_head_gift).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onHeadViewClickListener)
				{
					onHeadViewClickListener.onGiveGift();
				}
			}
		});
	}

	public void setHeadViewListener(OnHeadViewClickListener listener)
	{
		this.onHeadViewClickListener = listener;
	}

	public void initHeadData()
	{
		ImageView avatarView = viewHolder.get(R.id.circle_star_info_avatar);
		Glide.with(sContext).load(DeleteConstant.url_default_avatar).into(avatarView);
	}

	public interface OnHeadViewClickListener
	{
		/**
		 * 点击关注
		 */
		void onCareOrCancel();

		/**
		 * 点击联系
		 */
		void onContact();

		/**
		 * 点击 送红豆
		 */
		void onGiveGift();
	}
}
