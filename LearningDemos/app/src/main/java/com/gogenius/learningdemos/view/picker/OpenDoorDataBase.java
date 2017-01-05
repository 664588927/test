package com.gogenius.learningdemos.view.picker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shijiwei on 2016/12/29.
 */
public class OpenDoorDataBase extends SQLiteOpenHelper {

    private final String VALUE_ID = "id";
    private final String VALUE_LAST_OPTION_TIME = "last_option_time";

    public OpenDoorDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
