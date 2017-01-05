package com.gogenius.learningdemos.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.gogenius.learningdemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/12.
 */
public class NestedScrollListCardtView extends FrameLayout implements NestedScrollingChild{

    private int[] mColorArray = {
            Color.parseColor("#DD6B55"),
            Color.parseColor("#ff009688"),
            Color.parseColor("#FFFF00"),
            Color.parseColor("#6B8E23")
    };

    private List<FlyCardVie> mCardSet;

    private CoordinatorLayout mContainer;

    public NestedScrollListCardtView(Context context) {
        super(context);

        initialize(context);
    }

    public NestedScrollListCardtView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize(context);
    }

    public NestedScrollListCardtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(context);
    }


    private void initialize(Context context) {

        LayoutInflater.from(context).inflate(R.layout.layout_nested_scroll_card_list, this);
        mContainer = (CoordinatorLayout) findViewById(R.id.container);

        mCardSet = new ArrayList<>();

        for (int i = 0; i < mContainer.getChildCount(); i++){
            mCardSet.add((FlyCardVie) mContainer.getChildAt(i));

        }


        for (int i = 0; i < mCardSet.size(); i++)
            mCardSet.get(i).setCardBackgroundColor(mColorArray.length % i);

        PathMeasure pathMeasure = new PathMeasure();

    }

}
