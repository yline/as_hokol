package com.hokol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.hokol.R;
import com.hokol.application.DeleteConstant;
import com.hokol.fragment.TaskAssignedSignDetailEdFragment;
import com.hokol.fragment.TaskAssignedSignDetailUnFragment;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VTaskUserSignUpDetailBean;
import com.hokol.medium.http.bean.WTaskUserSignUpDetailBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.yline.base.BaseAppCompatActivity;
import com.yline.base.BaseFragment;
import com.yline.log.LogFileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 报名详情
 *
 * @author yline 2017/6/2 -- 16:37
 * @version 1.0.0
 */
public class TaskAssignedSignDetailActivity extends BaseAppCompatActivity implements TaskAssignedSignDetailUnFragment.OnAssignedDetailRefreshListener
{
	private static final String KeyTaskId = "TaskId";

	private static final String KeyIsNegotiable = "IsNegotiable";

	private TaskAssignedSignDetailUnFragment unFragment;

	private TaskAssignedSignDetailEdFragment edFragment;

	private String taskId;

	private boolean isNegotiable;

	/**
	 * @param context
	 * @param taskId       任务标识
	 * @param isNegotiable 是否担保
	 */
	public static void actionStart(Context context, String taskId, boolean isNegotiable)
	{
		context.startActivity(new Intent(context, TaskAssignedSignDetailActivity.class).putExtra(KeyTaskId, taskId).putExtra(KeyIsNegotiable, isNegotiable));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assigned_sign_detail);

		taskId = getIntent().getStringExtra(KeyTaskId);
		isNegotiable = getIntent().getBooleanExtra(KeyIsNegotiable, false);

		initView();
		initTabView();
		initData();
	}

	private void initView()
	{
		// 结束
		findViewById(R.id.iv_task_assigned_sign_detail_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	private void initTabView()
	{
		final List<BaseFragment> fragmentList = new ArrayList<>();
		final List<String> titleList = new ArrayList<>();

		unFragment = TaskAssignedSignDetailUnFragment.newInstance(taskId, isNegotiable);
		fragmentList.add(unFragment);
		titleList.add("待录用");

		edFragment = TaskAssignedSignDetailEdFragment.newInstance(taskId);
		fragmentList.add(edFragment);
		titleList.add("已录用");

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_task_assigned_sign_detail);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_task_assigned_sign_detail);

		viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public Fragment getItem(int position)
			{
				return fragmentList.get(position);
			}

			@Override
			public int getCount()
			{
				return fragmentList.size();
			}

			@Override
			public CharSequence getPageTitle(int position)
			{
				return titleList.get(position);
			}
		});
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.hokolGrayHeavy), ContextCompat.getColor(this, R.color.hokolRed));
		tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.hokolRed));
	}

	private void initData()
	{
		onRefresh(0, DeleteConstant.defaultNumberLarge);
	}

	@Override
	public void onRefresh(int start, int length)
	{
		if (!TextUtils.isEmpty(taskId))
		{
			XHttpUtil.doTaskUserSignUpDetail(new WTaskUserSignUpDetailBean(taskId, start, length), new HokolAdapter<VTaskUserSignUpDetailBean>()
			{
				@Override
				public void onSuccess(VTaskUserSignUpDetailBean signUpDetailBean)
				{
					List<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean> resultList = signUpDetailBean.getList();

					ArrayList<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean> unList = new ArrayList<>();
					ArrayList<VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean> edList = new ArrayList<>();

					if (null != resultList)
					{
						for (VTaskUserSignUpDetailBean.VTaskUserSignUpDetailOneBean bean : resultList)
						{
							if (bean.getIs_employe() == VTaskUserSignUpDetailBean.EmployUn || bean.getIs_employe() == VTaskUserSignUpDetailBean.EmployWaiting)
							{
								unList.add(bean);
							}
							else
							{
								edList.add(bean);
							}
						}
					}
					else
					{
						LogFileUtil.v("Task Assigned Sign Detail Data is Null");
					}

					unFragment.onRefreshData(unList);
					edFragment.onRefreshData(edList);
				}
			});
		}
	}
}
