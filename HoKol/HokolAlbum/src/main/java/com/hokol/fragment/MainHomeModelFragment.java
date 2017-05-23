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
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.callback.OnRecyclerItemClickListener;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainHomeModelFragment extends BaseFragment implements MainHomeFragment.OnHomeFilterCallback
{
	private static final int COUNT_MODEL = 3;

	private MainHomeModelFragment.MainHomeModelAdapter mainHomeModelAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private int refreshedNumber;

	private GridLayoutManager gridLayoutManager;

	public static MainHomeModelFragment newInstance()
	{
		MainHomeModelFragment fragment = new MainHomeModelFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home_model, container, false);
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
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_home_model);

		gridLayoutManager = new GridLayoutManager(getContext(), COUNT_MODEL);
		recyclerView.setLayoutManager(gridLayoutManager);
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
				return R.drawable.widget_solid_white_size_little;
			}
		});

		mainHomeModelAdapter = new MainHomeModelFragment.MainHomeModelAdapter();
		mainHomeModelAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VHomeMainBean.VHomeMainOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VHomeMainBean.VHomeMainOneBean bean, int position)
			{
				StarDynamicActivity.actionStart(getContext(), bean.getDt_id());
			}
		});

		recyclerView.setAdapter(mainHomeModelAdapter);

		// 分割线
		View divideView = new View(getContext());
		divideView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 6)));
		divideView.setBackgroundResource(R.color.hokolGrayLight);
		mainHomeModelAdapter.addHeadView(divideView);

		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_home_model);
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
		WHomeMainBean wHomeMainBean = new WHomeMainBean(HttpEnum.UserTag.Model, refreshedNumber, DeleteConstant.defaultNumberLarge);
		XHttpUtil.doHomeMain(wHomeMainBean, new XHttpAdapter<VHomeMainBean>()
		{
			@Override
			public void onSuccess(VHomeMainBean vHomeMainBean)
			{
				if (null == vHomeMainBean.getList())
				{
					vHomeMainBean.setList(new ArrayList<VHomeMainBean.VHomeMainOneBean>());
				}

				if (vHomeMainBean.getList().size() == 0)
				{
					gridLayoutManager.setSpanCount(1);
				}
				else
				{
					gridLayoutManager.setSpanCount(COUNT_MODEL);
				}

				mainHomeModelAdapter.setDataList(vHomeMainBean.getList());

				refreshedNumber = mainHomeModelAdapter.dataSize();
				LogFileUtil.v("vHomeMainBean size = " + refreshedNumber);
			}
		});
	}

	@Override
	public void onAreaUpdate(String first, List<String> second)
	{

	}

	@Override
	public void onFilterUpdate(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
	{

	}

	private class MainHomeModelAdapter extends HeadFootRecyclerAdapter<VHomeMainBean.VHomeMainOneBean>
	{
		private OnRecyclerItemClickListener listener;

		public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener)
		{
			this.listener = listener;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_model;
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
						listener.onItemClick(viewHolder, sList.get(position), position);
					}
				}
			});

			ImageView imageView = viewHolder.get(R.id.iv_item_main_home_model);
			int width = (UIScreenUtil.getScreenWidth(getContext())) / COUNT_MODEL;
			UIResizeUtil.build().setWidth(width).setHeight(width).commit(imageView);

			Glide.with(getContext()).load(sList.get(position).getDt_img()).centerCrop()
					.placeholder(R.drawable.global_load_failed)
					.error(R.drawable.global_load_failed)
					.into(imageView);
		}

		@Override
		public int getEmptyItemRes()
		{
			return R.layout.fragment_main_home__empty;
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
		}
	}
}
