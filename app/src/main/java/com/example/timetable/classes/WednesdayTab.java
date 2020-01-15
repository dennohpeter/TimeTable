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

public class WednesdayTab extends Fragment {
    private static final String TAG = "WednesdayTab";

    private String[] unitCodes = {"401", "403", "425", "405", "411"};
    private String[] startTimes = {"7", "9", "11", "2", "4"};
    private String[] endTimes = {"9", "11", "1", "4", "6"};
    private String[] unitNames = {"Computer Systems Engineering I", "Computer Design Lab", "CISCO III",
            "Computer Technology Project I", "Neural Networks"};
    private String[] lecturers = {"McOyowo", "Nyabundi", "Alwala", "All", "Okoyo"};
    private String[] rooms = {"TB3", "ELAB", "LAB1", "TB2", "TB3"};

    private int units = unitCodes.length;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClassesModel> classes = new ArrayList<>();

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