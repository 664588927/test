package com.gogenius.learningdemos.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by shijiwei on 2016/11/2.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        this(context, 0);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
