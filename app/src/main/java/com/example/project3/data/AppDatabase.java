package com.example.project3.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Attempt.class, QuestionResponse.class}, version = 1)
@TypeConverters(MyTypeConverters.class)
public abstract class AppDatabase extends RoomDatabase {
    // Note: this is the class you should use to access the application's sqlite database.
    // Use AppDatabase.db.getQuizDao() method to get an instance of QuizDao
    // From there, use the methods of QuizDao to insert and query data in the database
    public abstract QuizDao getQuizDao();

    public static AppDatabase db;

    public static void initDB(Context ctx) {
        db = Room
                .databaseBuilder(ctx, AppDatabase.class, "quiz-database")
                .allowMainThreadQueries()
                .build();
    }
}
