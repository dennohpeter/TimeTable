package com.xwaxes.timetable.classes;

class ClassesModel {
    private String unit_code_and_venue;
    private String time;
    private String unit_name;
    private String lecturer_name;

    ClassesModel(String unit_code_and_venue, String time, String unit_name, String lecturer_name) {
        this.unit_code_and_venue = unit_code_and_venue;
        this.time = time;
        this.unit_name = unit_name;
        this.lecturer_name = lecturer_name;
    }

    String getUnitCodeAndVenue() {
        return unit_code_and_venue;
    }

    String getTime() {
        return time;
    }

    String getUnitName() { return unit_name; }

    String getLecturerName() { return lecturer_name; }
}
