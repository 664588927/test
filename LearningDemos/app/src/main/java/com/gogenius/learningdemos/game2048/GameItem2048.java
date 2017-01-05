package com.gogenius.learningdemos.game2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by shijiwei on 2016/10/12.
 */
public class GameItem2048 extends View {

    private int mNumber;
    private String mNumStr = "";

    private Paint mPain;
    private Rect mRect;

    private int[] mColors = {
            Color.parseColor("#EA7821"),
            Color.parseColor("#CCC0B3"),
            Color.parseColor("#EEE4DA"),
            Color.parseColor("#EDE0C8"),
            Color.parseColor("#F2B179"),
            Color.parseColor("#F49563"),
            Color.parseColor("#F5794D"),
            Color.parseColor("#F55D37"),
            Color.parseColor("#EEE863"),
            Color.parseColor("#EDB04D"),
            Color.parseColor("#ECB04D"),
            Color.parseColor("#EB9437"),
            Color.parseColor("#EA7821")
    };

    public GameItem2048(Context context) {
        this(context, null);
    }

    public GameItem2048(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameItem2048(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }


    private void initialize() {

        mPain = new Paint();
        mPain.setAntiAlias(true);
        mPain.setTextSize(30.0f);

        mRect = new Rect();

    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int mNumber) {
        this.mNumber = mNumber;
        this.mNumStr = this.mNumber + "";
        mPain.getTextBounds(mNumStr, 0, mNumStr.length(), mRect);
        invalidate();
    }

    private int calculateThePower2(int value) {

        int power = 0;

        while (value >= 2 && value % 2 == 0) {
            value = value / 2;
            power++;
        }
        return power;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPain.setStyle(Paint.Style.FILL);
        mPain.setColor(mColors[calculateThePower2(mNumber) % mColors.length]);

        canvas.drawRect(0, 0, getWidth(), getHeight(), mPain);

        mPain.setColor(Color.BLACK);
        canvas.drawText(
                mNumStr,
                (getWidth() - mRect.width()) / 2,
                getHeight() / 2 + mRect.height() / 2,
                mPain);
    }


}
