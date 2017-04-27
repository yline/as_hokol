package com.hokol.test.test;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.NumberPicker;

import com.hokol.test.R;
import com.yline.base.BaseAppCompatActivity;
import com.yline.log.LogFileUtil;

public class SomeTestActivity extends BaseAppCompatActivity
{
	private NumberPicker numberPicker;

	String[] stringList = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天枰座", "天蝎座", "射手座", "摩羯座"};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_some_test);

		numberPicker = (NumberPicker) findViewById(R.id.number_picker);
		numberPicker.setMinValue(0);
		numberPicker.setMaxValue(11);
		numberPicker.setDisplayedValues(stringList);
		numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		// numberPicker.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
		new NumberPickerReflect()
				.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
				.commit(numberPicker);

		numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
		{
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal)
			{
				LogFileUtil.v("newVal = " + newVal + ", newVal = " + stringList[newVal]);
			}
		});
	}
}
