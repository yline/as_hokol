package com.hokol.test.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hokol.medium.widget.recycler.HeadFootRecyclerAdapter;
import com.hokol.test.R;
import com.yline.base.BaseFragment;
import com.yline.utils.UIResizeUtil;
import com.yline.view.common.RecyclerViewHolder;

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

	protected RecyclerView addRecycleView()
	{
		RecyclerView recycleView = new RecyclerView(getContext());
		linearLayout.addView(recycleView);
		return recycleView;
	}

	protected HeadFootRecyclerAdapter getRecycleAdapter()
	{
		return new RecycleAdapter();
	}

	private class RecycleAdapter extends HeadFootRecyclerAdapter<String>
	{
		@Override
		public int getItemRes()
		{
			return android.R.layout.simple_list_item_1;
		}

		@Override
		public void setViewContent(RecyclerViewHolder viewHolder, int position)
		{
			TextView textView = viewHolder.get(android.R.id.text1);
			textView.setText(sList.get(position));
			textView.setBackgroundResource(android.R.color.holo_blue_light);
		}
	}
}
