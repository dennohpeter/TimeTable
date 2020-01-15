package com.example.timetable.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetable.R;

import java.util.ArrayList;
import java.util.List;

public class ThursdayTab extends Fragment {
    private static final String TAG = "ThursdayTab";

    private String[] unitCodes = {"411"};
    private String[] startTimes = {"12"};
    private String[] endTimes = {"1"};
    private String[] unitNames = {"Neural Networks"};
    private String[] lecturers = {"Okoyo"};
    private String[] rooms = {"TB2"};

    private int units = unitCodes.length;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClassesModel> classes = new ArrayList<>();
        // generating classes and adding them to classes arrayList.
        for (int i = 0; i < units; i++) {
            classes.add( new ClassesModel(
                    startTimes[i] + ":00 - " + endTimes[i] + ":00",
                    "CCT " + unitCodes[i] + " | " + rooms[i],
                    unitNames[i],
                    lecturers[i]
            ));
        }
        ClassesAdapter adapter = new ClassesAdapter(getContext(), classes);
        recyclerView.setAdapter(adapter);
        return view;
    }
}