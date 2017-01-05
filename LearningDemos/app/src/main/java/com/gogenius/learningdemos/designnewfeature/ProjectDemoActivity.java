package com.gogenius.learningdemos.designnewfeature;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.view.LineChart;
import com.gogenius.learningdemos.view.LoopingLable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by shijiwei on 2016/9/21.
 */
public class ProjectDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_main);

        LoopingLable loopingView = (LoopingLable) findViewById(R.id.looperview);

        List<String> list = new ArrayList<>();
        list.add("今天打折了");
        list.add("打到你骨折");
        list.add("全场1折");
        list.add("老板娘不卖");
        loopingView.setLableSet(list);

        List<Integer> list1 = new ArrayList<>();
        list1.add(23);
        list1.add(1);
        list1.add(8);
        list1.add(28);
        list1.add(-52);
        list1.add(22);
        list1.add(28);
        list1.add(-2);

        list1.add(23);
        list1.add(43);
        list1.add(1);
        list1.add(8);
        list1.add(28);
        list1.add(-82);
        list1.add(22);
        list1.add(28);

        list1.add(23);
        list1.add(43);
        list1.add(1);
        list1.add(8);
        list1.add(28);
        list1.add(-52);
        list1.add(22);
        list1.add(28);

        list1.add(23);
        list1.add(43);
        list1.add(1);
        list1.add(8);
        list1.add(28);
        list1.add(-102);
        list1.add(22);
        list1.add(28);

        list1.add(23);
        list1.add(43);
        list1.add(1);
        list1.add(8);
        list1.add(28);
        list1.add(-52);
        list1.add(22);
        list1.add(28);

        LineChart brokenLine = (LineChart) findViewById(R.id.broken_line);

        brokenLine.setValueSet(list1);

        brokenLine.setCircleColor(Color.parseColor("#00ff00"));

        brokenLine.setCircleRadius(3);




    }


}
