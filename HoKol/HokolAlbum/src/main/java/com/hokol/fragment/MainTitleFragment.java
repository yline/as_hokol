package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;

public class MainTitleFragment extends BaseFragment
{
	private ViewHolder viewHolder = new ViewHolder();

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_main_title, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		setViewClick();
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
			if (getActivity() instanceof OnTitleClickListener)
			{
				((OnTitleClickListener) getActivity()).onTitleClick(type);
			}
		}
	}

	private class ViewHolder
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
