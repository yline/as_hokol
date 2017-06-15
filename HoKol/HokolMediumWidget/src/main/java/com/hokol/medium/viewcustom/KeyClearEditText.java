package com.hokol.medium.viewcustom;

import android.content.Context;
import android.util.AttributeSet;

import com.hokol.medium.widget.R;
import com.yline.view.text.ViewKeyClearEditText;

public class KeyClearEditText extends ViewKeyClearEditText
{
	public KeyClearEditText(Context context)
	{
		super(context);
	}

	public KeyClearEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public KeyClearEditText(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected int getKeyClearDrawable()
	{
		return R.drawable.widget_drawable_et_key_clear;
	}
}
