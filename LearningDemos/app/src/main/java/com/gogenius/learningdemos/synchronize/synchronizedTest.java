package com.gogenius.learningdemos.synchronize;

/**
 * Created by shijiwei on 2016/12/21.
 */
public class synchronizedTest {

    private static final String TAG = "synchronizedTest";

    public static void main(String[] args) {
        SyncRunnle runnle = new SyncRunnle();
        new Thread(runnle, "a").start();
        new Thread(runnle, "b").start();
        new Thread(runnle, "c").start();
    }

}
