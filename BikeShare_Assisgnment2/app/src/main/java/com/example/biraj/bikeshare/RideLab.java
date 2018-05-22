package com.example.biraj.bikeshare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.biraj.bikeshare.database.DatabaseHelper;
import com.example.biraj.bikeshare.database.RideCursorWrapper;
import com.example.biraj.bikeshare.database.RidesDbSchema;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.example.biraj.bikeshare.database.RidesDbSchema.RideTable.Cols.BIKENAME;
import static com.example.biraj.bikeshare.database.RidesDbSchema.RideTable.Cols.ENDDATETIME;
import static com.example.biraj.bikeshare.database.RidesDbSchema.RideTable.Cols.ENDLOCATION;
import static com.example.biraj.bikeshare.database.RidesDbSchema.RideTable.Cols.PRICE;
import static com.example.biraj.bikeshare.database.RidesDbSchema.RideTable.Cols.STARTDATETIME;
import static com.example.biraj.bikeshare.database.RidesDbSchema.RideTable.Cols.STARTLOCATION;
import static com.example.biraj.bikeshare.database.RidesDbSchema.RideTable.Cols.UUID;

/**
 * Created by biraj on 4/27/2018.
 */

public class RideLab {
    private static RideLab sRideLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static RideLab get(Context context) {
        if (sRideLab == null) {
            sRideLab = new RideLab(context);
        }

        return sRideLab;
    }
    private RideLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext)
                .getWritableDatabase();
    }
    public void addRide(Ride c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(RidesDbSchema.RideTable.Name, null, values);
    }
    public List<Ride> getRides() {
        List<Ride> rides = new ArrayList<>();
        RideCursorWrapper cursor = queryRides(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                rides.add(cursor.getRide());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return rides;
    }
    public Ride getRide(UUID id) {
        RideCursorWrapper cursor = queryRides(
                RidesDbSchema.RideTable.Cols.BIKENAME + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRide();
        } finally {
            cursor.close();
        }
    }
    public void updateRide(Ride ride) {
        String uuidString = ride.getId().toString();
        ContentValues values = getContentValues(ride);
        mDatabase.update(RidesDbSchema.RideTable.Name, values,
                UUID + " = ?",
                new String[]{uuidString});
    }
    private RideCursorWrapper queryRides(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RidesDbSchema.RideTable.Name,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new RideCursorWrapper(cursor);
    }
    private static ContentValues getContentValues(Ride ride) {
        ContentValues values = new ContentValues();
        values.put(UUID,ride.getId().toString());
        values.put(BIKENAME,ride.getName());
        values.put(STARTLOCATION, ride.getStartLocation());
        values.put(STARTDATETIME, getDateTime());
        values.put(ENDLOCATION,ride.getEndLocation());
        values.put(ENDDATETIME,getDateTime());
        values.put(PRICE,ride.getPrice());
        return values;
    }
    private static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
