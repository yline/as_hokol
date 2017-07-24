package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.google.gson.JsonParseException;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.application.IApplication;
import com.hokol.fragment.StarInfoDatumFragment;
import com.hokol.fragment.StarInfoDynamicFragment;
import com.hokol.fragment.StarInfoPrivateFragment;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicUserDetailBean;
import com.hokol.medium.http.bean.WDynamicUserDetailBean;
import com.hokol.medium.http.bean.WUserCoinGiftBean;
import com.hokol.medium.widget.HokolGiftWidget;
import com.hokol.viewhelper.StarInfoHelper;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.view.recycler.holder.ViewHolder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 明星人物信息详情页面
 *
 * @author yline 2017/3/5 --> 15:24
 * @version 1.0.0
 */
public class StarInfoActivity extends BaseAppCompatActivity
{
	private static final String KeyStarId = "StarUserId";

	private StarInfoHelper starInfoHelper;

	private HokolGiftWidget hokolGiftWidget;

	private ViewHolder viewHolder;

	private StarInfoDatumFragment starInfoDatumFragment;

	private String starId;

	private float userCoinNum;

	private String userId;

	public static void actionStart(Context context, String starId)
	{
		context.startActivity(new Intent(context, StarInfoActivity.class).putExtra(KeyStarId, starId));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star_info);

		viewHolder = new ViewHolder(this);
		starInfoHelper = new StarInfoHelper(this, viewHolder);

		starId = getIntent().getStringExtra(KeyStarId);
		initView();
		initTabView();
		initData();
	}

	private void initView()
	{
		hokolGiftWidget = new HokolGiftWidget(this);
		hokolGiftWidget.setDataList(Arrays.asList(1, 10, 66, 128, 288, 520, 666, 999, 1314, 6666, 9999, 10888));
		hokolGiftWidget.setOnSendClickListener(new HokolGiftWidget.OnSendClickListener<Integer>()
		{
			@Override
			public void onSendClick(View v, Integer integer, int position)
			{
				if (integer > userCoinNum)
				{
					SDKManager.toast("您余额不足，请先充值");
				}
				else
				{
					userCoinGift(integer);
				}
			}
		});
		hokolGiftWidget.setOnRechargeClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserRechargeActivity.actionStart(StarInfoActivity.this, userId);
			}
		});

		starInfoHelper.initHeadView();
		starInfoHelper.setHeadViewListener(new StarInfoHelper.OnHeadViewClickListener()
		{
			@Override
			public void onCareOrCancel(boolean isCared)
			{
				IApplication.toast(isCared ? "已关注" : "未关注");
			}

			@Override
			public void onContact()
			{
				SDKManager.toast("点击联系");
			}

			@Override
			public void onGiveGift()
			{
				hokolGiftWidget.setPopupWindowBeanNum(userCoinNum);
				hokolGiftWidget.showAtLocation(viewHolder.get(R.id.tab_layout_start_info), Gravity.BOTTOM, 0, 0);
			}
		});

		// 信用
		viewHolder.setOnClickListener(R.id.iv_star_info_credit, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!TextUtils.isEmpty(starId))
				{
					UserInfoCreditActivity.actionStart(StarInfoActivity.this, starId);
				}
			}
		});
		// 等级
		viewHolder.setOnClickListener(R.id.iv_star_info_vip, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("等级点击");
				UserVipLevelActivity.actionStart(StarInfoActivity.this);
			}
		});
	}

	private void initTabView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		fragmentList.add(StarInfoDynamicFragment.newInstance(starId));
		titleList.add("ta的动态");

		fragmentList.add(StarInfoPrivateFragment.newInstance(starId));
		titleList.add("私密空间");

		starInfoDatumFragment = StarInfoDatumFragment.newInstance();
		fragmentList.add(starInfoDatumFragment);
		titleList.add("个人资料");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_start_info);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_start_info);
		viewPager.setOffscreenPageLimit(3);

		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public Fragment getItem(int position)
			{
				return fragmentList.get(position);
			}

			@Override
			public int getCount()
			{
				return fragmentList.size();
			}

			@Override
			public CharSequence getPageTitle(int position)
			{
				return titleList.get(position);
			}
		});
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_red_light));
		tabLayout.setTabTextColors(getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_light));
	}

	private void initData()
	{
		userCoinNum = AppStateManager.getInstance().getUserCoinNum(this);
		userId = AppStateManager.getInstance().getUserLoginId(this);

		XHttpUtil.doDynamicUserDetail(new WDynamicUserDetailBean(userId, starId), new XHttpAdapter<VDynamicUserDetailBean>()
		{
			@Override
			public void onSuccess(VDynamicUserDetailBean vDynamicUserDetailBean)
			{
				starInfoHelper.initHeadData(vDynamicUserDetailBean);

				int fans = vDynamicUserDetailBean.getUser_fans_num();
				int care = vDynamicUserDetailBean.getUser_care_num();
				int praise = vDynamicUserDetailBean.getUser_zan();
				String constell = vDynamicUserDetailBean.getUser_constell();
				ArrayList<String> province = vDynamicUserDetailBean.getProvince();
				ArrayList<String> city = vDynamicUserDetailBean.getCity();
				String sign = vDynamicUserDetailBean.getUser_sign();
				String prize = vDynamicUserDetailBean.getUser_prize();
				starInfoDatumFragment.updateStarInfo(fans, care, praise, constell, province, city, sign, prize);
			}

			@Override
			public void onSuccess(int code, String data) throws JSONException, JsonParseException
			{
				super.onSuccess(code, data);
				if (code == 2001)
				{
					LogFileUtil.v("Star info failed; user do not exist");
					SDKManager.getHandler().postDelayed(new Runnable()
					{
						@Override
						public void run()
						{
							finish();
						}
					}, 2000);
				}
			}
		});
	}

	private void userCoinGift(final int giftCoinNum)
	{
		if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(starId))
		{
			LogFileUtil.v("user = " + userId + ", star = " + starId);
			return;
		}

		XHttpUtil.doUserCoinGift(new WUserCoinGiftBean(userId, starId, giftCoinNum), new XHttpAdapter<String>()
		{
			@Override
			public void onSuccess(String s)
			{
				LogFileUtil.v("发送礼物成功");
				SDKManager.toast("礼物赠送成功");
				
				userCoinNum = userCoinNum - giftCoinNum;
				AppStateManager.getInstance().updateKeyUserCoinNum(StarInfoActivity.this, userCoinNum);
			}
		});
	}
}
