package com.gogenius.learningdemos.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by shijiwei on 2016/10/6.
 */
public class BannerView extends FrameLayout {

//    private ViewPager mViewPager;
    private LinearLayout mLayoutOfCircle;

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initialize() {

//        mViewPager = new ViewPager(getContext());
//        FrameLayout.LayoutParams lpOfViewPager = new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        mViewPager.setLayoutParams(lpOfViewPager);

        mLayoutOfCircle = new LinearLayout(getContext());
    }
}
