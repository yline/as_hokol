package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskMainDetailBean;
import com.hokol.medium.http.bean.WTaskActionStaffSignUpBean;
import com.hokol.medium.http.bean.WTaskMainCollectionBean;
import com.hokol.medium.http.bean.WTaskMainDetailBean;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.medium.widget.SecondaryWidget;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.view.recycler.holder.ViewHolder;

/**
 * 任务详情
 *
 * @author yline 2017/4/1 -- 17:54
 * @version 1.0.0
 */
public class TaskDetailActivity extends BaseAppCompatActivity
{
	private static final String KeyTaskId = "TaskUserId";

	private static final String KeyIsMaster = "TaskIsMaster";

	private ViewHolder viewHolder;

	private String taskId;

	private boolean isMaster;

	private boolean isCollected;

	private VTaskMainDetailBean taskDetailBean;

	public static void actionStart(Context context, String taskId, boolean isMaster)
	{
		context.startActivity(new Intent(context, TaskDetailActivity.class).putExtra(KeyTaskId, taskId).putExtra(KeyIsMaster, isMaster));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_detail);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		taskId = getIntent().getStringExtra(KeyTaskId);
		isMaster = getIntent().getBooleanExtra(KeyIsMaster, false);
		if (isMaster)
		{
			viewHolder.get(R.id.iv_task_detail_collect).setVisibility(View.GONE);
			viewHolder.get(R.id.btn_task_detail_contact).setVisibility(View.GONE);
		}

