package com.xwaxes.timetable.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xwaxes.timetable.R;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ClassViewHolder> {

    private List<ClassesModel> classes;
    private Context context;

    ClassesAdapter(Context context, List<ClassesModel> classes) {
        this.classes = classes;
        this.context = context;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_card, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        ClassesModel lesson = this.classes.get(position);

        holder.time.setText(lesson.getTime());
        holder.unit_code_and_venue.setText(lesson.getUnitCodeAndVenue());
        holder.unit_name.setText(lesson.getUnitName());
        holder.lecturer_name.setText(lesson.getLecturerName());
        holder.unit_category.setText(lesson.getUnitCategory());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView time, unit_code_and_venue, lecturer_name, unit_name, unit_category;

        ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.duration);
            unit_code_and_venue = itemView.findViewById(R.id.unit_code_and_venue);
            unit_name = itemView.findViewById(R.id.unit_name);
            lecturer_name = itemView.findViewById(R.id.lecturer_name);
            unit_category = itemView.findViewById(R.id.unit_category);
        }
    }
}
