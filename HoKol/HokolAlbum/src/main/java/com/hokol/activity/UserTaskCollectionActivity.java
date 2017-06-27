package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserTaskCollectionBean;
import com.hokol.medium.http.bean.WUserTaskCollectionBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_task_collection);

		initView();
		initData();

		findViewById(R.id.iv_task_collection).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private void initView()
	{
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_task_collection);
		DefaultLinearItemDecoration itemDecoration = new DefaultLinearItemDecoration(this)
		{
			@Override
			protected int getHeadNumber()
			{
				return 1;
			}

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_medium;
			}
		};
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		// recyclerView.addItemDecoration(itemDecoration);

		collectionAdapter = new CollectionAdapter();
		recyclerView.setAdapter(collectionAdapter);

		collectionAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VUserTaskCollectionBean.VUserCollectionOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VUserTaskCollectionBean.VUserCollectionOneBean collectBean, int position)
			{
				TaskDetailActivity.actionStart(UserTaskCollectionActivity.this, collectBean.getTask_id());
			}
		});
		collectionAdapter.setOnTaskCollectCallback(new OnTaskCollectCallback()
		{
			@Override
			public void onSignClick(View view, boolean isSign)
			{
				SDKManager.toast(isSign ? "已报名" : "未报名");
			}
		});
	}

	private void initData()
	{
		String userId = getIntent().getStringExtra(KeyCollectUserId);
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

	private class CollectionAdapter extends WidgetRecyclerAdapter<VUserTaskCollectionBean.VUserCollectionOneBean>
	{
		private OnTaskCollectCallback onTaskCollectCallback;

		public void setOnTaskCollectCallback(OnTaskCollectCallback onTaskCollectCallback)
		{
			this.onTaskCollectCallback = onTaskCollectCallback;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, int position)
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
			viewHolder.setText(R.id.tv_item_main_task_time, deadLineTimeStr);

			// 是否报名
			final boolean isSign = collectionBean.getIs_join() == VUserTaskCollectionBean.Signed ? true : false;
			TextView actionTextView = viewHolder.get(R.id.tv_item_main_task_action);
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
						onTaskCollectCallback.onSignClick(v, isSign);
					}
				}
			});
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_task_collection_content;
		}
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserTaskCollectionActivity.class).putExtra(KeyCollectUserId, userId));
	}

	public interface OnTaskCollectCallback
	{
		void onSignClick(View view, boolean isSign);
	}
}
