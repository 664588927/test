package com.gogenius.learningdemos.ItemTouch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.gogenius.learningdemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/7.
 */
public class ItemTouchActivity extends Activity {

    private Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchAdapter mItemTouchAdapter;

    private List<String> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch);

        mContext = this;

        initial();

    }

    private void initial() {

        dataSet = new ArrayList<>();
        char letter = 'A';
        for (int i = 0; i < 26; i++)
            dataSet.add((char) (letter + i) + "");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mItemTouchAdapter = new ItemTouchAdapter(dataSet);
        mRecyclerView.setAdapter(mItemTouchAdapter);

        ItemTouchHelperCallBack callBack = new ItemTouchHelperCallBack();
        callBack.setItemTouchNotifyListener(mItemTouchAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callBack);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
