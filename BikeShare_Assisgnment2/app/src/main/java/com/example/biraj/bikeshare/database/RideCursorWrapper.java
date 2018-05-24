package com.example.biraj.bikeshare.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.biraj.bikeshare.Ride;

import java.util.UUID;

/**
 * Created by biraj on 5/22/2018.
 */

public class RideCursorWrapper extends CursorWrapper {
    public RideCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Ride getRide() {
        String rideId=getString(getColumnIndex(RidesDbSchema.RideTable.Cols.UUID));
        String bikeName=getString(getColumnIndex(RidesDbSchema.RideTable.Cols.BIKENAME));
        String startLocation= getString(getColumnIndex(RidesDbSchema.RideTable.Cols.STARTLOCATION));
        String startDateTime= getString(getColumnIndex(RidesDbSchema.RideTable.Cols.STARTDATETIME));
        String endLocation= getString(getColumnIndex(RidesDbSchema.RideTable.Cols.ENDLOCATION));
        String endDateTime= getString(getColumnIndex(RidesDbSchema.RideTable.Cols.ENDDATETIME));
        String price= getString(getColumnIndex(RidesDbSchema.RideTable.Cols.PRICE));


        Ride ride= new Ride();
        ride.setId(UUID.fromString(rideId));
        ride.setName(bikeName);
        ride.setStartLocation(startLocation);
        ride.setStartDateTime(startDateTime);
        ride.setEndLocation(endLocation);
        ride.setEndDateTime(endDateTime);
        ride.setPrice(price);

        return ride;
    }
}
