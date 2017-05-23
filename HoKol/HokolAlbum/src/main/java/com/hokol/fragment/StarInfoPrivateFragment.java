package com.hokol.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.StarDynamicActivity;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.http.bean.VHomeMainBean;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.recycler.DefaultGridItemDecoration;
import com.yline.base.BaseFragment;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.callback.OnRecyclerItemClickListener;
import com.yline.view.common.HeadFootRecyclerAdapter;
import com.yline.view.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StarInfoPrivateFragment extends BaseFragment
{
	private SuperSwipeRefreshLayout superRefreshLayout;

	private StarInfoPrivateAdapter starInfoPrivateAdapter;

	private RelativeLayout lockRelativeLayout;

	public static StarInfoPrivateFragment newInstance()
	{
		StarInfoPrivateFragment fragment = new StarInfoPrivateFragment();
		/*Bundle args = new Bundle();
		fragment.setArguments(args);*/
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
				return R.drawable.widget_solid_white_size_little;
			}
		});
		starInfoPrivateAdapter = new StarInfoPrivateAdapter();
		recyclerView.setAdapter(starInfoPrivateAdapter);

		View headView = new View(getContext());
		headView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIScreenUtil.dp2px(getContext(), 8)));
		headView.setBackgroundResource(R.color.hokolGrayLight);
		starInfoPrivateAdapter.addHeadView(headView);

		List<String> data = new ArrayList<>();
		for (int i = 0; i < 35; i++)
		{
			data.add(DeleteConstant.getUrlSquare());
		}
		starInfoPrivateAdapter.addAll(data);
		starInfoPrivateAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener<VHomeMainBean.VHomeMainOneBean>()
		{
			@Override
			public void onItemClick(RecyclerViewHolder viewHolder, VHomeMainBean.VHomeMainOneBean bean, int position)
			{
				StarDynamicActivity.actionStart(getContext(), bean.getDt_id());
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

		lockRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_star_info_private_lock);
		lockRelativeLayout.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 弹框, popWindow
				View contentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_star_info_pop_window, null);

				Dialog dialog = new Dialog(getContext(), R.style.Widget_Dialog_Default);
				dialog.setContentView(contentView);

				Window dialogWindow = dialog.getWindow();
				dialogWindow.setGravity(Gravity.BOTTOM);

				WindowManager.LayoutParams lp = dialogWindow.getAttributes();
				lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
				lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
				dialog.onWindowAttributesChanged(lp);

				dialog.setOnShowListener(new DialogInterface.OnShowListener()
				{
					@Override
					public void onShow(DialogInterface dialog)
					{
						IApplication.toast("点击弹框");
						// 延时200ms
						IApplication.getHandler().postDelayed(new Runnable()
						{
							@Override
							public void run()
							{
								lockRelativeLayout.setVisibility(View.GONE);
							}
						}, 200);
					}
				});
				dialog.setOnDismissListener(new DialogInterface.OnDismissListener()
				{
					@Override
					public void onDismiss(DialogInterface dialog)
					{
						lockRelativeLayout.setVisibility(View.VISIBLE);
					}
				});

				dialog.show();
			}
		});
	}

	private class StarInfoPrivateAdapter extends HeadFootRecyclerAdapter<String>
	{
		private final int border_square;

		private OnRecyclerItemClickListener listener;

		public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener)
		{
			this.listener = listener;
		}

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

			ImageView imageView = viewHolder.get(R.id.iv_item_star_info_dynamic);

			UIResizeUtil.build().setWidth(border_square).setHeight(border_square).commit(imageView);
			Glide.with(getContext()).load(sList.get(position)).into(imageView);
		}
	}
}
