package com.gogenius.learningdemos.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;


/**
 * Created by shijiwei on 2016/9/11.
 */
public class ParallaxLayout extends ScrollView {

    private static final String TAG = "ParallaxLayout";

    private float zoomParallaxMultiplier = 1f;
    private long bounceDurationMillis = 300;

    private int mMaxParallaxViewSize;
    private int mDefaultImageViewSize;

    private int mWidth;
    private int mHeight;

    private LinearLayout mContainerView;
    private ViewGroup mHeaderView;


    private ImageView mParallaxImageView;

    public ParallaxLayout(Context context) {
        super(context);

        initial(context);
    }

    public ParallaxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initial(context);
    }

    public ParallaxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initial(context);
    }


    private void initial(Context context) {

        setVerticalScrollBarEnabled(false);

        mContainerView = new LinearLayout(context);
        mContainerView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mContainerView.setLayoutParams(l);

        addView(mContainerView);

        mHeaderView = new LinearLayout(context);
        mHeaderView.setLayoutParams(l);

        mParallaxImageView = new ImageView(context);
        mParallaxImageView.setLayoutParams(l);
        mParallaxImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mHeaderView.addView(mParallaxImageView);

        mContainerView.addView(mHeaderView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        mDefaultImageViewSize = (int) (mHeight * 0.3);

        mMaxParallaxViewSize = (int) (mHeight * 0.5);

        mParallaxImageView.getLayoutParams().height = mDefaultImageViewSize;

        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                   int maxOverScrollY, boolean isTouchEvent) {

        if (deltaY < 0) {

            int currentHeight = mParallaxImageView.getHeight();
            int refreshHeight = (int) (currentHeight - deltaY * zoomParallaxMultiplier);

            mParallaxImageView.getLayoutParams().height =
                    refreshHeight > mMaxParallaxViewSize ?
                            mMaxParallaxViewSize : refreshHeight;

            if (refreshHeight < mMaxParallaxViewSize)
                mParallaxImageView.requestLayout();

        } else {

            int currentHeight = mParallaxImageView.getHeight();
            int refreshHeight = (int) (currentHeight - deltaY * zoomParallaxMultiplier);

            mParallaxImageView.getLayoutParams().height =
                    refreshHeight < mDefaultImageViewSize ?
                            mDefaultImageViewSize : refreshHeight;

            if (refreshHeight > mDefaultImageViewSize)
                mParallaxImageView.requestLayout();


        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_UP:

                if (mParallaxImageView != null && mParallaxImageView.getHeight() > mDefaultImageViewSize) {

                    GoBackAnimation mGoBackAnimation =
                            new GoBackAnimation(mParallaxImageView, mDefaultImageViewSize);

                    mGoBackAnimation.setDuration(bounceDurationMillis);

                    mParallaxImageView.startAnimation(mGoBackAnimation);
                }

                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * set the duration millis of the parallax view bounce to origin
     *
     * @param millis
     */
    public void setBounceDurationMillis(long millis) {
        this.bounceDurationMillis = millis;
    }

    /**
     * set the resource to the parallax layout
     *
     * @param resId
     */
    public void setHeaderParallaxViewResource(int resId) {

        mParallaxImageView.setImageResource(resId);

    }

    /**
     * set the bitmap to the parallax layout
     *
     * @param bm
     */
    public void setHeadParallaxViewBitmap(Bitmap bm) {

        mParallaxImageView.setImageBitmap(bm);
    }

    /**
     * set the parallax imageview of the headerview
     *
     * @param parallaxView
     */
    public void setParallaxView(ImageView parallaxView) {
        this.mParallaxImageView = parallaxView;
    }

    /**
     * set a header view of the parallax layout
     *
     * @param header
     */
    public void setHeaderView(View header) {
        mContainerView.removeView(mHeaderView);
        mHeaderView = (ViewGroup) header;
        mContainerView.addView(header, 0);
    }

    /**
     * add a body view of the parallaxlayout
     *
     * @param body
     */
    public void addBodyView(View body) {
        mContainerView.addView(body);
    }


    /**
     * a custom animation for go back
     */
    private class GoBackAnimation extends Animation {

        private View mTargetView;

        private int mTargetHeight;

        public GoBackAnimation(View targetView, int targetHeight) {

            this.mTargetHeight = targetHeight;
            this.mTargetView = targetView;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            // 0.0f ~ 1.0f

            int currentHeight = (int) (mTargetHeight +
                    (mTargetView.getHeight() - mTargetHeight) * (1 - interpolatedTime));

            mTargetView.getLayoutParams().height = currentHeight;

            mTargetView.requestLayout();
        }
    }
}
