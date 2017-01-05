package com.gogenius.learningdemos.game2048;

import android.content.Context;
import android.test.ServiceTestCase;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridLayout;


/**
 * Created by shijiwei on 2016/10/12.
 */
public class GameLayout2048 extends GridLayout {

    private int SCREEN_HEIGHT;
    private int SCREEN_WIDTH;

    private final int SPACING_ITEM = 20;
    private int mNumItemPerLine = 4;

    private int mWidth;
    private int mHeight;
    private int mItemSize;

    private GameItem2048[][] mGameItemSet;


    public GameLayout2048(Context context) {
        this(context,null);
    }

    public GameLayout2048(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GameLayout2048(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {

        SCREEN_HEIGHT = getContext().getResources().getDisplayMetrics().heightPixels;
        SCREEN_WIDTH = getContext().getResources().getDisplayMetrics().widthPixels;

        setRowCount(mNumItemPerLine);
        setColumnCount(mNumItemPerLine);

//        creatGameItem(mNumItemPerLine);

    }

    public void creatGameItem(int num) {

        mGameItemSet = new GameItem2048[num][num];
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(110, 110);

        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {
                GameItem2048 item = new GameItem2048(getContext());
                item.setLayoutParams(lp);
                mGameItemSet[x][y] = item;
                item.setNumber(2);
                addView(item);
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        int actualSize = Math.min(mWidth, mHeight);

        mItemSize = (actualSize - SPACING_ITEM * (mNumItemPerLine + 1)) / mNumItemPerLine;

        setMeasuredDimension(actualSize, actualSize);
    }
}
