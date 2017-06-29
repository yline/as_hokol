package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserVipInfoBean;
import com.hokol.medium.http.bean.WUserVipInfoBean;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.Calendar;

public class UserVIPActivity extends BaseAppCompatActivity
{
	private static final String KeyVipUserId = "VipUserId";

	private ViewHolder viewHolder;

	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_vip);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		// 返回按钮
		viewHolder.setOnClickListener(R.id.iv_user_setting_account_bind_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 交易记录
		viewHolder.setOnClickListener(R.id.rl_user_vip_trade_record, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(userId))
				{
					UserTradeRecordActivity.actionStart(UserVIPActivity.this, userId);
				}
			}
		});

		// 交流卷
		viewHolder.setOnClickListener(R.id.rl_user_vip_contact_volume, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(userId))
				{
					UserContactVolumeRecordActivity.actionStart(UserVIPActivity.this, userId);
				}
			}
		});

		// 立即续费
		viewHolder.setOnClickListener(R.id.iv_user_setting_account_bind_recharge_top, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("立即续费");
			}
		});

		// 立即续费
		viewHolder.setOnClickListener(R.id.btn_user_setting_account_bind_recharge, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("立即续费");
			}
		});
	}

	private void initData()
	{
		// 头像
		String userAvatarUrl = AppStateManager.getInstance().getUserLoginAvatar(this);
		ImageView avatarImageView = viewHolder.get(R.id.iv_user_setting_account_bind_avatar);
		Glide.with(this).load(userAvatarUrl).error(R.drawable.global_load_failed).into(avatarImageView);

		// 昵称
		String userNickname = AppStateManager.getInstance().getUserLoginNickName(this);
		viewHolder.setText(R.id.tv_user_setting_account_bind_nickname, userNickname);

		userId = getIntent().getStringExtra(KeyVipUserId);
		XHttpUtil.doUserVipInfo(new WUserVipInfoBean(userId), new XHttpAdapter<VUserVipInfoBean>()
		{
			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
				if (code == VUserVipInfoBean.CodeVipNone)
				{
					updateVipInfoView(VUserVipInfoBean.TypeNull, "您还不是会员", 0);
				}
				else if (code == VUserVipInfoBean.CodeVipPass)
				{
					updateVipInfoView(VUserVipInfoBean.TypeNull, "会员已到期", 0);
				}
			}

			@Override
			public void onSuccess(VUserVipInfoBean vUserVipInfoBean)
			{
				String expireTimeStr = HokolTimeConvertUtil.stampToFormatDate(vUserVipInfoBean.getExpire_time() * 1000, Calendar.DAY_OF_MONTH);
				updateVipInfoView(vUserVipInfoBean.getMember_type(), String.format("VIP %s 到期", expireTimeStr), vUserVipInfoBean.getCall_card_num());
			}
		});
	}

	/**
	 * @param vipType       vip类型
	 * @param expireTimeStr 会员时间显示
	 * @param cardNum       交流卷数量
	 */
	private void updateVipInfoView(int vipType, String expireTimeStr, int cardNum)
	{
		// 会员类型
		if (vipType == VUserVipInfoBean.TypeMonth)
		{
			viewHolder.setImageResource(R.id.iv_user_setting_account_bind_vip, R.drawable.user_vip_month).setVisibility(View.VISIBLE);
		}
		else if (vipType == VUserVipInfoBean.TypeSeason)
		{
			viewHolder.setImageResource(R.id.iv_user_setting_account_bind_vip, R.drawable.user_vip_quarter).setVisibility(View.VISIBLE);
		}
		else if (vipType == VUserVipInfoBean.TypeYear)
		{
			viewHolder.setImageResource(R.id.iv_user_setting_account_bind_vip, R.drawable.user_vip_year).setVisibility(View.VISIBLE);
		}
		else
		{
			viewHolder.get(R.id.iv_user_setting_account_bind_vip).setVisibility(View.GONE);
		}

		// 会员时间显示
		viewHolder.setText(R.id.tv_user_setting_account_bind_time, expireTimeStr);

		// 交流卷张数
		viewHolder.setText(R.id.tv_user_vip_contact_volume_num, String.format("%d张", cardNum));
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserVIPActivity.class).putExtra(KeyVipUserId, userId));
	}
}
