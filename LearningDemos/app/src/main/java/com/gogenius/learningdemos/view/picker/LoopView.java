package com.gogenius.learningdemos.view.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by shijiwei on 2016/12/19.
 */
public class LoopView extends ListView {

    public LoopView(Context context) {
        super(context);
    }

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            smoothScrollToPosition(3);
        }
        return super.onTouchEvent(ev);
    }
}
