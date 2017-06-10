package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.hokol.R;
import com.hokol.fragment.TaskPublishRightAreaFragment;
import com.hokol.fragment.TaskPublishRightStyleFragment;
import com.hokol.medium.widget.DialogIosWidget;
import com.yline.application.SDKManager;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.KeyBoardUtil;
import com.yline.view.common.ViewHolder;

/**
 * 发布任务
 *
 * @author yline 2017/4/1 -- 17:53
 * @version 1.0.0
 */
public class TaskPublishActivity extends BaseAppCompatActivity
{
	private ViewHolder viewHolder;

	// private DrawerLayout drawerLayout;

	private FragmentManager fragmentManager = getSupportFragmentManager();

	private TaskPublishRightStyleFragment rightStyleFragment;

	private TaskPublishRightAreaFragment rightAreaFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_publish);

		viewHolder = new ViewHolder(this);

		initView();
		initViewClick();
	}

	private void initView()
	{
		/*drawerLayout = viewHolder.get(R.id.drawer_task_publish);
		drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); // 禁止手势滑动
		drawerLayout.setClickable(false);

		drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener()
		{
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset)
			{

			}

			@Override
			public void onDrawerOpened(View drawerView)
			{

			}

			@Override
			public void onDrawerClosed(View drawerView)
			{

			}

			@Override
			public void onDrawerStateChanged(int newState)
			{

			}
		});*/

		rightStyleFragment = TaskPublishRightStyleFragment.newInstance();
		rightAreaFragment = TaskPublishRightAreaFragment.newInstance();

		viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.GONE);
		fragmentManager.beginTransaction().add(R.id.rl_task_publish_right, rightStyleFragment).add(R.id.rl_task_publish_right, rightAreaFragment).commit();
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

		viewHolder.setOnClickListener(R.id.ll_task_publish_content_style, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				fragmentManager.beginTransaction().show(rightStyleFragment).hide(rightAreaFragment).commit();
				KeyBoardUtil.closeKeyboard(TaskPublishActivity.this);

				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.VISIBLE);
				// drawerLayout.openDrawer(Gravity.RIGHT);
			}
		});

		viewHolder.setOnClickListener(R.id.ll_task_publish_content_area, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				fragmentManager.beginTransaction().show(rightAreaFragment).hide(rightStyleFragment).commit();
				KeyBoardUtil.closeKeyboard(TaskPublishActivity.this);

				viewHolder.get(R.id.rl_task_publish_right).setVisibility(View.VISIBLE);
				// drawerLayout.openDrawer(Gravity.RIGHT);
			}
		});
		
		viewHolder.setOnClickListener(R.id.ll_task_publish_content_time, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("选择时间");
			}
		});

		viewHolder.setOnClickListener(R.id.btn_task_publish_content_commit, new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SDKManager.toast("提交任务");
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

	public static void actionStart(Context context)
	{
		context.startActivity(new Intent(context, TaskPublishActivity.class));
	}
}
