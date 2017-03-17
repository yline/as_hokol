package com.hokol.medium.widget.swiperefresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.hokol.base.log.LogFileUtil;
import com.hokol.base.utils.UIScreenUtil;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description 自定义CustomeSwipeRefreshLayout<br>
 * 支持下拉刷新和上拉加载更多<br>
 * 非侵入式，对原来的ListView、RecyclerView没有任何影响,用法和SwipeRefreshLayout类似。<br>
 * 可自定义头部View的样式，调用setHeaderView方法即可 <br>
 * 可自定义页尾View的样式，调用setFooterView方法即可 <br>
 * 支持RecyclerView，ListView，ScrollView，GridView等等。<br>
 * 被包含的View(RecyclerView,ListView etc.)可跟随手指的滑动而滑动<br>
 * 默认是跟随手指的滑动而滑动，也可以设置为不跟随：setTargetScrollWithLayout(false) 回调方法更多<br>
 * 比如：onRefresh() onPullDistance(int distance)和onPullEnable(boolean
 * enable)<br>
 * 开发人员可以根据下拉过程中distance的值做一系列动画。 <br>
 */
@SuppressLint("ClickableViewAccessibility")
public class SuperSwipeRefreshLayout extends ViewGroup
{
	private static final String LOG_TAG = "CustomSwipeRefreshLayout";

	private static final int INVALID_POINTER = -1;

	private static final float DRAG_RATE = .5f;

	private static final int SCALE_DOWN_DURATION = 150;

	private static final int ANIMATE_TO_TRIGGER_DURATION = 200;

	private static final int ANIMATE_TO_START_DURATION = 200;


	// SuperSwipeRefreshLayout内的目标View，比如RecyclerView,ListView,ScrollView,GridView and etc.
	private View mTarget;

	/* 下拉刷新[有新建,就代表有默认值] */
	private BaseSwipeRefreshAdapter refreshAdapter;

	/* 上拉加载[有新建,就代表有默认值] */
	private BaseSwipeRefreshAdapter loadAdapter;

	private boolean mRefreshing = false;

	private boolean mLoadMore = false;

	private int mCurrentTargetOffsetTop;

	private boolean mOriginalOffsetCalculated = false;

	private float mInitialMotionY;

	private boolean mIsBeingDragged;

	private int mActivePointerId = INVALID_POINTER;

	private boolean mReturningToStart;

	private static final int[] LAYOUT_ATTRS = new int[]{android.R.attr.enabled};

	private int mHeaderViewIndex = -1;

	private int mFooterViewIndex = -1;

	protected int mFrom;

	protected int mOriginalOffsetTop;

	private Animation mScaleAnimation;

	private Animation mScaleDownAnimation;

	private boolean mNotify;

	private int pushDistance = 0;

	/* ---------------------------------- 常量 ---------------------------------- */

	private static final int HEADER_VIEW_HEIGHT = 50;// HeaderView height (dp)

	private static final int FOOTER_VIEW_HEIGHT = 50;// HeaderView height (dp)

	private static final int DEFAULT_CIRCLE_TARGET = 64;

	private static final float DECELERATE_INTERPOLATION_FACTOR = 2f;

	private final int screenWidth;

	private final int headerViewHeight;

	private final int footerViewHeight;

	// 最后停顿时的偏移量px，与DEFAULT_CIRCLE_TARGET正比
	private final float totalDragOffset;

	// 表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件
	private final int touchSlop;

	private final int mediumAnimationDuration;

	/* ---------------------------------- 引用 ---------------------------------- */

	private final DecelerateInterpolator decelerateInterpolator;

	private HeadViewContainer mHeadViewContainer;

	private RelativeLayout mFooterViewContainer;

	public SuperSwipeRefreshLayout(Context context)
	{
		this(context, null);
	}