		viewHolder.setOnClickListener(R.id.iv_task_detail_back, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		viewHolder.setOnClickListener(R.id.iv_task_detail_collect, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(TaskDetailActivity.this);
				int actionCollect = isCollected ? WTaskMainCollectionBean.actionCollectCancel : WTaskMainCollectionBean.actionCollect;
				XHttpUtil.doTaskMainCollection(new WTaskMainCollectionBean(userId, taskId, actionCollect), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						isCollected = !isCollected;
						if (isCollected)
						{
							viewHolder.setImageResource(R.id.iv_task_detail_collect, R.drawable.global_collected);
						}
						else
						{
							viewHolder.setImageResource(R.id.iv_task_detail_collect, R.drawable.global_uncollect);
						}
					}
				});
			}
		});

		viewHolder.setOnClickListener(R.id.btn_task_detail_contact, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(TaskDetailActivity.this);
				if (TextUtils.isEmpty(userId))
				{
					LogFileUtil.v("userId = " + userId + ", taskId = " + taskId);
					return;
				}

				if (null != taskDetailBean && VTaskMainDetailBean.StatusAssigning == taskDetailBean.getStatus() && VTaskMainDetailBean.UserAssignNull == taskDetailBean.getUser_is_join())
				{
					XHttpUtil.doTaskActionStaffSignUp(new WTaskActionStaffSignUpBean(userId, taskId), new XHttpAdapter<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							SDKManager.toast("报名成功");
							finish();
						}
					});
				}
			}
		});
	}
	
	private void initData()
	{
		if (TextUtils.isEmpty(taskId))
		{
			SDKManager.toast("点击的任务出错啦");
			finish();
		}
		else
		{
			String userId = AppStateManager.getInstance().getUserLoginId(this);
			if (TextUtils.isEmpty(userId))
			{
				userId = WTaskMainDetailBean.UnLoginState;
			}

			XHttpUtil.doTaskMainDetail(new WTaskMainDetailBean(userId, taskId), new XHttpAdapter<VTaskMainDetailBean>()
			{
				@Override
				public void onSuccess(VTaskMainDetailBean vTaskMainDetailBean)
				{
					if (!isMaster)
					{
						// 是否收藏
						isCollected = vTaskMainDetailBean.getIs_collect() == VTaskMainDetailBean.Collected ? true : false;
						if (isCollected)
						{
							viewHolder.setImageResource(R.id.iv_task_detail_collect, R.drawable.global_collected);
						}
						else
						{
							viewHolder.setImageResource(R.id.iv_task_detail_collect, R.drawable.global_uncollect);
						}

						// 是否报名 、 任务状态
						if (VTaskMainDetailBean.UserAssignNull == vTaskMainDetailBean.getUser_is_join())
						{
							if (VTaskMainDetailBean.StatusAssigning == vTaskMainDetailBean.getStatus())
							{
								viewHolder.setText(R.id.btn_task_detail_contact, "立即报名").setBackgroundResource(R.drawable.widget_shape_rectangle_solid_redhokol);
							}
							else if (VTaskMainDetailBean.StatusAssignPass == vTaskMainDetailBean.getStatus())
							{
								viewHolder.setText(R.id.btn_task_detail_contact, "已终止报名").setBackgroundResource(R.drawable.widget_shape_rectangle_solid_pinkhokol);
							}
							else
							{
								viewHolder.setText(R.id.btn_task_detail_contact, "已结束").setBackgroundResource(R.drawable.widget_shape_rectangle_solid_pinkhokol);
							}
						}
						else
						{
							if (VTaskMainDetailBean.StatusAssigning == vTaskMainDetailBean.getStatus())
							{
								viewHolder.setText(R.id.btn_task_detail_contact, "已报名").setBackgroundResource(R.drawable.widget_shape_rectangle_solid_pinkhokol);
							}
							else if (VTaskMainDetailBean.StatusAssignPass == vTaskMainDetailBean.getStatus())
							{
								viewHolder.setText(R.id.btn_task_detail_contact, "已报名").setBackgroundResource(R.drawable.widget_shape_rectangle_solid_pinkhokol);
							}
							else
							{
								viewHolder.setText(R.id.btn_task_detail_contact, "已结束").setBackgroundResource(R.drawable.widget_shape_rectangle_solid_pinkhokol);
							}
						}
					}

					// 标价
					viewHolder.setText(R.id.iv_task_detail_price, String.format("￥%d × %d", vTaskMainDetailBean.getTask_fee(), vTaskMainDetailBean.getTask_peo_num()));

					// 标题
					viewHolder.setText(R.id.iv_task_detail_title, vTaskMainDetailBean.getTask_title());

					// 托管
					String guaranteeStr = VTaskMainDetailBean.Guaranteed == vTaskMainDetailBean.getIs_guarantee() ? "已托管押金" : "未托管押金";
					viewHolder.setText(R.id.iv_task_detail_guarantee, guaranteeStr);

					// 剩余时间
					String restTime = HokolTimeConvertUtil.stampToRestFormatTime(vTaskMainDetailBean.getTask_rem_time() * 1000 + System.currentTimeMillis());
					if (TextUtils.isEmpty(restTime))
					{
						viewHolder.setText(R.id.iv_task_detail_rest_time, "已到期");
					}
					else
					{
						viewHolder.setText(R.id.iv_task_detail_rest_time, "剩余" + restTime);
					}

					// 任务详情
					viewHolder.setText(R.id.iv_task_detail_content, vTaskMainDetailBean.getTask_content());

					// 任务发布时间
					viewHolder.setText(R.id.iv_task_detail_publish_time, HokolTimeConvertUtil.stampToFormatDate(vTaskMainDetailBean.getTask_pub_time() * 1000));

					// 地区
					String provinceName = vTaskMainDetailBean.getProvince().get(0);
					String cityName = vTaskMainDetailBean.getCity().get(0);
					if (TextUtils.isEmpty(provinceName) || SecondaryWidget.DefaultFirst.equals(provinceName))
					{
						viewHolder.setText(R.id.iv_task_detail_area, "不限");
					}
					else if (TextUtils.isEmpty(cityName) || SecondaryWidget.DefaultFirst.equals(cityName))
					{
						viewHolder.setText(R.id.iv_task_detail_area, provinceName);
					}
					else
					{
						viewHolder.setText(R.id.iv_task_detail_area, String.format("%s %s", provinceName, cityName));
					}
					
					// 属性
					FlowWidget flowWidget = new FlowWidget(TaskDetailActivity.this, R.id.lable_flow_task_detail)
					{
						@Override
						protected int getItemResourceId()
						{
							return R.layout.widget_item_label_flow_padright_medium;
						}
					};
					flowWidget.setDataList(vTaskMainDetailBean.getTask_tag());

					// 女
					viewHolder.setText(R.id.iv_task_detail_num_girl, vTaskMainDetailBean.getTask_woman_num() + "");

					// 男
					viewHolder.setText(R.id.iv_task_detail_num_boy, vTaskMainDetailBean.getTask_man_num() + "");
				}

				@Override
				public void onFailureCode(int code)
				{
					super.onFailureCode(code);
					if (code == 2003)
					{
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
	}
}
