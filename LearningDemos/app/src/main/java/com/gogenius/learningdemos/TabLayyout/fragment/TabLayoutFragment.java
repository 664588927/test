package com.gogenius.learningdemos.TabLayyout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2016/9/10.
 */
public class TabLayoutFragment extends Fragment {

    private static final String TAG = "TabLayoutFragment";
    private View view;

    private TextView mTitle;

    private String mTitleContent;


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        Log.e(TAG, mTitleContent + " onHiddenChanged " + hidden);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, mTitleContent + " onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.e(TAG, mTitleContent + " onCreateView");

        view = inflater.inflate(R.layout.fragment_tab_layout, container, false);

        initialView();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e(TAG, mTitleContent + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e(TAG, mTitleContent + " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e(TAG, mTitleContent + " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.e(TAG, mTitleContent + " onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e(TAG, mTitleContent + " onDestroy");
    }

    private void initialView() {

        mTitle = (TextView) view.findViewById(R.id.fragment_title);
        mTitle.setText(mTitleContent);
    }

    public TabLayoutFragment setTile(String titlle) {

       this.mTitleContent = titlle;

        return this;
    }
}
