package com.gogenius.learningdemos.keywordsynchronized;

import android.util.Log;

/**
 * 模拟售票（现剩1张票）
 */
class Seller implements Runnable {

    private static final String TAG = "SyncRunnable";

    private static Seller mSeller;

    private int count = 1;

    private Seller() {
    }

    public static Seller build(){

        if (mSeller == null)
            mSeller = new Seller();

        return mSeller;
    }

    public void run() {

//        synchronized (this) {

            if (count > 0) {
                count--;
                Log.e(TAG, "售票成功,当前余票：" + count);
            }

//        }
    }

}
