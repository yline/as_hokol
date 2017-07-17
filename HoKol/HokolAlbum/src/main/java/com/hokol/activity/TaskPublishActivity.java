package com.hokol.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.application.DeleteConstant;
import com.hokol.fragment.TaskPublishRightAreaFragment;
import com.hokol.fragment.TaskPublishRightStyleFragment;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WTaskMainPublishBean;
import com.hokol.medium.widget.DialogIosWidget;
import com.hokol.util.TextDecorateUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.KeyBoardUtil;
import com.yline.view.recycler.holder.ViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 发布任务
 *
 * @author yline 2017/4/1 -- 17:53
 * @version 1.0.0
 */
public class TaskPublishActivity extends BaseAppCompatActivity
{
	private static final String KeyUserId = "TaskPublishUserId";

	private static final String ValueAreaUnChoice = "未选择";

	private ViewHolder viewHolder;

	private FragmentManager fragmentManager = getSupportFragmentManager();

	private TaskPublishRightStyleFragment rightStyleFragment;

	private TaskPublishRightAreaFragment rightAreaFragment;

	private WTaskMainPublishBean taskPublishBean;

	private boolean isPriceDiscuss = false;

	private boolean isProtocolChecked = true;

	private String userId;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, TaskPublishActivity.class).putExtra(KeyUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_publish);

		viewHolder = new ViewHolder(this);

		initView();
		initViewClick();
		initData();
	}

	private void initView()
	{
		rightStyleFragment = TaskPublishRightStyleFragment.newInstance();
		rightStyleFragment.setOnRightStyleCallback(new TaskPublishRightStyleFragment.OnPublishRightStyleCallback()
		{
			@Override
			public void onRightStyleCancel()
			{
				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
				fragmentManager.popBackStack();
			}

			@Override
			public void onRightStyleConfirm(List<HttpEnum.UserTag> taskType, int boyNum, int girlNum)
			{
				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
				fragmentManager.popBackStack();

				// 设置数据
				List<Integer> tagTypeList = new ArrayList<>();
				for (HttpEnum.UserTag tag : taskType)
				{
					tagTypeList.add(tag.getIndex());
				}
				taskPublishBean.setTask_type(tagTypeList);
				taskPublishBean.setTask_man_num(boyNum);
				taskPublishBean.setTask_woman_num(girlNum);

				viewHolder.setText(R.id.tv_task_publish_content_num, (boyNum + girlNum) + "");
			}
		});
		rightAreaFragment = TaskPublishRightAreaFragment.newInstance();
		rightAreaFragment.setOnPublishAreaCallback(new TaskPublishRightAreaFragment.OnPublishRightAreaCallback()
		{
			@Override
			public void onRightAreaCancel()
			{
				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
				fragmentManager.popBackStack();
			}

			@Override
			public void onRightAreaConfirm(String pCode, String pName, String cCode, String cName)
			{
				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
				fragmentManager.popBackStack();

				// 设置数据
				taskPublishBean.setP_code(pCode);
				taskPublishBean.setC_code(cCode);

				if (TextUtils.isEmpty(pName))
				{
					viewHolder.setText(R.id.tv_task_publish_content_area, TaskPublishRightAreaFragment.ValueAllChoice);
				}
				else
				{
					if (TextUtils.isEmpty(cCode))
					{
						viewHolder.setText(R.id.tv_task_publish_content_area, pName);
					}
					else
					{
						viewHolder.setText(R.id.tv_task_publish_content_area, String.format("%s %s", pName, cName));
					}
				}
			}
		});

		viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);

		// 用户 协议
		initProtocolView(new TextDecorateUtil.OnTextSpannableCallback()
		{
			@Override
			public void onClick(View widget)
			{
				HokolProtocolActivity.actionStart(TaskPublishActivity.this, HokolProtocolActivity.TypeProtocol.Charge);
			}
		});
	}

	private void initViewClick()
	{
		viewHolder.setOnClickListener(R.id.iv_task_publish_back, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 属性
		viewHolder.setOnClickListener(R.id.ll_task_publish_content_style, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				boolean isEmpty = fragmentManager.beginTransaction().isEmpty();
				if (isEmpty)
				{
					fragmentManager.beginTransaction().add(R.id.rl_task_publish_right, rightStyleFragment).addToBackStack("Right").commit();
				}
				else
				{
					fragmentManager.beginTransaction().replace(R.id.rl_task_publish_right, rightStyleFragment).addToBackStack("Right").commit();
				}
				KeyBoardUtil.closeKeyboard(TaskPublishActivity.this);
				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.VISIBLE);
			}
		});

		// 报酬
		viewHolder.setOnClickListener(R.id.tv_task_publish_content_discuss, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				DialogIosWidget dialogIosWidget = new DialogIosWidget(TaskPublishActivity.this)
				{
					@Override
					protected void initXView(TextView tvTitle, TextView tvMsg, Button btnNegative, Button btnPositive, Dialog dialog)
					{
						super.initXView(tvTitle, tvMsg, btnNegative, btnPositive, dialog);

						tvTitle.setText("您选择面议将在交流成功后\n" + "提交每个应聘者价格");
					}
				};
				dialogIosWidget.setOnPositiveListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						updatePriceState(true);
					}
				});
				dialogIosWidget.show();

			}
		});
		EditText editTextPrice = viewHolder.get(R.id.et_task_publish_content_price);
		editTextPrice.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				if (isPriceDiscuss && s.length() > 0)
				{
					updatePriceState(false);
				}
			}

			@Override
			public void afterTextChanged(Editable s)
			{

			}
		});

		// 地区数据
		viewHolder.setOnClickListener(R.id.ll_task_publish_content_area, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				boolean isEmpty = fragmentManager.beginTransaction().isEmpty();
				if (isEmpty)
				{
					fragmentManager.beginTransaction().add(R.id.rl_task_publish_right, rightAreaFragment).addToBackStack("Right").commit();
				}
				else
				{
					fragmentManager.beginTransaction().replace(R.id.rl_task_publish_right, rightAreaFragment).addToBackStack("Right").commit();
				}
				KeyBoardUtil.closeKeyboard(TaskPublishActivity.this);
				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.VISIBLE);
			}
		});

		// 时间数据
		viewHolder.setOnClickListener(R.id.ll_task_publish_content_time, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new DatePickerDialog(TaskPublishActivity.this, new DatePickerDialog.OnDateSetListener()
				{
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
					{
						month += 1; // 月份需要加一，进行适配

						Calendar sInstance = Calendar.getInstance();
						sInstance.set(year, month, dayOfMonth, 12, 0, 0);
						Long endTime = sInstance.getTimeInMillis();

						if (endTime > System.currentTimeMillis())
						{
							endTime /= 1000;
							taskPublishBean.setTask_end_time(endTime);
							viewHolder.setText(R.id.tv_task_publish_content_time, String.format("%d年%d月%d日", year, month, dayOfMonth));
						}
						else
						{
							taskPublishBean.setTask_end_time(0);
							SDKManager.toast("请选择有效的时间");
						}
					}
				}, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		// 提交
		viewHolder.setOnClickListener(R.id.btn_task_publish_content_commit, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 协议是否同意
				if (!isProtocolChecked)
				{
					SDKManager.toast("未同意发布任务协议");
					return;
				}

				// 填入数据
				String taskTitle = viewHolder.getText(R.id.tv_task_publish_content_title); // 标题
				taskPublishBean.setTask_title(taskTitle);
				String taskDemand = viewHolder.getText(R.id.tv_task_publish_content_demand); // 需求
				taskPublishBean.setTask_content(taskDemand);
				String taskPrice = viewHolder.getText(R.id.et_task_publish_content_price); // 价格
				if (!TextUtils.isEmpty(taskPrice))
				{
					taskPublishBean.setTask_fee(Integer.parseInt(taskPrice));
				}

				// 数据请求
				String result = taskPublishBean.isDataEnough();
				if (viewHolder.getText(R.id.tv_task_publish_content_area) == ValueAreaUnChoice)
				{
					result = "地区数据未选择";
				}

				// 余额不足
				final int oldCoinNum = AppStateManager.getInstance().getUserCoinNum(TaskPublishActivity.this);
				final float taskTotalFee = taskPublishBean.getTask_fee() * (taskPublishBean.getTask_man_num() + taskPublishBean.getTask_woman_num()) * DeleteConstant.ScaleOfHokolCoin;
				if (taskPublishBean.getTask_fee() != WTaskMainPublishBean.ValueNegotiableInt) // 非面议
				{
					if (oldCoinNum < taskTotalFee)
					{
						SDKManager.toast("你的资金不足");
						DialogIosWidget dialogIosWidget = new DialogIosWidget(TaskPublishActivity.this)
						{
							@Override
							protected void initXView(TextView tvTitle, TextView tvMsg, Button btnNegative, Button btnPositive, Dialog dialog)
							{
								super.initXView(tvTitle, tvMsg, btnNegative, btnPositive, dialog);
								tvTitle.setText("您的余额不足");
								btnPositive.setText("立即充值");
							}
						};
						dialogIosWidget.setOnPositiveListener(new View.OnClickListener()
						{
							@Override
							public void onClick(View v)
							{
								UserRechargeActivity.actionStart(TaskPublishActivity.this, userId);
							}
						});
						dialogIosWidget.show();
						return;
					}
				}

				if (WTaskMainPublishBean.ValueSuccessStr == result)
				{
					XHttpUtil.doTaskMainPublish(taskPublishBean, new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("提交任务成功");
							// 更新本地数据
							AppStateManager.getInstance().updateKeyUserCoinNum(TaskPublishActivity.this, (int) (oldCoinNum - taskTotalFee));

							finish();
						}
					});
				}
				else
				{
					SDKManager.toast(result);
				}
			}
		});


	}

	private void initData()
	{
		viewHolder.setText(R.id.tv_task_publish_content_area, ValueAreaUnChoice);

		userId = getIntent().getStringExtra(KeyUserId);
		if (TextUtils.isEmpty(userId))
		{
			// 违规操作，进来了
			finish();
			SDKManager.toast("亲，还没登录哦");
		}
		else
		{
			taskPublishBean = new WTaskMainPublishBean(userId);
		}
	}

	private void initProtocolView(TextDecorateUtil.OnTextSpannableCallback spannableCallback)
	{
		TextView textView = viewHolder.get(R.id.tv_task_publish_content_protocol);
		int length = textView.length();

		final int clickable_length = 12;
		if (length <= clickable_length)
		{
			LogFileUtil.e("checkBox text span", "too short");
			return;
		}

		TextDecorateUtil.decorateTextSpan(textView, false, length - clickable_length, length, Color.RED, spannableCallback);

		CheckBox checkBox = viewHolder.get(R.id.checkbox_task_publish_content_protocol);
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				isProtocolChecked = isChecked;
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		if (fragmentManager.getBackStackEntryCount() == 1)
		{
			viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
		}
		else if (fragmentManager.getBackStackEntryCount() == 0)
		{
			// 退出activity
		}

		super.onBackPressed();
	}

	private void updatePriceState(boolean isDiscuss)
	{
		// 价格
		EditText editTextPrice = viewHolder.get(R.id.et_task_publish_content_price);
		if (isDiscuss)
		{
			editTextPrice.setText("");
		}

		// 面议 数据
		isPriceDiscuss = isDiscuss;
		if (isDiscuss)
		{
			taskPublishBean.setTask_fee(WTaskMainPublishBean.ValueNegotiableInt);
		}
		else
		{
			taskPublishBean.setTask_fee(WTaskMainPublishBean.ValueErrorInt);
		}

		// 面议按钮 变化
		TextView textViewDiscuss = viewHolder.get(R.id.tv_task_publish_content_discuss);
		if (isDiscuss)
		{
			textViewDiscuss.setBackgroundResource(R.drawable.widget_shape_radiusall_solid_null_stroke_redhokol);
			textViewDiscuss.setTextColor(ContextCompat.getColor(this, R.color.hokolRed));
		}
		else
		{
			textViewDiscuss.setBackgroundResource(R.drawable.widget_shape_radiusall_stroke_gray_solid_null);
			textViewDiscuss.setTextColor(ContextCompat.getColor(this, R.color.hokolGray));
		}
	}
}
