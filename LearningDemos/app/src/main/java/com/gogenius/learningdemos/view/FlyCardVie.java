package com.gogenius.learningdemos.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.gogenius.learningdemos.coordinatorlayout.behavior.EasyBehavior;

/**
 * Created by shijiwei on 2016/9/12.
 */

@CoordinatorLayout.DefaultBehavior(EasyBehavior.class)
public class FlyCardVie extends CardView {


    private OnFlyOutOfTheScreenListener mOnFlyOutOfTheScreenListener;

    private int mWidth;
    private int mHeight;

    private int mPrentHeight;

    private float mOriginY;

    private boolean isHandleTouch = false;

    public FlyCardVie(Context context) {
        super(context);
    }

    public FlyCardVie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlyCardVie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);

        mPrentHeight = ((View) getParent()).getHeight();

    }

    float downX, downY;
    float moveX, moveY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                if (mOriginY == 0)
                    mOriginY = getY();

                downX = event.getRawX();
                downY = event.getRawY();

                break;
            case MotionEvent.ACTION_UP:

                FlyAnimation animation = new FlyAnimation(getY());
                animation.setDuration(300);
                startAnimation(animation);

                break;
            case MotionEvent.ACTION_MOVE:

                moveX = event.getRawX();
                moveY = event.getRawY();

                setY(getY() + (moveY - downY));

                downX = moveX;
                downY = moveY;

                break;
        }

        return isHandleTouch;
    }

    public void setOnFlyOutOfTheScreenListener(OnFlyOutOfTheScreenListener listener) {
        this.mOnFlyOutOfTheScreenListener = listener;
    }

    public void notifyTouchEvent() {
        isHandleTouch = true;
    }


    /**
     * a custom animation for fly the card
     */
    private class FlyAnimation extends Animation {

        private float currentY;

        public FlyAnimation(float currentY) {

            this.currentY = currentY;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            float refreshY = 0.0f;

            if (currentY < mPrentHeight / 4) {

                // fly out of the screen from the screen's top

                refreshY = currentY * (1 - interpolatedTime);

                if (refreshY == 0) {

                    refreshY = -mPrentHeight;

                    if (mOnFlyOutOfTheScreenListener != null)
                        mOnFlyOutOfTheScreenListener.onFlyOut(FlyCardVie.this);
                }

            } else if (currentY > mPrentHeight / 4 * 3) {

                // fly out of the screen from the screen's bottom

                refreshY = getY() + (mPrentHeight - getY()) * interpolatedTime;
//
                if (refreshY == mPrentHeight){

                    refreshY = mPrentHeight * 2;

                    if (mOnFlyOutOfTheScreenListener != null)
                        mOnFlyOutOfTheScreenListener.onFlyOut(FlyCardVie.this);

                }


            } else {

                // go back to origin

                if (currentY < mOriginY) {

                    // from the upper part of the screen back to the origin

                    refreshY = currentY + (mOriginY - currentY) * interpolatedTime;

                } else {

                    // from the bottom  part of the screen back to the origin

                    refreshY = mOriginY + (currentY - mOriginY) * (1- interpolatedTime);

                }


            }

            setY(refreshY);

        }

    }

    public interface OnFlyOutOfTheScreenListener {

        void onFlyOut(View view);
    }
}
