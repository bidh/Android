package com.example.biraj.bikeshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by biraj on 3/22/2018.
 */

public class RidesDB {
    public static RidesDB sRidesDB;

    public static RidesDB get(Context context){
        if(sRidesDB==null){
            sRidesDB=new RidesDB(context);
        }
        return sRidesDB;
    }
    private ArrayList<Ride> mallRides;
    private Ride mStartRide=new Ride("","","");

    public List<Ride> getRidesDB(){
        return  mallRides;
    }
    public void addRide(String what,String where){
        mStartRide.setBikeName(what);
        mStartRide.setStartRide(where);
    }
    public void endRide(String what,String where){
        mStartRide.setBikeName(what);
        mStartRide.setEndRide(where);
    }
    private RidesDB(Context context) {
        mallRides= new ArrayList<>();
        // Add some rides for testing purposes
        mallRides.add(new Ride("Peters bike", "ITU","KU"));
        mallRides.add(new Ride("Peters bike", "Fields","Amager St."));
        mallRides.add(new Ride("Jørgens bike", "Home","CPH Lufthavn"));
        mallRides.add(new Ride(mStartRide.getBikeName(),mStartRide.getStartRide(),mStartRide.getEndRide()));
    }
}
