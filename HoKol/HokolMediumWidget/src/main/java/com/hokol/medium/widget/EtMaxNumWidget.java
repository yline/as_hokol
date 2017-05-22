package com.hokol.medium.widget;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 单独编辑某个选项
 *
 * @author yline 2017/4/15 -- 14:35
 * @version 1.0.0
 */
public class EtMaxNumWidget
{
	private EditText editText;

	private TextView textView;

	public EtMaxNumWidget(@NonNull EditText editText)
	{
		this(editText, null);
	}

	public EtMaxNumWidget(@NonNull EditText editText, TextView textView)
	{
		this.editText = editText;
		this.textView = textView;
	}

	public void init(String oldContent)
	{
		editText.setText(oldContent);
		editText.setSelection(oldContent.length());

		if (null != textView)
		{
			int remainderLength = getMaxInputSize() - oldContent.length();
			if (remainderLength > 0)
			{
				textView.setText(remainderLength + "");
			}

			editText.addTextChangedListener(new TextWatcher()
			{
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after)
				{

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count)
				{
					int remainderLength = getMaxInputSize() - start - count;
					if (remainderLength > 0)
					{
						textView.setText(remainderLength + "");
					}
				}

				@Override
				public void afterTextChanged(Editable s)
				{

				}
			});
		}
	}

	public String getEditResult()
	{
		if (null != editText)
		{
			return editText.getText().toString();
		}
		return "";
	}

	/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 提供重写的方法 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */
	protected int getMaxInputSize()
	{
		return -1;
	}
}
