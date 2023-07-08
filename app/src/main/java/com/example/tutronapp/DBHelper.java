package com.example.tutronapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.biometrics.BiometricManager;
import android.telephony.mbms.StreamingServiceInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "registration.db";

    // Table name and column names for tutors
    private static final String TABLE_TUTORS = "tutors";
    private static final String COLUMN_TUTOR_ID = "id";
    private static final String COLUMN_TUTOR_FIRSTNAME = "tut_fName";
    private static final String COLUMN_TUTOR_LASTNAME = "tut_lName";
    private static final String COLUMN_TUTOR_EMAIL = "email";
    private static final String COLUMN_TUTOR_PASSWORD = "password";
    private static final String COLUMN_TUTOR_EDUCATION = "education";
    private static final String COLUMN_TUTOR_NATIVE_LANGUAGE = "native_language";
    private static final String COLUMN_TUTOR_ROLE = "tutor";
    private static final String COLUMN_TUTOR_DESCRIPTION = "description";

    // Table name and column names for students
    private static final String TABLE_STUDENTS = "students";

    private static final String COLUMN_STUDENT_FIRSTNAME = "std_fName";

    private static final String COLUMN_STUDENT_LASTNAME = "std_lName";
    private static final String COLUMN_STUDENT_ID = "id";
    private static final String COLUMN_STUDENT_EMAIL = "email";
    private static final String COLUMN_STUDENT_PASSWORD = "password";
    private static final String COLUMN_STUDENT_ADDRESS = "address";
    private static final String COLUMN_STUDENT_ROLE = "student";
    private static final String COLUMN_STUDENT_CREDIT_CARD = "credit_card";

    private static final String COLUMN_TUTOR_STATUS = "status";

    private String role;
    private String status;
    Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table for tutors
        String createTutorsTableQuery = "CREATE TABLE " + TABLE_TUTORS + "("
                + COLUMN_TUTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TUTOR_FIRSTNAME + " TEXT,"
                + COLUMN_TUTOR_LASTNAME + " TEXT,"
                + COLUMN_TUTOR_EMAIL + " TEXT,"
                + COLUMN_TUTOR_PASSWORD + " TEXT,"
                + COLUMN_TUTOR_ROLE + " TEXT,"
                + COLUMN_TUTOR_EDUCATION + " TEXT,"
                + COLUMN_TUTOR_NATIVE_LANGUAGE + " TEXT,"
                + COLUMN_TUTOR_DESCRIPTION + " TEXT"
                + COLUMN_TUTOR_STATUS + "TEXT"
                + ")";
        db.execSQL(createTutorsTableQuery);

        // Create table for students
        String createStudentsTableQuery = "CREATE TABLE " + TABLE_STUDENTS + "("
                + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_STUDENT_FIRSTNAME + " TEXT,"
                + COLUMN_STUDENT_LASTNAME + " TEXT,"
                + COLUMN_STUDENT_EMAIL + " TEXT,"
                + COLUMN_STUDENT_PASSWORD + " TEXT,"
                + COLUMN_STUDENT_ADDRESS + " TEXT,"
                + COLUMN_STUDENT_ROLE + " TEXT,"
                + COLUMN_STUDENT_CREDIT_CARD + " TEXT"
                + ")";
        db.execSQL(createStudentsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Create new tables
        onCreate(db);
    }

    // Method to register a tutor
    public long registerTutor(String tut_fName,String tut_lName,String email, String password, String education, String role, String native_language, String description,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TUTOR_FIRSTNAME, tut_fName);
        values.put(COLUMN_TUTOR_LASTNAME, tut_lName);
        values.put(COLUMN_TUTOR_EMAIL, email);
        values.put(COLUMN_TUTOR_PASSWORD, password);
        values.put(COLUMN_TUTOR_EDUCATION, education);
        values.put(COLUMN_TUTOR_ROLE, role);
        values.put(COLUMN_TUTOR_NATIVE_LANGUAGE, native_language);
        values.put(COLUMN_TUTOR_DESCRIPTION, description);
        values.put(COLUMN_TUTOR_STATUS,status);
        return db.insert(TABLE_TUTORS, null, values);
    }

    // Method to register a student
    public long registerStudent(String std_fName,String std_lName,String email, String password, String address, String role, String creditCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_FIRSTNAME, std_fName);
        values.put(COLUMN_STUDENT_LASTNAME, std_lName);
        values.put(COLUMN_STUDENT_EMAIL, email);
        values.put(COLUMN_STUDENT_PASSWORD, password);
        values.put(COLUMN_STUDENT_ADDRESS, address);
        values.put(COLUMN_STUDENT_ROLE, role);
        values.put(COLUMN_STUDENT_CREDIT_CARD, creditCard);
        return db.insert(TABLE_STUDENTS, null, values);
    }
    // verify the correct Email and password fromm database
    public boolean authenticateUser(String email, String password) {

        role = "";//student or tutor
        status = "";//active,dismiss or suspended

        SQLiteDatabase db = this.getReadableDatabase();

        // Query for the email and password in the student table
        String studentQuery = "SELECT * FROM students WHERE email = ? AND password = ?";
        Cursor studentCursor = db.rawQuery(studentQuery, new String[]{email, password});

        // Query for the email and password in the tutor table
        String tutorQuery = "SELECT * FROM tutors WHERE email = ? AND password = ?";
        Cursor tutorCursor = db.rawQuery(tutorQuery, new String[]{email, password});

        if (tutorCursor.getCount() > 0) {
            tutorCursor.moveToFirst();
            role = tutorCursor.getString(tutorCursor.getColumnIndexOrThrow(COLUMN_TUTOR_ROLE));
            status = tutorCursor.getString(tutorCursor.getColumnIndexOrThrow(COLUMN_TUTOR_STATUS));
        } else if (studentCursor.getCount() > 0) {
            studentCursor.moveToFirst();
            role = studentCursor.getString(studentCursor.getColumnIndexOrThrow(COLUMN_STUDENT_ROLE));
        }
        Toast.makeText(context,String.valueOf(studentCursor.getCount()), Toast.LENGTH_SHORT).show();

        boolean isAuthenticated = (studentCursor.getCount() > 0 || tutorCursor.getCount() > 0);


        studentCursor.close();
        tutorCursor.close();
        db.close();

        return isAuthenticated;
    }

    public String getRole(){
        return role;
    }
    public String getStatus(){
        return status;
    }



}
