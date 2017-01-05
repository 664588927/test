package com.gogenius.learningdemos.ItemTouch;

/**
 * Created by shijiwei on 2016/9/6.
 */
public interface ItemTouchNotifyListener {

    /**
     * 上下拖拽
     */
    void drag(int fromPosition, int toPosition);

    /**
     * 左右滑动
     */
    void swipe(int position);
}
