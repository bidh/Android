package com.example.biraj.bikeshare;

import java.util.Date;
import java.util.UUID;

/**
 * Created by biraj on 4/27/2018.
 */

public class Ride {
    private UUID mId;
    private String mbikeName;
    private String mstartRide;
    private String mendRide;

    public Ride(){ mbikeName= "";  mstartRide= "";   mendRide= "";  }
    public Ride(String name, String start) { mbikeName= name; mstartRide= start; mId = UUID.randomUUID();}
    public Ride(String name, String startRide, String endRide) {
        mId = UUID.randomUUID();
        mbikeName= name;
        mstartRide= startRide;
        mendRide= endRide;
    }
    public UUID getId() {
        return mId;
    }
    public String getBikeName() {
        return mbikeName;
    }
    public void setBikeName(String mbikeName) {
        this.mbikeName = mbikeName;
    }
    public String getStartRide() {
        return mstartRide;
    }
    public void setStartRide(String mstartRide) {
        this.mstartRide = mstartRide;
    }
    public String getEndRide() { return mendRide; }
    public void setEndRide(String mendRide) { this.mendRide = mendRide; }
    public String toString() { return mbikeName + " started: " + mstartRide + " ended: " + mendRide; }

}
