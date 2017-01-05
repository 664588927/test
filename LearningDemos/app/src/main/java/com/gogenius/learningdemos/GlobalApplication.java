package com.gogenius.learningdemos;

import android.app.Application;

/**
 * Created by shijiwei on 2016/11/22.
 */
public class GlobalApplication extends Application {

    public static GlobalApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public GlobalApplication() {
    }

    public static GlobalApplication getApplication() {
        return application;
    }
}
