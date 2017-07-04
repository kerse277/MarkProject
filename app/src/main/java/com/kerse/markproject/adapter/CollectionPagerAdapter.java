package com.kerse.markproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kerse.markproject.fragment.CollectedCollectionFragment;
import com.kerse.markproject.fragment.MyCollectedCollectionFragment;

/**
 * Created by KAPLAN on 13.06.2017.
 */

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

    int NumOfTabs;
    private String fragments[] = {"Collected", "My Collected"};

    public CollectionPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.NumOfTabs=NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CollectedCollectionFragment();
            case 1:
                return new MyCollectedCollectionFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
