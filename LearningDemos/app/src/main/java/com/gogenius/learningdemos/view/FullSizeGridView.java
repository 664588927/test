package com.gogenius.learningdemos.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by steven on 14-7-4.
 */
public class FullSizeGridView extends GridView {

    public FullSizeGridView(Context context) {
        super(context);
    }

    public FullSizeGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullSizeGridView(Context context, AttributeSet attrs,
                            int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
