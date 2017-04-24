package com.hokol.viewhelper;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.labellayout.FlowLayout;
import com.yline.common.ViewHolder;

import java.util.Arrays;

public class StarInfoHelper
{
	private Context sContext;

	public StarInfoHelper(Context context)
	{
		this.sContext = context;
	}

	private ViewHolder headViewHolder;

	private OnHeadViewClickListener onHeadViewClickListener;

	public void initHeadView(View headView)
	{
		headViewHolder = new ViewHolder(headView);

		FlowLayout flowLayout = headViewHolder.get(R.id.label_flow_star_info);
		LabelWidget labelWidget = new LabelWidget(sContext, flowLayout);
		labelWidget.setDataList(Arrays.asList("网红", "模特"));

		headViewHolder.get(R.id.tv_star_info_head_care_or_cancel).setOnClickListener(new View.OnClickListener()
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
		headViewHolder.get(R.id.tv_star_info_head_contact).setOnClickListener(new View.OnClickListener()
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
		headViewHolder.get(R.id.tv_star_info_head_gift).setOnClickListener(new View.OnClickListener()
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
		ImageView avatarView = headViewHolder.get(R.id.circle_star_info_avatar);
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
