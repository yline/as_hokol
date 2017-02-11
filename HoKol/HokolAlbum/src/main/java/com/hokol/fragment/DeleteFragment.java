package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hokol.base.common.BaseFragment;

/**
 * 用于测试
 * @author yline 2017/2/8 --> 14:05
 * @version 1.0.0
 */
public class DeleteFragment extends BaseFragment
{
	private TextView tvShow;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(android.R.layout.simple_list_item_1, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
	}

	private void initView(View view)
	{
		tvShow = (TextView) view.findViewById(android.R.id.text1);
	}

	public void setText(String content)
	{
		if (null != tvShow)
		{
			tvShow.setText(content);
		}
	}
}
