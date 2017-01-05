package com.gogenius.learningdemos.xRefreshView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2016/9/26.
 */
public class XRefreshViewActivity extends Activity {


    private XRefreshView xRefreshView;
    private ListView mListView;

    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_refresh_view);


        initializeView();

    }

    private void initializeView() {

//        mListView = (ListView) findViewById(R.id.list_view);
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
//        mListView.setAdapter(arrayAdapter);
//        for (int i = 0; i < 30; i++) {
//            arrayAdapter.add(System.currentTimeMillis() + " " + i);
//        }

        xRefreshView = (XRefreshView) findViewById(R.id.x_refresh_view);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setPinnedTime(500);
        xRefreshView.setHeaderType(XRefreshView.TEXT_PROGRESS_TYPE);

        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRefreshView.stopRefresh();
                    }
                }, 2 * 1000);

            }

            @Override
            public void onLoadMore(boolean isSilence) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRefreshView.stopLoadMore();
                    }
                }, 2 * 1000);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }
}
