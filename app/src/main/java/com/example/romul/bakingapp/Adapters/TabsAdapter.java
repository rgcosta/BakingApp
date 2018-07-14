package com.example.romul.bakingapp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TabsAdapter extends FragmentPagerAdapter implements Serializable {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabTitles = new ArrayList<>();

    public TabsAdapter(FragmentManager fm){
        super(fm);
    }

    public void addData(Fragment fragment, String tabName){
        this.mFragments.add(fragment);
        this.mTabTitles.add(tabName);
        //notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
