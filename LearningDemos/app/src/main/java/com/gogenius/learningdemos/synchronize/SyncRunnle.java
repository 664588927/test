package com.gogenius.learningdemos.synchronize;

import android.util.Log;

/**
 * Created by shijiwei on 2016/12/21.
 */
public class SyncRunnle implements Runnable {

    private static final String TAG = "SyncRunnle";

    int mTotalTicket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (mTotalTicket > 0)
                Log.e(TAG, " ,  余票：" + (mTotalTicket--));
        }
    }
}
