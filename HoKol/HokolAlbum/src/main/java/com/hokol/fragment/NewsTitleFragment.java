package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIResizeUtil;

public class NewsTitleFragment extends BaseFragment
{
	private ViewHolder viewHolder = new ViewHolder();

	private final static int LINE_WIDTH = UIResizeUtil.getDesignWidth() / 2;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_news_title, container, false);
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
		viewHolder.tvOne = (TextView) view.findViewById(R.id.tv_news_one);
		viewHolder.tvTwo = (TextView) view.findViewById(R.id.tv_news_two);

		viewHolder.llOne = (LinearLayout) view.findViewById(R.id.ll_news_one);
		viewHolder.llTwo = (LinearLayout) view.findViewById(R.id.ll_news_two);

		viewHolder.ivLine = (ImageView) view.findViewById(R.id.iv_news_line);
	}

	private void initData()
	{
		UIResizeUtil.build().setWidth(LINE_WIDTH).setLeftMargin(0 * LINE_WIDTH).commit(viewHolder.ivLine);
	}

	private class ViewHolder
	{
		private TextView tvOne, tvTwo;

		private LinearLayout llOne, llTwo;

		private ImageView ivLine;
	}
}
