package com.gogenius.learningdemos.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2016/11/9.
 */
public class BaseDialog extends Dialog implements Animation.AnimationListener {

    private Animation mDialongInanim;
    private Animation mDialongOutanim;

    private View mDialogView;

    public BaseDialog(Context context) {
        this(context, 0);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, R.style.simple_dialog);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void dismiss() {
        mDialongOutanim.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        super.dismiss();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
