package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.VWDynamicPrivateSingleBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicPrivateSingleBean;
import com.hokol.medium.http.bean.WUserCareOrCancelBean;
import com.hokol.medium.http.bean.WUserCoinGiftBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.widget.HokolGiftWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.log.LogFileUtil;
import com.yline.utils.TimeConvertUtil;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.Arrays;

public class StarDynamicPrivateActivity extends BaseAppCompatActivity
{
	private static final String KeyDynamicPrivateId = "StarKeyDynamicPrivateId";

	private ViewHolder viewHolder;

	private ImageView contentImageView;

	private HokolGiftWidget giftWidget;

	private String starId;

	private String dynamicPrivateId;

	private boolean isPraised;

	// 用户 coin数目
	private float userCoin;

	public static void actionStart(Context context, String dynamicPrivateId)
	{
		Intent intent = new Intent(context, StarDynamicPrivateActivity.class);
		intent.putExtra(KeyDynamicPrivateId, dynamicPrivateId);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_dynamic_private);

		viewHolder = new ViewHolder(this);

		initView();
		initViewClick();
		initData();
	}

	private void initView()
	{
		// 背景大小
		contentImageView = viewHolder.get(R.id.iv_star_dynamic_private_content);
		int width = UIScreenUtil.getScreenWidth(this);
		UIResizeUtil.build().setHeight(width).commit(contentImageView);

		// 初始化弹框控件
		giftWidget = new HokolGiftWidget(this);
		giftWidget.setDataList(Arrays.asList(1, 10, 66, 128, 288, 520, 666, 999, 1314, 6666, 9999, 10888));
	}

	private void initViewClick()
	{
		// 关注
		viewHolder.get(R.id.iv_star_dynamic_private_care).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(StarDynamicPrivateActivity.this);
				XHttpUtil.doUserCareOrCancel(new WUserCareOrCancelBean(userId, starId, WUserCareOrCancelBean.actionCare), new HokolAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("关注成功");
						viewHolder.get(R.id.iv_star_dynamic_private_care).setVisibility(View.GONE);
					}
				});
			}
		});
		// 点赞
		viewHolder.get(R.id.iv_star_dynamic_private_praise).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(StarDynamicPrivateActivity.this);
				int actionPraise = isPraised ? WDynamicPraiseSingleBean.actionPraiseCancel : WDynamicPraiseSingleBean.actionPraise;

				XHttpUtil.doDynamicPraiseSingle(new WDynamicPraiseSingleBean(userId, dynamicPrivateId, actionPraise), new HokolAdapter<VDynamicPraiseSingleBean>()
				{
					@Override
					public void onSuccess(VDynamicPraiseSingleBean vDynamicPraiseSingleBean)
					{
						isPraised = !isPraised;
						if (isPraised)
						{
							viewHolder.setImageResource(R.id.iv_star_dynamic_private_praise, R.drawable.star_dynamic_liked);
						}
						else
						{
							viewHolder.setImageResource(R.id.iv_star_dynamic_private_praise, R.drawable.star_dynamic_unlike);
						}
					}
				});
			}
		});
		viewHolder.get(R.id.iv_star_dynamic_private_give_gift).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!giftWidget.isShowing())
				{
					userCoin = AppStateManager.getInstance().getUserCoinNum(StarDynamicPrivateActivity.this);
					giftWidget.setPopupWindowBeanNum(userCoin); // 更新用户 红豆数目
					giftWidget.showAtLocation(viewHolder.get(R.id.iv_star_dynamic_private_content), Gravity.BOTTOM, 0, 0);
				}
			}
		});

		viewHolder.get(R.id.ll_star_dynamic_private_user).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(starId))
				{
					StarInfoActivity.actionStart(StarDynamicPrivateActivity.this, starId);
				}
			}
		});

		// 充值
		giftWidget.setOnRechargeClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(StarDynamicPrivateActivity.this);
				UserRechargeActivity.actionStart(StarDynamicPrivateActivity.this, userId);
			}
		});
		// 发送礼物
		giftWidget.setOnSendClickListener(new HokolGiftWidget.OnSendClickListener<Integer>()
		{
			@Override
			public void onSendClick(View v, Integer integer, int position)
			{
				if (integer > userCoin)
				{
					SDKManager.toast("您余额不足，请先充值");
				}
				else
				{
					userCoinGift(integer);
				}
			}
		});
	}

	private void userCoinGift(final int giftCoinNum)
	{
		String userId = AppStateManager.getInstance().getUserLoginId(this);
		if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(starId) || TextUtils.isEmpty(dynamicPrivateId))
		{
			LogFileUtil.v("user = " + userId + ", star = " + starId + ", dynamic = " + dynamicPrivateId);
			return;
		}

		XHttpUtil.doUserCoinGift(new WUserCoinGiftBean(userId, starId, dynamicPrivateId, giftCoinNum, false), new HokolAdapter<String>()
		{
			@Override
			public void onSuccess(String s)
			{
				LogFileUtil.v("发送礼物成功");
				SDKManager.toast("礼物赠送成功");

				float restCoinNum = userCoin - giftCoinNum;
				AppStateManager.getInstance().updateKeyUserCoinNum(StarDynamicPrivateActivity.this, restCoinNum);
			}
		});
	}

	private void initData()
	{
		dynamicPrivateId = getIntent().getStringExtra(KeyDynamicPrivateId);
		if (!TextUtils.isEmpty(dynamicPrivateId))
		{
			String userId = AppStateManager.getInstance().getUserLoginId(this);
			WDynamicPrivateSingleBean privateSingleBean = new WDynamicPrivateSingleBean(userId, dynamicPrivateId);


			XHttpUtil.doDynamicPrivateSingle(privateSingleBean, new HokolAdapter<VWDynamicPrivateSingleBean>()
			{
				@Override
				public void onSuccess(VWDynamicPrivateSingleBean privateSingleBean1)
				{
					// 赋值
					starId = privateSingleBean1.getPri_user_id();

					// 内容
					String contentUrl = privateSingleBean1.getPri_img();
					Glide.with(StarDynamicPrivateActivity.this).load(contentUrl).error(R.drawable.global_load_failed).into(contentImageView);

					// 头像
					String avatarUrl = privateSingleBean1.getUser_logo();
					ImageView avatarImageView = viewHolder.get(R.id.circle_star_dynamic_private);
					Glide.with(StarDynamicPrivateActivity.this).load(avatarUrl).error(R.drawable.global_load_avatar).into(avatarImageView);

					// 昵称
					viewHolder.setText(R.id.tv_star_dynamic_private_nickname, privateSingleBean1.getUser_nickname());

					// 签名
					viewHolder.setText(R.id.tv_star_dynamic_private_sign, privateSingleBean1.getPri_content());

					// 红豆数目
					viewHolder.setText(R.id.tv_star_dynamic_private_coin, privateSingleBean1.getUser_coin() + "");

					// 点赞数
					viewHolder.setText(R.id.tv_star_dynamic_private_laud, privateSingleBean1.getPri_zan_people_nickname().size() + "");

					// 时间
					long startTime = privateSingleBean1.getPri_pub_time();
					viewHolder.setText(R.id.tv_star_dynamic_private_time, TimeConvertUtil.stamp2FormatTime(startTime * 1000));

					// 是否关注
					boolean isCared = privateSingleBean1.getIs_care() == VWDynamicPrivateSingleBean.Cared ? true : false;
					if (isCared)
					{
						viewHolder.get(R.id.iv_star_dynamic_private_care).setVisibility(View.GONE);
					}
					else
					{
						viewHolder.get(R.id.iv_star_dynamic_private_care).setVisibility(View.VISIBLE);
					}
					
					// 是否点赞
					isPraised = privateSingleBean1.getIs_zan() == VWDynamicPrivateSingleBean.Praised ? true : false;
					if (isPraised)
					{
						viewHolder.setImageResource(R.id.iv_star_dynamic_private_praise, R.drawable.star_dynamic_liked);
					}
					else
					{
						viewHolder.setImageResource(R.id.iv_star_dynamic_private_praise, R.drawable.star_dynamic_unlike);
					}

				}
			});
		}
		else
		{
			LogFileUtil.e("Procedure Error", "Dynamic Private is Empty");
		}
	}
}
