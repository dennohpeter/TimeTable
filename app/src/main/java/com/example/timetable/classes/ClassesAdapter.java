package com.example.timetable.classes;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timetable.R;

import java.util.List;

public class ClassesAdapter extends RecyclerView.Adapter <ClassesAdapter.ClassViewHolder> {

    private List<ClassesModel> classesModel;
    private Context context;

    ClassesAdapter(Context context, List<ClassesModel> classesModel) {
        this.classesModel = classesModel;
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
        ClassesModel classesModel = this.classesModel.get(position);

        holder.textViewHead.setText(classesModel.getHead());
        holder.textViewDesc.setText(classesModel.getDesc());
        holder.textViewLesson.setText(classesModel.getLesson());
        holder.textViewLecturer.setText(classesModel.getLecturer());
    }

    @Override
    public int getItemCount() {
        return classesModel.size();
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
