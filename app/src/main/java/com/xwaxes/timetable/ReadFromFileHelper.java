package com.xwaxes.timetable;

import android.content.Context;
import android.util.Log;

import com.xwaxes.timetable.classes.ClassesModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFileHelper {
    private static final String TAG = "ReadFromFileHelper";
    private int file_name;
    private Context context;

    private String unit_code_and_venue, time, unit_name, lecturer_name, unit_category;

    public ReadFromFileHelper(Context context, int file_name) {
        this.context = context;
        this.file_name = file_name;
    }

    public List<ClassesModel> classes() {
        List<ClassesModel> classes = new ArrayList<>();
        InputStream inputStream = context.getResources().openRawResource(file_name);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );
        String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    // Split by delimiter ,
                    String[] tokens = line.split(",");

                    // Read the data
                    unit_code_and_venue = tokens[0];
                    time = tokens[1];
                    unit_name = tokens[2];
                    lecturer_name = tokens[3];
                    unit_category = tokens[4];

                    // Add read data to the classes arrayList
                    classes.add(new ClassesModel(unit_code_and_venue, time, unit_name, lecturer_name, unit_category));

                    Log.d(TAG, "Just created: " + classes);
                }

            } catch (IOException e) {
                Log.wtf(TAG, "Error Reading data from file on line: " + line, e);
                e.printStackTrace();
            }

        return classes;

    }

    public void exams(){

    }
}