	public SuperSwipeRefreshLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		setWillNotDraw(false);
		decelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);

		final TypedArray a = context.obtainStyledAttributes(attrs, LAYOUT_ATTRS);
		setEnabled(a.getBoolean(0, true));
		a.recycle();

		/**
		 * getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件
		 */
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

		screenWidth = UIScreenUtil.getScreenWidth(context);
		headerViewHeight = UIScreenUtil.dp2px(context, HEADER_VIEW_HEIGHT);
		footerViewHeight = UIScreenUtil.dp2px(context, FOOTER_VIEW_HEIGHT);
		totalDragOffset = UIScreenUtil.dp2px(context, DEFAULT_CIRCLE_TARGET);

		mediumAnimationDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);

		createHeaderViewContainer(context);
		createFooterViewContainer();

		ViewCompat.setChildrenDrawingOrderEnabled(this, true);

		refreshAdapter = new CircleSwipeAdapter(context);
		setRefreshAdapter(refreshAdapter);
	}

	/**
	 * 创建头布局的容器
	 */
	private void createHeaderViewContainer(Context context)
	{
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth, headerViewHeight);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		mHeadViewContainer = new HeadViewContainer(context);
		mHeadViewContainer.setVisibility(View.GONE);
		addView(mHeadViewContainer, layoutParams);
	}

	/**
	 * 添加底部布局
	 */
	private void createFooterViewContainer()
	{
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, footerViewHeight);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		mFooterViewContainer = new RelativeLayout(getContext());
		mFooterViewContainer.setVisibility(View.GONE);
		addView(mFooterViewContainer, layoutParams);
	}

	/**
	 * 下拉时，超过距离之后，弹回来的动画监听器
	 */
	private AnimationListener mRefreshListener = new AnimationListener()
	{
		@Override
		public void onAnimationStart(Animation animation)
		{
		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{
		}

		@Override
		public void onAnimationEnd(Animation animation)
		{
			if (mRefreshing)
			{
				if (mNotify)
				{
					if (refreshAdapter != null)
					{
						refreshAdapter.animating();
					}
				}
			}
			else
			{
				mHeadViewContainer.setVisibility(View.GONE);
				setTargetOffsetTopAndBottom(mOriginalOffsetTop - mCurrentTargetOffsetTop, true);
			}
			mCurrentTargetOffsetTop = mHeadViewContainer.getTop();
		}
	};

	/**
	 * 下拉刷新
	 *
	 * @param refreshAdapter
	 */
	public void setRefreshAdapter(BaseSwipeRefreshAdapter refreshAdapter)
	{
		this.refreshAdapter = refreshAdapter;
		if (null != refreshAdapter)
		{
			View child = refreshAdapter.getView();

			if (null == child)
			{
				return;
			}

			if (null == mHeadViewContainer)
			{
				return;
			}

			mHeadViewContainer.removeAllViews();
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, headerViewHeight);
			layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			mHeadViewContainer.addView(child, layoutParams);

			mHeadViewContainer.setBackgroundResource(refreshAdapter.getBackgroundResource());
		}
	}

	/**
	 * 监听动画
	 *
	 * @param onRefreshListener
	 */
	public void setOnRefreshListener(OnRefreshListener onRefreshListener)
	{
		if (null != refreshAdapter)
		{
			refreshAdapter.setSwipeAnimatingListener(onRefreshListener);
		}
	}

	/**
	 * 上拉加载
	 *
	 * @param loadAdapter
	 */
	public void setLoadAdapter(BaseSwipeRefreshAdapter loadAdapter)
	{
		this.loadAdapter = loadAdapter;

		if (null != loadAdapter)
		{
			View child = loadAdapter.getView();


			if (child == null)
			{
				return;
			}
			if (mFooterViewContainer == null)
			{
				return;
			}
			mFooterViewContainer.removeAllViews();
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth, footerViewHeight);
			mFooterViewContainer.addView(child, layoutParams);
		}
	}

	/**
	 * 孩子节点绘制的顺序
	 *
	 * @param childCount
	 * @param i
	 * @return
	 */
	@Override
	protected int getChildDrawingOrder(int childCount, int i)
	{
		// 将新添加的View,放到最后绘制
		if (mHeaderViewIndex < 0 && mFooterViewIndex < 0)
		{
			return i;
		}
		if (i == childCount - 2)
		{
			return mHeaderViewIndex;
		}
		if (i == childCount - 1)
		{
			return mFooterViewIndex;
		}
		int bigIndex = mFooterViewIndex > mHeaderViewIndex ? mFooterViewIndex : mHeaderViewIndex;
		int smallIndex = mFooterViewIndex < mHeaderViewIndex ? mFooterViewIndex : mHeaderViewIndex;
		if (i >= smallIndex && i < bigIndex - 1)
		{
			return i + 1;
		}
		if (i >= bigIndex || (i == bigIndex - 1))
		{
			return i + 2;
		}
		return i;
	}

	/**
	 * Notify the widget that refresh state has changed. Do not call this when
	 * refresh is triggered by a swipe gesture.
	 *
	 * @param refreshing Whether or not the view should show refresh progress.
	 */
	public void setRefreshing(boolean refreshing)
	{
		if (refreshing && mRefreshing != refreshing)
		{
			// scale and show
			mRefreshing = refreshing;
			int endTarget = (int) (totalDragOffset + mOriginalOffsetTop);
			setTargetOffsetTopAndBottom(endTarget - mCurrentTargetOffsetTop, true /* requires update */);
			mNotify = false;
			startScaleUpAnimation(mRefreshListener);
		}
		else
		{
			setRefreshing(refreshing, false /* notify */);
		}
	}

	private void startScaleUpAnimation(AnimationListener listener)
	{
		mHeadViewContainer.setVisibility(View.VISIBLE);
		mScaleAnimation = new Animation()
		{
			@Override
			public void applyTransformation(float interpolatedTime,
			                                Transformation t)
			{
				setAnimationProgress(interpolatedTime);
			}
		};
		mScaleAnimation.setDuration(mediumAnimationDuration);
		if (listener != null)
		{
			mHeadViewContainer.setAnimationListener(listener);
		}
		mHeadViewContainer.clearAnimation();
		mHeadViewContainer.startAnimation(mScaleAnimation);
	}

	private void setAnimationProgress(float progress)
	{
		ViewCompat.setScaleX(mHeadViewContainer, progress);
		ViewCompat.setScaleY(mHeadViewContainer, progress);
	}

	private void setRefreshing(boolean refreshing, final boolean notify)
	{
		if (mRefreshing != refreshing)
		{
			mNotify = notify;
			ensureTarget();
			mRefreshing = refreshing;
			if (mRefreshing)
			{
				animateOffsetToCorrectPosition(mCurrentTargetOffsetTop, mRefreshListener);
			}
			else
			{
				animateOffsetToStartPosition(mCurrentTargetOffsetTop, mRefreshListener);
			}
		}
	}

	private void startScaleDownAnimation(AnimationListener listener)
	{
		mScaleDownAnimation = new Animation()
		{
			@Override
			public void applyTransformation(float interpolatedTime,
			                                Transformation t)
			{
				setAnimationProgress(1 - interpolatedTime);
			}
		};
		mScaleDownAnimation.setDuration(SCALE_DOWN_DURATION);
		mHeadViewContainer.setAnimationListener(listener);
		mHeadViewContainer.clearAnimation();
		mHeadViewContainer.startAnimation(mScaleDownAnimation);
	}

	public boolean isRefreshing()
	{
		return mRefreshing;
	}

	/**
	 * 确保mTarget不为空<br>
	 * mTarget一般是可滑动的ScrollView,ListView,RecyclerView等
	 */
	private void ensureTarget()
	{
		if (mTarget == null)
		{
			for (int i = 0; i < getChildCount(); i++)
			{
				View child = getChildAt(i);
				if (!child.equals(mHeadViewContainer) && !child.equals(mFooterViewContainer))
				{
					mTarget = child;
					break;
				}
			}
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		// 获取控件 宽高
		final int width = getMeasuredWidth();
		final int height = getMeasuredHeight();

		// 排除异常情况
		if (getChildCount() == 0)
		{
			return;
		}
		if (mTarget == null)
		{
			ensureTarget();
		}
		if (mTarget == null)
		{
			return;
		}

		int distance = mCurrentTargetOffsetTop + mHeadViewContainer.getMeasuredHeight();

		if (null != refreshAdapter && !refreshAdapter.isTargetScroll())
		{
			distance = 0;
		}

		final View child = mTarget;
		final int childLeft = getPaddingLeft();
		final int childTop = getPaddingTop() + distance - pushDistance;// 根据偏移量distance更新
		final int childWidth = width - getPaddingLeft() - getPaddingRight();
		final int childHeight = height - getPaddingTop() - getPaddingBottom();
		// LogFileUtil.d(LOG_TAG, "debug:onLayout childHeight = " + childHeight);

		child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);// 更新目标View的位置
		int headViewWidth = mHeadViewContainer.getMeasuredWidth();
		int headViewHeight = mHeadViewContainer.getMeasuredHeight();
		mHeadViewContainer.layout((width / 2 - headViewWidth / 2), mCurrentTargetOffsetTop,
				(width / 2 + headViewWidth / 2), mCurrentTargetOffsetTop + headViewHeight);// 更新头布局的位置

		int footViewWidth = mFooterViewContainer.getMeasuredWidth();
		int footViewHeight = mFooterViewContainer.getMeasuredHeight();
		mFooterViewContainer.layout((width / 2 - footViewWidth / 2), height - pushDistance,
				(width / 2 + footViewWidth / 2), height + footViewHeight - pushDistance);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mTarget == null)
		{
			ensureTarget();
		}
		if (mTarget == null)
		{
			return;
		}
		mTarget.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));
		mHeadViewContainer.measure(MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(3 * headerViewHeight, MeasureSpec.EXACTLY));
		mFooterViewContainer.measure(MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(footerViewHeight, MeasureSpec.EXACTLY));
		if (!mOriginalOffsetCalculated)
		{
			mOriginalOffsetCalculated = true;
			mCurrentTargetOffsetTop = mOriginalOffsetTop = -mHeadViewContainer.getMeasuredHeight();
		}
		mHeaderViewIndex = -1;
		for (int index = 0; index < getChildCount(); index++)
		{
			if (getChildAt(index) == mHeadViewContainer)
			{
				mHeaderViewIndex = index;
				break;
			}
		}
		mFooterViewIndex = -1;
		for (int index = 0; index < getChildCount(); index++)
		{
			if (getChildAt(index) == mFooterViewContainer)
			{
				mFooterViewIndex = index;
				break;
			}
		}
	}

	/**
	 * 判断目标View是否滑动到顶部-还能否继续滑动
	 *
	 * @return
	 */
	public boolean isChildScrollToTop()
	{
		if (Build.VERSION.SDK_INT < 14)
		{
			if (mTarget instanceof AbsListView)
			{
				final AbsListView absListView = (AbsListView) mTarget;
				return !(absListView.getChildCount() > 0 && (absListView
						.getFirstVisiblePosition() > 0 || absListView
						.getChildAt(0).getTop() < absListView.getPaddingTop()));
			}
			else
			{
				return !(mTarget.getScrollY() > 0);
			}
		}
		else
		{
			return !ViewCompat.canScrollVertically(mTarget, -1);
		}
	}

	/**
	 * 是否滑动到底部
	 *
	 * @return
	 */
	public boolean isChildScrollToBottom()
	{
		if (isChildScrollToTop())
		{
			return false;
		}
		if (mTarget instanceof RecyclerView)
		{
			RecyclerView recyclerView = (RecyclerView) mTarget;
			LayoutManager layoutManager = recyclerView.getLayoutManager();
			int count = recyclerView.getAdapter().getItemCount();
			if (layoutManager instanceof LinearLayoutManager && count > 0)
			{
				LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
				if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == count - 1)
				{
					return true;
				}
			}
			else if (layoutManager instanceof StaggeredGridLayoutManager)
			{
				StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
				int[] lastItems = new int[2];
				staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(lastItems);
				int lastItem = Math.max(lastItems[0], lastItems[1]);
				if (lastItem == count - 1)
				{
					return true;
				}
			}
			return false;
		}
		else if (mTarget instanceof AbsListView)
		{
			final AbsListView absListView = (AbsListView) mTarget;
			int count = absListView.getAdapter().getCount();
			int fristPos = absListView.getFirstVisiblePosition();
			if (fristPos == 0
					&& absListView.getChildAt(0).getTop() >= absListView
					.getPaddingTop())
			{
				return false;
			}
			int lastPos = absListView.getLastVisiblePosition();
			if (lastPos > 0 && count > 0 && lastPos == count - 1)
			{
				return true;
			}
			return false;
		}
		else if (mTarget instanceof ScrollView)
		{
			ScrollView scrollView = (ScrollView) mTarget;
			View view = (View) scrollView
					.getChildAt(scrollView.getChildCount() - 1);
			if (view != null)
			{
				int diff = (view.getBottom() - (scrollView.getHeight() + scrollView
						.getScrollY()));
				if (diff == 0)
				{
					return true;
				}
			}
		}
		else if (mTarget instanceof NestedScrollView)
		{
			NestedScrollView nestedScrollView = (NestedScrollView) mTarget;
			View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
			if (view != null)
			{
				int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
				if (diff == 0)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 主要判断是否应该拦截子View的事件<br>
	 * 如果拦截，则交给自己的OnTouchEvent处理<br>
	 * 否者，交给子View处理<br>
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		ensureTarget();

		final int action = MotionEventCompat.getActionMasked(ev);

		if (mReturningToStart && action == MotionEvent.ACTION_DOWN)
		{
			mReturningToStart = false;
		}
		if (!isEnabled() || mReturningToStart || mRefreshing || mLoadMore
				|| (!isChildScrollToTop() && !isChildScrollToBottom()))
		{
			// 如果子View可以滑动，不拦截事件，交给子View处理-下拉刷新
			// 或者子View没有滑动到底部不拦截事件-上拉加载更多
			return false;
		}

		// 下拉刷新判断
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				setTargetOffsetTopAndBottom(
						mOriginalOffsetTop - mHeadViewContainer.getTop(), true);// 恢复HeaderView的初始位置
				mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
				mIsBeingDragged = false;
				final float initialMotionY = getMotionEventY(ev, mActivePointerId);
				if (initialMotionY == -1)
				{
					return false;
				}
				mInitialMotionY = initialMotionY;// 记录按下的位置

			case MotionEvent.ACTION_MOVE:
				if (mActivePointerId == INVALID_POINTER)
				{
					LogFileUtil.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
					return false;
				}

				final float y = getMotionEventY(ev, mActivePointerId);
				if (y == -1)
				{
					return false;
				}
				float yDiff = 0;
				if (isChildScrollToBottom())
				{
					yDiff = mInitialMotionY - y;// 计算上拉距离
					if (yDiff > touchSlop && !mIsBeingDragged)
					{// 判断是否下拉的距离足够
						mIsBeingDragged = true;// 正在上拉
					}
				}
				else
				{
					yDiff = y - mInitialMotionY;// 计算下拉距离
					if (yDiff > touchSlop && !mIsBeingDragged)
					{// 判断是否下拉的距离足够
						mIsBeingDragged = true;// 正在下拉
					}
				}
				break;

			case MotionEventCompat.ACTION_POINTER_UP:
				onSecondaryPointerUp(ev);
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				mIsBeingDragged = false;
				mActivePointerId = INVALID_POINTER;
				break;
		}

		return mIsBeingDragged;// 如果正在拖动，则拦截子View的事件
	}

	private float getMotionEventY(MotionEvent ev, int activePointerId)
	{
		final int index = MotionEventCompat.findPointerIndex(ev,
				activePointerId);
		if (index < 0)
		{
			return -1;
		}
		return MotionEventCompat.getY(ev, index);
	}

	@Override
	public void requestDisallowInterceptTouchEvent(boolean b)
	{
		// Nope.
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		final int action = MotionEventCompat.getActionMasked(ev);

		if (mReturningToStart && action == MotionEvent.ACTION_DOWN)
		{
			mReturningToStart = false;
		}
		if (!isEnabled() || mReturningToStart
				|| (!isChildScrollToTop() && !isChildScrollToBottom()))
		{
			// 如果子View可以滑动，不拦截事件，交给子View处理
			return false;
		}

		if (isChildScrollToBottom())
		{// 上拉加载更多
			return handlerPushTouchEvent(ev, action);
		}
		else
		{// 下拉刷新
			return handlerPullTouchEvent(ev, action);
		}
	}

	private boolean handlerPullTouchEvent(MotionEvent ev, int action)
	{
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
				mIsBeingDragged = false;
				break;

			case MotionEvent.ACTION_MOVE:
			{
				final int pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
				if (pointerIndex < 0)
				{
					LogFileUtil.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
					return false;
				}

				final float y = MotionEventCompat.getY(ev, pointerIndex);
				final float overscrollTop = (y - mInitialMotionY) * DRAG_RATE;
				if (mIsBeingDragged)
				{
					float originalDragPercent = overscrollTop / totalDragOffset;
					if (originalDragPercent < 0)
					{
						return false;
					}
					float dragPercent = Math.min(1f, Math.abs(originalDragPercent));
					float extraOS = Math.abs(overscrollTop) - totalDragOffset;
					float slingshotDist = totalDragOffset;
					float tensionSlingshotPercent = Math.max(0,
							Math.min(extraOS, slingshotDist * 2) / slingshotDist);
					float tensionPercent = (float) ((tensionSlingshotPercent / 4) - Math
							.pow((tensionSlingshotPercent / 4), 2)) * 2f;
					float extraMove = (slingshotDist) * tensionPercent * 2;

					int targetY = mOriginalOffsetTop
							+ (int) ((slingshotDist * dragPercent) + extraMove);
					if (mHeadViewContainer.getVisibility() != View.VISIBLE)
					{
						mHeadViewContainer.setVisibility(View.VISIBLE);
					}

					ViewCompat.setScaleX(mHeadViewContainer, 1f);
					ViewCompat.setScaleY(mHeadViewContainer, 1f);

					if (overscrollTop < totalDragOffset)
					{
						if (null != refreshAdapter)
						{
							refreshAdapter.create(totalDragOffset, overscrollTop);
						}
						if (refreshAdapter != null)
						{
							refreshAdapter.start(false);
						}
					}
					else
					{
						if (refreshAdapter != null)
						{
							refreshAdapter.start(true);
						}
					}
					setTargetOffsetTopAndBottom(targetY - mCurrentTargetOffsetTop, true);
				}
				break;
			}
			case MotionEventCompat.ACTION_POINTER_DOWN:
			{
				final int index = MotionEventCompat.getActionIndex(ev);
				mActivePointerId = MotionEventCompat.getPointerId(ev, index);
				break;
			}

			case MotionEventCompat.ACTION_POINTER_UP:
				onSecondaryPointerUp(ev);
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
			{
				if (mActivePointerId == INVALID_POINTER)
				{
					if (action == MotionEvent.ACTION_UP)
					{
						LogFileUtil.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
					}
					return false;
				}
				final int pointerIndex = MotionEventCompat.findPointerIndex(ev,
						mActivePointerId);
				final float y = MotionEventCompat.getY(ev, pointerIndex);
				final float overscrollTop = (y - mInitialMotionY) * DRAG_RATE;
				mIsBeingDragged = false;
				if (overscrollTop > totalDragOffset)
				{
					setRefreshing(true, true /* notify */);
				}
				else
				{
					mRefreshing = false;
					AnimationListener listener = new AnimationListener()
					{

						@Override
						public void onAnimationStart(Animation animation)
						{
						}

						@Override
						public void onAnimationEnd(Animation animation)
						{
							startScaleDownAnimation(null);
						}

						@Override
						public void onAnimationRepeat(Animation animation)
						{
						}

					};
					animateOffsetToStartPosition(mCurrentTargetOffsetTop, listener);
				}
				mActivePointerId = INVALID_POINTER;
				return false;
			}
		}

		return true;
	}

	/**
	 * 处理上拉加载更多的Touch事件
	 *
	 * @param ev
	 * @param action
	 * @return
	 */
	private boolean handlerPushTouchEvent(MotionEvent ev, int action)
	{
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
				mIsBeingDragged = false;
				LogFileUtil.d(LOG_TAG, "debug:onTouchEvent ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
			{
				final int pointerIndex = MotionEventCompat.findPointerIndex(ev,
						mActivePointerId);
				if (pointerIndex < 0)
				{
					LogFileUtil.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
					return false;
				}
				final float y = MotionEventCompat.getY(ev, pointerIndex);
				final float overscrollBottom = (mInitialMotionY - y) * DRAG_RATE;
				if (mIsBeingDragged)
				{
					pushDistance = (int) overscrollBottom;
					updateFooterViewPosition();
					if (loadAdapter != null)
					{
						loadAdapter.start(pushDistance >= footerViewHeight);
					}
				}
				break;
			}
			case MotionEventCompat.ACTION_POINTER_DOWN:
			{
				final int index = MotionEventCompat.getActionIndex(ev);
				mActivePointerId = MotionEventCompat.getPointerId(ev, index);
				break;
			}

			case MotionEventCompat.ACTION_POINTER_UP:
				onSecondaryPointerUp(ev);
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
			{
				if (mActivePointerId == INVALID_POINTER)
				{
					if (action == MotionEvent.ACTION_UP)
					{
						LogFileUtil.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
					}
					return false;
				}
				final int pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
				final float y = MotionEventCompat.getY(ev, pointerIndex);
				final float overscrollBottom = (mInitialMotionY - y) * DRAG_RATE;// 松手是下拉的距离
				mIsBeingDragged = false;
				mActivePointerId = INVALID_POINTER;
				if (overscrollBottom < footerViewHeight || loadAdapter == null)
				{// 直接取消
					pushDistance = 0;
				}
				else
				{// 下拉到mFooterViewHeight
					pushDistance = footerViewHeight;
				}
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
				{
					updateFooterViewPosition();
					if (pushDistance == footerViewHeight && loadAdapter != null)
					{
						mLoadMore = true;
						loadAdapter.animating();
					}
				}
				else
				{
					animatorFooterToBottom((int) overscrollBottom, pushDistance);
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * 松手之后，使用动画将Footer从距离start变化到end
	 *
	 * @param start
	 * @param end
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void animatorFooterToBottom(int start, final int end)
	{
		ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
		valueAnimator.setDuration(150);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener()
		{

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator)
			{
				// update
				pushDistance = (Integer) valueAnimator.getAnimatedValue();
				updateFooterViewPosition();
			}
		});
		valueAnimator.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation)
			{
				if (end > 0 && loadAdapter != null)
				{
					// start loading more
					mLoadMore = true;
					loadAdapter.animating();
				}
				else
				{
					resetTargetLayout();
					mLoadMore = false;
				}
			}
		});
		valueAnimator.setInterpolator(decelerateInterpolator);
		valueAnimator.start();
	}

	/**
	 * 设置停止加载
	 *
	 * @param loadMore
	 */
	public void setLoadMore(boolean loadMore)
	{
		if (!loadMore && mLoadMore)
		{// 停止加载
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
			{
				mLoadMore = false;
				pushDistance = 0;
				updateFooterViewPosition();
			}
			else
			{
				animatorFooterToBottom(footerViewHeight, 0);
			}
		}
	}

	private void animateOffsetToCorrectPosition(int from, AnimationListener listener)
	{
		mFrom = from;
		mAnimateToCorrectPosition.reset();
		mAnimateToCorrectPosition.setDuration(ANIMATE_TO_TRIGGER_DURATION);
		mAnimateToCorrectPosition.setInterpolator(decelerateInterpolator);
		if (listener != null)
		{
			mHeadViewContainer.setAnimationListener(listener);
		}
		mHeadViewContainer.clearAnimation();
		mHeadViewContainer.startAnimation(mAnimateToCorrectPosition);
	}

	private void animateOffsetToStartPosition(int from, AnimationListener listener)
	{
		mFrom = from;
		mAnimateToStartPosition.reset();
		mAnimateToStartPosition.setDuration(ANIMATE_TO_START_DURATION);
		mAnimateToStartPosition.setInterpolator(decelerateInterpolator);
		if (listener != null)
		{
			mHeadViewContainer.setAnimationListener(listener);
		}
		mHeadViewContainer.clearAnimation();
		mHeadViewContainer.startAnimation(mAnimateToStartPosition);

		resetTargetLayoutDelay(ANIMATE_TO_START_DURATION);
	}

	/**
	 * 重置Target位置
	 *
	 * @param delay
	 */
	public void resetTargetLayoutDelay(int delay)
	{
		new Handler().postDelayed(new Runnable()
		{

			@Override
			public void run()
			{
				resetTargetLayout();
			}
		}, delay);
	}

	/**
	 * 重置Target的位置
	 */
	public void resetTargetLayout()
	{
		final int width = getMeasuredWidth();
		final int height = getMeasuredHeight();
		final View child = mTarget;
		final int childLeft = getPaddingLeft();
		final int childTop = getPaddingTop();
		final int childWidth = child.getWidth() - getPaddingLeft()
				- getPaddingRight();
		final int childHeight = child.getHeight() - getPaddingTop()
				- getPaddingBottom();
		child.layout(childLeft, childTop, childLeft + childWidth, childTop
				+ childHeight);

		int headViewWidth = mHeadViewContainer.getMeasuredWidth();
		int headViewHeight = mHeadViewContainer.getMeasuredHeight();
		mHeadViewContainer.layout((width / 2 - headViewWidth / 2),
				-headViewHeight, (width / 2 + headViewWidth / 2), 0);// 更新头布局的位置
		int footViewWidth = mFooterViewContainer.getMeasuredWidth();
		int footViewHeight = mFooterViewContainer.getMeasuredHeight();
		mFooterViewContainer.layout((width / 2 - footViewWidth / 2), height,
				(width / 2 + footViewWidth / 2), height + footViewHeight);
	}

	private final Animation mAnimateToCorrectPosition = new Animation()
	{
		@Override
		public void applyTransformation(float interpolatedTime, Transformation t)
		{
			int targetTop = 0;
			int endTarget = 0;
			endTarget = (int) (totalDragOffset - Math.abs(mOriginalOffsetTop));
			targetTop = (mFrom + (int) ((endTarget - mFrom) * interpolatedTime));
			int offset = targetTop - mHeadViewContainer.getTop();
			setTargetOffsetTopAndBottom(offset, false /* requires update */);
		}

		@Override
		public void setAnimationListener(AnimationListener listener)
		{
			super.setAnimationListener(listener);
		}
	};

	private void moveToStart(float interpolatedTime)
	{
		int targetTop = 0;
		targetTop = (mFrom + (int) ((mOriginalOffsetTop - mFrom) * interpolatedTime));
		int offset = targetTop - mHeadViewContainer.getTop();
		setTargetOffsetTopAndBottom(offset, false /* requires update */);
	}

	private final Animation mAnimateToStartPosition = new Animation()
	{
		@Override
		public void applyTransformation(float interpolatedTime, Transformation t)
		{
			moveToStart(interpolatedTime);
		}
	};

	private void setTargetOffsetTopAndBottom(int offset, boolean requiresUpdate)
	{
		mHeadViewContainer.bringToFront();
		mHeadViewContainer.offsetTopAndBottom(offset);
		mCurrentTargetOffsetTop = mHeadViewContainer.getTop();
		if (requiresUpdate && Build.VERSION.SDK_INT < 11)
		{
			invalidate();
		}
	}

	/**
	 * 修改底部布局的位置-敏感pushDistance
	 */
	private void updateFooterViewPosition()
	{
		mFooterViewContainer.setVisibility(View.VISIBLE);
		mFooterViewContainer.bringToFront();
		//针对4.4及之前版本的兼容
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
		{
			mFooterViewContainer.getParent().requestLayout();
		}
		mFooterViewContainer.offsetTopAndBottom(-pushDistance);
	}

	private void onSecondaryPointerUp(MotionEvent ev)
	{
		final int pointerIndex = MotionEventCompat.getActionIndex(ev);
		final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
		if (pointerId == mActivePointerId)
		{
			final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
			mActivePointerId = MotionEventCompat.getPointerId(ev,
					newPointerIndex);
		}
	}

	/**
	 * 下拉刷新，头部布局容器
	 */
	private class HeadViewContainer extends RelativeLayout
	{
		private AnimationListener animationListener;

		public HeadViewContainer(Context context)
		{
			super(context);
		}

		public void setAnimationListener(AnimationListener listener)
		{
			animationListener = listener;
		}

		@Override
		public void onAnimationStart()
		{
			super.onAnimationStart();
			if (null != animationListener)
			{
				animationListener.onAnimationStart(getAnimation());
			}
		}

		@Override
		public void onAnimationEnd()
		{
			super.onAnimationEnd();
			if (null != animationListener)
			{
				animationListener.onAnimationEnd(getAnimation());
			}
		}
	}

	/**
	 * 给用户使用
	 */
	public interface OnRefreshListener
	{
		/**
		 * 动画中
		 */
		void onAnimating();
	}
}
