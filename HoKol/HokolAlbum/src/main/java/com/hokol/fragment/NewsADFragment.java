package com.hokol.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hokol.R;
import com.hokol.base.common.BaseFragment;
import com.hokol.base.utils.UIResizeUtil;
import com.hokol.bean.NewsADBean;

public class NewsADFragment extends BaseFragment
{
	private ViewPager viewPager;

	private LinearLayout linearLayout;

	private NewsADBean params = new NewsADBean();

	private UserTouchState touchState;
	
	private ViewPagerAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_news_ad, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		initView(view);
		initPreData();
		initData();

		if (params.isAutoRecycle())
		{
			touchState = UserTouchState.OnAuto;
			initTimeThread();
		}
	}

	private void initView(View view)
	{
		viewPager = (ViewPager) view.findViewById(R.id.viewpager_news_ad);
		linearLayout = (LinearLayout) view.findViewById(R.id.ll_news_ad);
	}

	private void initPreData()
	{
		int[] mImageSrc = {R.drawable.delete_ad_img1, R.drawable.delete_ad_img2, R.drawable.delete_ad_img3, R.drawable.delete_ad_img4, R.drawable.delete_ad_img5,};
		params.setResInt(mImageSrc);
	}

	private void initData()
	{
		// 指示点
		initIndicatorPoint(getActivity(), linearLayout, params.getPointCount());
		selectIndicatorPoint(linearLayout, params.getPointStartPosition());
		// viewPager
		UIResizeUtil.build().setHeight(360).commit(viewPager);

		adapter = new ViewPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(params.getPointStartPosition());

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{
				position = params.isRecycle() ? position % params.getPointCount() : position;
				// 指示点
				selectIndicatorPoint(linearLayout, position);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				position = params.isRecycle() ? position % params.getPointCount() : position;
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});
	}

	private class ViewPagerAdapter extends PagerAdapter
	{

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return (view == object);
		}

		@Override
		public int getCount()
		{
			return params.isRecycle() ? params.getRecycleCount() : params.getPointCount();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			position = params.isRecycle() ? position % params.getPointCount() : position;

			View view = params.getViewRes(getActivity(), position);
			container.addView(view);

			// viewPager点击事件
			final int index = position;
			view.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					if (getActivity() instanceof onPagerClickListener)
					{
						((onPagerClickListener) getActivity()).onPagerClicked(v, index);
					}
				}
			});

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}

		@Override
		public void startUpdate(ViewGroup container)
		{
			super.startUpdate(container);
		}

		@Override
		public void finishUpdate(ViewGroup container)
		{
			super.finishUpdate(container);
			if (params.isRecycle())
			{
				int position = viewPager.getCurrentItem();
				if (position == 0)
				{
					viewPager.setCurrentItem(params.getPointCount(), false);
				}
				else if (position == params.getRecycleCount() - 2)
				{
					viewPager.setCurrentItem(position % params.getPointCount(), false);
				}
			}
		}
	}

	private Thread thread;

	private Handler handler;

	@SuppressLint("HandlerLeak")
	private void initTimeThread()
	{
		thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					try
					{ // 做一个暂缓
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
						handler.obtainMessage(-1, e + "").sendToTarget();
						return;
					}

					if (touchState == UserTouchState.OnAuto)
					{
						try
						{
							Thread.sleep(params.getTimeAutoRecycle());
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
							handler.obtainMessage(-1, e + "").sendToTarget();
							return;
						}
						// 快速滑动之后
						if (touchState == UserTouchState.OnAuto)
						{
							handler.obtainMessage(1, "休眠成功").sendToTarget();
						}
						else if (touchState == UserTouchState.OnMove)
						{
							// do nothing
						}
						else
						{ // UserTouchState.OnUp
							try
							{
								Thread.sleep(params.getTimeUpRecycle() - params.getTimeAutoRecycle() / 3);
								handler.obtainMessage(1, "休眠成功").sendToTarget();
							}
							catch (InterruptedException e)
							{
								e.printStackTrace();
								handler.obtainMessage(-1, e + "").sendToTarget();
							}
							touchState = UserTouchState.OnAuto;
						}
					}
					else if (touchState == UserTouchState.OnMove)
					{
						// do nothing
					}
					else
					{ // UserTouchState.OnUp
						try
						{
							Thread.sleep(params.getTimeUpRecycle());
							handler.obtainMessage(1, "休眠成功").sendToTarget();
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
							handler.obtainMessage(-1, e + "").sendToTarget();
						}
						touchState = UserTouchState.OnAuto;
					}
				}
			}
		});
		thread.start();

		viewPager.setOnTouchListener(new View.OnTouchListener()
		{

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						break;
					case MotionEvent.ACTION_MOVE:
						touchState = UserTouchState.OnMove;
						break;
					case MotionEvent.ACTION_UP:
						touchState = UserTouchState.OnUp;
						break;
				}
				return false;
			}
		});

		handler = new Handler()
		{

			@Override
			public void handleMessage(Message msg)
			{
				super.handleMessage(msg);

				switch (msg.what)
				{
					case -1:
						Log.e("log_carouseFragment", (String) msg.obj);
						break;
					case 1:
						if (params.isAutoRecycleToRight())
						{
							viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
						}
						else
						{
							viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
						}
						break;
				}
			}
		};
	}

	/**
	 * 添加指示点
	 * @param context   本Activity
	 * @param viewGroup 指示点父框体(此处LinearLayout)
	 * @param count     指示点个数
	 */
	private void initIndicatorPoint(Context context, ViewGroup viewGroup, int count)
	{
		for (int i = 0; i < count; i++)
		{
			View view = new View(context);
			UIResizeUtil.build().setIsWidthAdapter(false).setWidth(15).setHeight(15).setLeftMargin(15).commit(view);
			view.setBackgroundResource(params.getPointResId());
			viewGroup.addView(view);
		}
	}

	/**
	 * 指示点状态
	 * @param viewGroup 指示点父框体(此处LinearLayout)
	 * @param position  当前的指示点
	 */
	private void selectIndicatorPoint(ViewGroup viewGroup, int position)
	{
		for (int i = 0; i < viewGroup.getChildCount(); i++)
		{
			if (i == position)
			{
				viewGroup.getChildAt(i).setSelected(true);
				UIResizeUtil.build().setIsWidthAdapter(false).setWidth(20).setHeight(20).commit(viewGroup.getChildAt(i));
			}
			else
			{
				viewGroup.getChildAt(i).setSelected(false);
				UIResizeUtil.build().setIsWidthAdapter(false).setWidth(15).setHeight(15).commit(viewGroup.getChildAt(i));
			}
		}
	}

	private enum UserTouchState
	{
		/** 用户滑动 */
		OnMove,
		/** 用户松手 */
		OnUp,
		/** 自动播放 */
		OnAuto
	}

	/**
	 * viewPager 点击接口
	 */
	public interface onPagerClickListener
	{
		/**
		 * @param v        当前控件
		 * @param position 位置(0,开始)
		 */
		void onPagerClicked(View v, int position);
	}
}
