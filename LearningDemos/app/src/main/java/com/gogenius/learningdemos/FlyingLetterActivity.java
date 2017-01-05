package com.gogenius.learningdemos;

import android.app.Activity;
import android.os.Bundle;

import com.gogenius.learningdemos.view.header.StoreHouseHeader;

/**
 * Created by shijiwei on 2016/12/5.
 */
public class FlyingLetterActivity extends Activity {

    StoreHouseHeader header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flying_lerrer);
        //header = (StoreHouseHeader) findViewById(R.id.header);
        //header.setPadding(0, PtrLocalDisplay.dp2px(20), 0, PtrLocalDisplay.dp2px(20));
        //header.initWithString("M O R E");

    }
}
