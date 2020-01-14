package com.example.timetable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;


    SectionsPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new TueFragment();
                break;
            case 2:
                fragment = new WedFragment();
                break;
            case 3:
                fragment = new ThurFragment();
                break;
            case 4:
                fragment = new FriFragment();
                break;
            default:
                fragment = new MonFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
