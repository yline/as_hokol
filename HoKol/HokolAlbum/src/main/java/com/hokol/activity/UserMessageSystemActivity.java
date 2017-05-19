package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hokol.R;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.OnRecyclerItemClickListener;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.UIScreenUtil;
import com.yline.view.common.CommonRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.Arrays;

public class UserMessageSystemActivity extends BaseAppCompatActivity
{
	private MessageSystemAdapter messageSystemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_message_system);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_message_system);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(this)
		{
			@Override
			protected boolean isDivideLastLine()
			{
				return true;
			}

			@Override
			public void drawVerticalDivider(Canvas c, Drawable divide, int currentPosition, int childLeft, int childTop, int childRight, int childBottom)
			{
				childLeft = childLeft + UIScreenUtil.dp2px(UserMessageSystemActivity.this, 15);
				super.drawVerticalDivider(c, divide, currentPosition, childLeft, childTop, childRight, childBottom);
			}
		});
		messageSystemAdapter = new MessageSystemAdapter();
		recyclerView.setAdapter(messageSystemAdapter);

		messageSystemAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener()
		{
			@Override
			public void onClick(RecyclerViewHolder viewHolder, Object o, int position)
			{
				UserMessageDetailActivity.actionStart(UserMessageSystemActivity.this);
			}
		});

		findViewById(R.id.iv_message_system).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		messageSystemAdapter.addAll(Arrays.asList("A", "B", "C", "D"));
	}

	private class MessageSystemAdapter extends CommonRecyclerAdapter<String>
	{
		private OnRecyclerItemClickListener listener;

		public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener)
		{
			this.listener = listener;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			viewHolder.getItemView().setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != listener)
					{
						listener.onClick(viewHolder, sList.get(position), position);
					}
				}
			});
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_user_message_system;
		}
	}

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, UserMessageSystemActivity.class));
	}
}
