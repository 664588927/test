package com.gogenius.learningdemos.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gogenius.learningdemos.R;


/**
 * Created by shijiwei on 2016/10/17.
 */
public class SimpleDialog extends Dialog implements View.OnClickListener, Animation.AnimationListener {

    private View mDialogView;
    private ImageView mIvIcon;
    private TextView mTvTitle;
    private TextView mTvContent;
    private LinearLayout mButtonsLayout;
    private TextView mConfirmButton;
    private TextView mCancelButton;
    private TextView mButtonDivider;
    private TextView mDialogDivider;
    private Builder mBuilder;
    private AnimationSet mDialogInAnim;
    private AnimationSet mDialogOutAnim;
    private SimpleDialogClickListner mSimpleDialogClickListner;
    private static SimpleDialog mSimpleDialog;
    private boolean isDismiss = false;

    private SimpleDialog(Context context, Builder builder) {
        this(context, 0, builder);
    }

    private SimpleDialog(Context context, int themeResId, Builder builder) {
        super(context, R.style.simple_dialog);

        mBuilder = builder;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        getWindow().setGravity(Gravity.CENTER);
        mDialogInAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.dialog_in);
        mDialogOutAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.dialog_out);
        mDialogOutAnim.setAnimationListener(this);
        setContentView(R.layout.layout_simple_dialog);
        initialView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mDialogView.startAnimation(mDialogInAnim);
    }

    @Override
    public void dismiss() {
        if (!isDismiss){
            isDismiss = true;
            mDialogView.startAnimation(mDialogOutAnim);
        }
    }

    private void initialView() {
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mIvIcon = (ImageView) findViewById(R.id.dialog_icon);
        mTvTitle = (TextView) findViewById(R.id.dialog_title);
        mTvContent = (TextView) findViewById(R.id.dialog_content);
        mButtonsLayout = (LinearLayout) findViewById(R.id.dialog_buttons_lay);
        mCancelButton = (TextView) findViewById(R.id.cancel_button);
        mConfirmButton = (TextView) findViewById(R.id.confirm_button);
        mButtonDivider = (TextView) findViewById(R.id.button_divider);
        mDialogDivider = (TextView) findViewById(R.id.dialog_divider);

        setBuilder(mBuilder);

    }

    private void setBuilder(Builder builder) {

        this.mBuilder = builder;

        setIcon(builder.getIcon());
        setTitle(builder.getTitle());
        setContent(builder.getContent());
        setConfirmText(builder.getConfirmButtonText());
        setCancelText(builder.getCancelButtonText());

        changeLayoutDependOnBuilder();
    }

    public void resetBuilderAndShow(Builder builder){

        show();
        setBuilder(builder);
    }

    public void resetBuilderAndShowWithTime(Builder builder,long delayTimeMills){

        showWithTime(delayTimeMills);
        setBuilder(builder);
    }

    public Builder getBuilder(){
        return mBuilder;
    }


    private void changeLayoutDependOnBuilder() {

        if (!TextUtils.isEmpty(mBuilder.getContent())) {

            if (TextUtils.isEmpty(mBuilder.getConfirmButtonText()) && TextUtils.isEmpty(mBuilder.getCancelButtonText())) {

                /**
                 * just contain icon ，title and content
                 */

                LinearLayout.LayoutParams iconLp = (LinearLayout.LayoutParams) mIvIcon.getLayoutParams();
                iconLp.setMargins(0, 42, 0, 0);

                LinearLayout.LayoutParams titleLp = (LinearLayout.LayoutParams) mTvTitle.getLayoutParams();
                titleLp.setMargins(0, 20, 0, 0);

                mTvContent.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams contentLp = (LinearLayout.LayoutParams) mTvContent.getLayoutParams();
                contentLp.setMargins(0, 10, 0, 0);

                mDialogDivider.setVisibility(View.GONE);
                mButtonsLayout.setVisibility(View.GONE);

            } else {

                LinearLayout.LayoutParams iconLp = (LinearLayout.LayoutParams) mIvIcon.getLayoutParams();
                iconLp.setMargins(0, 30, 0, 0);

                LinearLayout.LayoutParams titleLp = (LinearLayout.LayoutParams) mTvTitle.getLayoutParams();
                titleLp.setMargins(0, 16, 0, 0);

                mTvContent.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams contentLp = (LinearLayout.LayoutParams) mTvContent.getLayoutParams();
                contentLp.setMargins(0, 8, 0, 0);

                mDialogDivider.setVisibility(View.VISIBLE);

                mButtonsLayout.setVisibility(View.VISIBLE);
                mConfirmButton.setOnClickListener(this);
                mCancelButton.setOnClickListener(this);
            }

        } else {

            /**
             * just contain icon and title
             */

            LinearLayout.LayoutParams iconLp = (LinearLayout.LayoutParams) mIvIcon.getLayoutParams();
            iconLp.setMargins(0, 60, 0, 0);

            LinearLayout.LayoutParams titleLp = (LinearLayout.LayoutParams) mTvTitle.getLayoutParams();
            titleLp.setMargins(0, 28, 0, 0);

            mTvContent.setVisibility(View.GONE);
            mDialogDivider.setVisibility(View.GONE);
            mButtonsLayout.setVisibility(View.GONE);


        }


    }


    public void setIcon(int resId) {

        mIvIcon.setImageResource(resId);
    }

    public void setTitle(String title) {

        mTvTitle.setText(title);
    }

    public void setContent(String content) {

        mTvContent.setText(content);
    }

    public void setConfirmText(String text) {

        mConfirmButton.setText(text);
    }

    public void setCancelText(String text) {

        mCancelButton.setText(text);
    }


    public void showWithTime(long delayedTimeMillis) {

        if (!this.isShowing())
            show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, delayedTimeMillis);

    }


    public SimpleDialog setSimpleDialogClickListner(SimpleDialogClickListner simpleDialogClickListner) {
        this.mSimpleDialogClickListner = simpleDialogClickListner;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_button:
                if (mSimpleDialogClickListner != null)
                    mSimpleDialogClickListner.onConfirmButtonClick(this);
                break;
            case R.id.cancel_button:
                if (mSimpleDialogClickListner != null)
                    mSimpleDialogClickListner.onCancelButtonClick(this);
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        isDismiss = false;
        super.dismiss();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    public interface SimpleDialogClickListner {

        void onConfirmButtonClick(SimpleDialog dialog);

        void onCancelButtonClick(SimpleDialog dialog);
    }

    public static class Builder {

        private Context mContext;

        private int mDialogIconRid;
        private String mDialogTitle;
        private String mDialogContent;
        private String mConfirmButtonText;
        private String mCancelButtonText;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setIcon(int resId) {
            this.mDialogIconRid = resId;
            return this;
        }

        public Builder setTitle(String title) {
            this.mDialogTitle = title;
            return this;
        }

        public Builder setContent(String content) {
            this.mDialogContent = content;
            return this;
        }

        public Builder setConfirmText(String text) {
            this.mConfirmButtonText = text;
            return this;
        }

        public Builder setCancelText(String text) {
            this.mCancelButtonText = text;
            return this;
        }


        public int getIcon() {
            return mDialogIconRid;
        }

        public String getTitle() {
            return mDialogTitle;
        }

        public String getContent() {
            return mDialogContent;
        }

        public String getConfirmButtonText() {
            return mConfirmButtonText;
        }

        public String getCancelButtonText() {
            return mCancelButtonText;
        }

        public Builder reset(){
            setIcon(0);
            setTitle(null);
            setContent(null);
            setConfirmText(null);
            setCancelText(null);
            return this;
        }

        public SimpleDialog build() {

            return new SimpleDialog(mContext, this);
        }


    }


}
