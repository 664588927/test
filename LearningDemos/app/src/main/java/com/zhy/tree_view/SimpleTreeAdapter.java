package com.zhy.tree_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.tree.bean.Node;
import com.zhy.tree.bean.TreeListViewAdapter;

import java.util.List;

public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {

	private String mArea_id = null;
	private int normal = 0;
	private int seclet = 0;

	public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas,
			int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException {
		super(mTree, context, datas, defaultExpandLevel);
		//normal = context.getResources().getColor(R.color.deep_gray);
		//seclet = context.getResources().getColor(R.color.apple_blue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getConvertView(Node node, int position, View convertView,
			ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			//convertView = mInflater.inflate(R.layout.list_item, parent, false);
			//viewHolder = new ViewHolder();
			//viewHolder.icon = (ImageView) convertView
			//		.findViewById(R.id.id_treenode_icon);
			//viewHolder.label = (TextView) convertView
			//		.findViewById(R.id.id_treenode_label);
			//convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (node.getIcon() == -1) {
			viewHolder.icon.setVisibility(View.INVISIBLE);
		} else {
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(node.getIcon());
		}
		viewHolder.label.setText(node.getAreaName());
		if (mArea_id == null) {
			viewHolder.label.setTextColor(normal);
		} else {
			if (node.getAreaId().equals(mArea_id)) {
				viewHolder.label.setTextColor(seclet);
			} else {
				viewHolder.label.setTextColor(normal);
			}
		}

		return convertView;
	}

	private final class ViewHolder {
		ImageView icon;
		TextView label;
	}

	public void setSectlet(String area_id) {
		mArea_id = area_id;
		this.notifyDataSetChanged();
	}

}
