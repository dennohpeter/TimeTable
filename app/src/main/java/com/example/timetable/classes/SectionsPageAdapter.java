package com.example.timetable.classes;

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
        Fragment tab;
        switch (position) {
            case 1:
                tab = new TuesdayTab();
                break;
            case 2:
                tab = new WednesdayTab();
                break;
            case 3:
                tab = new ThursdayTab();
                break;
            case 4:
                tab = new FridayTab();
                break;
            default:
                tab = new MondayTab();
        }
        return tab;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
