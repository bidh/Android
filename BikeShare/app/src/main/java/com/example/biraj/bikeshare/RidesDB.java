package com.example.biraj.bikeshare;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by biraj on 3/22/2018.
 */

public class RidesDB extends Observable{
    private static RidesDB sRidesDB;

    private ArrayList<Ride> mallRides;
    private Ride mlastRide= new Ride("", "");

    public synchronized static RidesDB get(Context context) {
        if (sRidesDB == null) { sRidesDB= new RidesDB(context); }
        return sRidesDB;
    }

    public synchronized ArrayList<Ride> getRidesDB() {return mallRides; }

    public synchronized void startRide(String what, String where) {
        mlastRide.setBikeName(what);
        mlastRide.setStartRide(where);
    }

    public synchronized void endRide(String where) {
        mlastRide.setEndRide(where);
        mallRides.add(mlastRide);
        mlastRide= new Ride("", "");
        this.setChanged();
        notifyObservers();
    }

    public synchronized void addFullRide(Ride r) {
        mallRides.add(r);
        this.setChanged();
        notifyObservers();
    }

    public synchronized void delete(Context c, int pos) {
        if ((pos >= 0) && (pos < mallRides.size())) {
            mallRides.remove(pos);
            Toast.makeText(c, "Deleted ride " + pos, Toast.LENGTH_LONG).show();
            this.setChanged();
            notifyObservers();
        }
    }

    public synchronized boolean activeRide(){
        return (  (!mlastRide.getBikeName().equals("")) && (!mlastRide.getStartRide().equals("")) );
    }

    public synchronized Ride currentRide(){
        return activeRide() ? mlastRide : new Ride();
    }

    private RidesDB(Context context) {
        mallRides= new ArrayList<>();
        mlastRide.setStartRide(""); // indicates that no ride is ongoing

        // Add some rides for testing purposes
        mallRides.add(new Ride("Peters bike", "ITU", "Fields"));
        mallRides.add(new Ride("Peters bike", "Fields", "Kongens Nytorv"));
        mallRides.add(new Ride("Jørgens bike", "Home", "ITU"));
        this.setChanged();
        notifyObservers();
    }

}
