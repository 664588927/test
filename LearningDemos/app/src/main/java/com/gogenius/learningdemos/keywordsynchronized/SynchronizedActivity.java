package com.gogenius.learningdemos.keywordsynchronized;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by shijiwei on 2016/9/17.
 */
public class SynchronizedActivity extends Activity {

    private static final String TAG = "SynchronizedActivity";

    int cusor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Seller mSeller = Seller.build();

        Thread tA = new Thread(mSeller,"A");
        Thread tB = new Thread(mSeller,"B");
        Thread tC = new Thread(mSeller,"C");

        tA.start();
        tB.start();
        tC.start();

    }



}
