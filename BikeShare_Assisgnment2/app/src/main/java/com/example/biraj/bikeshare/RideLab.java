package com.example.biraj.bikeshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by biraj on 4/27/2018.
 */

public class RideLab {
    private static RideLab sRideLab;

    private List<Ride> mRides;

    public static RideLab get(Context context) {
        if (sRideLab == null) {
            sRideLab = new RideLab(context);
        }
        return sRideLab;
    }
    public void addRide(Ride ride){
        mRides.add(ride);
    }
    private RideLab(Context context) {
        mRides = new ArrayList<>();
        /*for (int i = 0; i < 100; i++) {
            Ride ride = new Ride();
            ride.setBikeName("Ride #" + i);
            ride.setStartRide("Start Ride: Amagerbo");
            ride.setEndRide("End Ride:  Norrebo");
            mRides.add(ride);
        }*/
    }

    public List<Ride> getRides() {
        return mRides;
    }

    public Ride getRide(UUID id) {
        for (Ride ride : mRides) {
            if (ride.getId().equals(id)) {
                return ride;
            }
        }

        return null;
    }
}
