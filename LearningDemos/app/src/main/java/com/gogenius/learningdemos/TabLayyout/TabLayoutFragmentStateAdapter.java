package com.gogenius.learningdemos.TabLayyout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by shijiwei on 2016/9/10.
 */
public class TabLayoutFragmentStateAdapter extends FragmentStatePagerAdapter {


    private List<String> mTitleSet;
    private List<Fragment> mFragmentSet;

    public TabLayoutFragmentStateAdapter(FragmentManager fm, List<String> mTitleSet, List<Fragment> mFragmentSet) {
        super(fm);
        this.mTitleSet = mTitleSet;
        this.mFragmentSet = mFragmentSet;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentSet.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentSet.size();
    }

    //此方法用来显示tab上的名字

    @Override
    public CharSequence getPageTitle(int position) {

        return mTitleSet.get(position);
    }
}
