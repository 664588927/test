package com.gogenius.learningdemos.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.gogenius.learningdemos.view.CardRecyclerView;

/**
 * Created by shijiwei on 2016/9/9.
 */
public class SlidingCardBehavior extends CoordinatorLayout.Behavior<CardRecyclerView> {

    public SlidingCardBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, CardRecyclerView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {

        int offset = getChildMeasureOffset(parent, child);

        Log.e("SlidingCardBehavior", " onMeasureChild offset " + offset);

        int heightMeasureSpec = View.MeasureSpec.getSize(parentHeightMeasureSpec) - offset;

        child.measure(parentWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(heightMeasureSpec, View.MeasureSpec.EXACTLY));

        return true;
    }

    private int getChildMeasureOffset(CoordinatorLayout parent, CardRecyclerView child) {

        int offset = 0;

        for (int i = 0; i < parent.getChildCount(); i++) {

            View view = parent.getChildAt(i);
            if (view != child && view instanceof CardRecyclerView) {
                offset += ((CardRecyclerView) view).getLableHeight();
            } else {
                return offset;
            }
        }

        return offset;
    }

    int count = 0;

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, CardRecyclerView child, int layoutDirection) {

        Log.e("SlidingCardBehavior", "onLayoutChild");

        //摆放布局
        parent.onLayoutChild(child, layoutDirection);

        //子控件设置偏移量
        CardRecyclerView previous = getPreviousChild(parent, child);

        if (previous != null) {

            int offset = previous.getLableHeight() + previous.getTop();

            child.offsetTopAndBottom(offset * (count++));

            Log.e("SlidingCardBehavior", "onLayoutChild offset " + offset * count + " count " + count);
        }

        return true;
    }

    private CardRecyclerView getPreviousChild(CoordinatorLayout parent, CardRecyclerView child) {

        int childIndex = parent.indexOfChild(child);

        for (int i = childIndex; i >= 0; i--) {

            View v = parent.getChildAt(i);

            if (v instanceof CardRecyclerView)
                return (CardRecyclerView) v;
        }
        return null;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, CardRecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {

        Log.e("SlidingCardBehavior", "onStartNestedScroll result " +
                (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ? true : false));

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, CardRecyclerView child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

    }

}
