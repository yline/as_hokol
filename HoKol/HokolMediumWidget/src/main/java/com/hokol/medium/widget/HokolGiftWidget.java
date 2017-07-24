package com.hokol.medium.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yline.view.recycler.adapter.CommonRecyclerAdapter;
import com.yline.view.recycler.holder.RecyclerViewHolder;

import java.util.Arrays;
import java.util.List;

public class HokolGiftWidget
{
	private PopupWindow popupWindow;

	private GiftAdapter giftAdapter;

	private OnSendClickListener onSendClickListener;

	private View.OnClickListener onRechargeClickListener;

	public HokolGiftWidget(Context context)
	{
		View view = LayoutInflater.from(context).inflate(getResourceId(), null);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_hokol_gift);
		recyclerView.setLayoutManager(new GridLayoutManager(context, 6));
		giftAdapter = new GiftAdapter();
		recyclerView.setAdapter(giftAdapter);

		view.findViewById(R.id.tv_hokol_gift).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onRechargeClickListener)
				{
					onRechargeClickListener.onClick(v);
				}
			}
		});
		view.findViewById(R.id.btn_hokol_gift_bean).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onSendClickListener && -1 != giftAdapter.getOldPosition())
				{
					int position = giftAdapter.getOldPosition();
					onSendClickListener.onSendClick(v, giftAdapter.getItem(position), position);
					popupWindow.dismiss();
				}
			}
		});

		if (null == popupWindow)
		{
			popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.setOutsideTouchable(true); // 点击外部消失
		}
	}

	public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener)
	{
		popupWindow.setOnDismissListener(onDismissListener);
	}

	public void showAsDropDown(View animView)
	{
		if (!popupWindow.isShowing())
		{
			popupWindow.showAsDropDown(animView);
		}
		else
		{
			popupWindow.dismiss();
		}
	}

	public void showAsDropDown(View animView, int xoff, int yoff)
	{
		if (!popupWindow.isShowing())
		{
			popupWindow.showAsDropDown(animView, xoff, yoff);
		}
		else
		{
			popupWindow.dismiss();
		}
	}

	public void showAtLocation(View animView, int gravity, int x, int y)
	{
		if (!popupWindow.isShowing())
		{
			popupWindow.showAtLocation(animView, gravity, x, y);
		}
		else
		{
			popupWindow.dismiss();
		}
	}

	public void setOnSendClickListener(OnSendClickListener onSendClickListener)
	{
		this.onSendClickListener = onSendClickListener;
	}

	public void setOnRechargeClickListener(View.OnClickListener onRechargeClickListener)
	{
		this.onRechargeClickListener = onRechargeClickListener;
	}

	public void setDataList(List<Integer> tList)
	{
		giftAdapter.setDataList(tList);
		giftAdapter.initSelect();
	}

	public boolean isShowing()
	{
		if (null != popupWindow)
		{
			return popupWindow.isShowing();
		}
		return false;
	}

	public void setPopupWindowBeanNum(float num)
	{
		if (null != popupWindow)
		{
			TextView textView = (TextView) popupWindow.getContentView().findViewById(R.id.tv_widget_hokol_gift_num);
			textView.setText(String.format("%3.2f", num));
		}
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 重写 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	protected int getResourceId()
	{
		return R.layout.widget_hokol_gift;
	}

	protected int getItemResourceId()
	{
		return R.layout.widget_item_hokol_gift;
	}

	public interface OnSendClickListener<T>
	{
		void onSendClick(View v, T t, int position);
	}

	private class GiftAdapter extends CommonRecyclerAdapter<Integer>
	{
		private boolean[] isSelected;

		private int oldPosition = -1;

		public void initSelect()
		{
			isSelected = new boolean[getItemCount()];
			Arrays.fill(isSelected, false);
		}

		public int getOldPosition()
		{
			return oldPosition;
		}

		@Override
		public int getItemRes()
		{
			return getItemResourceId();
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, final int position)
		{
			if (isSelected[position])
			{
				holder.get(R.id.ll_item_hokol_gift).setBackgroundResource(R.drawable.widget_shape_solid_black_stroke_redhokol);
			}
			else
			{
				holder.get(R.id.ll_item_hokol_gift).setBackgroundResource(R.drawable.widget_shape_solid_transparent);
			}

			holder.setText(R.id.tv_item_hokol_gift, String.format("%d豆", sList.get(position)));
			holder.getItemView().setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (oldPosition == position)
					{
						return;
					}

					isSelected[position] = true;
					notifyItemChanged(position);
					if (-1 != oldPosition)
					{
						isSelected[oldPosition] = false;
						notifyItemChanged(oldPosition);
					}

					oldPosition = position;
					if (-1 != oldPosition)
					{
						popupWindow.getContentView().findViewById(R.id.btn_hokol_gift_bean).setBackgroundResource(R.drawable.widget_shape_rectangle_solid_redhokol);
					}
				}
			});
		}
	}
}
