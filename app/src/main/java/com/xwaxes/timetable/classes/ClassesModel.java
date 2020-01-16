package com.xwaxes.timetable.classes;

public class ClassesModel {
    private String unit_code_and_venue;
    private String time;
    private String unit_name;
    private String lecturer_name;
    private String unit_category;

    public ClassesModel(String unit_code_and_venue, String time, String unit_name, String lecturer_name, String unit_category) {
        this.unit_code_and_venue = unit_code_and_venue;
        this.time = time;
        this.unit_name = unit_name;
        this.lecturer_name = lecturer_name;
        this.unit_category = unit_category;
    }

    String getUnitCodeAndVenue() {
        return unit_code_and_venue;
    }

    String getTime() {
        return time;
    }

    String getUnitName() { return unit_name; }

    String getLecturerName() { return lecturer_name; }

    public String getUnitCategory() {
        return unit_category;
    }

    @Override
    public String toString() {
        return "ClassesModel{" +
                "unit_code_and_venue='" + unit_code_and_venue + '\'' +
                ", time='" + time + '\'' +
                ", unit_name='" + unit_name + '\'' +
                ", lecturer_name='" + lecturer_name + '\'' +
                ", unit_category='" + unit_category + '\'' +
                '}';
    }
}
