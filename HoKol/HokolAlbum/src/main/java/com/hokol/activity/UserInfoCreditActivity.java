package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserCreditBean;
import com.hokol.medium.http.bean.WUserCreditBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.yline.base.BaseAppCompatActivity;
import com.yline.view.recycler.holder.ViewHolder;

/**
 * 用户信用 界面
 *
 * @author yline 2017/6/3 -- 10:21
 * @version 1.0.0
 */
public class UserInfoCreditActivity extends BaseAppCompatActivity
{
	private static final String KeyUserInfoCreditStarId = "UserInfoCreditStarId";

	private ViewHolder viewHolder;

	private String starId;

	public static void actionStart(Context context, String starId)
	{
		context.startActivity(new Intent(context, UserInfoCreditActivity.class).putExtra(KeyUserInfoCreditStarId, starId));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_credit);

		viewHolder = new ViewHolder(this);

		initView();
		initData();
	}

	private void initView()
	{
		viewHolder.setOnClickListener(R.id.iv_task_assigned_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 是否是自己的信用 界面
		final String userId = AppStateManager.getInstance().getUserLoginId(this);
		starId = getIntent().getStringExtra(KeyUserInfoCreditStarId);
		if (starId.equals(userId))
		{
			// 完善资料
			viewHolder.get(R.id.tv_user_info_credit_datum_improve).setVisibility(View.VISIBLE);
			viewHolder.setOnClickListener(R.id.tv_user_info_credit_datum_improve, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					UserInfoActivity.actionStart(UserInfoCreditActivity.this, userId);
				}
			});
		}
		else
		{
			// 完善资料
			viewHolder.get(R.id.tv_user_info_credit_datum_improve).setVisibility(View.INVISIBLE);
		}
	}
	
	private void initData()
	{
		XHttpUtil.doUserCredit(new WUserCreditBean(starId), new HokolAdapter<VUserCreditBean>()
		{
			@Override
			public void onSuccess(VUserCreditBean vUserCreditBean)
			{
				// 完整度
				viewHolder.setProgress(R.id.progress_user_info_credit_title, (int) (vUserCreditBean.getScore1()));
				viewHolder.setText(R.id.tv_user_info_credit_title, String.format("%d %%", (int) vUserCreditBean.getScore1()));

				// sub
				VUserCreditBean.VUserCreditSubBean subBean = vUserCreditBean.getScore3();
				viewHolder.setProgress(R.id.progress_user_info_credit_sub_outlook, (int) (10 * subBean.getConformity_score()));
				viewHolder.setText(R.id.tv_user_info_credit_sub_outlook, String.format("%3.1f", subBean.getConformity_score()));

				viewHolder.setProgress(R.id.progress_user_info_credit_sub_action, (int) (10 * subBean.getAction_capacity_score()));
				viewHolder.setText(R.id.tv_user_info_credit_sub_action, String.format("%3.1f", subBean.getAction_capacity_score()));

				viewHolder.setProgress(R.id.progress_user_info_credit_sub_server, (int) (10 * subBean.getAttitude_score()));
				viewHolder.setText(R.id.tv_user_info_credit_sub_server, String.format("%3.1f", subBean.getAttitude_score()));

				// host
				VUserCreditBean.VUserCreditHostBean hostBean = vUserCreditBean.getScore2();
				viewHolder.setProgress(R.id.progress_user_info_credit_host_action, (int) (10 * hostBean.getConformity_score()));
				viewHolder.setText(R.id.tv_user_info_credit_host_action, String.format("%3.1f", hostBean.getConformity_score()));

				viewHolder.setProgress(R.id.progress_user_info_credit_host_communication, (int) (10 * hostBean.getCommunion_score()));
				viewHolder.setText(R.id.tv_user_info_credit_host_communication, String.format("%3.1f", hostBean.getCommunion_score()));

				viewHolder.setProgress(R.id.progress_user_info_credit_host_sincerity, (int) (10 * hostBean.getCredibility_score()));
				viewHolder.setText(R.id.tv_user_info_credit_host_sincerity, String.format("%3.1f", hostBean.getCredibility_score()));
			}
		});
	}
}
