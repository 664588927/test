package com.gogenius.learningdemos.dialogUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DialerFilter;

import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2016/9/19.
 */
public class DialogMainActivity extends Activity {

    private static final String TAG = "DialogUIActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_ui);

        Log.e(TAG, "onCreate: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "onDestroy: ");
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_1:

                startActivity(new Intent(this, ActionSheet.class));
                break;
        }
    }
}
