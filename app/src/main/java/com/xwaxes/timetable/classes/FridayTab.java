package com.xwaxes.timetable.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xwaxes.timetable.R;

public class FridayTab extends Fragment {
    private static final String TAG = "FridayTab";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.no_classes, container, false);
    }
}