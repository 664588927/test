package com.gogenius.learningdemos.ItemTouch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gogenius.learningdemos.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/6.
 */
public class ItemTouchAdapter extends
        RecyclerView.Adapter<ItemTouchAdapter.ItemTouchViewHolder>
        implements ItemTouchNotifyListener{

    private List<String> dataSet;

    public ItemTouchAdapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ItemTouchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_single_text,parent,false);
        return new ItemTouchViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onBindViewHolder(ItemTouchViewHolder holder, int position) {

        holder.lable.setText(dataSet.get(position));
    }

    @Override
    public void drag(int fromPosition, int toPosition) {

        Collections.swap(dataSet, fromPosition, fromPosition);

        notifyItemMoved(fromPosition, toPosition);

    }

    @Override
    public void swipe(int position) {

        dataSet.remove(position);

        notifyItemRemoved(position);
    }


    protected class ItemTouchViewHolder extends RecyclerView.ViewHolder {

        TextView lable;

        public ItemTouchViewHolder(View itemView) {
            super(itemView);
            lable = (TextView) itemView.findViewById(R.id.lable);
        }
    }
}
