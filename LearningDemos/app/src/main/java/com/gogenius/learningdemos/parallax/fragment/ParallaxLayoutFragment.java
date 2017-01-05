package com.gogenius.learningdemos.parallax.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.view.ParallaxLayout;

/**
 * Created by shijiwei on 2016/9/12.
 */
public class ParallaxLayoutFragment extends Fragment {

    private View view;

    private ParallaxLayout mParallaxLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null){

            view = inflater.inflate(R.layout.fragment_parallaxlayout, container, false);
            initialView();
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    private void initialView() {

        mParallaxLayout = (ParallaxLayout) view.findViewById(R.id.parallax_layout);
        mParallaxLayout.setHeaderParallaxViewResource(R.mipmap.sea);


        LinearLayout headerView = (LinearLayout) getActivity().
                getLayoutInflater().inflate(R.layout.layout_header_parallax_listview,null);
        ImageView parallax = (ImageView) headerView.findViewById(R.id.parallax_image);



        TextView lable = new TextView(getActivity());
        LinearLayout.LayoutParams lt = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lable.setLayoutParams(lt);
        lable.setText(R.string.article);
        lable.setTextSize(24);

        mParallaxLayout.setHeaderView(headerView);
        mParallaxLayout.setParallaxView(parallax);
        mParallaxLayout.setHeaderParallaxViewResource(R.mipmap.sea);
        mParallaxLayout.addBodyView(lable);

    }
}
