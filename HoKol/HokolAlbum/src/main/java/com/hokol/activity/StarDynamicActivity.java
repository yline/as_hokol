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
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicCareSingleBean;
import com.hokol.medium.http.bean.VDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WDynamicCareSingleBean;
import com.hokol.medium.http.bean.WDynamicPraiseSingleBean;
import com.hokol.medium.http.bean.WUserCareOrCancelBean;
import com.hokol.medium.widget.HokolGiftWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.TimeConvertUtil;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.Arrays;

/**
 * 动态信息详情界面
 *
 * @author yline 2017/3/5 --> 15:12
 * @version 1.0.0
 */
public class StarDynamicActivity extends BaseAppCompatActivity
{
	private static final String KeyDynamicId = "StarKeyDynamicId";

	private ViewHolder viewHolder;

	private ImageView contentImageView;

	private HokolGiftWidget giftWidget;

	private String starId;

	private String dynamicId;

	private boolean isPraised;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_dynamic);

		viewHolder = new ViewHolder(this);

		initView();
		initViewClick();
		initData();
	}

	private void initView()
	{
		// 背景大小
		contentImageView = viewHolder.get(R.id.iv_star_dynamic_content);
		int width = UIScreenUtil.getScreenWidth(this);
		UIResizeUtil.build().setHeight(width).commit(contentImageView);

		giftWidget = new HokolGiftWidget(this);
		giftWidget.setDataList(Arrays.asList(1, 10, 66, 128, 288, 520, 666, 999, 1314, 6666, 9999, 10888));
	}

	private void initViewClick()
	{
		// 关注
		viewHolder.get(R.id.iv_star_dynamic_care).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(StarDynamicActivity.this);
				XHttpUtil.doUserCareOrCancel(new WUserCareOrCancelBean(userId, starId, WUserCareOrCancelBean.actionCare), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("关注成功");
						viewHolder.get(R.id.iv_star_dynamic_care).setVisibility(View.GONE);
					}

					@Override
					public void onFailureCode(int code)
					{
						super.onFailureCode(code);
						SDKManager.toast("code = " + code);
					}
				});
			}
		});
		// 点赞
		viewHolder.get(R.id.iv_star_dynamic_praise).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(StarDynamicActivity.this);
				int actionPraise = isPraised ? WDynamicPraiseSingleBean.actionPraiseCancel : WDynamicPraiseSingleBean.actionPraise;

				XHttpUtil.doDynamicPraiseSingle(new WDynamicPraiseSingleBean(userId, dynamicId, actionPraise), new XHttpAdapter<VDynamicPraiseSingleBean>()
				{
					@Override
					public void onSuccess(VDynamicPraiseSingleBean vDynamicPraiseSingleBean)
					{
						isPraised = !isPraised;
						if (isPraised)
						{
							viewHolder.setImageResource(R.id.iv_star_dynamic_praise, R.drawable.star_dynamic_liked);
						}
						else
						{
							viewHolder.setImageResource(R.id.iv_star_dynamic_praise, R.drawable.star_dynamic_unlike);
						}
					}
				});
			}
		});
		viewHolder.get(R.id.iv_star_dynamic_give_gift).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IApplication.toast("点击送礼物");

				giftWidget.showAtLocation(viewHolder.get(R.id.iv_star_dynamic_content), Gravity.BOTTOM, 0, 0);
			}
		});

		viewHolder.get(R.id.ll_star_dynamic_user).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(starId))
				{
					StarInfoActivity.actionStart(StarDynamicActivity.this, starId);
				}
			}
		});

		giftWidget.setOnRechargeClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("充值");
			}
		});
		giftWidget.setOnSendClickListener(new HokolGiftWidget.OnSendClickListener()
		{
			@Override
			public void onSendClick(View v, int position)
			{
				SDKManager.toast("发送 position = " + position);
			}
		});
	}

	private void initData()
	{
		dynamicId = getIntent().getStringExtra(KeyDynamicId);
		if (!TextUtils.isEmpty(dynamicId))
		{
			String userId = AppStateManager.getInstance().getUserLoginId(this);
			WDynamicCareSingleBean wDynamicSingleBean = new WDynamicCareSingleBean(userId, dynamicId);
			XHttpUtil.doDynamicSingle(wDynamicSingleBean, new XHttpAdapter<VDynamicCareSingleBean>()
			{
				@Override
				public void onSuccess(VDynamicCareSingleBean vDynamicCareSingleBean)
				{
					// 赋值
					starId = vDynamicCareSingleBean.getDt_user_id();

					// 内容
					String contentUrl = vDynamicCareSingleBean.getDt_img();
					Glide.with(StarDynamicActivity.this).load(contentUrl).error(R.drawable.global_load_failed).into(contentImageView);

					// 头像
					String avatarUrl = vDynamicCareSingleBean.getUser_logo();
					ImageView avatarImageView = viewHolder.get(R.id.circle_star_dynamic);
					Glide.with(StarDynamicActivity.this).load(avatarUrl).error(R.drawable.global_load_failed).into(avatarImageView);

					// 昵称
					viewHolder.setText(R.id.tv_star_dynamic_nickname, vDynamicCareSingleBean.getUser_nickname());

					// 签名
					viewHolder.setText(R.id.tv_star_dynamic_sign, vDynamicCareSingleBean.getDt_content());

					// 红豆数目
					viewHolder.setText(R.id.tv_star_dynamic_coin, vDynamicCareSingleBean.getUser_coin() + "");

					// 点赞数
					viewHolder.setText(R.id.tv_star_dynamic_laud, vDynamicCareSingleBean.getDt_zan_people_nickname().size() + "");

					// 时间
					long startTime = vDynamicCareSingleBean.getDt_pub_time();
					viewHolder.setText(R.id.tv_star_dynamic_time, TimeConvertUtil.stamp2FormatTime(startTime * 1000));

					// 是否关注
					boolean isCared = vDynamicCareSingleBean.getIs_care() == VDynamicCareSingleBean.Cared ? true : false;
					if (isCared)
					{
						viewHolder.get(R.id.iv_star_dynamic_care).setVisibility(View.GONE);
					}
					else
					{
						viewHolder.get(R.id.iv_star_dynamic_care).setVisibility(View.VISIBLE);
					}
					
					// 是否点赞
					isPraised = vDynamicCareSingleBean.getIs_zan() == VDynamicCareSingleBean.Praised ? true : false;
					if (isPraised)
					{
						viewHolder.setImageResource(R.id.iv_star_dynamic_praise, R.drawable.star_dynamic_liked);
					}
					else
					{
						viewHolder.setImageResource(R.id.iv_star_dynamic_praise, R.drawable.star_dynamic_unlike);
					}
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
