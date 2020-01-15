package com.example.timetable.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetable.R;

import java.util.ArrayList;
import java.util.List;

public class MondayTab extends Fragment {
    private static final String TAG = "MondayTab";

    private String[] unitCodes = {"416", "419", "413"};
    private String[] startTimes = {"11", "2", "4"};
    private String[] endTimes = {"1", "4", "5"};
    private String[] unitNames = {"Data Mining", "Intelligent Agents", "Principles of Functional Programming"};
    private String[] lecturers = {"Kinya", "Mugoye", "Kinya"};
    private String[] rooms = {"NL7", "TB2", "TB2"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClassesModel> classes = new ArrayList<>();

        int units = 3;
        for (int i = 0; i < units; i++) {
            classes.add(new ClassesModel(
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