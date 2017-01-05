package com.gogenius.learningdemos.parallax.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.view.ParallaxListView;

/**
 * Created by shijiwei on 2016/9/12.
 */
public class ParallaxListViewFragment extends Fragment {

    private View view;

    private ParallaxListView mParallaxListView;
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_parallax_listview, container, false);

        initialView();
        return view;
    }

    private void initialView() {
        mParallaxListView = (ParallaxListView) view.findViewById(R.id.parallax_listview);

        mArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        mParallaxListView.setAdapter(mArrayAdapter);

        char letter = 'A';
        for (int i = 0; i < 26; i++)
            mArrayAdapter.add("—————————— " + (char) (letter + i));

        mParallaxListView.setHeaderParallaxViewResource(R.mipmap.sea);

        mArrayAdapter.notifyDataSetChanged();


    }
}
