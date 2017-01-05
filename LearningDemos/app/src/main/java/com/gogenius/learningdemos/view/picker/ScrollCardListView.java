package com.gogenius.learningdemos.view.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2017/1/4.
 */
public class ScrollCardListView extends LinearLayout {

    private OverScroller mOverScroller;
    /** 判定为拖动的最小移动像素数 */
    private int mTouchSlop;
    /** 左边界 */
    private int mLeftBorder;
    /** 右边界 */
    private int mRightBorder;

    private int mWidth;
    private int mHeight;

    private PointF mOldPoint = new PointF();
    private PointF mCurrentPoint = new PointF();
    private int offset = 0;

    public ScrollCardListView(Context context) {
        this(context, null);
    }

    public ScrollCardListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollCardListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ScrollCardListView);
        }

        setOrientation(HORIZONTAL);
        mOverScroller = new OverScroller(getContext());
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }


    @Override
    public void computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            this.scrollTo(mOverScroller.getCurrX(), 0);
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
         /* 测量每一个子控件的大小 */
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            int left;
            int right = 0;
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                if (i == 0) {
                    left = 0;
                    right = childView.getMeasuredWidth();
                } else {
                    left = right;
                    right = right + childView.getMeasuredWidth();
                }
                childView.layout(left, 0, right, childView.getMeasuredHeight());
            }
            /* 初始化左右边界值 */
            mLeftBorder = getChildAt(0).getLeft();
            mRightBorder = getChildAt(getChildCount() - 1).getRight();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOldPoint.x = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentPoint.x = event.getX();
                offset = (int) (mOldPoint.x - mCurrentPoint.x);
                scrollBy(offset, 0);
                mOldPoint.x = mCurrentPoint.x;
                break;
            case MotionEvent.ACTION_UP:
                //if (mOverScroller.getCurrX() + offset < mLeftBorder) {
                //    mOverScroller.startScroll(mOverScroller.getCurrX(), 0, mLeftBorder - mOverScroller.getCurrX(), 0, 1000);
                //} else if (mOverScroller.getCurrX() + offset > mRightBorder) {
                //    mOverScroller.startScroll(mOverScroller.getCurrX(), 0, mOverScroller.getCurrX() - mRightBorder, 0, 1000);
                //}
                //invalidate();

                break;
        }
        return true;
    }

    private void autoScroll2Target() {
        int x = mOverScroller.getCurrX();
    }
}
