package com.hokol.medium.widget.swiperefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.hokol.base.utils.UIScreenUtil;

public class CircleProgressView extends View implements Runnable
{
	private static final int PEROID = 16;// 绘制周期
	
	private Paint progressPaint;
	
	private Paint bgPaint;
	
	private int width;// view的高度
	
	private int height;// view的宽度
	
	private boolean isOnDraw = false;
	
	private boolean isRunning = false;
	
	private int startAngle = 0;
	
	private int speed = 8;
	
	private RectF ovalRect = null;
	
	private RectF bgRect = null;
	
	private int swipeAngle;
	
	private int progressColor = 0xffcccccc;
	
	private int circleBackgroundColor = 0xffffffff;
	
	private int shadowColor = 0xff999999;
	
	// 屏幕分辨率
	private float density = 1.0f;
	
	public CircleProgressView(Context context)
	{
		this(context, null);
	}
	
	public CircleProgressView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}
	
	public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		
		final DisplayMetrics metrics = getResources().getDisplayMetrics();
		density = metrics.density;

		width = UIScreenUtil.dp2px(context, 50);
		height = UIScreenUtil.dp2px(context, 50);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawArc(getBgRect(), 0, 360, false, createBgPaint());
		int index = startAngle / 360;
		if (index % 2 == 0)
		{
			swipeAngle = (startAngle % 720) / 2;
		}
		else
		{
			swipeAngle = 360 - (startAngle % 720) / 2;
		}
		canvas.drawArc(getOvalRect(), startAngle, swipeAngle, false, createPaint());
	}
	
	private RectF getBgRect()
	{
		/*width = getWidth();
		height = getHeight();*/
		if (bgRect == null)
		{
			int offset = (int) (density * 2);
			bgRect = new RectF(offset, offset, width - offset, height - offset);
		}
		return bgRect;
	}
	
	private RectF getOvalRect()
	{
		/*width = getWidth();
		height = getHeight();*/
		if (ovalRect == null)
		{
			int offset = (int) (density * 8);
			ovalRect = new RectF(offset, offset, width - offset, height - offset);
		}
		return ovalRect;
	}
	
	public void setProgressColor(int progressColor)
	{
		this.progressColor = progressColor;
	}
	
	public void setCircleBackgroundColor(int circleBackgroundColor)
	{
		this.circleBackgroundColor = circleBackgroundColor;
	}
	
	public void setShadowColor(int shadowColor)
	{
		this.shadowColor = shadowColor;
	}
	
	/**
	 * 根据画笔的颜色，创建画笔
	 *
	 * @return
	 */
	private Paint createPaint()
	{
		if (this.progressPaint == null)
		{
			progressPaint = new Paint();
			progressPaint.setStrokeWidth((int) (density * 3));
			progressPaint.setStyle(Paint.Style.STROKE);
			progressPaint.setAntiAlias(true);
		}
		progressPaint.setColor(progressColor);
		return progressPaint;
	}
	
	private Paint createBgPaint()
	{
		if (this.bgPaint == null)
		{
			bgPaint = new Paint();
			bgPaint.setColor(circleBackgroundColor);
			bgPaint.setStyle(Paint.Style.FILL);
			bgPaint.setAntiAlias(true);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			{
				this.setLayerType(LAYER_TYPE_SOFTWARE, bgPaint);
			}
			bgPaint.setShadowLayer(4.0f, 0.0f, 2.0f, shadowColor);
		}
		return bgPaint;
	}
	
	public void setPullDistance(int distance)
	{
		this.startAngle = distance * 2;
		postInvalidate();
	}
	
	@Override
	public void run()
	{
		while (isOnDraw)
		{
			isRunning = true;
			long startTime = System.currentTimeMillis();
			startAngle += speed;
			postInvalidate();
			long time = System.currentTimeMillis() - startTime;
			if (time < PEROID)
			{
				try
				{
					Thread.sleep(PEROID - time);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setOnDraw(boolean isOnDraw)
	{
		this.isOnDraw = isOnDraw;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}
	
	@Override
	protected void onDetachedFromWindow()
	{
		isOnDraw = false;
		super.onDetachedFromWindow();
	}
}