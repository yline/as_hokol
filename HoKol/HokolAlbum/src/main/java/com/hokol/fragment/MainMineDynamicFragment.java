package com.hokol.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.activity.StarDynamicActivity;
import com.hokol.activity.StarInfoActivity;
import com.hokol.application.AppStateManager;
import com.hokol.application.DeleteConstant;
import com.hokol.application.IApplication;
import com.hokol.medium.callback.OnRecyclerDeleteCallback;
import com.hokol.medium.http.XHttpUtil;
import com.hokol.medium.http.bean.VDynamicUserAllBean;
import com.hokol.medium.http.bean.WDynamicDeleteBean;
import com.hokol.medium.http.bean.WDynamicUserAllBean;
import com.hokol.medium.http.hokol.HokolAdapter;
import com.hokol.medium.viewcustom.SuperSwipeRefreshLayout;
import com.hokol.medium.widget.DialogFootWidget;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
import com.hokol.util.IntentUtil;
import com.hokol.viewhelper.MainCareHelper;
import com.yline.application.SDKManager;
import com.yline.base.BaseFragment;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.dialog.ViewDialogFoot;
import com.yline.view.pop.ViewDeleteMenu;
import com.yline.view.recycler.adapter.HeadFootRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Arrays;
import java.util.List;

public class MainMineDynamicFragment extends BaseFragment
{
	private DynamicRecycleAdapter recyclerAdapter;

	private SuperSwipeRefreshLayout superSwipeRefreshLayout;

	private WDynamicUserAllBean wDynamicUserAllBean;

	private int dynamicRefreshNumber;

	private String userId;

