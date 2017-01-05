package com.gogenius.learningdemos.ItemTouch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by shijiwei on 2016/9/6.
 */
public class ItemTouchHelperCallBack extends ItemTouchHelper.Callback {

    private ItemTouchNotifyListener notifyListener;

    public void setItemTouchNotifyListener(ItemTouchNotifyListener notifyListener) {
        this.notifyListener = notifyListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder fromViewHolder, RecyclerView.ViewHolder target) {

        //上下拖拽回调
        notifyListener.drag(fromViewHolder.getAdapterPosition(), target.getAdapterPosition());

        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        //左右滑动回调
        notifyListener.swipe(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {

        return true;
    }
}
