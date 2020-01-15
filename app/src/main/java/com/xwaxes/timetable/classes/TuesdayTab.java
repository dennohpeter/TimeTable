package com.xwaxes.timetable.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwaxes.timetable.R;

import java.util.ArrayList;
import java.util.List;

public class TuesdayTab extends Fragment {
    private static final String TAG = "TuesdayTab";

    private String[] unitCodes = {"413", "416", "419", "403", "401"};
    private String[] startTimes = {"7", "9", "11", "2", "4"};
    private String[] endTimes = {"9", "11", "12", "4", "6"};
    private String[] unitNames = {"Principles of Functional Programming", "Data Mining", "Intelligent Agents",
            "Computer Design Lab", "Computer Systems Engineering I"};
    private String[] lecturers = {"Kinya", "Kinya", "Mugoye", "Nyabundi", "McOyowo"};
    private String[] rooms = {"TB3", "LAB3", "NL6", "ELAB", "TB3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_day, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClassesModel> classes = new ArrayList<>();

        for (int i = 0; i < unitCodes.length; i++) {
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