	public static MainMineDynamicFragment newInstance()
	{
		Bundle args = new Bundle();

		MainMineDynamicFragment fragment = new MainMineDynamicFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_mine_dynamic, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	@Override
	public void onStart()
	{
		super.onStart();
		initData();
	}

	private void initView(View view)
	{
		// RecyclerView 内容
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_main_mine_dynamic);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(getContext())
		{
			@Override
			protected int getHeadNumber()
			{
				return 1;
			}

			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}
		});

		recyclerAdapter = new DynamicRecycleAdapter();
		recyclerAdapter.setOnCareRecycleClickListener(new MainCareHelper.OnCareRecycleClickListener<VDynamicUserAllBean.VDynamicUserAllOneBean>()
		{
			@Override
			public void onAvatarClick(RecyclerViewHolder viewHolder, VDynamicUserAllBean.VDynamicUserAllOneBean bean, int position)
			{
				if (!TextUtils.isEmpty(bean.getUser_id()))
				{
					StarInfoActivity.actionStart(getContext(), bean.getUser_id());
				}
			}

			@Override
			public void onPictureClick(RecyclerViewHolder viewHolder, VDynamicUserAllBean.VDynamicUserAllOneBean bean, int position)
			{
				if (!TextUtils.isEmpty(bean.getDt_id()))
				{
					StarDynamicActivity.actionStart(getContext(), bean.getDt_id());
				}
			}
		});
		recyclerAdapter.setOnRecyclerDeleteCallback(new OnRecyclerDeleteCallback<VDynamicUserAllBean.VDynamicUserAllOneBean>()
		{
			@Override
			public void onDelete(RecyclerViewHolder viewHolder, VDynamicUserAllBean.VDynamicUserAllOneBean dynamicBean, final int position)
			{
				XHttpUtil.doDynamicDelete(new WDynamicDeleteBean(dynamicBean.getDt_id(), dynamicBean.getUser_id()), new HokolAdapter<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						recyclerAdapter.remove(position);
					}
				});
			}
		});
		recyclerView.setAdapter(recyclerAdapter);

		// 头部
		initRecyclerHeadView(recyclerAdapter);

		// 刷新
		superSwipeRefreshLayout = (SuperSwipeRefreshLayout) view.findViewById(R.id.super_swipe_main_mine_dynamic);
		superSwipeRefreshLayout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("加载结束");
						superSwipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});
		superSwipeRefreshLayout.setOnLoadListener(new SuperSwipeRefreshLayout.OnSwipeListener()
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
						IApplication.toast("加载结束");
						superSwipeRefreshLayout.setLoadMore(false);
					}
				}, 3000);
			}
		});
	}

	private void initData()
	{
		userId = AppStateManager.getInstance().getUserLoginId(getContext());
		if (TextUtils.isEmpty(userId))
		{
			recyclerAdapter.setShowEmpty(true);
		}
		else
		{
			wDynamicUserAllBean = new WDynamicUserAllBean(userId, 0, DeleteConstant.defaultNumberNormal);
			recyclerAdapter.setShowEmpty(false);
			XHttpUtil.doDynamicUserAll(wDynamicUserAllBean, new HokolAdapter<VDynamicUserAllBean>()
			{
				@Override
				public void onSuccess(VDynamicUserAllBean vDynamicUserAllBean)
				{
					recyclerAdapter.setShowEmpty(true);
					List<VDynamicUserAllBean.VDynamicUserAllOneBean> result = vDynamicUserAllBean.getList();
					if (null != result)
					{
						dynamicRefreshNumber = result.size();
						recyclerAdapter.setDataList(result);
					}
				}
			});
		}
	}

	/**
	 * 初始化 Recycler 头部
	 *
	 * @param wrapperAdapter
	 */
	private void initRecyclerHeadView(HeadFootRecyclerAdapter wrapperAdapter)
	{
		View cameraView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_mine_dynamic_camera, null);
		cameraView.findViewById(R.id.rl_main_mine_dynamic_camera).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (TextUtils.isEmpty(userId))
				{
					SDKManager.toast("亲, 请先登录");
					return;
				}

				DialogFootWidget dialogFootWidget = new DialogFootWidget(getContext(), Arrays.asList("从手机相册选择", "拍照"));
				dialogFootWidget.setOnSelectedListener(new ViewDialogFoot.OnSelectedListener()
				{
					@Override
					public void onCancelSelected(DialogInterface dialog)
					{
						dialog.dismiss();
					}

					@Override
					public void onOptionSelected(DialogInterface dialog, int position, String content)
					{
						if (content.equals("拍照"))
						{
							IntentUtil.openCamera(MainMineDynamicFragment.this, MainMineFragment.KeyDynamicFileName, MainMineFragment.KeyDynamicCameraCode);
						}
						else // 相册
						{
							IntentUtil.openAlbum(MainMineDynamicFragment.this, MainMineFragment.KeyDynamicAlbumCode);
						}
						dialog.dismiss();
					}
				});
				dialogFootWidget.show();
			}
		});
		wrapperAdapter.addHeadView(cameraView);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		getParentFragment().onActivityResult(requestCode, resultCode, data);
	}

	private class DynamicRecycleAdapter extends WidgetRecyclerAdapter<VDynamicUserAllBean.VDynamicUserAllOneBean>
	{
		private MainCareHelper.OnCareRecycleClickListener<VDynamicUserAllBean.VDynamicUserAllOneBean> onCareRecycleClickListener;

		private OnRecyclerDeleteCallback<VDynamicUserAllBean.VDynamicUserAllOneBean> onRecyclerDeleteCallback;

		public void setOnCareRecycleClickListener(MainCareHelper.OnCareRecycleClickListener<VDynamicUserAllBean.VDynamicUserAllOneBean> onCareRecycleClickListener)
		{
			this.onCareRecycleClickListener = onCareRecycleClickListener;
		}

		public void setOnRecyclerDeleteCallback(OnRecyclerDeleteCallback<VDynamicUserAllBean.VDynamicUserAllOneBean> onRecyclerDeleteCallback)
		{
			this.onRecyclerDeleteCallback = onRecyclerDeleteCallback;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_mine_dynamic;
		}

		@Override
		public void onBindViewHolder(final RecyclerViewHolder viewHolder, final int position)
		{
			// super.onBindViewHolder(viewHolder, position);
			VDynamicUserAllBean.VDynamicUserAllOneBean dynamicBean = sList.get(position);

			// 图片内容
			ImageView contentImageView = viewHolder.get(R.id.iv_item_main_mine_dynamic_content);
			int width = UIScreenUtil.getScreenWidth(getContext()) - UIScreenUtil.dp2px(getContext(), 10 + 10) * 7 / 8;
			UIResizeUtil.build().setWidth(width).setHeight(width).commit(contentImageView);
			Glide.with(MainMineDynamicFragment.this).load(dynamicBean.getDt_img()).error(R.drawable.global_load_failed).into(contentImageView);

			// 头像
			ImageView avatarImageView = viewHolder.get(R.id.circle_item_main_mine_dynamic_avatar);
			Glide.with(getContext()).load(dynamicBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarImageView);

			// 昵称
			viewHolder.setText(R.id.tv_item_main_mine_dynamic_name, dynamicBean.getUser_nickname());

			// 时间
			String timeStr = HokolTimeConvertUtil.stamp2FormatTime(dynamicBean.getDt_pub_time() * 1000);
			viewHolder.setText(R.id.tv_item_main_mine_dynamic_time, timeStr);

			// 地点
			if (null != dynamicBean.getCity())
			{
				viewHolder.setText(R.id.tv_item_main_mine_dynamic_location, dynamicBean.getCity().get(0));
			}

			// 动态文字
			viewHolder.setText(R.id.tv_item_main_mine_dynamic_scrap, dynamicBean.getDt_content());

			// 礼物个数
			viewHolder.setText(R.id.tv_item_main_dynamic_coin, dynamicBean.getUser_coin() + "");

			// 点赞个数
			viewHolder.setText(R.id.tv_item_main_dynamic_laud, dynamicBean.getDt_total_zan() + "");

			// 头部点击事件
			viewHolder.setOnClickListener(R.id.circle_item_main_mine_dynamic_avatar, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onCareRecycleClickListener)
					{
						onCareRecycleClickListener.onAvatarClick(viewHolder, sList.get(position), position);
					}
				}
			});
			// 内容点击事件
			viewHolder.setOnClickListener(R.id.iv_item_main_mine_dynamic_content, new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onCareRecycleClickListener)
					{
						onCareRecycleClickListener.onPictureClick(viewHolder, sList.get(position), position);
					}
				}
			});
			// 长按点击事件； 有点击事件，则不会显示
			ViewDeleteMenu widgetDeleteMenu = new ViewDeleteMenu(getContext());
			widgetDeleteMenu.setOnWidgetListener(new ViewDeleteMenu.OnWidgetListener()
			{
				@Override
				public void onOptionSelected(View view, int index, String content)
				{
					VDynamicUserAllBean.VDynamicUserAllOneBean dynamicBean = recyclerAdapter.getItem(position);
					if (content.equals("删除") && null != onRecyclerDeleteCallback)
					{
						onRecyclerDeleteCallback.onDelete(viewHolder, dynamicBean, position);
					}
				}
			});
			widgetDeleteMenu.showAtLocation(Arrays.asList("删除"), viewHolder.getItemView());
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			if (isShowEmpty)
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.VISIBLE);
				boolean isUserLogin = AppStateManager.getInstance().isUserLogin(getContext());
				if (isUserLogin)
				{
					viewHolder.setText(R.id.tv_loading_cover, "快去发布动态吧");
				}
				else
				{
					viewHolder.setText(R.id.tv_loading_cover, "还没登陆哦，亲");
				}
			}
			else
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.INVISIBLE);
			}
		}
	}
}
