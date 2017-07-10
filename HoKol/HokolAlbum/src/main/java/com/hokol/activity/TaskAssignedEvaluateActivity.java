package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskStaffCommentedInfoBean;
import com.hokol.medium.http.bean.WTaskActionMasterCommentBean;
import com.hokol.medium.http.bean.WTaskStaffCommentedInfoBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商家发布任务，评价
 *
 * @author yline 2017/6/1 -- 16:43
 * @version 1.0.0
 */
public class TaskAssignedEvaluateActivity extends BaseAppCompatActivity
{
	private static final String KeyTaskId = "TaskId";

	private AssignedEvaluateAdapter assignedEvaluateAdapter;

	private String taskId;

	public static void actionStart(Context context, String taskId)
	{
		context.startActivity(new Intent(context, TaskAssignedEvaluateActivity.class).putExtra(KeyTaskId, taskId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_evaluate);

		initView();
		initData();
	}

	private void initView()
	{
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_assigned_evaluate);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this)
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}

			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}
		});

		assignedEvaluateAdapter = new AssignedEvaluateAdapter();
		recyclerView.setAdapter(assignedEvaluateAdapter);
		
		// 返回
		findViewById(R.id.iv_task_assigned_evaluate_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 发布
		findViewById(R.id.tv_task_assigned_evaluate_evaluate).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("点击成功");
				String userId = AppStateManager.getInstance().getUserLoginId(TaskAssignedEvaluateActivity.this);
				XHttpUtil.doTaskActionMasterComment(new WTaskActionMasterCommentBean(userId, taskId, assignedEvaluateAdapter.getRequestList()), new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						SDKManager.toast("发布成功");
					}
				});
			}
		});
	}

	private void initData()
	{
		taskId = getIntent().getStringExtra(KeyTaskId);
		XHttpUtil.doTaskStaffCommentedInfo(new WTaskStaffCommentedInfoBean(taskId), new XHttpAdapter<VTaskStaffCommentedInfoBean>()
		{
			@Override
			public void onSuccess(VTaskStaffCommentedInfoBean vTaskStaffCommentedInfoBean)
			{
				List<VTaskStaffCommentedInfoBean.VTaskStaffInfo> resultList = vTaskStaffCommentedInfoBean.getList();
				if (null != resultList)
				{
					assignedEvaluateAdapter.setDataList(resultList);
					assignedEvaluateAdapter.initRequestArrays();
				}
			}
		});
	}
	
	private class AssignedEvaluateAdapter extends WidgetRecyclerAdapter<VTaskStaffCommentedInfoBean.VTaskStaffInfo>
	{
		private static final int MaxScore = 5;

		private WTaskActionMasterCommentBean.MasterCommentContentBean[] requestArrays;

		public void initRequestArrays()
		{
			requestArrays = new WTaskActionMasterCommentBean.MasterCommentContentBean[getItemCount()];
			for (int i = 0; i < getItemCount(); i++)
			{
				requestArrays[i].setComment_user_id(sList.get(i).getUser_id());
				requestArrays[i].setConformity_score(MaxScore);
				requestArrays[i].setAction_capacity_score(MaxScore);
				requestArrays[i].setAttitude_score(MaxScore);
				requestArrays[i].setUser_comment("");
			}
		}

		public List<WTaskActionMasterCommentBean.MasterCommentContentBean> getRequestList()
		{
			if (null != requestArrays)
			{
				return Arrays.asList(requestArrays);
			}
			else
			{
				return new ArrayList<>();
			}
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_task_assigned_evaluate;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, final int position)
		{
			super.onBindViewHolder(holder, position);

			VTaskStaffCommentedInfoBean.VTaskStaffInfo commentInfo = sList.get(position);

			// 头像
			ImageView avatarImageView = holder.get(R.id.circle_task_evaluate_avatar);
			Glide.with(TaskAssignedEvaluateActivity.this).load(commentInfo.getUser_logo()).error(R.drawable.global_load_avatar).into(avatarImageView);

			// 昵称
			holder.setText(R.id.tv_task_evaluate_nickname, commentInfo.getUser_nickname());

			// 外貌相符
			RatingBar conformityRatingBar = holder.get(R.id.rating_task_evaluate_conformity);
			conformityRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
			{
				@Override
				public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
				{
					requestArrays[position].setConformity_score((int) rating);
				}
			});

			// 活动能力
			RatingBar actionRatingBar = holder.get(R.id.rating_task_evaluate_action);
			updateStarHint((TextView) holder.get(R.id.tv_task_evaluate_conformity), requestArrays[position].getConformity_score());
			actionRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
			{
				@Override
				public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
				{
					requestArrays[position].setAction_capacity_score((int) rating);
				}
			});

			// 服务态度
			RatingBar attitudeRatingBar = holder.get(R.id.rating_task_evaluate_attitude);
			updateStarHint((TextView) holder.get(R.id.tv_task_evaluate_action), requestArrays[position].getConformity_score());
			attitudeRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
			{
				@Override
				public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
				{
					requestArrays[position].setAttitude_score((int) rating);
				}
			});

			// 评价内容
			EditText evaluateEditText = holder.get(R.id.et_task_evaluate);
			updateStarHint((TextView) holder.get(R.id.tv_task_evaluate_attitude), requestArrays[position].getConformity_score());
			evaluateEditText.addTextChangedListener(new TextWatcher()
			{
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after)
				{

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count)
				{

				}

				@Override
				public void afterTextChanged(Editable s)
				{
					LogFileUtil.v("content = " + s.toString());
					requestArrays[position].setUser_comment(s.toString());
				}
			});
		}

		private void updateStarHint(TextView textView, float rating)
		{
			if (rating < 1.5)
			{
				textView.setText("非常差");

			}
			else if (rating < 2.5)
			{
				textView.setText("差");

			}
			else if (rating < 3.5)
			{
				textView.setText("一般");

			}
			else
			{
				textView.setText("好");
			}
		}
	}
}
