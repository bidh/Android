package com.example.biraj.bikeshare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by biraj on 5/22/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "rideBase.db";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RegisterDbSchema.RegisterTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                RegisterDbSchema.RegisterTable.Cols.UUID + ", " +
                RegisterDbSchema.RegisterTable.Cols.NAME + ", " +
                RegisterDbSchema.RegisterTable.Cols.TYPE +  ", " +
                RegisterDbSchema.RegisterTable.Cols.PRICE + ", " +
                RegisterDbSchema.RegisterTable.Cols.DATE + ","+
                RegisterDbSchema.RegisterTable.Cols.IMAGE+
                ")"
        );
        db.execSQL("create table " + RidesDbSchema.RideTable.Name + "(" +
                "_id integer primary key autoincrement, " +
                RidesDbSchema.RideTable.Cols.UUID + ", " +
                RidesDbSchema.RideTable.Cols.BIKENAME + ", " +
                RidesDbSchema.RideTable.Cols.STARTLOCATION + ", " +
                RidesDbSchema.RideTable.Cols.STARTDATETIME +  ", " +
                RidesDbSchema.RideTable.Cols.ENDLOCATION + ", " +
                RidesDbSchema.RideTable.Cols.ENDDATETIME + ","+
                RidesDbSchema.RideTable.Cols.PRICE+
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
