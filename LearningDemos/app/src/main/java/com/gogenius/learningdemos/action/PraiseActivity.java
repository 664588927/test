package com.gogenius.learningdemos.action;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.gogenius.learningdemos.TabLayyout.fragment.TabLayoutFragment;

import java.util.List;

/**
 * Created by shijiwei on 2016/9/22.
 */
public class PraiseActivity extends ActionActivity {


    @Override
    protected void updateData(List<String> tabLableSet, List<Fragment> fragmentSet, FragmentPagerAdapter fragmentPagerAdapter) {

        tabLableSet.add("UFO");
        tabLableSet.add("CAR");

        fragmentSet.add(new TabLayoutFragment().setTile("UFO"));
        fragmentSet.add(new TabLayoutFragment().setTile("CAR"));

        fragmentPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setTabLayoutParams(TabLayout tabLayout) {

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff0000"));
    }

}
