package com.hokol.viewhelper;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.hokol.R;
import com.yline.log.LogFileUtil;

import java.lang.reflect.Field;

public class UserInfoHelper
{
	private Context context;

	private Dialog constellationDialog;

	private NumberPicker numberPicker;

	private String[] constellationList = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天枰座", "天蝎座", "射手座", "摩羯座"};

	private int constellationSelectPosition = 0;

	public UserInfoHelper(Context context)
	{
		this.context = context;
	}

	public void init()
	{
		// 进行控件初始化
	}

	public void updateConstellation(DialogInterface.OnDismissListener listener)
	{
		if (null == constellationDialog)
		{
			constellationDialog = new Dialog(context, com.hokol.medium.widget.R.style.Widget_Dialog_Default);
			constellationDialog.setContentView(R.layout.dialog_user_info_constell);
			constellationDialog.setCanceledOnTouchOutside(true);

			Window dialogWindow = constellationDialog.getWindow();
			dialogWindow.setGravity(Gravity.BOTTOM);

			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
			lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			constellationDialog.onWindowAttributesChanged(lp);

			numberPicker = (NumberPicker) constellationDialog.findViewById(R.id.number_picker);
			numberPicker.setDisplayedValues(constellationList);
			numberPicker.setMinValue(0);
			numberPicker.setMaxValue(11);
			numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

			ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(context, R.color.hokolGrayLight));
			Rect defaultRect = colorDrawable.getBounds();
			colorDrawable.setBounds(0, 0, defaultRect.width(), 2);

			updateNumberPickerStyle(colorDrawable, ContextCompat.getColor(context, R.color.hokolBlackLight));
			numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
			{
				@Override
				public void onValueChange(NumberPicker picker, int oldVal, int newVal)
				{
					constellationSelectPosition = newVal;
				}
			});

			constellationDialog.setOnShowListener(new DialogInterface.OnShowListener()
			{
				@Override
				public void onShow(DialogInterface dialog)
				{
					numberPicker.setValue(constellationSelectPosition);
				}
			});

			constellationDialog.setOnDismissListener(listener);
			constellationDialog.show();
		}
		else
		{
			if (!constellationDialog.isShowing())
			{
				constellationDialog.show();
			}
		}
	}

	public String getSelectContent()
	{
		return constellationList[constellationSelectPosition];
	}

	private void updateNumberPickerStyle(Drawable divideDrawable, @ColorInt int textColor)
	{
		Field[] pickerFields = NumberPicker.class.getDeclaredFields();

		String fieldName;
		for (Field pf : pickerFields)
		{
			try
			{
				fieldName = pf.getName();
				if (fieldName.equals("mSelectionDivider"))
				{
					pf.setAccessible(true);
					pf.set(numberPicker, divideDrawable);
				}
				else if (fieldName.equals("mInputText"))
				{
					pf.setAccessible(true);

					EditText editText = (EditText) pf.get(numberPicker);
					editText.setTextColor(textColor);
				}
				else if (fieldName.equals("mSelectorWheelPaint"))
				{
					pf.setAccessible(true);

					Paint paint = (Paint) pf.get(numberPicker);
					paint.setColor(textColor);
				}
			} catch (IllegalArgumentException e)
			{
				LogFileUtil.e("reflect", "IllegalArgumentException", e);
			} catch (Resources.NotFoundException e)
			{
				LogFileUtil.e("reflect", "NotFoundException", e);
			} catch (IllegalAccessException e)
			{
				LogFileUtil.e("reflect", "IllegalAccessException", e);
			}
		}
	}
}
