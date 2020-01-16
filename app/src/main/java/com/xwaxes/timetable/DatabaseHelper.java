package com.xwaxes.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xwaxes.timetable.classes.ClassesModel;

import java.util.ArrayList;
import java.util.List;

// TODO implement the database helper
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "classes.db";
    private static final String CREATE_QUERY = "CREATE TABLE classes (unit_name TEXT, time TEXT,  unit_code_and_name TEXT, lecturer_name TEXT, day_of_week TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void create_or_update_classes(SQLiteDatabase db, String table,  String unit_name, String time, String unit_code_and_name, String lecturer_name, String day_of_week) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("unit_name", unit_name);
        contentValues.put("time", time);
        contentValues.put("unit_code_and_name", unit_code_and_name);
        contentValues.put("lecturer_name", lecturer_name);
        contentValues.put("day_of_week", day_of_week);

        int id = (int) db.insertWithOnConflict(table, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1){
            db.update(table, contentValues, "unit_name=?",new String[] {unit_name});
        }
    }

     public List<ClassesModel> getClasses(SQLiteDatabase db, String table, String day_of_week) {
        String unit_name, time, unit_code_and_name, lecturer_name, unit_category;
        String[] columns = {"unit_name", "time", "unit_code_and_name", "lecturer_name"};
        String where = "day_of_week=?";
        String[] whereArgs = new String[]{day_of_week};
        Cursor cursor = db.query(table, columns, where, whereArgs, null, null, null);
        List<ClassesModel> classes = new ArrayList<>();
         while (cursor.moveToNext()) {
             unit_name = cursor.getString(cursor.getColumnIndex("unit_name"));
             time = cursor.getString(cursor.getColumnIndex("time"));
             unit_code_and_name = cursor.getString(cursor.getColumnIndex("unit_code_and_name"));
             lecturer_name = cursor.getString(cursor.getColumnIndex("lecturer_name"));
             unit_category = cursor.getString(cursor.getColumnIndex("unit_category"));

             classes.add(new ClassesModel(unit_name, time, unit_code_and_name, lecturer_name, unit_category));

         }
         cursor.close();
         return classes;
    }
}
