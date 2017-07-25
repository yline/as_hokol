package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.hokol.R;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VEnterRegisterCompleteInfoBean;
import com.hokol.medium.http.bean.WEnterRegisterCompleteInfoBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.widget.FlowAbleWidget;
import com.yline.base.BaseAppCompatActivity;
import com.yline.log.LogFileUtil;
import com.yline.view.layout.label.FlowLayout;
import com.yline.view.layout.label.LabelAdapter;
import com.yline.view.recycler.holder.ViewHolder;
import com.yline.view.text.helper.PhonePwdHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class EnterRegisterCompleteInfoActivity extends BaseAppCompatActivity
{
	private final static String KeyPhoneNumber = "PhoneNumber";

	private ViewHolder viewHolder;

	private FlowAbleWidget flowAbleWidget;

	private WEnterRegisterCompleteInfoBean completeInfoBean;

	private PhonePwdHelper phonePwdHelper;

	private boolean isPasswordVisible = true;

	public static void actionStart(Context context, String phoneNumber)
	{
		context.startActivity(new Intent(context, EnterRegisterCompleteInfoActivity.class).putExtra(KeyPhoneNumber, phoneNumber));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_register_complete_info);

		viewHolder = new ViewHolder(this);

		String telPhone = getIntent().getStringExtra(KeyPhoneNumber);
		completeInfoBean = new WEnterRegisterCompleteInfoBean(telPhone);
		completeInfoBean.setUser_sex(HttpEnum.UserSex.Girl.getIndex());
		completeInfoBean.setUser_tag(Arrays.asList(HttpEnum.UserTag.Red.getIndex()));
		initView();
	}

	private void initView()
	{
		flowAbleWidget = new FlowAbleWidget(this, R.id.label_flow_enter_register_complete_info)
		{
			@Override
			protected int getItemResourceId()
			{
				return R.layout.activity_user_info__flow_able;
			}
		};
		flowAbleWidget.setMaxCountEachLine(4); // 每行4个
		flowAbleWidget.setMaxSelectCount(2); // 最多选择两个
		flowAbleWidget.setMinSelectCount(1); // 最少选择1个
		flowAbleWidget.setLabelGravity(FlowLayout.LabelGravity.EQUIDISTANT);
		flowAbleWidget.setDataList(HttpEnum.getUserTagListTail());
		flowAbleWidget.addSelectedPosition(0);
		flowAbleWidget.setOnLabelClickListener(new LabelAdapter.OnLabelClickListener()
		{
			@Override
			public boolean onLabelClick(FlowLayout container, View view, Object o, int position)
			{
				flowAbleWidget.toggleSpecialState(position, HttpEnum.getUserTagListTail().size() - 1);
				return false;
			}
		});
		flowAbleWidget.setOnLabelSelectListener(new LabelAdapter.OnLabelSelectListener()
		{
			@Override
			public void onLabelSelected(Deque<Integer> selectedDeque)
			{
				int length = selectedDeque.size();
				viewHolder.setText(R.id.tv_enter_register_complete_info_label, String.format("我的标签(%d/2)", length));

				// 更新 选择的数据
				List<Integer> selectedData = new ArrayList<>();
				for (Integer selectedPosition : selectedDeque)
				{
					selectedData.add(selectedPosition + 1);
				}
				completeInfoBean.setUser_tag(selectedData);
			}
		});

		RadioGroup radioGroup = viewHolder.get(R.id.radio_group_enter_register_complete);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				if (checkedId == R.id.rbtn_enter_register_complete_girl)
				{
					completeInfoBean.setUser_sex(HttpEnum.UserSex.Girl.getIndex());
				}
				else
				{
					completeInfoBean.setUser_sex(HttpEnum.UserSex.Boy.getIndex());
				}
			}
		});

		EditText editTextNickName = viewHolder.get(R.id.et_enter_register_complete_info_name);
		EditText editTextPwd = viewHolder.get(R.id.et_enter_register_complete_info_new_pwd);

		phonePwdHelper = new PhonePwdHelper(editTextNickName, editTextPwd)
		{
			@Override
			protected boolean onPhoneTextChanged(String inputString, int start, int before, int count)
			{
				if (inputString.length() > 0 && inputString.length() < 17)
				{
					return true;
				}
				return false;
			}
		};
		phonePwdHelper.setOnCheckResultListener(new PhonePwdHelper.OnCheckResultListener()
		{
			@Override
			public void onChecked(boolean isMatch)
			{
				if (isMatch)
				{
					viewHolder.get(R.id.btn_enter_register_complete_info_commit).setBackgroundResource(R.drawable.widget_shape_radiusall_solid_redhokol);
				}
				else
				{
					viewHolder.get(R.id.btn_enter_register_complete_info_commit).setBackgroundResource(R.drawable.widget_shape_radiusall_solid_graysmall);
				}
			}
		});

		// 眼睛
		viewHolder.setOnClickListener(R.id.iv_enter_register_complete_info_new_pwd, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText editText = viewHolder.get(R.id.et_enter_register_complete_info_new_pwd);
				if (isPasswordVisible)
				{
					editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_register_complete_info_new_pwd, R.drawable.global_ward_close);
					isPasswordVisible = !isPasswordVisible;
				}
				else
				{
					editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					viewHolder.setImageResource(R.id.iv_enter_register_complete_info_new_pwd, R.drawable.global_ward_open);
					isPasswordVisible = !isPasswordVisible;
				}
			}
		});

		viewHolder.setOnClickListener(R.id.btn_enter_register_complete_info_commit, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				LogFileUtil.v("isResultMatch = " + phonePwdHelper.isResultMatch() + ", complete info = " + completeInfoBean.toString());

				if (phonePwdHelper.isResultMatch())
				{
					completeInfoBean.setUser_nickname(viewHolder.getText(R.id.et_enter_register_complete_info_name));
					completeInfoBean.setUser_pwd(viewHolder.getText(R.id.et_enter_register_complete_info_new_pwd));
					XHttpUtil.doEnterRegisterCompleteInfo(completeInfoBean, new HokolAdapter<VEnterRegisterCompleteInfoBean>()
					{
						@Override
						public void onSuccess(VEnterRegisterCompleteInfoBean infoBean)
						{
							MainActivity.actionStart(EnterRegisterCompleteInfoActivity.this, infoBean);
							IApplication.finishActivity();
						}
					});
				}
			}
		});
	}
}
