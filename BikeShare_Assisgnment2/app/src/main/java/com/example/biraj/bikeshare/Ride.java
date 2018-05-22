package com.example.biraj.bikeshare;

import java.util.Date;
import java.util.UUID;

/**
 * Created by biraj on 4/27/2018.
 */

public class Ride {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private UUID mId;
    private String name;
    private String mStartLocation;
    private String mStartDateTime;
    private String mEndLocation;
    private String mEndDateTime;

    public Ride(){
        this(UUID.randomUUID());;
    }
    public Ride(UUID id) {
        mId = id;
    }
    public UUID getId(){
        return mId;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

    public String getStartDateTime() {
        return mStartDateTime;
    }

    public void setStartDateTime(String mStartDateTime) {
        this.mStartDateTime = mStartDateTime;
    }

    public String getEndDateTime() {
        return mEndDateTime;
    }
    public void setEndDateTime(String mEndDateTime) {
        this.mEndDateTime = mEndDateTime;
    }
    public String getStartLocation() {
        return mStartLocation;
    }
    public void setStartLocation(String mstartLocation) {
        this.mStartLocation = mstartLocation;
    }
    public String getEndLocation() { return mEndLocation; }
    public void setEndLocation(String mendLocation) { this.mEndLocation = mendLocation; }

}
