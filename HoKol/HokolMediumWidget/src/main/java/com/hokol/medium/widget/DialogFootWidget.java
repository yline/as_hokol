package com.hokol.medium.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yline.common.ViewHolder;

import java.util.List;

/**
 * 底部出现弹框,仿IOS
 *
 * @author yline 2017/4/11 -- 10:54
 * @version 1.0.0
 */
public class DialogFootWidget
{
	private Dialog dialog;

	private OnSelectedListener onSelectedListener;

	private ViewHolder viewHolder;

	/**
	 * 当前最多只支持4个
	 *
	 * @param context
	 * @param dataList 数据是从下往上排序
	 */
	public DialogFootWidget(Context context, @NonNull List<String> dataList)
	{
		View view = LayoutInflater.from(context).inflate(getResourceId(), null);
		viewHolder = new ViewHolder(view);
		initData(dataList);

		dialog = new Dialog(context, R.style.AppDialog_Default);// android.R.style.Theme_Holo_Light_Dialog_NoActionBar
		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(true);

		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);

		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
		lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		dialog.onWindowAttributesChanged(lp);
	}

	/**
	 * @param dataList 数据是从下往上排序
	 */
	private void initData(final List<String> dataList)
	{
		int dataLength = dataList.size();
		if (dataLength > 4)
		{
			throw new IllegalArgumentException("dialog only support to 4");
		}

		if (dataLength > 3)
		{
			viewHolder.setText(R.id.btn_dialog_foot_one, dataList.get(3)).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onSelectedListener)
					{
						onSelectedListener.onOptionSelected(dialog, 3, dataList.get(3));
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.btn_dialog_foot_one).setVisibility(View.GONE);
			viewHolder.get(R.id.view_dialog_foot_one).setVisibility(View.GONE);
		}

		if (dataLength > 2)
		{
			viewHolder.setText(R.id.btn_dialog_foot_two, dataList.get(2)).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onSelectedListener)
					{
						onSelectedListener.onOptionSelected(dialog, 2, dataList.get(2));
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.btn_dialog_foot_two).setVisibility(View.GONE);
			viewHolder.get(R.id.view_dialog_foot_two).setVisibility(View.GONE);
		}

		if (dataLength > 1)
		{
			viewHolder.setText(R.id.btn_dialog_foot_three, dataList.get(1)).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onSelectedListener)
					{
						onSelectedListener.onOptionSelected(dialog, 1, dataList.get(1));
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.btn_dialog_foot_three).setVisibility(View.GONE);
			viewHolder.get(R.id.view_dialog_foot_three).setVisibility(View.GONE);
		}

		if (dataLength > 0)
		{
			viewHolder.setText(R.id.btn_dialog_foot_four, dataList.get(0)).setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != onSelectedListener)
					{
						onSelectedListener.onOptionSelected(dialog, 0, dataList.get(0));
					}
				}
			});
		}
		else
		{
			viewHolder.get(R.id.btn_dialog_foot_four).setVisibility(View.GONE);
		}

		viewHolder.get(R.id.btn_dialog_foot_cancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (null != onSelectedListener)
				{
					onSelectedListener.onCancelSelected(dialog);
				}
			}
		});
	}

	public void setOnSelectedListener(OnSelectedListener onSelectedListener)
	{
		this.onSelectedListener = onSelectedListener;
	}

	public void show()
	{
		dialog.show();
	}

	public void show(OnSelectedListener onSelectedListener)
	{
		this.onSelectedListener = onSelectedListener;
		dialog.show();
	}

	public void show(OnSelectedListener onSelectedListener, DialogInterface.OnDismissListener dismissListener)
	{
		if (null != dismissListener)
		{
			dialog.setOnDismissListener(dismissListener);
		}
		dialog.show();
	}

	public void show(OnSelectedListener onSelectedListener, DialogInterface.OnDismissListener dismissListener, DialogInterface.OnShowListener showListener)
	{
		if (null != dismissListener)
		{
			dialog.setOnDismissListener(dismissListener);
		}
		if (null != showListener)
		{
			dialog.setOnShowListener(showListener);
		}
		dialog.show();
	}

	public ViewHolder getViewHolder()
	{
		return viewHolder;
	}

	public interface OnSelectedListener
	{
		public void onCancelSelected(DialogInterface dialog);

		/**
		 * This method will be invoked when the dialog is dismissed.
		 *
		 * @param dialog The dialog that was dismissed will be passed into the
		 *               method.
		 */
		public void onOptionSelected(DialogInterface dialog, int position, String content);
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 重写 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	protected int getResourceId()
	{
		return R.layout.widget_dialog_foot_radius;
	}
}
