package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserVipRechargeRecordBean;
import com.hokol.medium.http.bean.WUserVipRechargeRecordBean;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class UserTradeRecordActivity extends BaseAppCompatActivity
{
	private static final String KeyTradeRecordUserId = "TradeRecordUserId";

	private TradeRecordAdapter tradeRecordAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_trade_record);

		initView();
		initData();
	}

	private void initView()
	{
		findViewById(R.id.iv_user_trade_record_finish).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_user_trade_record);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		tradeRecordAdapter = new TradeRecordAdapter();
		recyclerView.setAdapter(tradeRecordAdapter);
	}

	private void initData()
	{
		tradeRecordAdapter.setShowEmpty(false);
		String userId = getIntent().getStringExtra(KeyTradeRecordUserId);

		XHttpUtil.doUserVipRechargeRecord(new WUserVipRechargeRecordBean(userId), new XHttpAdapter<VUserVipRechargeRecordBean>()
		{
			@Override
			public void onSuccess(VUserVipRechargeRecordBean vUserVipRechargeRecordBean)
			{
				List<VUserVipRechargeRecordBean.VUserVipRechargeRecordOneBean> resultList = vUserVipRechargeRecordBean.getList();
				if (null != resultList)
				{
					tradeRecordAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class TradeRecordAdapter extends WidgetRecyclerAdapter<VUserVipRechargeRecordBean.VUserVipRechargeRecordOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_trade_record;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			VUserVipRechargeRecordBean.VUserVipRechargeRecordOneBean recordBean = sList.get(position);

			// 购买价格
			holder.setText(R.id.tv_user_trade_record_coin_num, "");

			// 标题 + 购买内容
			int contentInt = recordBean.getMember_type();
			if (contentInt == VUserVipRechargeRecordBean.VipTypeMonth)
			{
				holder.setText(R.id.tv_user_trade_record_content, "购买内容：一个月会员");
			}
			else if (contentInt == VUserVipRechargeRecordBean.VipTypeSeason)
			{
				holder.setText(R.id.tv_user_trade_record_content, "购买内容：三个月会员");
			}
			else if (contentInt == VUserVipRechargeRecordBean.VipTypeYear)
			{
				holder.setText(R.id.tv_user_trade_record_content, "购买内容：一年会员");
			}
			else if (contentInt == VUserVipRechargeRecordBean.VipTypeOnceCall)
			{
				holder.setText(R.id.tv_user_trade_record_content, "购买内容：单张交流卷");
			}
			else if (contentInt == VUserVipRechargeRecordBean.VipTypeOnceSpace)
			{
				holder.setText(R.id.tv_user_trade_record_content, "购买内容：单次查看私密空间");
			}
			else
			{
				holder.setText(R.id.tv_user_trade_record_content, "购买内容：一个月会员");
			}


			// 交易时间
			String tradeTimeStr = HokolTimeConvertUtil.stampToFormatDate(recordBean.getAdd_time() * 1000);
			holder.setText(R.id.tv_user_trade_record_trade_time, String.format("交易时间：%s", tradeTimeStr));

			// 会员有效时间段；起效时间和过期时间
			if (recordBean.getBegin_time() == 0 && recordBean.getExpire_time() == 0)
			{
				holder.setText(R.id.tv_user_trade_record_valid_time, "会员有效时间：永久");
			}
			else
			{
				String beginTimeStr = HokolTimeConvertUtil.stampToFormatDate(recordBean.getBegin_time() * 1000);
				String expireTimeStr = HokolTimeConvertUtil.stampToFormatDate(recordBean.getExpire_time() * 1000);
				holder.setText(R.id.tv_user_trade_record_valid_time, String.format("会员有效时间：%s 到 %s", beginTimeStr, expireTimeStr));
			}

		}
	}
	
	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserTradeRecordActivity.class).putExtra(KeyTradeRecordUserId, userId));
	}
}
