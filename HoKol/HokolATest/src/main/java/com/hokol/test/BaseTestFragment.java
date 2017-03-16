package com.hokol.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hokol.base.adapter.CommonRecyclerAdapter;
import com.hokol.base.adapter.CommonRecyclerViewHolder;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIResizeUtil;

public abstract class BaseTestFragment extends BaseFragment
{
	protected LinearLayout linearLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		View view = inflater.inflate(getResourceId(), container, false);
		linearLayout = (LinearLayout) view.findViewById(R.id.ll_fragment_base);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		test();
	}

	/**
	 * 需要按照规定，包含ll_fragment_base控件
	 *
	 * @return
	 */
	protected int getResourceId()
	{
		return R.layout.fragment_test_base;
	}

	protected abstract void test();

	protected void addButton(String content, View.OnClickListener listener)
	{
		Button button = new Button(getContext());
		button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		UIResizeUtil.build().setBottomMargin(20).commit(button);
		button.setText(content);
		button.setOnClickListener(listener);
		linearLayout.addView(button);
	}

	protected EditText addEditText(String hintContent)
	{
		EditText editText = new EditText(getContext());
		editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		editText.setHint(hintContent);
		linearLayout.addView(editText);
		return editText;
	}

	private CommonRecyclerAdapter recycleAdapter;

	protected RecyclerView addRecycleView()
	{
		RecyclerView recycleView = new RecyclerView(getContext());
		recycleAdapter = new RecycleAdapter();
		recycleView.setAdapter(recycleAdapter);

		linearLayout.addView(recycleView);

		return recycleView;
	}

	protected CommonRecyclerAdapter getRecycleAdapter()
	{
		return recycleAdapter;
	}

	private class RecycleAdapter extends CommonRecyclerAdapter<String>
	{

		@Override
		public int getItemRes()
		{
			return android.R.layout.simple_list_item_1;
		}

		@Override
		public void setViewContent(CommonRecyclerViewHolder viewHolder, int position)
		{
			viewHolder.setText(android.R.id.text1, sList.get(position));
		}
	}
}
