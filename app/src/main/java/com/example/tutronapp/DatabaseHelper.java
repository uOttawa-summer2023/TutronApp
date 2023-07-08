package com.example.tutronapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "complaints_database";
    private static final String TABLE_COMPLAINTS = "complaints";
    private static final String KEY_ID = "id";
    private static final String KEY_STUDENT_NAME = "student_name";
    private static final String KEY_TUTOR_NAME = "tutor_name";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COMPLAINTS_TABLE = "CREATE TABLE " + TABLE_COMPLAINTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_STUDENT_NAME + " TEXT,"
                + KEY_TUTOR_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_COMPLAINTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLAINTS);
        onCreate(db);
    }

    public void addComplaint(Complaints complaint) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, complaint.getStudentName());
        values.put(KEY_TUTOR_NAME, complaint.getTutorName());
        values.put(KEY_DESCRIPTION, complaint.getDescription());
        db.insert(TABLE_COMPLAINTS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Complaints> getAllComplaints() {

            List<Complaints> complaintsList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {
                    KEY_STUDENT_NAME,
                    KEY_TUTOR_NAME,
                    KEY_DESCRIPTION
            };

            Cursor cursor = db.query(
                    TABLE_COMPLAINTS,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );


                while (cursor.moveToNext()) {

                    String studentName = cursor.getString(cursor.getColumnIndex(KEY_STUDENT_NAME));
                    String tutorName = cursor.getString(cursor.getColumnIndex(KEY_TUTOR_NAME));
                    String description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));

                Complaints complaint = new Complaints(studentName, tutorName, description);
                complaintsList.add(complaint);
            }

            cursor.close();
            db.close();
           return complaintsList;


        }
}



