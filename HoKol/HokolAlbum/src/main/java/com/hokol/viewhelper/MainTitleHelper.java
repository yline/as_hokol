package com.hokol.viewhelper;

import android.view.View;
import android.widget.TextView;

import com.hokol.R;

/**
 * @author yline 2017/2/14 --> 14:29
 * @version 1.0.0
 */
public class MainTitleHelper
{
	private ViewHolder viewHolder = new ViewHolder();

	private OnTitleClickListener listener;

	public MainTitleHelper()
	{
	}

	public void initTitleView(View parentView)
	{
		initView(parentView);
		setViewClick();
	}

	public void setListener(OnTitleClickListener listener)
	{
		this.listener = listener;
	}

	private void initView(View view)
	{
		viewHolder.tvLeft = (TextView) view.findViewById(R.id.tv_title_left);
		viewHolder.tvCenter = (TextView) view.findViewById(R.id.tv_title_center);
		viewHolder.tvRight = (TextView) view.findViewById(R.id.tv_title_right);
	}

	private void setViewClick()
	{
		TitleClickListener titleListener = new TitleClickListener();

		viewHolder.tvLeft.setOnClickListener(titleListener);
		viewHolder.tvLeft.setTag(TITLE_TYPE.LEFT);
		viewHolder.tvCenter.setOnClickListener(titleListener);
		viewHolder.tvCenter.setTag(TITLE_TYPE.CENTER);
		viewHolder.tvRight.setOnClickListener(titleListener);
		viewHolder.tvRight.setTag(TITLE_TYPE.RIGHT);
	}

	private class TitleClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			TITLE_TYPE type = (TITLE_TYPE) v.getTag();
			if (null != listener)
			{
				listener.onTitleClick(type);
			}
		}
	}

	private static class ViewHolder
	{
		private TextView tvLeft, tvCenter, tvRight;
	}

	public enum TITLE_TYPE
	{
		LEFT, CENTER, RIGHT;
	}

	public interface OnTitleClickListener
	{
		/**
		 * title 点击回调
		 * @param type 点击的位置
		 */
		void onTitleClick(TITLE_TYPE type);
	}
}
