package com.gogenius.learningdemos.action;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.gogenius.learningdemos.TabLayyout.fragment.TabLayoutFragment;

import java.util.List;

/**
 * Created by shijiwei on 2016/9/22.
 */
public class FeedbackActivity extends ActionActivity {


    @Override
    protected void updateData(List<String> tabLableSet, List<Fragment> fragmentSet, FragmentPagerAdapter fragmentPagerAdapter) {

        tabLableSet.add("ABC");
        tabLableSet.add("DEF");

        fragmentSet.add(new TabLayoutFragment().setTile("ABC"));
        fragmentSet.add(new TabLayoutFragment().setTile("EDF"));

        fragmentPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public void setTabLayoutParams(TabLayout tabLayout) {

    }
}
