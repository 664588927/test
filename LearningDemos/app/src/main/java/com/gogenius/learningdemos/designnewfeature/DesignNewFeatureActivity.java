package com.gogenius.learningdemos.designnewfeature;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2016/9/9.
 */
public class DesignNewFeatureActivity extends Activity implements Toolbar.OnMenuItemClickListener, AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = "DesignNewFeature";

    private final float PERCENTAGE_TO_SHOW_TITLE = 0.9f;
    private final float PERCENTEGE_TO_HIDE_CONTAINER = 0.3f;
    private final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private TextView mTitle;
    private LinearLayout mTitleContainer;


//    private XRefreshView xRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_new_feature);

        initialView();

        mAppBarLayout.addOnOffsetChangedListener(this);
        mToolbar.inflateMenu(R.menu.main_menu);
        mToolbar.setOnMenuItemClickListener(this);

        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
    }

    private void initialView() {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle = (TextView) findViewById(R.id.toolbar_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);

//        xRefreshView = (XRefreshView) findViewById(R.id.x_refresh_view);
//        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
//            @Override
//            public void onRefresh() {
//                xRefreshView.stopRefresh();
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//
//                xRefreshView.stopLoadMore();
//            }
//
//            @Override
//            public void onRelease(float direction) {
//
//            }
//
//            @Override
//            public void onHeaderMove(double headerMovePercent, int offsetY) {
//
//            }
//        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        //verticalOffset ： 滑动的偏移量

        //AppBarLayout 的高度
        int totalRange = appBarLayout.getTotalScrollRange();

        float percentage = (float) Math.abs(verticalOffset) / (float) totalRange;

        handleAlphaTitel(percentage);

        handleAlphaTilelContaiber(percentage);

    }

    private void handleAlphaTilelContaiber(float percentage) {

        if (percentage >= PERCENTEGE_TO_HIDE_CONTAINER) {

            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    private void handleAlphaTitel(float percentage) {

        if (percentage >= PERCENTAGE_TO_SHOW_TITLE) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    /**
     * 透明度动画
     */
    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
