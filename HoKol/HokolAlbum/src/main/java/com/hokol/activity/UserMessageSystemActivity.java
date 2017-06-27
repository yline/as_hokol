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
import com.hokol.application.DeleteConstant;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VUserMessageSystemBean;
import com.hokol.medium.http.bean.WUserMessageSystemBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.base.BaseAppCompatActivity;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

/**
 * 我的消息删除（系统）
 * 我的消息读取(系统)
 * 这两个还没做
 *
 * @author yline 2017/6/27 -- 14:44
 * @version 1.0.0
 */
public class UserMessageSystemActivity extends BaseAppCompatActivity
{
	private static final String KeyMessageUserId = "MessageSystemUserId";

	private MessageSystemAdapter messageSystemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_message_system);

		initView();
		initData();
	}

	private void initView()
	{
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

		messageSystemAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VUserMessageSystemBean.VUserMessageOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VUserMessageSystemBean.VUserMessageOneBean messageBean, int position)
			{
				UserMessageDetailActivity.actionStart(UserMessageSystemActivity.this, messageBean.getMess_title(), messageBean.getMess_content(), messageBean.getPub_time());
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
	}

	private void initData()
	{
		String userId = getIntent().getStringExtra(KeyMessageUserId);

		messageSystemAdapter.setShowEmpty(false);
		XHttpUtil.doUserMessageSystem(new WUserMessageSystemBean(userId, 0, DeleteConstant.defaultNumberLarge), new XHttpAdapter<VUserMessageSystemBean>()
		{
			@Override
			public void onSuccess(VUserMessageSystemBean vUserMessageSystemBean)
			{
				List<VUserMessageSystemBean.VUserMessageOneBean> result = vUserMessageSystemBean.getList();
				if (null != result)
				{
					messageSystemAdapter.setDataList(vUserMessageSystemBean.getList());
				}
			}
		});
	}

	private class MessageSystemAdapter extends WidgetRecyclerAdapter<VUserMessageSystemBean.VUserMessageOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_user_message_system;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			VUserMessageSystemBean.VUserMessageOneBean messageBean = sList.get(position);

			// 标题
			viewHolder.setText(R.id.tv_message_system_title, messageBean.getMess_title());

			// 时间
			String timeStr = HokolTimeConvertUtil.stamp2FormatTime(messageBean.getPub_time() * 1000);
			viewHolder.setText(R.id.tv_message_system_time, timeStr);

			// 内容
			viewHolder.setText(R.id.tv_message_system_content, messageBean.getMess_content());

			// 是否已读
			boolean haveRead = VUserMessageSystemBean.MessageHaveRead == messageBean.getIs_read() ? true : false;
			if (haveRead)
			{
				viewHolder.get(R.id.view_message_system_bubble).setVisibility(View.GONE);
			}
			else
			{
				viewHolder.get(R.id.view_message_system_bubble).setVisibility(View.VISIBLE);
			}
		}
	}

	public static void actionStart(Context context, String userId)
	{
		context.startActivity(new Intent(context, UserMessageSystemActivity.class).putExtra(KeyMessageUserId, userId));
	}
}
