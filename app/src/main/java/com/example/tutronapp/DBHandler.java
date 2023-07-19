package com.example.tutronapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "tutoring_app.db";
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_TUTORS = "tutors";
    private static final String TABLE_COMPLAINTS = "complaints";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EDUCATION_LEVEL = "education_level";
    private static final String COLUMN_NATIVE_LANGUAGE = "native_language";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CARD_NUMBER = "card_number";
    private static final String COLUMN_COMPLAINT_TEXT = "complaint_text";
    private static final String COLUMN_COMPLAINT_TUTOR_EMAIL = "tutor_email";
    private static final String COLUMN_IS_SUSPENDED = "is_suspended";
    private static final String COLUMN_IS_TEMPORARY_SUSPENSION = "is_temporary_suspended";
    private static final String COLUMN_SUSPENSION_END_DATE = "suspension_end_date";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_TOPICS = "topics";
    private static final String COLUMN_TOPIC_ID = "topic_id";
    private static final String COLUMN_TUTOR_EMAIL = "tutor_email";
    private static final String COLUMN_TOPIC_NAME = "topic_name";
    private static final String COLUMN_YEARS_OF_EXPERIENCE = "years_of_experience";
    private static final String COLUMN_TOPIC_DESCRIPTION = "description";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the Students table
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_FIRST_NAME + " TEXT,"
                + COLUMN_LAST_NAME + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_CARD_NUMBER + " TEXT"
                + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);

        // Create the Tutors table
        String CREATE_TUTORS_TABLE = "CREATE TABLE " + TABLE_TUTORS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_FIRST_NAME + " TEXT,"
                + COLUMN_LAST_NAME + " TEXT,"
                + COLUMN_EDUCATION_LEVEL + " TEXT,"
                + COLUMN_NATIVE_LANGUAGE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_IS_SUSPENDED + " INTEGER DEFAULT 0," // Add the new column here
                + COLUMN_IS_TEMPORARY_SUSPENSION + " INTEGER DEFAULT 0," // Add the new column here
                + COLUMN_SUSPENSION_END_DATE + " TEXT" // Add the new column here
                + ")";
        db.execSQL(CREATE_TUTORS_TABLE);

        // Create the Complaints table
        String CREATE_COMPLAINTS_TABLE = "CREATE TABLE " + TABLE_COMPLAINTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_COMPLAINT_TEXT + " TEXT,"
                + COLUMN_COMPLAINT_TUTOR_EMAIL + " TEXT,"
                + COLUMN_IS_SUSPENDED + " INTEGER DEFAULT 0"
                + ")";
        db.execSQL(CREATE_COMPLAINTS_TABLE);

        // Create the Topics table
        String CREATE_TOPICS_TABLE = "CREATE TABLE " + TABLE_TOPICS + "("
                + COLUMN_TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TUTOR_EMAIL + " TEXT,"
                + COLUMN_TOPIC_NAME + " TEXT,"
                + COLUMN_YEARS_OF_EXPERIENCE + " INTEGER,"
                + COLUMN_TOPIC_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(CREATE_TOPICS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLAINTS);
        // Create new tables
        onCreate(db);
    }

    // Add a new Student record to the database
    public void registerStudent(String firstName, String lastName, String email, String password,
                                String address, String userType, String cardNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_CARD_NUMBER, cardNumber);
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }

    // Add a new Tutor record to the database
    public void registerTutor(String firstName, String lastName, String email, String password,
                              String educationLevel, String userType,
                              String nativeLanguage, String description, String active) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_EDUCATION_LEVEL, educationLevel);
        values.put(COLUMN_NATIVE_LANGUAGE, nativeLanguage);
        values.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_TUTORS, null, values);
        db.close();
    }

    // Add a new complaint record to the database
    public void addComplaint(String complaintText, String tutorEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPLAINT_TEXT, complaintText);
        values.put(COLUMN_COMPLAINT_TUTOR_EMAIL, tutorEmail);
        db.insert(TABLE_COMPLAINTS, null, values);
        db.close();
    }

    // Retrieve a User (Student or Tutor) from the database based on email and password
    public User userLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        // Check if the user is a Student
        cursor = db.query(TABLE_STUDENTS, null,
                COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{email, password},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
            @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
            @SuppressLint("Range") String cardNumber = cursor.getString(cursor.getColumnIndex(COLUMN_CARD_NUMBER));

            // Create and populate a Student object
            Student student = new Student(id, email, password, firstName, lastName);
            student.setAddress(address);
            student.setCardNumber(cardNumber);

            cursor.close();
            return student;
        }

        cursor.close();

        // If the user is not a Student, check if they are a Tutor
        cursor = db.query(TABLE_TUTORS, null,
                COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{email, password},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
            @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
            @SuppressLint("Range") String educationLevel = cursor.getString(cursor.getColumnIndex(COLUMN_EDUCATION_LEVEL));
            @SuppressLint("Range") String nativeLanguage = cursor.getString(cursor.getColumnIndex(COLUMN_NATIVE_LANGUAGE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

            // Retrieve suspension status and end date from the cursor
            @SuppressLint("Range") int isSuspendedValue = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_SUSPENDED));
            @SuppressLint("Range") int isTemporarySuspendedValue = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_TEMPORARY_SUSPENSION));
            @SuppressLint("Range") String suspensionEndDate = cursor.getString(cursor.getColumnIndex(COLUMN_SUSPENSION_END_DATE));

            // Create and populate a Tutor object
            Tutor tutor = new Tutor(id, email, password, firstName, lastName, educationLevel, nativeLanguage, description);

            // Check for possible null values in the cursor
            boolean isSuspended = (isSuspendedValue == 1);
            boolean isTemporarySuspended = (isTemporarySuspendedValue == 1);

            // Set suspension status and end date for the tutor
            tutor.setSuspended(isSuspended);
            tutor.setTemporarySuspended(isTemporarySuspended);
            tutor.setSuspensionEndDate(suspensionEndDate);

            cursor.close();
            return tutor;
        }

        cursor.close();
        return null; // Return null if neither a Student nor a Tutor is found with the given credentials
    }

    // Delete a Student from the database based on their email
    public boolean deleteStudentByEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_STUDENTS, COLUMN_EMAIL + " = ?", new String[]{email});
        db.close();
        return result != 0;
    }

    // Delete a Tutor from the database based on their email
    public boolean deleteTutorByEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_TUTORS, COLUMN_EMAIL + " = ?", new String[]{email});
        db.close();
        return result != 0;
    }

    // Get all complaints from the database
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COMPLAINTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String complaintText = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLAINT_TEXT));
                @SuppressLint("Range") String tutorEmail = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLAINT_TUTOR_EMAIL));
                @SuppressLint("Range") boolean isSuspended = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_SUSPENDED)) == 1;

                //String newtutorEmail = tutorEmail.toString();
                Complaint complaint = new Complaint(id, complaintText, tutorEmail, isSuspended);
                complaints.add(complaint);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return complaints;
    }

    // Update complaint status in the database (dismiss or suspend)
    public void updateComplaintStatus(int complaintId, boolean isSuspended, boolean isTemporarySuspension, String suspensionEndDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_SUSPENDED, isSuspended ? 1 : 0); // Store 1 for true, 0 for false

        // If the suspension is temporary, update the necessary fields
        if (isSuspended && isTemporarySuspension) {
            values.put(COLUMN_IS_TEMPORARY_SUSPENSION, 1);
            values.put(COLUMN_SUSPENSION_END_DATE, suspensionEndDate);
        } else {
            // If the suspension is not temporary, ensure the temporary suspension fields are set to default values
            values.put(COLUMN_IS_TEMPORARY_SUSPENSION, 0);
            values.put(COLUMN_SUSPENSION_END_DATE, "");
        }

        db.update(TABLE_COMPLAINTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(complaintId)});
        db.close();
    }

    // Delete a complaint from the database based on its ID
    public boolean deleteComplaintById(int complaintId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_COMPLAINTS, COLUMN_ID + " = ?", new String[]{String.valueOf(complaintId)});
        db.close();
        return result != 0;
    }


    // Get a Tutor from the database based on their email
    public Tutor getTutorByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TUTORS, null,
                COLUMN_EMAIL + " = ?", new String[]{email},
                null, null, null);

        Tutor tutor = null;
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
            @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
            @SuppressLint("Range") String educationLevel = cursor.getString(cursor.getColumnIndex(COLUMN_EDUCATION_LEVEL));
            @SuppressLint("Range") String nativeLanguage = cursor.getString(cursor.getColumnIndex(COLUMN_NATIVE_LANGUAGE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

            tutor = new Tutor(id, email, "", firstName, lastName,
                    educationLevel, nativeLanguage, description);
            cursor.close();
        }

        return tutor;
    }

    // Update a Tutor record in the database
    // Update a Tutor record in the database
    public void updateTutor(Tutor tutor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, tutor.getFirstName());
        values.put(COLUMN_LAST_NAME, tutor.getLastName());
        values.put(COLUMN_EDUCATION_LEVEL, tutor.getEducationLevel());
        values.put(COLUMN_NATIVE_LANGUAGE, tutor.getNativeLanguage());
        values.put(COLUMN_DESCRIPTION, tutor.getDescription());
        values.put(COLUMN_IS_SUSPENDED, tutor.isSuspended() ? 1 : 0); // Store 1 for true, 0 for false

        // Update suspension status and suspension end date only if the tutor is suspended
        if (tutor.isSuspended()) {
            values.put(COLUMN_IS_TEMPORARY_SUSPENSION, tutor.isTemporarySuspended() ? 1 : 0);
            values.put(COLUMN_SUSPENSION_END_DATE, tutor.getSuspensionEndDate());
        } else {
            // If the tutor is not suspended, set the suspension status and suspension end date to default values
            values.put(COLUMN_IS_TEMPORARY_SUSPENSION, 0);
            values.put(COLUMN_SUSPENSION_END_DATE, "");
        }

        db.update(TABLE_TUTORS, values, COLUMN_EMAIL + " = ?", new String[]{tutor.getEmail()});
        db.close();
    }

    // Get a Complaint from the database based on its ID
    public Complaint getComplaintById(int complaintId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COMPLAINTS, null,
                COLUMN_ID + " = ?", new String[]{String.valueOf(complaintId)},
                null, null, null);

        Complaint complaint = null;
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String complaintText = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLAINT_TEXT));
            @SuppressLint("Range") String tutorEmail = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLAINT_TUTOR_EMAIL));
            @SuppressLint("Range") boolean isSuspended = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_SUSPENDED)) == 1;

            complaint = new Complaint(id, complaintText, tutorEmail, isSuspended);
            cursor.close();
        }

        return complaint;
    }

    // Add a new TutorTopics record to the database
    public void addTutorTopic(String tutorEmail, String topicName, int yearsOfExperience, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TUTOR_EMAIL, tutorEmail);
        values.put(COLUMN_TOPIC_NAME, topicName);
        values.put(COLUMN_YEARS_OF_EXPERIENCE, yearsOfExperience);
        values.put(COLUMN_TOPIC_DESCRIPTION, description);
        db.insert(TABLE_TOPICS, null, values);
        db.close();
    }

    // Get all TutorTopics for a specific tutor from the database
    public List<TutorTopics> getTutorTopics(String tutorEmail) {
        List<TutorTopics> topics = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TOPICS + " WHERE " + COLUMN_TUTOR_EMAIL + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{tutorEmail});

        if (cursor.moveToFirst()) {
            do {
                int topicId = cursor.getInt(cursor.getColumnIndex(COLUMN_TOPIC_ID));
                String topicName = cursor.getString(cursor.getColumnIndex(COLUMN_TOPIC_NAME));
                int yearsOfExperience = cursor.getInt(cursor.getColumnIndex(COLUMN_YEARS_OF_EXPERIENCE));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_TOPIC_DESCRIPTION));

                TutorTopics topic = new TutorTopics(topicName, yearsOfExperience, description);
                topics.add(topic);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return topics;
    }

    // Delete a TutorTopics record from the database
    public boolean deleteTutorTopic(int topicId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_TOPICS, COLUMN_TOPIC_ID + "=?", new String[]{String.valueOf(topicId)});
        db.close();
        return result != 0;
    }

}
