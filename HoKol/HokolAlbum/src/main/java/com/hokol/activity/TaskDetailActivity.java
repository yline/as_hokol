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
import com.hokol.medium.http.bean.WTaskMainCollectionBean;
import com.hokol.medium.http.bean.WTaskMainDetailBean;
import com.hokol.medium.widget.FlowWidget;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
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

	private ViewHolder viewHolder;

	private String taskId;

	private boolean isCollected;

	public static void actionStart(Context context, String taskId)
	{
		context.startActivity(new Intent(context, TaskDetailActivity.class).putExtra(KeyTaskId, taskId));
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
				SDKManager.toast("立即报名");
			}
		});
	}
	
	private void initData()
	{
		taskId = getIntent().getStringExtra(KeyTaskId);
		if (TextUtils.isEmpty(taskId))
		{
			SDKManager.toast("点击的任务出错啦");
			finish();
		}
		else
		{
			String userId = AppStateManager.getInstance().getUserLoginId(this);
			if (TextUtils.isEmpty(WTaskMainDetailBean.UnLoginState))
			{
				userId = WTaskMainDetailBean.UnLoginState;
			}

			XHttpUtil.doTaskMainDetail(new WTaskMainDetailBean(userId, taskId), new XHttpAdapter<VTaskMainDetailBean>()
			{
				@Override
				public void onSuccess(VTaskMainDetailBean vTaskMainDetailBean)
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
						viewHolder.setText(R.id.iv_task_detail_rest_time, "剩" + restTime);
					}

					// 任务详情
					viewHolder.setText(R.id.iv_task_detail_content, vTaskMainDetailBean.getTask_content());

					// 任务发布时间
					viewHolder.setText(R.id.iv_task_detail_publish_time, HokolTimeConvertUtil.stampToFormatDate(vTaskMainDetailBean.getTask_pub_time() * 1000));

					// 地区
					viewHolder.setText(R.id.iv_task_detail_area, String.format("%s %s", vTaskMainDetailBean.getProvince().get(0), vTaskMainDetailBean.getCity().get(0)));

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
