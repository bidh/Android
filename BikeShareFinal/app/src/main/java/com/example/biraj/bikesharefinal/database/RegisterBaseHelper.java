package com.example.biraj.bikesharefinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.biraj.bikesharefinal.Register;
import com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable;

/**
 * Created by biraj on 5/19/2018.
 */

public class RegisterBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "rideBase.db";
    public RegisterBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RegisterTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                RegisterTable.Cols.UUID + ", " +
                RegisterTable.Cols.NAME + ", " +
                RegisterTable.Cols.TYPE +  ", " +
                RegisterTable.Cols.PRICE + ", " +
                RegisterTable.Cols.DATE + //","+
                //RegisterTable.Cols.IMAGE +
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
