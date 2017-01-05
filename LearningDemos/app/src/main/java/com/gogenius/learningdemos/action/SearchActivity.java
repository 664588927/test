package com.gogenius.learningdemos.action;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.view.HotSearchLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/11/10.
 */
public class SearchActivity extends Activity {


    private ArrayAdapter<String> mHistoryRecordAdapter;
    private List<String> mHistoryRecordSet;

    private HotSearchLayout xcf_layout;

    private ImageView loadMore;

    private AnimationDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_search);

        mHistoryRecordSet = new ArrayList<>();

        mHistoryRecordSet.add("sssss");
        mHistoryRecordSet.add("白吃");
        mHistoryRecordSet.add("皮大衣");
        mHistoryRecordSet.add("皮大衣白吃");
        mHistoryRecordSet.add("皮大衣白吃免费尼玛皮大衣白吃免费尼玛皮大衣白吃免费尼玛皮大衣白吃免费尼玛皮大衣白吃免费尼玛皮大衣白吃免费尼玛");
        mHistoryRecordSet.add("皮大衣白吃是是");
        mHistoryRecordSet.add("sssss");
        mHistoryRecordSet.add("白吃");
        mHistoryRecordSet.add("皮大衣");
        mHistoryRecordSet.add("皮大衣白吃");
        mHistoryRecordSet.add("皮大衣白吃免费尼玛");

        xcf_layout = (HotSearchLayout) findViewById(R.id.xcf_layout);
        xcf_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.start();
            }
        });

        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < mHistoryRecordSet.size(); i++) {
            TextView view = new TextView(this);
            view.setTextSize(14);
            view.setText(mHistoryRecordSet.get(i));
            view.setTextColor(Color.WHITE);
            view.setBackgroundResource(R.drawable.shape_message_search_labe_bg);
            xcf_layout.addView(view, lp);
        }


        loadMore = (ImageView) findViewById(R.id.load_more);
        drawable = (AnimationDrawable) loadMore.getDrawable();
        drawable.start();
    }

    int count = 0;

    public void onClick(View view) {

    }
}
