package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hokol.R;
import com.hokol.activity.EnterChoiceActivity;
import com.hokol.activity.MainActivity;
import com.hokol.activity.StarDynamicActivity;
import com.hokol.activity.StarInfoActivity;
import com.hokol.application.AppStateManager;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicCareAllBean;
import com.hokol.medium.http.bean.VDynamicCareBean;
import com.hokol.medium.http.bean.WDynamicCareAllBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainCareHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.view.common.ViewHolder;

import java.util.List;

public class MainCareFragment extends BaseFragment
{
	private MainCareHelper mainCareHelper;

	private int refreshedNumber;

	private ViewHolder viewHolder;

	private SuperSwipeRefreshLayout swipeRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_care, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		viewHolder = new ViewHolder(view);
		mainCareHelper = new MainCareHelper(getContext());

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		// 内容
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_care);
		mainCareHelper.initRecycleView(recyclerView);
		mainCareHelper.setOnRecycleItemClickListener(new MainCareHelper.OnCareRecycleClickListener()
		{
			@Override
			public void onAvatarClick(VDynamicCareBean bean)
			{
				StarInfoActivity.actionStart(getContext());
			}

			@Override
			public void onPictureClick(VDynamicCareBean bean)
			{
				StarDynamicActivity.actionStart(getContext(), bean.getDt_id());
			}
		});

		// 刷新
		swipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_care);
		swipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("刷新结束");
						swipeRefreshLayout.setRefreshing(false);
					}
				}, 2000);
			}
		});
		swipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
		{
			@Override
			public void onAnimate()
			{
				IApplication.toast("正在加载");
				IApplication.getHandler().postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						IApplication.toast("刷新结束");
						swipeRefreshLayout.setLoadMore(false);
					}
				}, 2000);
			}
		});

		// 没有数据 或者 未登录时 状态
		mainCareHelper.setOnEmptyBtnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String userId = AppStateManager.getInstance().getUserLoginId(getContext());
				LogFileUtil.v("userId = " + userId);
				if (TextUtils.isEmpty(userId))
				{
					EnterChoiceActivity.actionStart(getContext());
				}
				else
				{
					if (getActivity() instanceof MainActivity)
					{
						((MainActivity) getActivity()).doSelected(MainActivity.TAB.Home.getPosition());
					}
				}
			}
		});
	}

	private void initData()
	{
		String userId = AppStateManager.getInstance().getUserLoginId(getContext());
		if (TextUtils.isEmpty(userId))
		{
			mainCareHelper.updateRecyclerEmptyState(true);
		}
		else
		{
			mainCareHelper.updateRecyclerEmptyState(false);

			WDynamicCareAllBean wDynamicCareAllBean = new WDynamicCareAllBean(userId, 0, DeleteConstant.defaultNumberSmall);
			XHttpUtil.doDynamicCareAll(wDynamicCareAllBean, new XHttpAdapter<VDynamicCareAllBean>()
			{
				@Override
				public void onSuccess(VDynamicCareAllBean vDynamicCareAllBean)
				{
					List<VDynamicCareBean> result = vDynamicCareAllBean.getList();
					if (null == result)
					{
						mainCareHelper.updateRecyclerEmptyState(true);
					}
					else
					{
						mainCareHelper.setRecycleData(result);
						refreshedNumber += result.size();
					}
				}

				@Override
				public void onFailure(Exception ex)
				{
					super.onFailure(ex);
				}

				@Override
				public void onFailureCode(int code)
				{
					super.onFailureCode(code);
				}
			});
		}
	}
}
