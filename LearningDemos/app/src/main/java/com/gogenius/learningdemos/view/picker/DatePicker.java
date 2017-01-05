package com.gogenius.learningdemos.view.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2016/12/19.
 */
public class DatePicker extends LinearLayout {

    private PickerType mPickerType = PickerType.DATE_TIME;
    private Scroller scroller;

    /* 默认最小显示5个item */
    private int mMinDisplayItemNum = 5;

    public enum PickerType {
        DATE_TIME,
        DATE,
        TIME
    }

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }


    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_date_picker, this);
        scroller = new Scroller(getContext());
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scroller.startScroll(0, 0, 0, -200, 5000);
                invalidate();
            }
        });

    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY());
            postInvalidate();
        }
    }

}
