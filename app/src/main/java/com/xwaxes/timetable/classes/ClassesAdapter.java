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

        holder.textViewHead.setText(lesson.getUnitCodeAndVenue());
        holder.textViewDesc.setText(lesson.getTime());
        holder.textViewLesson.setText(lesson.getUnitName());
        holder.textViewLecturer.setText(lesson.getLecturerName());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHead;
        TextView textViewDesc;
        TextView textViewLecturer;
        TextView textViewLesson;
        ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = itemView.findViewById(R.id.duration);
            textViewDesc = itemView.findViewById(R.id.unit_code_and_venue);
            textViewLesson = itemView.findViewById(R.id.unit_name);
            textViewLecturer = itemView.findViewById(R.id.lecturer_name);
        }
    }
}
