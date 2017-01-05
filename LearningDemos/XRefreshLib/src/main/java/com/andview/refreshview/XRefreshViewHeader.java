package com.andview.refreshview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andview.refreshview.callback.IHeaderCallBack;
import com.andview.refreshview.utils.Utils;

import java.util.Calendar;

public class XRefreshViewHeader extends LinearLayout implements IHeaderCallBack {

    private ViewGroup mContent;

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;


    private final int ROTATE_ANIM_DURATION = 180;

    private static final int TEXT_PROGRESS_TYPE = 0;
    private static final int TEXT_ANIMATION_TYPE = 1;
    private static final int PROGRESS_TYPE = 2;


    private ProgressBar mHeaderProgressBar;
    private ImageView mHeaderIcon;
    private LinearLayout mHeaderLableLayout;
    private TextView mHeaderLableHint;
    private TextView mHeaderLableTime;


    public XRefreshViewHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XRefreshViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        mContent = (ViewGroup) LayoutInflater.from(context).inflate(
                R.layout.xrefreshview_xheader, this);

        mHeaderLableLayout = (LinearLayout) findViewById(R.id.xrefreshview_header_lable_layout);
        mHeaderLableHint = (TextView) findViewById(R.id.xrefreshview_header_lable_hint);
        mHeaderLableTime = (TextView) findViewById(R.id.xrefreshview_header_lable_time);
        mHeaderIcon = (ImageView) findViewById(R.id.xrefreshview_header_icon);
        mHeaderProgressBar = (ProgressBar) findViewById(R.id.xrefreshview_header_progressbar);

        changedType(TEXT_ANIMATION_TYPE);

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);

        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(0);
        mRotateDownAnim.setFillAfter(true);
    }

    public void setRefreshTime(long lastRefreshTime) {
        // 获取当前时间
        Calendar mCalendar = Calendar.getInstance();
        long refreshTime = mCalendar.getTimeInMillis();
        long howLong = refreshTime - lastRefreshTime;
        int minutes = (int) (howLong / 1000 / 60);
        String refreshTimeText = null;
        Resources resources = getContext().getResources();
        if (minutes < 1) {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_justnow);
        } else if (minutes < 60) {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_minutes_ago);
            refreshTimeText = Utils.format(refreshTimeText, minutes);
        } else if (minutes < 60 * 24) {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_hours_ago);
            refreshTimeText = Utils.format(refreshTimeText, minutes / 60);
        } else {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_days_ago);
            refreshTimeText = Utils.format(refreshTimeText, minutes / 60 / 24);
        }
        mHeaderLableTime.setText(refreshTimeText);
    }

    /**
     * hide header when disable pull refresh
     */
    public void hide() {
        setVisibility(View.GONE);
    }

    /**
     * show header when disable pull refresh
     */
    public void show() {
        setVisibility(View.VISIBLE);
    }

    /**
     *  改变头的显示模式
     *
     * @param type
     */
    public void changedType(int type) {
        switch (type) {
            case TEXT_PROGRESS_TYPE:
                mHeaderLableLayout.setVisibility(VISIBLE);
                mHeaderProgressBar.setVisibility(VISIBLE);
                mHeaderIcon.setVisibility(GONE);
                break;
            case TEXT_ANIMATION_TYPE:
                mHeaderLableLayout.setVisibility(VISIBLE);
                mHeaderProgressBar.setVisibility(GONE);
                mHeaderIcon.setVisibility(VISIBLE);
                break;
            case PROGRESS_TYPE:
                mHeaderLableLayout.setVisibility(GONE);
                mHeaderProgressBar.setVisibility(VISIBLE);
                mHeaderIcon.setVisibility(GONE);
                break;
        }
    }

    @Override
    public void onStateNormal() {

        mHeaderLableHint.setText(R.string.xrefreshview_header_hint_normal);
        mHeaderLableTime.setVisibility(GONE);
        ((AnimationDrawable) mHeaderIcon.getBackground()).start();
    }

    @Override
    public void onStateReady() {

        mHeaderLableHint.setText(R.string.xrefreshview_header_hint_ready);
        mHeaderLableTime.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStateRefreshing() {

        mHeaderLableHint.setText(R.string.xrefreshview_header_hint_loading);
    }

    @Override
    public void onStateFinish(boolean success) {

        mHeaderLableHint.setText(success ? R.string.xrefreshview_header_hint_loaded
                : R.string.xrefreshview_header_hint_loaded_fail);
        mHeaderLableTime.setVisibility(View.GONE);
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {

    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
