package com.gogenius.learningdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shijiwei on 2016/10/17.
 */
public class MarqueeText extends TextView implements Runnable {

    private int contentWidth = 0;
    private int scrollToX = 0;
    private boolean isStop = false;
    private boolean isRun = true;
    private boolean isMeasureContentWidth = false;

    public MarqueeText(Context context) {
        this(context, null);
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void run() {
        if (isRun) {
            if (scrollToX >= contentWidth) {
                //重新开始
                scrollToX = -150;
            }
            scrollTo(scrollToX, 0);
            scrollToX = scrollToX + 5;
            postDelayed(this, 150);
        }

    }

    // 点击开始,执行线程
    public void startScroll() {
        post(this);
    }

    // 点击暂停
    public void pauseScroll() {
        isRun = false;
    }

    // 点击重新开始
    public void restartScroll() {
        isRun = true;
        scrollToX = 0;
        startScroll();
    }

}

