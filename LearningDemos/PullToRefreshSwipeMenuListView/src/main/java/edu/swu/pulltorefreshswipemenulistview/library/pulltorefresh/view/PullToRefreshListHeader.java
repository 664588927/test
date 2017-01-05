package edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.R;

public class PullToRefreshListHeader extends LinearLayout {
    private LinearLayout mContainer;
    private int mState = STATE_NORMAL;
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    private PullToRefreshSwipeMenuListView mParentView;
    private ImageView mIvIcon;
    private TextView mTvLable;
    private AnimationDrawable mAnimationDrawable;

    public PullToRefreshListHeader(Context context) {
        super(context);
        initView(context);
    }


    public void setmParentView(PullToRefreshSwipeMenuListView mParentView) {
        this.mParentView = mParentView;
    }

    /**
     * @param context
     * @param attrs
     */
    public PullToRefreshListHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);

        mIvIcon = (ImageView) findViewById(R.id.iv_icon);
        mTvLable = (TextView) findViewById(R.id.tv_lable);
        mAnimationDrawable = (AnimationDrawable) mIvIcon.getDrawable();
    }

    public void setState(int state) {
        if (state == mState)
            return;

        switch (state) {
            case STATE_NORMAL:
                if (mState == STATE_READY) {
                    mTvLable.setText("下拉刷新");
                    mAnimationDrawable.start();
                }
                if (mState == STATE_REFRESHING) {
                    mTvLable.setText("");
                }
                break;
            case STATE_READY:
                if (mState != STATE_READY) {
                    mTvLable.setText("释放刷新");
                }
                break;
            case STATE_REFRESHING:
                if (mParentView.getFirstVisiblePosition() == 0){
                    mParentView.setEnabled(false);
                    mParentView.setIsLoading(true);
                    mParentView.getmFooterView().setEnabled(false);
                }
                mTvLable.setText("刷新中");
                mAnimationDrawable.start();
                break;
            default:
        }

        mState = state;
    }

    public void setVisiableHeight(int height) {
        if (height <= 0){
            height = 0;
            mParentView.setEnabled(true);
            mParentView.setIsLoading(false);
            mParentView.getmFooterView().setEnabled(true);
            mTvLable.setText("下拉刷新");
        }
        if (height >= 180)
            height = 180;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight() {
        return mContainer.getHeight();
    }

}
