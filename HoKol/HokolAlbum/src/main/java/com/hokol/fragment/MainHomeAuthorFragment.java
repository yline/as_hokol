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
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.viewhelper.MainHomeHelper;
import com.yline.base.BaseFragment;
import com.yline.http.XHttpAdapter;
import com.yline.log.LogFileUtil;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.callback.OnRecyclerItemClickListener;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainHomeAuthorFragment extends BaseFragment implements MainHomeFragment.OnHomeFilterCallback
{
	private static final int COUNT_AUTHOR = 3;

	private MainHomeAuthorAdapter mainHomeAuthorAdapter;

	private SuperSwipeRefreshLayout superRefreshLayout;

	private int refreshedNumber;

	private GridLayoutManager gridLayoutManager;

	private WHomeMainBean wHomeMainBean;

	public static MainHomeAuthorFragment newInstance()
	{
		MainHomeAuthorFragment fragment = new MainHomeAuthorFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_home_author, container, false);
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
		RecyclerView recycleView = (RecyclerView) view.findViewById(R.id.recycle_main_home_author);

		gridLayoutManager = new GridLayoutManager(getContext(), COUNT_AUTHOR);
		recycleView.setLayoutManager(gridLayoutManager);
		recycleView.addItemDecoration(new DefaultGridItemDecoration(getContext())
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

		mainHomeAuthorAdapter = new MainHomeAuthorAdapter();
		mainHomeAuthorAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VHomeMainBean.VHomeMainOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VHomeMainBean.VHomeMainOneBean bean, int position)
			{
				StarDynamicActivity.actionStart(getContext(), bean.getDt_id());
			}
		});

		recycleView.setAdapter(mainHomeAuthorAdapter);

		// 分割线
		View divideView = new View(getContext());
		divideView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 6)));
		divideView.setBackgroundResource(R.color.hokolGrayLight);
		mainHomeAuthorAdapter.addHeadView(divideView);

		superRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_home_author);
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
		wHomeMainBean = new WHomeMainBean(HttpEnum.UserTag.Author, 0, DeleteConstant.defaultNumberSuper);
		doRequest();
	}

	private void doRequest()
	{
		mainHomeAuthorAdapter.setShowEmpty(false);
		XHttpUtil.doHomeMain(wHomeMainBean, new XHttpAdapter<VHomeMainBean>()
		{
			@Override
			public void onSuccess(VHomeMainBean vHomeMainBean)
			{
				mainHomeAuthorAdapter.setShowEmpty(true);

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
					gridLayoutManager.setSpanCount(COUNT_AUTHOR);
				}

				mainHomeAuthorAdapter.setDataList(vHomeMainBean.getList());
				refreshedNumber = mainHomeAuthorAdapter.dataSize();

			}
		});
	}

	@Override
	public void onAreaUpdate(String firstCode, List<String> secondCodeList)
	{
		LogFileUtil.v("onAreaUpdate first = " + firstCode + ",second = " + secondCodeList.toString());

		refreshedNumber = 0;
		wHomeMainBean.setNum1(refreshedNumber);
		wHomeMainBean.setLength(DeleteConstant.defaultNumberSuper);

		wHomeMainBean.setP_code(firstCode);
		wHomeMainBean.setC_code(secondCodeList);

		doRequest();
	}

	@Override
	public void onFilterUpdate(MainHomeHelper.FilterSex typeSex, MainHomeHelper.FilterRecommend typeRecommend)
	{
		LogFileUtil.v("onAreaUpdate typeSex = " + typeSex + ",typeRecommend = " + typeRecommend);

		refreshedNumber = 0;
		wHomeMainBean.setNum1(refreshedNumber);
		wHomeMainBean.setLength(DeleteConstant.defaultNumberSuper);

		wHomeMainBean.setUser_sex(typeSex.getIndex());
		wHomeMainBean.setUser_adv(typeRecommend.getIndex());

		doRequest();
	}

	private class MainHomeAuthorAdapter extends WidgetRecyclerAdapter<VHomeMainBean.VHomeMainOneBean>
	{
		@Override
		public int getItemRes()
		{
			return R.layout.item_main_home_author;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			super.onBindViewHolder(viewHolder, position);

			ImageView imageView = viewHolder.get(R.id.iv_item_main_home_author);
			int width = (UIScreenUtil.getScreenWidth(getContext())) / COUNT_AUTHOR;
			UIResizeUtil.build().setWidth(width).setHeight(width).commit(imageView);

			Glide.with(getContext()).load(sList.get(position).getDt_img()).centerCrop().placeholder(R.drawable.global_load_failed).error(R.drawable.global_load_failed).into(imageView);
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			if (isShowEmpty)
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.VISIBLE);
				viewHolder.setText(R.id.tv_loading_cover, "没有找到相关信息，减少筛选条件试一试^_^");
			}
			else
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.INVISIBLE);
			}
		}
	}
}
