package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.WTaskActionStaffCommentBean;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.holder.ViewHolder;

public class TaskDeliveredEvaluateActivity extends BaseAppCompatActivity
{
	private static final String KeyTaskId = "taskId";

	private static final String KeyMasterId = "masterId";

	private static final String KeyMasterLogo = "masterLogo";

	private static final String KeyMasterNickname = "masterNickname";

	private ViewHolder viewHolder;

	private WTaskActionStaffCommentBean commentBean;

	public static void actionStart(Context context, String taskId, String masterId, String masterLogo, String masterNickname)
	{
		Intent intent = new Intent(context, TaskDeliveredEvaluateActivity.class);
		intent.putExtra(KeyTaskId, taskId);
		intent.putExtra(KeyMasterId, masterId);
		intent.putExtra(KeyMasterLogo, masterLogo);
		intent.putExtra(KeyMasterNickname, masterNickname);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_delivered_evaluate);

		viewHolder = new ViewHolder(this);
		initView();
		initData();
	}

	private void initView()
	{
		// 结束
		viewHolder.setOnClickListener(R.id.iv_task_delivered_evaluate_cancel, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		// 发布任务
		viewHolder.setOnClickListener(R.id.tv_task_delivered_evaluate_publish, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				XHttpUtil.doTaskActionStaffComment(commentBean, new XHttpAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{

					}
				});
			}
		});
	}

	private void initData()
	{
		String taskId = getIntent().getStringExtra(KeyTaskId);
		String masterId = getIntent().getStringExtra(KeyMasterId);
		String userId = AppStateManager.getInstance().getUserLoginId(this);
		commentBean = new WTaskActionStaffCommentBean(userId, taskId, masterId);

		// 头像
		String masterAvatar = getIntent().getStringExtra(KeyMasterLogo);
		ImageView avatarImageView = viewHolder.get(R.id.iv_task_delivered_evaluate_avatar);
		Glide.with(this).load(masterAvatar).error(R.drawable.global_load_avatar).into(avatarImageView);

		// 昵称
		String masterNickName = getIntent().getStringExtra(KeyMasterNickname);
		viewHolder.setText(R.id.tv_task_delivered_evaluate_nickname, masterNickName);

		// 外貌相符
		/*RatingBar conformityRatingBar = holder.get(R.id.rating_task_evaluate_conformity);
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
		});*/
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
