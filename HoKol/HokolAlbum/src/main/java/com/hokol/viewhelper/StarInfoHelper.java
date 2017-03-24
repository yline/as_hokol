package com.hokol.viewhelper;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.base.adapter.ViewHolder;
import com.hokol.medium.widget.LabelWidget;
import com.hokol.medium.widget.labellayout.LabelFlowLayout;

import java.util.Arrays;

public class StarInfoHelper
{
	private Context context;

	public StarInfoHelper(Context context)
	{
		this.context = context;
	}

	private ViewHolder headViewHolder;

	public void initHeadView(View headView)
	{
		headViewHolder = new ViewHolder(headView);

		LabelWidget labelWidget = new LabelWidget(context)
		{
			@Override
			protected LabelFlowLayout getLabelFlowLayout()
			{
				return headViewHolder.get(R.id.label_flow_star_info);
			}
		};
		labelWidget.setDataList(Arrays.asList("网红", "模特"));
	}

	public void initHeadData()
	{
		ImageView avatarView = headViewHolder.get(R.id.circle_star_info_avatar);
		Glide.with(context).load(DeleteConstant.url_default_avatar).placeholder(android.R.color.white).into(avatarView);
	}
}
