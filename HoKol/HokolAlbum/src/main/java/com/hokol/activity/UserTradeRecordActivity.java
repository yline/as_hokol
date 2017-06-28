package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserVipRechargeRecordBean;
import com.hokol.medium.http.bean.WUserVipRechargeRecordBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
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
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this)
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}

			@Override
			protected int getHeadNumber()
			{
				return 1;
			}
			
			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}
		});

		tradeRecordAdapter = new TradeRecordAdapter();
		recyclerView.setAdapter(tradeRecordAdapter);

		View headView = new View(this);
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(this, 8)));
		headView.setBackgroundColor(ContextCompat.getColor(this, R.color.hokolGrayLight));
		tradeRecordAdapter.addHeadView(headView);
	}

	private void initData()
	{
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

	private class TradeRecordAdapter extends HeadFootRecyclerAdapter<VUserVipRechargeRecordBean.VUserVipRechargeRecordOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_trade_record;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{

		}
	}
	
	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserTradeRecordActivity.class));
	}
}
