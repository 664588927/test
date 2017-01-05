package com.gogenius.learningdemos.action;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.TabLayyout.TabLayoutFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/22.
 */
public abstract class ActionActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private FragmentPagerAdapter mFragmentPagerAdapter;

    private List<String> mTabLableSet;
    private List<Fragment> mFragmentSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        initialize();

        updateData(mTabLableSet, mFragmentSet, mFragmentPagerAdapter);

        setTabLayoutParams(mTabLayout);

    }

    /**
     * initialize the ui and data
     */

    private void initialize() {

        mTabLableSet = new ArrayList<>();
        mFragmentSet = new ArrayList<>();

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mFragmentPagerAdapter = new TabLayoutFragmentAdapter(
                getSupportFragmentManager(), mTabLableSet, mFragmentSet);

        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * update the data which bind to adapter
     *
     * @param tabLableSet
     * @param fragmentSet
     * @param fragmentPagerAdapter
     */
    protected abstract void updateData(List<String> tabLableSet,
                                       List<Fragment> fragmentSet,
                                       FragmentPagerAdapter fragmentPagerAdapter);

    /**
     * set the params of the TabLayout
     *
     * @param tabLayout
     */
    public abstract void setTabLayoutParams(TabLayout tabLayout);


}
