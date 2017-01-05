package com.gogenius.learningdemos.cardlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.view.FlyCardVie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/10.
 */
public class CardListActivity extends Activity implements FlyCardVie.OnFlyOutOfTheScreenListener {

    private int[] mTitleColors = {R.color.darkkhaki,
            R.color.primary,
            R.color.olivedrab};


    private FlyCardVie first;
    private FlyCardVie second;
    private FlyCardVie third;
    private FlyCardVie fourth;

    private List<FlyCardVie> list;

    private CoordinatorLayout container;

    private static final String TAG = "CardListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nested_scroll_card_list);

        init();
    }


    private void init() {

        container = (CoordinatorLayout) findViewById(R.id.container);
        first = (FlyCardVie) findViewById(R.id.first);
        second = (FlyCardVie) findViewById(R.id.second);
        third = (FlyCardVie) findViewById(R.id.third);
        fourth = (FlyCardVie) findViewById(R.id.fourth);

        fourth.notifyTouchEvent();

        list = new ArrayList<>();
        list.add(first);
        list.add(second);
        list.add(third);
        list.add(fourth);

        fourth.setOnFlyOutOfTheScreenListener(this);
        third.setOnFlyOutOfTheScreenListener(this);
        second.setOnFlyOutOfTheScreenListener(this);
        first.setOnFlyOutOfTheScreenListener(this);


    }


    @Override
    public void onFlyOut(View view) {
        container.removeView(view);
        list.remove(view);
        if (list.size() != 0)
            list.get(list.size() - 1).notifyTouchEvent();
    }
}
