package com.gogenius.learningdemos.cardlist;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by shijiwei on 2016/9/10.
 */
public class CardBehavior extends CoordinatorLayout.Behavior<CardView> {

    private static final String TAG = "CardBehavior";

    private final int CARD_TITLE_LENGTH = 28;

    public CardBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, CardView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {

        int offset = measureChildOffset(parent, child);

        Log.e(TAG, " onMeasureChild offset " + offset);

        int widthMeasereSize = View.MeasureSpec.getSize(parentWidthMeasureSpec)
                - parent.getPaddingLeft()
                - parent.getPaddingRight();

        int heightMeasureSize = View.MeasureSpec.getSize(parentHeightMeasureSpec)
                - offset;

        child.measure(View.MeasureSpec.makeMeasureSpec(widthMeasereSize, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(heightMeasureSize, View.MeasureSpec.EXACTLY));

        return true;
    }

    private int measureChildOffset(CoordinatorLayout parent, CardView child) {

        int offset = 0;

        for (int i = 0; i < parent.getChildCount(); i++) {

            View v = parent.getChildAt(i);

            if (v instanceof CardView) {

                if (v != child) {
                    offset += CARD_TITLE_LENGTH;
                } else {
                    return offset;
                }
            }

        }

        return offset;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, CardView child, int layoutDirection) {

        parent.onLayoutChild(child, layoutDirection);

        View previousChild = findPreviousChild(parent, child);

        if (previousChild != null) {

            int offset = previousChild.getTop() + CARD_TITLE_LENGTH;

            child.offsetTopAndBottom(offset);

            Log.e(TAG, " onLayoutChild offset " + offset);
        }

        Log.e(TAG, "onLayoutChild "
                + (previousChild == null ? "null" : previousChild.toString()));

        return true;
    }

    private View findPreviousChild(CoordinatorLayout parent, CardView child) {


        int position = parent.indexOfChild(child);

        Log.e(TAG, " findPreviousChild position " + position);

        for (int i = position; i > 0; i--) {

            View previousChild = parent.getChildAt(i);

            if (previousChild instanceof CardView)
                return previousChild;

        }

        return null;
    }



    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CardView child, View dependency) {
        return dependency instanceof CardView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CardView child, View dependency) {


        return true;
    }
}
