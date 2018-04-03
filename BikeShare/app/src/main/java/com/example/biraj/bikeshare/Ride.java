package com.example.biraj.bikeshare;

/**
 * Created by biraj on 2/10/2018.
 */

public class Ride {
    private String mBikeName;
    private String mStartRide;
    private String mEndRide;

    public Ride(){ mBikeName= "";  mStartRide= "";   mEndRide= "";  }
    public Ride(String bikeName, String startRide, String endRide) {
        mBikeName = bikeName;
        mStartRide = startRide;
        mEndRide= endRide;
    }
    public Ride(String bikeName,String startRide){
        mBikeName=bikeName;
        mStartRide=startRide;
    }

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

    public String getEndRide(){return mEndRide;}

    public void setEndRide(String endRide){mEndRide=endRide;}


    public String toString(){
        if (mEndRide == null) {
            return mBikeName + " started: " + mStartRide;
        } else {
            return mBikeName + " started here: " + mStartRide + " and ended " + mEndRide;
        }
    }

}
