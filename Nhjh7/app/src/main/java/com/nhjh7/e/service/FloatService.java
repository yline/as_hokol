package com.nhjh7.e.service;

import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhjh7.e.R;
import com.nhjh7.e.view.MultiScrollNumberLayout;
import com.yline.log.LogFileUtil;

import java.util.Random;

/**
 * 由于需要关闭Activity后,悬浮框依旧显示,因此还是需要开Service
 * @author yline 2017/2/7 --> 10:11
 * @version 1.0.0
 */
public class FloatService extends Service
{
	private static final int[] DEFAULT_COLOR = new int[]{R.color.def_blue, R.color.def_red,
			R.color.def_green, R.color.def_purple, R.color.def_orange};

	private WindowManager mWindowManager;

	private View mFloatView;

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();

		createWindowView();
	}

	private void createWindowView()
	{
		mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

		mFloatView = LayoutInflater.from(this.getApplication()).inflate(R.layout.window_float, null);
		initView(mFloatView);
		WindowManager.LayoutParams param = initRightWindowManagerParam();

		mWindowManager.addView(mFloatView, param);
	}

	/**
	 * 设置点击事件等操作
	 * @param view
	 */
	private void initView(View view)
	{
		ImageView ivRotate = (ImageView) view.findViewById(R.id.iv_float_rotate);

		// 旋转
		ObjectAnimator animate = ObjectAnimator.ofFloat(ivRotate, "rotation", 0f, 360f);
		animate.setDuration(3000); // 旋转速度
		animate.setInterpolator(new LinearInterpolator()); // 匀速旋转
		animate.setRepeatCount(-1); // never stop
		animate.start();

		MultiScrollNumberLayout multiScrollNumberLayout = (MultiScrollNumberLayout) view.findViewById(R.id.view_scroll_number);

		Random random = new Random();
		int number = random.nextInt(10000);
		int length = (number + "").length();
		LogFileUtil.v("number = " + number + ",length = " + length);

		multiScrollNumberLayout.setTextColors(generateColor(length));
		multiScrollNumberLayout.setNumber(number);

		TextView tvFloat = (TextView) view.findViewById(R.id.tv_float);
		tvFloat.setTextSize(30);
		tvFloat.setText("测试数据测试数据测试数据测试数据测试数据测试数据");
	}

	/**
	 * 设置 WindowManager参数
	 * @return
	 */
	private WindowManager.LayoutParams initRightWindowManagerParam()
	{
		WindowManager.LayoutParams params = new WindowManager.LayoutParams();

		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2)
		{
			// 可以避免悬浮框权限,但api18及以下无法点击
			params.type = WindowManager.LayoutParams.TYPE_TOAST;
		}
		else
		{
			// params.type = WindowManager.LayoutParams.TYPE_PHONE;
			params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		}

		params.format = android.graphics.PixelFormat.RGBA_8888;
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		params.flags = (0x40000 | params.flags);
		params.flags = (0x200 | params.flags);
		params.alpha = 1.0F; // 透明
		params.gravity = Gravity.TOP;
		params.x = Gravity.NO_GRAVITY;
		params.y = Gravity.NO_GRAVITY;

		params.height = WindowManager.LayoutParams.WRAP_CONTENT;

		return params;
	}

	private int[] generateColor(int n)
	{
		int[] result = new int[n];
		for (int i = 0; i < n; i++)
		{
			result[i] = DEFAULT_COLOR[i % DEFAULT_COLOR.length];
		}
		return result;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if (null != mWindowManager)
		{
			mWindowManager.removeView(mFloatView);
		}
	}

	/**
	 * 开启悬浮窗
	 * @param context
	 */
	public static void actionStart(Context context)
	{
		context.startService(new Intent(context, FloatService.class));
	}

	/**
	 * 关闭悬浮窗
	 * @param context
	 */
	public static void actionStop(Context context)
	{
		context.stopService(new Intent(context, FloatService.class));
	}
}
