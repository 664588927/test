package com.gogenius.learningdemos.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.coordinatorlayout.behavior.FooterBehavior;

/**
 * Created by shijiwei on 2016/9/8.
 */
@CoordinatorLayout.DefaultBehavior(FooterBehavior.class)
public class CardRecyclerView extends CardView {

    private Context mContext;

    private int cardBackgroundColor = Color.parseColor("#ffffff");
    private float cardElevation = 20;

    private int mWidth;
    private int mHeight;

    private TextView label;

    public CardRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        initial();
    }

    public CardRecyclerView(Context context) {
        super(context);

        this.mContext = context;

        initial();
    }

    public CardRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        initial();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    private void initial() {

        setRadius(5f);
        setCardBackgroundColor(cardBackgroundColor);
        setCardElevation(cardElevation);

        LayoutInflater.from(mContext).inflate(R.layout.layout_card_recyclerview, this);

        this.label = (TextView) findViewById(R.id.card_lable);

    }


    public void setLabelBackgroundColor(int color) {

        label.setBackgroundColor(color);
    }

    public void setLableText(String content) {
        this.label.setText(content);
    }

    public int getLableHeight() {

        return label.getHeight();
    }


}
