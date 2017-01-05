package com.gogenius.learningdemos.TabLayyout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.TabLayyout.fragment.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/10.
 */
public class TabLayoutActivity extends FragmentActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private TabLayoutFragmentAdapter mFragmentAdapter;
    private TabLayoutFragmentStateAdapter mFragmentStateAdapter;
    private List<String> mTitleSet;
    private List<Fragment> mFragmentSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        initialView();

        bindDataSet2View();
    }

    private void bindDataSet2View() {

        mTitleSet = new ArrayList<>();
        mTitleSet.add("热门推荐");
        mTitleSet.add("热门收藏");
        mTitleSet.add("本月热榜");
        mTitleSet.add("今日热榜");
        mTitleSet.add("今年热榜");

        mFragmentSet = new ArrayList<>();
        mFragmentSet.add(new TabLayoutFragment().setTile("热门推荐"));
        mFragmentSet.add(new TabLayoutFragment().setTile("热门收藏"));
        mFragmentSet.add(new TabLayoutFragment().setTile("本月热榜"));
        mFragmentSet.add(new TabLayoutFragment().setTile("今日热榜"));
        mFragmentSet.add(new TabLayoutFragment().setTile("今年热榜"));

        mFragmentAdapter = new TabLayoutFragmentAdapter(getSupportFragmentManager(),mTitleSet,mFragmentSet);
        mFragmentStateAdapter =new TabLayoutFragmentStateAdapter(getSupportFragmentManager(),mTitleSet,mFragmentSet);

//        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setAdapter(mFragmentStateAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initialView() {

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

    }


}
