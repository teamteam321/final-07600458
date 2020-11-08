package com.akzaki.speedrecords.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.akzaki.speedrecords.model.Record;
import com.akzaki.speedrecords.util.AppExecutors;

import java.util.Calendar;
@Database(entities = {Record.class}, exportSchema = false, version = 1)
public abstract class RecordDatabase extends RoomDatabase{
    //private static final String TAG = "AppDatabase";
    private static final String DB_NAME = "record_2.db";

    private static RecordDatabase sInstance;

    public abstract RecordDAO recordDao();

    public static synchronized RecordDatabase getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    RecordDatabase.class,
                    DB_NAME
            ).addCallback(new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    //insertInitialData(context);
                    //Log.i("SUCC","suscess");
                }
            }).build();
        }
        return sInstance;
    }
    private static void insertInitialData(final Context context) {
        AppExecutors exe = new AppExecutors();
        exe.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                RecordDatabase db = getInstance(context);
                db.recordDao().addRecord(
                        new Record(0,0,0)
                );
            }
        });



    }
}
