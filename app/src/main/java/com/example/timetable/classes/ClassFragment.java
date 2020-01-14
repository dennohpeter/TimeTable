package com.example.timetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.tabbedactivity.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class ClassFragment extends Fragment {
private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_class, container, false);


        //Set the tab layout and its qualities
        TabLayout tabLayout = root.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // setting up tabs in class fragment
        tabLayout.addTab(tabLayout.newTab().setText("Monday"));
        tabLayout.addTab(tabLayout.newTab().setText("Tuesday"));
        tabLayout.addTab(tabLayout.newTab().setText("Wednesday"));
        tabLayout.addTab(tabLayout.newTab().setText("Thursday"));
        tabLayout.addTab(tabLayout.newTab().setText("Friday"));

        viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(new SectionsPageAdapter(getChildFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //Retrieves the day of the week and opens the app on that tab
        setdefaultTab();
        return root;
    }
//    TODO fix this
   private void setdefaultTab(){
       Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.TUESDAY:
                viewPager.setCurrentItem(1);
                break;
            case Calendar.WEDNESDAY:
                viewPager.setCurrentItem(2);
                break;
            case Calendar.THURSDAY:
                viewPager.setCurrentItem(3);
                break;
            case Calendar.FRIDAY:
                viewPager.setCurrentItem(4);
                break;
            default:
                viewPager.setCurrentItem(0);
        }
    }
}
