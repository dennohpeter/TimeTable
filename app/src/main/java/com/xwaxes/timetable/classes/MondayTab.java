package com.xwaxes.timetable.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwaxes.timetable.ReadFromFileHelper;
import com.xwaxes.timetable.R;

import java.util.List;

public class MondayTab extends Fragment {
    private static final String TAG = "MondayTab";
    private RecyclerView recyclerView;
    private LinearLayout no_classes;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_day, container, false);
        no_classes = view.findViewById(R.id.no_classes);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ClassesModel> classes = new ReadFromFileHelper(getContext(), R.raw.monday).classes();
        populateRecyclerView(classes);
        return view;
    }
    private void populateRecyclerView(List<ClassesModel> classes) {
        if (classes.size() == 0 ){
            recyclerView.setVisibility(View.GONE);
            no_classes.setVisibility(View.VISIBLE);

        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            no_classes.setVisibility(View.GONE);
        }

        ClassesAdapter adapter = new ClassesAdapter(getContext(), classes);
        recyclerView.setAdapter(adapter);
    }

}