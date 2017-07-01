package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WSettingSubmitProposalBean;
import com.hokol.medium.viewcustom.RadioGroupNestLinear;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

public class UserSettingFeedbackActivity extends BaseAppCompatActivity
{
	private static final String KeyFeedbackUserId = "FeedbackUserId";

	private ViewHolder viewHolder;

	private int typeOfAdvice;

	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting_feedback);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		// 反馈类型
		RadioGroupNestLinear radioGroupNestLinear = viewHolder.get(R.id.radio_group_user_setting_feedback);
		radioGroupNestLinear.setOnCheckedChangeListener(new RadioGroupNestLinear.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroupNestLinear group, int checkedId)
			{
				if (checkedId == R.id.radio_user_setting_type_crash)
				{
					typeOfAdvice = WSettingSubmitProposalBean.TypeCrash;
				}
				else if (checkedId == R.id.radio_user_setting_type_function)
				{
					typeOfAdvice = WSettingSubmitProposalBean.TypeFunction;
				}
				else if (checkedId == R.id.radio_user_setting_type_enter)
				{
					typeOfAdvice = WSettingSubmitProposalBean.TypeEnter;
				}
				else if (checkedId == R.id.radio_user_setting_type_improve)
				{
					typeOfAdvice = WSettingSubmitProposalBean.TypeImprove;
				}
				else if (checkedId == R.id.radio_user_setting_type_pay)
				{
					typeOfAdvice = WSettingSubmitProposalBean.TypePay;
				}
			}
		});

		// 结束应用
		viewHolder.setOnClickListener(R.id.iv_user_setting_feedback_finish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 提交
		viewHolder.get(R.id.btn_user_setting_feedback).setBackgroundResource(R.drawable.widget_shape_radiuall_huge_solid_redhokol);
		viewHolder.setOnClickListener(R.id.btn_user_setting_feedback, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String content = viewHolder.getText(R.id.et_user_setting_content);
				if (TextUtils.isEmpty(content))
				{
					SDKManager.toast("请输入您的问题或建议");
					return;
				}

				String contactWay = viewHolder.getText(R.id.et_user_setting_contact);
				XHttpUtil.doSettingSubmitProposal(new WSettingSubmitProposalBean(userId, typeOfAdvice, content, contactWay), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("提交成功");
						finish();
					}
				});
			}
		});
	}

	private void initData()
	{
		userId = getIntent().getStringExtra(KeyFeedbackUserId);
		typeOfAdvice = WSettingSubmitProposalBean.TypeImprove;
	}
	
	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserSettingFeedbackActivity.class).putExtra(KeyFeedbackUserId, userId));
	}
}
