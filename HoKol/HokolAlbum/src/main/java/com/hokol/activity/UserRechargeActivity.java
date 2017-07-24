package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VAliPayOrderInfoBean;
import com.hokol.medium.http.bean.WAliPayOrderInfoBean;
import com.hokol.medium.module.AliPayActivity;
import com.hokol.medium.module.AliPayBean;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.application.SDKManager;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRechargeActivity extends AliPayActivity
{
	public static final int Max_Recharge_AliPay_Num = 200000;

	private static final String KeyRechargeUserId = "UserId";

	private ViewHolder viewHolder;

	private UserRechargeAdapter rechargeAdapter;

	private String userId;

	private List<Float> rechargeNumList = Arrays.asList(100f, 680f, 1280f, 2680f, 5180f, 9980f);

	private EditText etAccount;

	private Pattern etAccountPattern;
	
	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserRechargeActivity.class).putExtra(KeyRechargeUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_recharge);

		viewHolder = new ViewHolder(this);
		initView();
		initViewClick();
		initData();
	}

	private void initView()
	{
		RecyclerView recyclerView = viewHolder.get(R.id.recycler_user_recharge);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(this)
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_medium;
			}
		});

		rechargeAdapter = new UserRechargeAdapter();
		recyclerView.setAdapter(rechargeAdapter);

		rechargeAdapter.setDataList(rechargeNumList);

		// 限定输入内容
		etAccount = viewHolder.get(R.id.et_user_recharge_account);
		etAccountPattern = Pattern.compile("[0-9]{0," + (8 - 1) + "}+((\\.[0-9]{0," + (2 - 1) + "})?)||(\\.)?");
		etAccount.setFilters(new InputFilter[]{new InputFilter()
		{
			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
			{
				Matcher matcher = etAccountPattern.matcher(dest);
				if (!matcher.matches())
				{
					return "";
				}
				return null;
			}
		}});
		etAccount.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				if (s.length() > 0)
				{
					if (rechargeAdapter.getOldPosition() != -1)
					{
						rechargeAdapter.clearChoose();
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});
	}

	private void initViewClick()
	{
		viewHolder.setOnClickListener(R.id.iv_user_recharge_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		viewHolder.setOnClickListener(R.id.tv_user_recharge_record, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				UserRechargeRecordActivity.actionStart(UserRechargeActivity.this, userId);
			}
		});

		// 暂不支持 微信充值
		viewHolder.setOnClickListener(R.id.iv_user_recharge_wechat, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("微信充值");
			}
		});

		// 网络付款
		viewHolder.setOnClickListener(R.id.iv_user_recharge_ali_pay, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				float payCount = 0;
				if (TextUtils.isEmpty(etAccount.getText().toString()))
				{
					if (rechargeAdapter.getOldPosition() != -1)
					{
						payCount = rechargeNumList.get(rechargeAdapter.getOldPosition());
						payCount = payCount / DeleteConstant.ScaleOfHokolCoin;
					}
					else
					{
						SDKManager.toast("请选择充值金额");
					}
				}
				else
				{
					payCount = Float.parseFloat(etAccount.getText().toString());
				}

				if (payCount != 0)
				{
					XHttpUtil.doAliPayOrderInfo(new WAliPayOrderInfoBean(userId, payCount), new XHttpAdapter<VAliPayOrderInfoBean>()
					{
						@Override
						public void onSuccess(VAliPayOrderInfoBean vAliPayOrderInfoBean)
						{
							doPay(vAliPayOrderInfoBean.getMess());
						}

						@Override
						public void onFailure(Exception ex, boolean isDebug)
						{
							super.onFailure(ex, isDebug);
							SDKManager.toast("网络异常，充值失败");
						}
					});
				}
			}
		});

		rechargeAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<Integer>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, Integer integer, int position)
			{
				SDKManager.toast("coin = " + integer);
			}
		});
	}

	private void initData()
	{
		// id
		userId = getIntent().getStringExtra(KeyRechargeUserId);

		// 充值账号
		String userTel = AppStateManager.getInstance().getUserTel(this);
		viewHolder.setText(R.id.tv_user_recharge, String.format("充值账号：%s", userTel));

		// 硬币
		float userCoin = AppStateManager.getInstance().getUserCoinNum(this);
		viewHolder.setText(R.id.tv_user_coin_num, String.format("余额：%3.2f红豆", userCoin));
	}

	@Override
	public void onPayBack(final String memo, final String status, final String jsonResult)
	{
		LogFileUtil.v("memo = " + memo + ", status = " + status + ", jsonResult = " + jsonResult);
		if (status.equals("9000"))
		{
			if (!TextUtils.isEmpty(jsonResult))
			{
				AliPayBean aliPayBean = new Gson().fromJson(jsonResult, AliPayBean.class);
				float totalAmount = aliPayBean.getAlipay_trade_app_pay_response().getTotal_amount();
				
				float preCoinNum = AppStateManager.getInstance().getUserCoinNum(UserRechargeActivity.this);
				AppStateManager.getInstance().updateKeyUserCoinNum(UserRechargeActivity.this, preCoinNum + totalAmount * DeleteConstant.ScaleOfHokolCoin);
				viewHolder.setText(R.id.tv_user_coin_num, String.format("余额：%3.2f红豆", preCoinNum + totalAmount * DeleteConstant.ScaleOfHokolCoin));
			}
			SDKManager.toast("支付成功");
		}
		else if (status.equals("8000"))
		{
			SDKManager.toast("正在处理中");
		}
		else if (status.equals("4000"))
		{
			SDKManager.toast("支付失败");
		}
		else if (status.equals("6001"))
		{
			SDKManager.toast("取消支付");
		}
		else if (status.equals("6002"))
		{
			SDKManager.toast("网络连接出错");
		}
		else
		{
			SDKManager.toast("支付失败");
		}
	}

	private class UserRechargeAdapter extends WidgetRecyclerAdapter<Float>
	{
		private int oldPosition = -1;

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_recharge_value;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder holder, final int position)
		{
			super.onBindViewHolder(holder, position);

			holder.setText(R.id.tv_user_recharge_value_top, String.format("%.0f红豆", sList.get(position)));
			holder.setText(R.id.tv_user_recharge_value, String.format("￥%3.2f", sList.get(position) / DeleteConstant.ScaleOfHokolCoin));

			if (position == oldPosition)
			{
				holder.getItemView().setSelected(true);
			}
			else
			{
				holder.getItemView().setSelected(false);
			}

			holder.getItemView().setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (position != oldPosition)
					{
						if (-1 != oldPosition)
						{
							notifyItemChanged(oldPosition);
						}

						oldPosition = position;
						notifyItemChanged(oldPosition);
					}
				}
			});
		}

		public int getOldPosition()
		{
			return oldPosition;
		}

		public void clearChoose()
		{
			this.oldPosition = -1;
			notifyDataSetChanged();
		}
	}
}
