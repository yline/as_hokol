package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.StarDynamicActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.HttpEnum;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VHomeMainBean;
import com.hokol.medium.http.bean.WHomeMainBean;
import com.hokol.medium.widget.ADWidget;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.medium.widget.recycler.OnRecyclerItemClickListener;
import com.hokol.medium.widget.swiperefresh.SuperSwipeRefreshLayout;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;
import com.yline.view.common.ViewHolder;

import java.util.List;

/**
 * @author yline 2017/2/13 --> 17:36
 * @version 1.0.0
 */
public class MainHomeRedFragment extends BaseFragment implements MainHomeFragment.OnHomeFilterCallback
{
	private MainNewsHotAdapter recycleAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private int refreshedNumber;

	private ViewHolder viewHolder;

	private View adView;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home_red, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		viewHolder = new ViewHolder(view);

		initView(view);
		initData();
	}
	
	private void initView(View view)
	{
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_home_red);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
		recyclerView.addItemDecoration(new DefaultGridItemDecoration(getContext())
		{

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_recycler_divider_white_small;
			}

			@Override
			protected int getHeadNumber()
			{
				return 2;
			}
		});

		recycleAdapter = new MainNewsHotAdapter();
		recycleAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VHomeMainBean.VHomeMainOneBean>()
		{
			@Override
			public void onClick(RecyclerViewHolder viewHolder, VHomeMainBean.VHomeMainOneBean vHomeMainOneBean, int position)
			{
				StarDynamicActivity.actionStart(getContext(), vHomeMainOneBean.getDt_id());
			}
		});
		recyclerView.setAdapter(recycleAdapter);

		// RecycleView
		initRecycleViewHead(recycleAdapter);

		// 下拉刷新
		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_home_red);
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
	}

	private void initData()
	{
		refreshedNumber = 0;
		WHomeMainBean wHomeMainBean = new WHomeMainBean(HttpEnum.UserTag.Red, refreshedNumber, DeleteConstant.defaultNumberSmall);
		XHttpUtil.doHomeMain(wHomeMainBean, new XHttpAdapter<VHomeMainBean>()
		{
			@Override
			public void onSuccess(VHomeMainBean vHomeMainBean)
			{
				if (vHomeMainBean.getList().size() == 2)
				{/*
					viewHolder.get(R.id.super_swipe_main_home_red).setVisibility(View.INVISIBLE);
					viewHolder.get(R.id.rl_red_loading_cover).setVisibility(View.VISIBLE);

					ViewGroup viewGroup = viewHolder.get(R.id.ll_red_ad);
					viewGroup.addView(adView);
					*/
					recycleAdapter.setNullData(true);

					recycleAdapter.setDataList(vHomeMainBean.getList());

					refreshedNumber = recycleAdapter.dataSize();
					LogFileUtil.v("vHomeMainBean size = " + refreshedNumber);
				}
				else
				{
					recycleAdapter.setNullData(false);
					/*
					viewHolder.get(R.id.super_swipe_main_home_red).setVisibility(View.VISIBLE);
					viewHolder.get(R.id.rl_red_loading_cover).setVisibility(View.INVISIBLE);
					*/
					recycleAdapter.setDataList(vHomeMainBean.getList());

					refreshedNumber = recycleAdapter.dataSize();
					LogFileUtil.v("vHomeMainBean size = " + refreshedNumber);
				}
			}
		});
	}

	/**
	 * RecycleView 添加头部
	 */
	private void initRecycleViewHead(HeadFootRecyclerAdapter wrapperAdapter)
	{
		// AD
		ADWidget adWidget = new ADWidget()
		{
			@Override
			protected int getViewPagerHeight()
			{
				return UIScreenUtil.dp2px(getContext(), 150);
			}
		};
		adView = adWidget.start(getContext(), 3);
		adWidget.setListener(new ADWidget.OnPageListener()
		{
			@Override
			public void onPageClick(View v, int position)
			{
				IApplication.toast("广告位置 = " + position);
			}

			@Override
			public void onPageInstance(ImageView imageView, int position)
			{
				Glide.with(getContext()).load(DeleteConstant.getUrlRec()).centerCrop().error(R.drawable.global_load_failed).into(imageView);
			}
		});
		wrapperAdapter.addHeadView(adView);

		// 分割线
		View divideView = new View(getContext());
		divideView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 6)));
		divideView.setBackgroundResource(R.color.hokolGrayLight);
		wrapperAdapter.addHeadView(divideView);
	}

	@Override
	public void onAreaUpdate(String first, List<String> second)
	{
		LogFileUtil.v("onAreaUpdate first = " + first + ",second = " + second.toString());
	}

	@Override
	public void onFilterUpdate(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
	{
		LogFileUtil.v("onAreaUpdate typeSex = " + typeSex + ",typeRecommend = " + typeRecommend);
	}

	private class MainNewsHotAdapter extends HeadFootRecyclerAdapter<VHomeMainBean.VHomeMainOneBean>
	{
		private OnRecyclerItemClickListener listener;

		private boolean isNullData;

		public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener)
		{
			this.listener = listener;
		}

		@Override
		public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
		{
			return super.onCreateViewHolder(parent, viewType);
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_red;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			if (isNullData)
			{
				viewHolder.getItemView().setBackgroundColor(0xffff2742);
				return;
			}

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

			ImageView ivPic = viewHolder.get(R.id.iv_main_news_hot_pic);
			Glide.with(getContext()).load(sList.get(position).getDt_img()).centerCrop()
					.placeholder(R.drawable.global_load_failed)
					.error(R.drawable.global_load_failed)
					.into(ivPic);
		}

		public void setNullData(boolean nullData)
		{
			isNullData = nullData;
		}
	}

}
