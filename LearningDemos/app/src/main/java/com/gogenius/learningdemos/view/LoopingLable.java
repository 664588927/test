package com.gogenius.learningdemos.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/22.
 */
public class LoopingLable extends FrameLayout implements Animation.AnimationListener, View.OnClickListener {

    private TextView mLoopingLable;

    private List<String> mLableSet;
    private int position;

    private final int LOOPING_CODE = 1;

    private long mIntervalMillis = 3 * 1000;
    private long mDurationMillis = 1 * 1000;

    private AnimationSet mAnimationSet;
    private AlphaAnimation mAlphaAnimation;
    private TranslateAnimation mTranslateAnimation;

    private OnLableClickListener mOnLableClickListener;

    public LoopingLable(Context context) {

        this(context, null);
    }

    public LoopingLable(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopingLable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(context);
    }

    private void initialize(Context context) {

        mAnimationSet = new AnimationSet(true);
        mAlphaAnimation = new AlphaAnimation(1f, 0f);
        mTranslateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -1f);

        mAnimationSet.addAnimation(mAlphaAnimation);
        mAnimationSet.addAnimation(mTranslateAnimation);
        mAnimationSet.setDuration(mDurationMillis);
        mAnimationSet.setFillBefore(true);
        mAnimationSet.setAnimationListener(this);


        mLableSet = new ArrayList<>();

        mLoopingLable = new TextView(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = TEXT_ALIGNMENT_CENTER;
        mLoopingLable.setLayoutParams(lp);
        mLoopingLable.setGravity(Gravity.CENTER);
        mLoopingLable.setOnClickListener(this);
        addView(mLoopingLable);

        loopingHandler.sendEmptyMessageDelayed(LOOPING_CODE, mIntervalMillis);

    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (mLableSet == null && mLableSet.size() == 0)
            return;

        position++;

        if (position >= mLableSet.size())
            position = 0;

        mLoopingLable.setText(mLableSet.get(position));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {

        if (mOnLableClickListener != null)
            mOnLableClickListener.onClick(position);
    }

    /**
     * set titles of the lable
     * @param lables
     */
    public void setLableSet(List<String> lables) {

        this.mLableSet = lables;

        if (lables != null && lables.size() > 0)
            mLoopingLable.setText(lables.get(0));
    }

    public void setLableTextColor(int color) {

        mLoopingLable.setTextColor(color);
    }

    public void setLableTextSize(int size) {

        mLoopingLable.setTextSize(size);
    }

    public void setLableGravity(int gravity) {

        mLoopingLable.setGravity(gravity);
    }

    public int getLabelPosition() {

        return position;
    }

    public void setOnLableClickListener(OnLableClickListener mOnLableClickListener) {

        this.mOnLableClickListener = mOnLableClickListener;
    }

    private Handler loopingHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            mLoopingLable.startAnimation(mAnimationSet);
            loopingHandler.sendEmptyMessageDelayed(LOOPING_CODE, mIntervalMillis);
            return false;
        }
    });


    public interface OnLableClickListener {

        void onClick(int position);
    }
}
