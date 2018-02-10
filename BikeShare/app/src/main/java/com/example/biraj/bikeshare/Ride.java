package com.example.biraj.bikeshare;

/**
 * Created by biraj on 2/10/2018.
 */

public class Ride {
    private String mBikeName;
    private String mStartRide;

    public String getBikeName() {
        return mBikeName;
    }

    public void setBikeName(String bikeName) {
        mBikeName = bikeName;
    }

    public String getStartRide() {
        return mStartRide;
    }

    public void setStartRide(String startRide) {
        mStartRide = startRide;
    }

    public Ride(String bikeName, String startRide) {
        mBikeName = bikeName;
        mStartRide = startRide;
    }
    public String toString(){return mBikeName + "started here:" + mStartRide;}

}
