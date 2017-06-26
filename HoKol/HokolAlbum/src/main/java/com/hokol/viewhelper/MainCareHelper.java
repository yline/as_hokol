package com.hokol.viewhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hokol.R;
import com.hokol.application.AppStateManager;
import com.hokol.medium.http.bean.VDynamicCareBean;
import com.hokol.medium.widget.recycler.DefaultLinearItemDecoration;
import com.hokol.medium.widget.recycler.WidgetRecyclerAdapter;
import com.hokol.util.HokolTimeConvertUtil;
import com.yline.utils.UIResizeUtil;
import com.yline.utils.UIScreenUtil;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.List;

public class MainCareHelper
{
	private RecycleAdapter recyclerAdapter;

	private Context sContext;

	public MainCareHelper(Context context)
	{
		this.sContext = context;
	}

	/**
	 * 初始化Recycle控件
	 *
	 * @param recyclerView
	 */
	public void initRecycleView(RecyclerView recyclerView)
	{
		recyclerView.setLayoutManager(new LinearLayoutManager(sContext));
		recyclerView.addItemDecoration(new DefaultLinearItemDecoration(sContext)
		{
			@Override
			protected int getDivideResourceId()
			{
				return R.drawable.widget_solid_graylight_size_medium;
			}
		});

		recyclerAdapter = new RecycleAdapter();
		recyclerView.setAdapter(recyclerAdapter);
	}

	public void setOnRecycleItemClickListener(OnCareRecycleClickListener listener)
	{
		recyclerAdapter.setOnCareRecycleClickListener(listener);
	}

	public void setOnEmptyBtnClickListener(View.OnClickListener clickListener)
	{
		recyclerAdapter.setOnEmptyBtnClickListener(clickListener);
	}

	/**
	 * @param isShowEmpty 是否显示 空白页内容
	 */
	public void updateRecyclerEmptyState(boolean isShowEmpty)
	{
		recyclerAdapter.setShowEmpty(isShowEmpty);
	}

	public void setRecycleData(List<VDynamicCareBean> dataList)
	{
		recyclerAdapter.setDataList(dataList);
	}

	private class RecycleAdapter extends WidgetRecyclerAdapter<VDynamicCareBean>
	{
		private View.OnClickListener onEmptyBtnClickListener;

		private OnCareRecycleClickListener onCareRecycleClickListener;

		public void setOnEmptyBtnClickListener(View.OnClickListener onEmptyBtnClickListener)
		{
			this.onEmptyBtnClickListener = onEmptyBtnClickListener;
		}

		public void setOnCareRecycleClickListener(OnCareRecycleClickListener onCareRecycleClickListener)
		{
			this.onCareRecycleClickListener = onCareRecycleClickListener;
		}

		@Override
		public int getItemRes()
		{
			return R.layout.item_main_care;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder viewHolder, final int position)
		{
			VDynamicCareBean careBean = sList.get(position);

			// 头像
			ImageView avatarView = viewHolder.get(R.id.circle_item_main_care_avatar);
			Glide.with(sContext).load(careBean.getUser_logo()).error(R.drawable.global_load_failed).into(avatarView);

			// 昵称
			viewHolder.setText(R.id.tv_item_main_care_name, careBean.getUser_nickname());

			// 时间
			String timeStr = HokolTimeConvertUtil.stamp2FormatTime(careBean.getDt_pub_time() * 1000);
			viewHolder.setText(R.id.tv_item_main_care_time, timeStr);

			// 地点
			if (null != careBean.getCity())
			{
				viewHolder.setText(R.id.tv_item_main_care_location, careBean.getCity().get(0));
			}

			// 图片内容
			ImageView contentView = viewHolder.get(R.id.iv_item_main_care_content);
			int width = UIScreenUtil.getScreenWidth(sContext) * 7 / 8;
			UIResizeUtil.build().setWidth(width).setHeight(width).commit(contentView);
			Glide.with(sContext).load(careBean.getDt_img()).error(R.drawable.global_load_failed).into(contentView);

			// 动态文字
			viewHolder.setText(R.id.tv_item_main_care_scrap, careBean.getDt_content());

			// 礼物个数
			viewHolder.setText(R.id.tv_item_main_care_coin, careBean.getUser_coin() + "");

			// 点赞个数
			viewHolder.setText(R.id.tv_item_main_care_laud, careBean.getDt_total_zan() + "");

			// 点击事件
			viewHolder.get(R.id.rl_item_main_care).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onCareRecycleClickListener)
					{
						onCareRecycleClickListener.onAvatarClick(sList.get(position));
					}
				}
			});
			
			viewHolder.get(R.id.iv_item_main_care_content).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onCareRecycleClickListener)
					{
						onCareRecycleClickListener.onPictureClick(sList.get(position));
					}
				}
			});
		}

		@Override
		public int getEmptyItemRes()
		{
			return R.layout.widget_recycler_load_error_nomal;
		}

		@Override
		public void onBindEmptyViewHolder(RecyclerViewHolder viewHolder, int position)
		{
			if (isShowEmpty)
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.VISIBLE);

				boolean isLogin = AppStateManager.getInstance().isUserLogin(sContext);
				if (isLogin)
				{
					viewHolder.setText(R.id.tv_loading_cover, "您还没有关注的对象哦~");
					viewHolder.setText(R.id.btn_loading_cover, "去主页看看");
				}
				else
				{
					viewHolder.setText(R.id.tv_loading_cover, "登陆后才能看到你喜欢的哦~");
					viewHolder.setText(R.id.btn_loading_cover, "立即登录");
				}

				viewHolder.setOnClickListener(R.id.btn_loading_cover, new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (null != onEmptyBtnClickListener)
						{
							onEmptyBtnClickListener.onClick(v);
						}
					}
				});
			}
			else
			{
				viewHolder.get(R.id.rl_loading_cover).setVisibility(View.INVISIBLE);
			}
		}
	}

	public interface OnCareRecycleClickListener
	{
		/**
		 * 点击头像
		 *
		 * @param bean
		 */
		void onAvatarClick(VDynamicCareBean bean);

		/**
		 * 点击图片内容
		 *
		 * @param bean
		 */
		void onPictureClick(VDynamicCareBean bean);
	}
}
