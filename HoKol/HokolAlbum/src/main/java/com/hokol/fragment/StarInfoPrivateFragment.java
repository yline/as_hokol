package com.hokol.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.StarDynamicPrivateActivity;
import com.hokol.activity.VipHokolActivity;
import com.hokol.activity.VipSinglePrivateActivity;
import com.hokol.application.AppStateManager;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicUserPrivateAllBean;
import com.hokol.medium.http.bean.WDynamicUserPrivateAllBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.DialogIosWidget;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class StarInfoPrivateFragment extends BaseFragment
{
	private static final String KeyPrivateStarId = "PrivateStarId";

	private SuperSwipeRefreshLayout superRefreshLayout;

	private StarInfoPrivateAdapter starInfoPrivateAdapter;

	private RelativeLayout lockRelativeLayout;

	public static StarInfoPrivateFragment newInstance(String starId)
	{
		StarInfoPrivateFragment fragment = new StarInfoPrivateFragment();
		Bundle args = new Bundle();
		args.putString(KeyPrivateStarId, starId);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_star_info_private, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initData();
	}

	private void initView(View view)
	{
		// 内容
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_star_info_private);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{
			@Override
			protected int getHeadNumber()
			{
				return 1;
			}

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_null_size_little;
			}
		});
		starInfoPrivateAdapter = new StarInfoPrivateAdapter();
		starInfoPrivateAdapter.setShowEmpty(false);
		recyclerView.setAdapter(starInfoPrivateAdapter);

		View headView = new View(getContext());
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 6)));
		headView.setBackgroundResource(R.color.hokolGrayLittle);
		starInfoPrivateAdapter.addHeadView(headView);

		starInfoPrivateAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VDynamicUserPrivateAllBean.VDynamicUserPrivateSingleBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VDynamicUserPrivateAllBean.VDynamicUserPrivateSingleBean bean, int position)
			{
				StarDynamicPrivateActivity.actionStart(getContext(), bean.getPri_id());
			}
		});

		// 刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_star_info_private);
		superRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						superRefreshLayout.setRefreshing(false);
					}
				}, 2000);
			}
		});
		superRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						superRefreshLayout.setLoadMore(false);
					}
				}, 2000);
			}
		});

		// 锁屏
		lockRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_star_info_private_lock);
	}

	private void initData()
	{
		String userId = AppStateManager.getInstance().getUserLoginId(getContext());
		String starId = getArguments().getString(KeyPrivateStarId);

		if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(starId))
		{
			// TO DO 还未登录
		}
		else
		{
			XHttpUtil.doDynamicUserPrivateAll(new WDynamicUserPrivateAllBean(userId, starId, 0, DeleteConstant.defaultNumberSuper), new XHttpAdapter<VDynamicUserPrivateAllBean>()
			{
				@Override
				public void onSuccess(VDynamicUserPrivateAllBean vDynamicUserPrivateAllBean)
				{
					lockRelativeLayout.setVisibility(View.GONE);

					List<VDynamicUserPrivateAllBean.VDynamicUserPrivateSingleBean> resultList = vDynamicUserPrivateAllBean.getList();
					if (null != resultList)
					{
						starInfoPrivateAdapter.setDataList(resultList);
					}
				}

				@Override
				public void onSuccess(int code, String jsonContent, Class<VDynamicUserPrivateAllBean> defaultClazz) throws Exception
				{
					super.onSuccess(code, jsonContent, defaultClazz);
					if (code != REQUEST_SUCCESS_CODE)
					{
						lockRelativeLayout.setVisibility(View.VISIBLE);

						lockRelativeLayout.setOnClickListener(new View.OnClickListener()
						{
							@Override
							public void onClick(View v)
							{
								DialogIosWidget dialogIosWidget = new DialogIosWidget(getContext())
								{
									@Override
									protected void initXView(TextView tvTitle, TextView tvMsg, Button btnNegative, Button btnPositive, Dialog dialog)
									{
										super.initXView(tvTitle, tvMsg, btnNegative, btnPositive, dialog);
										tvTitle.setText("会员享有更多优惠哦");

										btnNegative.setText("单次购买");
										btnPositive.setText("开通会员");

										dialog.setCanceledOnTouchOutside(true);
									}
								};
								dialogIosWidget.setOnPositiveListener(new View.OnClickListener()
								{
									@Override
									public void onClick(View v)
									{
										VipHokolActivity.actionStart(getContext());
									}
								});
								dialogIosWidget.setOnNegativeListener(new View.OnClickListener()
								{
									@Override
									public void onClick(View v)
									{
										VipSinglePrivateActivity.actionStart(getContext());
									}
								});
								dialogIosWidget.show();
							}
						});
					}
				}
			});
		}
	}

	private class StarInfoPrivateAdapter extends WidgetRecyclerAdapter<VDynamicUserPrivateAllBean.VDynamicUserPrivateSingleBean>
	{
		private final int border_square;

		public StarInfoPrivateAdapter()
		{
			border_square = UIScreenUtil.getScreenWidth(getContext()) / 3 - 10;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_star_info_dynamic;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			VDynamicUserPrivateAllBean.VDynamicUserPrivateSingleBean dynamicPrivateBean = sList.get(position);

			ImageView imageView = viewHolder.get(R.id.iv_item_star_info_dynamic);
			UIResizeUtil.build().setWidth(border_square).setHeight(border_square).commit(imageView);
			Glide.with(getContext()).load(dynamicPrivateBean.getPri_img()).error(R.drawable.global_load_failed).into(imageView);
		}
	}
}
