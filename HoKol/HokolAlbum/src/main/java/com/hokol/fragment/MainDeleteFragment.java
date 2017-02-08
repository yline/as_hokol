package com.hokol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hokol.base.common.BaseFragment;

/**
 * 用于测试
 * @author yline 2017/2/8 --> 14:05
 * @version 1.0.0
 */
public class MainDeleteFragment extends BaseFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return new LinearLayout(getContext());
	}
}
