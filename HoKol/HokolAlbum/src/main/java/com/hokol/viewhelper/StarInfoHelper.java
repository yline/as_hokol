package com.hokol.viewhelper;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.bean.VDynamicUserDetailBean;
import com.hokol.medium.widget.FlowWidget;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.recycler.holder.ViewHolder;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;

public class StarInfoHelper
{
	private Context sContext;

	private ViewHolder viewHolder;

	private FlowWidget labelWidget;

	private boolean isCared;

	private OnHeadViewClickListener onHeadViewClickListener;

	public StarInfoHelper(Context context, ViewHolder viewHolder)
	{
		this.sContext = context;
		this.viewHolder = viewHolder;
	}

	public void initHeadView()
	{
		// 标签
		FlowLayout flowLayout = viewHolder.get(R.id.label_flow_star_info);
		labelWidget = new FlowWidget(sContext, flowLayout);

		// 关注、联系、送红豆
		viewHolder.get(R.id.iv_star_info_head_care_or_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onHeadViewClickListener)
				{
					onHeadViewClickListener.onCareOrCancel(isCared);
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

	public void setCared(boolean cared)
	{
		this.isCared = cared;
	}

	public void initHeadData(VDynamicUserDetailBean vDetailBean)
	{
		// 头像
		ImageView avatarView = viewHolder.get(R.id.circle_star_info_avatar);
		Glide.with(sContext).load(vDetailBean.getUser_logo()).error(R.drawable.global_load_avatar).into(avatarView);

		// 昵称 + 性别
		TextView textView = viewHolder.get(R.id.tv_star_info_nickname);
		textView.setText(vDetailBean.getUser_nickname());
		if (HttpEnum.UserSex.Girl.getContent().equals(vDetailBean.getUser_sex()))
		{
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.global_sex_girl, 0);
		}
		else if (HttpEnum.UserSex.Boy.getContent().equals(vDetailBean.getUser_sex()))
		{
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.global_sex_boy, 0);
		}
		else
		{
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		}

		// 等级
		ImageView vipLevelImageView = viewHolder.get(R.id.iv_star_info_vip);
		Glide.with(sContext).load(vDetailBean.getLevel_url()).into(vipLevelImageView);

		// 关注人数
		viewHolder.setText(R.id.tv_star_info_cared_number, String.format("关注 %d", vDetailBean.getUser_care_num()));

		// 粉丝数
		viewHolder.setText(R.id.tv_star_info_fans_number, String.format("粉丝 %d", vDetailBean.getUser_fans_num()));

		// 关注
		isCared = vDetailBean.getIs_care() == VDynamicUserDetailBean.cared ? true : false;
		if (isCared)
		{
			viewHolder.setImageResource(R.id.iv_star_info_head_care_or_cancel, R.drawable.star_info_followed);
		}
		else
		{
			viewHolder.setImageResource(R.id.iv_star_info_head_care_or_cancel, R.drawable.star_info_unfollow);
		}

		// 签名
		viewHolder.setText(R.id.tv_star_info_head_sign, vDetailBean.getUser_sign());

		// 标签
		labelWidget.setDataList(vDetailBean.getUser_tag());

		// 背景
		ImageView bgImageView = viewHolder.get(R.id.iv_star_info_bg);
		Glide.with(sContext).load(vDetailBean.getUser_big_logo()).error(R.drawable.global_load_failed).bitmapTransform(new KuwaharaFilterTransformation(sContext, 25)).bitmapTransform(new ColorFilterTransformation(sContext, 0xc0000000)).into(bgImageView); //  Color.argb(99, )
	}

	public interface OnHeadViewClickListener
	{
		/**
		 * 点击关注
		 */
		void onCareOrCancel(boolean isCared);

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
