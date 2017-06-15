package com.hokol.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import com.hokol.R;
import com.hokol.fragment.TaskPublishRightAreaFragment;
import com.hokol.fragment.TaskPublishRightStyleFragment;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WTaskMainPublishBean;
import com.hokol.medium.widget.DialogIosWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.utils.KeyBoardUtil;
import com.yline.view.recycler.holder.ViewHolder;

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

	private ViewHolder viewHolder;

	private FragmentManager fragmentManager = getSupportFragmentManager();

	private TaskPublishRightStyleFragment rightStyleFragment;

	private TaskPublishRightAreaFragment rightAreaFragment;

	private WTaskMainPublishBean taskPublishBean;

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
			public void onRightStyleConfirm(List<Integer> taskType, int boyNum, int girlNum)
			{
				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
				fragmentManager.popBackStack();

				// 设置数据
				taskPublishBean.setTask_type(taskType);
				taskPublishBean.setTask_man_num(boyNum);
				taskPublishBean.setTask_woman_num(girlNum);

				viewHolder.setText(R.id.tv_task_publish_content_num, (boyNum + girlNum) + "");
			}
		});
		rightAreaFragment = TaskPublishRightAreaFragment.newInstance();


		viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
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
						Calendar sInstance = Calendar.getInstance();
						sInstance.set(year, month, dayOfMonth, 12, 0, 0);
						Long endTime = sInstance.getTimeInMillis();

						taskPublishBean.setTask_end_time(endTime);
					}
				}, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		viewHolder.setOnClickListener(R.id.btn_task_publish_content_commit, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 填入数据
				String taskTitle = viewHolder.getText(R.id.tv_task_publish_content_title); // 标题
				taskPublishBean.setTask_title(taskTitle);
				String taskDemand = viewHolder.getText(R.id.tv_task_publish_content_demand); // 需求
				taskPublishBean.setTask_content(taskDemand);

				// 数据请求
				String result = taskPublishBean.isDataEnough();
				if (WTaskMainPublishBean.ValueSuccessStr == result)
				{
					XHttpUtil.doTaskMainPublish(taskPublishBean, new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{

						}
					});
					SDKManager.toast("提交任务成功");
				}
				else
				{
					SDKManager.toast(result);
				}
			}
		});

		viewHolder.setOnClickListener(R.id.tv_task_publish_content_discuss, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new DialogIosWidget(TaskPublishActivity.this)
				{
					@Override
					protected void initBuilder(Builder builder)
					{
						super.initBuilder(builder);
						builder.setTitle("您选择面议将在交流成功后\n提交每个应聘者价格");
						builder.setPositiveText("确定");
					}
				}.show();
			}
		});
	}

	private void initData()
	{
		String userId = getIntent().getStringExtra(KeyUserId);
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

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, TaskPublishActivity.class).putExtra(KeyUserId, userId));
	}
}
