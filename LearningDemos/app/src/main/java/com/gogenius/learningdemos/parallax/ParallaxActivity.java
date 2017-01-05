package com.gogenius.learningdemos.parallax;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.TabLayyout.TabLayoutFragmentAdapter;
import com.gogenius.learningdemos.parallax.fragment.ParallaxLayoutFragment;
import com.gogenius.learningdemos.parallax.fragment.ParallaxListViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/10.
 */
public class ParallaxActivity extends FragmentActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayoutFragmentAdapter mAdapter;
    private List<String> mTitleSet;
    private List<Fragment> mFragmentSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_listview);

        initialView();
    }

    private void initialView() {


        mTitleSet = new ArrayList<>();
        mTitleSet.add("parallaxListView");
        mTitleSet.add("parallaxLayout");

        mFragmentSet = new ArrayList<>();
        mFragmentSet.add(new ParallaxListViewFragment());
        mFragmentSet.add(new ParallaxLayoutFragment());

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mAdapter = new TabLayoutFragmentAdapter(getSupportFragmentManager(), mTitleSet, mFragmentSet);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}
