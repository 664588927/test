package com.gogenius.learningdemos.swipemenulistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gogenius.learningdemos.R;

import java.util.List;

/**
 * Created by shijiwei on 2016/9/7.
 */
public class SwipeMenuAdapter extends BaseAdapter {

    private List<String> dataSet;
    private Context mContext;

    public SwipeMenuAdapter(Context mContext, List<String> dataSet) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_single_text, null);
            holder.lable = (TextView) view.findViewById(R.id.lable);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.lable.setText(dataSet.get(i));
        return view;
    }

    class ViewHolder {
        TextView lable;
    }
}
