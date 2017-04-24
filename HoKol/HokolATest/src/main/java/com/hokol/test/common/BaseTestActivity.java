package com.hokol.test.common;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hokol.test.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.utils.UIResizeUtil;

public class BaseTestActivity extends BaseAppCompatActivity
{
	private LinearLayout linearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_base);
		linearLayout = (LinearLayout) findViewById(R.id.ll_base_content);
	}

	protected void addButton(String content, View.OnClickListener listener)
	{
		Button button = new Button(this);
		button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		UIResizeUtil.build().setBottomMargin(20).commit(button);
		button.setText(content);
		button.setOnClickListener(listener);
		linearLayout.addView(button);
	}

	protected EditText addEditText(String hintContent)
	{
		View itemView = LayoutInflater.from(this).inflate(R.layout.activity_test_base_edittext, null);

		TextView textView = (TextView) itemView.findViewById(R.id.tv_test_base);
		textView.setText(hintContent);

		EditText editText = (EditText) itemView.findViewById(R.id.et_test_base);
		editText.setHint(hintContent);

		linearLayout.addView(itemView);
		return editText;
	}

	protected EditText addEditText(String hintContent, String content)
	{
		View itemView = LayoutInflater.from(this).inflate(R.layout.activity_test_base_edittext, null);

		TextView textView = (TextView) itemView.findViewById(R.id.tv_test_base);
		textView.setText(hintContent);

		EditText editText = (EditText) itemView.findViewById(R.id.et_test_base);
		editText.setHint(hintContent);
		editText.setText(content);

		linearLayout.addView(itemView);
		return editText;
	}

	protected EditText addEditNumber(String hintContent)
	{
		View itemView = LayoutInflater.from(this).inflate(R.layout.activity_test_base_edittext, null);

		TextView textView = (TextView) itemView.findViewById(R.id.tv_test_base);
		textView.setText(hintContent);

		EditText editText = (EditText) itemView.findViewById(R.id.et_test_base);
		editText.setHint(hintContent);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);

		linearLayout.addView(itemView);
		return editText;
	}

	protected EditText addEditNumber(String hintContent, String content)
	{
		View itemView = LayoutInflater.from(this).inflate(R.layout.activity_test_base_edittext, null);

		TextView textView = (TextView) itemView.findViewById(R.id.tv_test_base);
		textView.setText(hintContent);

		EditText editText = (EditText) itemView.findViewById(R.id.et_test_base);
		editText.setHint(hintContent);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		editText.setText(content);
		
		linearLayout.addView(itemView);
		return editText;
	}

	protected int parseInt(TextView textView, int defaultInt)
	{
		String content = textView.getText().toString().trim();

		final int result;
		if (TextUtils.isEmpty(content))
		{
			result = defaultInt;
		}
		else
		{
			result = Integer.parseInt(content);
		}
		return result;
	}

	protected float parseFloat(TextView textView, int defaultInt)
	{
		String content = textView.getText().toString().trim();

		final float result;
		if (TextUtils.isEmpty(content))
		{
			result = defaultInt;
		}
		else
		{
			result = Float.parseFloat(content);
		}
		return result;
	}
}
