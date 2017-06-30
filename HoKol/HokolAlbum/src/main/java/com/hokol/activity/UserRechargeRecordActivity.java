package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserRechargeRecordBean;
import com.hokol.medium.http.bean.WUserRechargeRecordBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.adapter.CommonRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Calendar;
import java.util.List;

public class UserRechargeRecordActivity extends BaseAppCompatActivity
{
	private static final String KeyRechargeRecordUserId = "UserId";

	private UserRechargeRecordAdapter rechargeRecordAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_recharge_record);

		initView();
		initData();
	}

	private void initView()
	{
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_user_recharge_record);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this));

		rechargeRecordAdapter = new UserRechargeRecordAdapter();
		recyclerView.setAdapter(rechargeRecordAdapter);

		// 返回
		findViewById(R.id.iv_user_recharge_record_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private void initData()
	{
		String userId = getIntent().getStringExtra(KeyRechargeRecordUserId);
		XHttpUtil.doUserRechargeRecord(new WUserRechargeRecordBean(userId, 0, DeleteConstant.defaultNumberLarge), new XHttpAdapter<VUserRechargeRecordBean>()
		{
			@Override
			public void onSuccess(VUserRechargeRecordBean vUserRechargeRecordBean)
			{
				List<VUserRechargeRecordBean.VUserRechargeRecordOneBean> resultList = vUserRechargeRecordBean.getList();
				if (null != resultList)
				{
					rechargeRecordAdapter.setDataList(resultList);
				}
			}
		});
	}

	private class UserRechargeRecordAdapter extends CommonRecyclerAdapter<VUserRechargeRecordBean.VUserRechargeRecordOneBean>
	{

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_recharge_record;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position)
		{
			VUserRechargeRecordBean.VUserRechargeRecordOneBean recordBean = sList.get(position);

			// 个数
			holder.setText(R.id.tv_user_recharge_record_title, String.format("充值%d个红豆", recordBean.getRecharge_num()));

			// 时间
			String timeStr = HokolTimeConvertUtil.stampToFormatDate(recordBean.getAdd_time() * 1000, Calendar.SECOND);
			holder.setText(R.id.tv_user_recharge_record_sub, timeStr);
		}
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserRechargeRecordActivity.class).putExtra(KeyRechargeRecordUserId, userId));
	}
}
