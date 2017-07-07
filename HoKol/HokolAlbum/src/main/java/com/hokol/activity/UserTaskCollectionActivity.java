package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserTaskCollectionBean;
import com.hokol.medium.http.bean.WTaskActionStaffSignUpBean;
import com.hokol.medium.http.bean.WUserTaskCollectionBean;
import com.hokol.medium.widget.DialogIosWidget;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class UserTaskCollectionActivity extends BaseAppCompatActivity
{
	private static final String KeyCollectUserId = "TaskCollectionUserId";

	private CollectionAdapter collectionAdapter;

	private String userId;

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserTaskCollectionActivity.class).putExtra(KeyCollectUserId, userId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_task_collection);

		initView();

		findViewById(R.id.iv_task_collection).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		initData();
	}

	private void initView()
	{
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_collection);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		collectionAdapter = new CollectionAdapter();
		recyclerView.setAdapter(collectionAdapter);

		collectionAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VUserTaskCollectionBean.VUserCollectionOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VUserTaskCollectionBean.VUserCollectionOneBean collectBean, int position)
			{
				TaskDetailActivity.actionStart(UserTaskCollectionActivity.this, collectBean.getTask_id(), false);
			}
		});
		collectionAdapter.setOnTaskCollectCallback(new OnTaskCollectCallback<VUserTaskCollectionBean.VUserCollectionOneBean>()
		{
			@Override
			public void onSignClick(View view, VUserTaskCollectionBean.VUserCollectionOneBean vUserCollectionOneBean, boolean isSign)
			{
				// 未报名状态
				if (!isSign)
				{
					final String taskId = vUserCollectionOneBean.getTask_id();

					DialogIosWidget dialogIosWidget = new DialogIosWidget(UserTaskCollectionActivity.this)
					{
						@Override
						protected void initBuilder(Builder builder)
						{
							super.initBuilder(builder);
							builder.setNegativeText("取消");
							builder.setPositiveText("确认");
							builder.setTitle("确认立即报名嘛?");
							builder.setOnPositiveListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									XHttpUtil.doTaskActionStaffSignUp(new WTaskActionStaffSignUpBean(userId, taskId), new XHttpAdapter<String>()
									{
										@Override
										public void onSuccess(String s)
										{
											SDKManager.toast("报名成功");
										}
									});
								}
							});
						}
					};
					dialogIosWidget.show();
				}
			}
		});
	}

	private void initData()
	{
		userId = getIntent().getStringExtra(KeyCollectUserId);
		WUserTaskCollectionBean collectionBean = new WUserTaskCollectionBean(userId, 0, DeleteConstant.defaultNumberLarge);

		collectionAdapter.setShowEmpty(false);
		XHttpUtil.doUserCollection(collectionBean, new XHttpAdapter<VUserTaskCollectionBean>()
		{
			@Override
			public void onSuccess(VUserTaskCollectionBean vUserTaskCollectionBean)
			{
				collectionAdapter.setShowEmpty(true);
				List<VUserTaskCollectionBean.VUserCollectionOneBean> result = vUserTaskCollectionBean.getList();
				if (null != result)
				{
					collectionAdapter.setDataList(result);
				}
			}
		});
	}

	public interface OnTaskCollectCallback<T>
	{
		void onSignClick(View view, T t, boolean isSign);
	}

	private class CollectionAdapter extends WidgetRecyclerAdapter<VUserTaskCollectionBean.VUserCollectionOneBean>
	{
		private OnTaskCollectCallback<VUserTaskCollectionBean.VUserCollectionOneBean> onTaskCollectCallback;

		public void setOnTaskCollectCallback(OnTaskCollectCallback<VUserTaskCollectionBean.VUserCollectionOneBean> onTaskCollectCallback)
		{
			this.onTaskCollectCallback = onTaskCollectCallback;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			VUserTaskCollectionBean.VUserCollectionOneBean collectionBean = sList.get(position);
			// 价格
			viewHolder.setText(R.id.tv_collection_price, String.format("￥%d×%d", collectionBean.getTask_fee(), collectionBean.getTask_peo_num()));

			// 标题
			viewHolder.setText(R.id.tv_collection_title, collectionBean.getTask_title());

			// 头像
			ImageView avatarImageView = viewHolder.get(R.id.iv_item_main_task_avatar);
			Glide.with(UserTaskCollectionActivity.this).load(collectionBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

			// 用户名
			viewHolder.setText(R.id.tv_item_main_task_user, collectionBean.getUser_nickname());

			// 报名人数
			viewHolder.setText(R.id.tv_item_main_task_state, String.format("报名%d人，录取%d人", collectionBean.getTask_join_num(), collectionBean.getTask_employee_num()));

			// 截止时间
			String deadLineTimeStr = HokolTimeConvertUtil.stampToRestFormatTime(System.currentTimeMillis() + collectionBean.getTask_rem_time() * 1000);
			if (TextUtils.isEmpty(deadLineTimeStr))
			{
				viewHolder.setText(R.id.tv_item_main_task_time, "报名已截止");
			}
			else
			{
				viewHolder.setText(R.id.tv_item_main_task_time, "剩" + deadLineTimeStr);
			}

			// 是否报名
			final boolean isSign = collectionBean.getIs_join() == VUserTaskCollectionBean.Signed ? true : false;
			TextView actionTextView = viewHolder.get(R.id.tv_item_main_task_action);
			if (TextUtils.isEmpty(deadLineTimeStr))
			{
				actionTextView.setText("报名已截止");
				actionTextView.setBackgroundResource(R.color.hokolPink);
			}
			else
			{
				if (isSign)
				{
					actionTextView.setText("已报名");
					actionTextView.setBackgroundResource(R.color.hokolPink);
				}
				else
				{
					actionTextView.setText("立即报名");
					actionTextView.setBackgroundResource(R.color.hokolRed);
				}

				actionTextView.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (null != onTaskCollectCallback)
						{
							onTaskCollectCallback.onSignClick(v, sList.get(position), isSign);
						}
					}
				});
			}
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_task_collection_content;
		}
	}
}
