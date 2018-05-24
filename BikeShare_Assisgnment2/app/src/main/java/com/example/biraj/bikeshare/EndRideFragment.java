package com.example.biraj.bikeshare;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by biraj on 5/1/2018.
 */

public class EndRideFragment extends Fragment {
    private Button btnEndRide,btnGetLocation;
    private TextView bikeName,startLocation,startDateTime,endLocation,endDatetime,Price;
    private Ride mRide;
    Context mContext;
    Spinner spinner;

    String lati,longi;
    Geocoder geocoder;
    List<Address> addressList;
    // GPSTracker class
    GPSTracker gps;
    double latitude,longitude;
    String UUID="";
    Ride ride;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            UUID= bundle.getString("UUID", ""); // Key, default value
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_end_ride, container, false);
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        bikeName=(EditText) v.findViewById(R.id.bike_name);
        startLocation=(EditText) v.findViewById(R.id.start_location);
        startDateTime=(EditText) v.findViewById(R.id.start_datetime);
        endLocation=(EditText) v.findViewById(R.id.end_location);
        endDatetime=(EditText) v.findViewById(R.id.end_datetime);
        Price=(EditText) v.findViewById(R.id.price);

        btnEndRide=(Button)  v.findViewById(R.id.btn_end_ride);
        btnGetLocation=(Button) v.findViewById(R.id.btn_end_location);

        mContext=getActivity();

        RideLab rideLab= RideLab.get(getActivity());
        ride= rideLab.getRide(java.util.UUID.fromString(UUID));

        String startDateString=ride.getStartDateTime();
        Date startDate = null,endDate=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            startDate=dateFormat.parse(startDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate=dateFormat.parse(getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff=endDate.getTime()-startDate.getTime();
        long seconds=diff/1000;
        long minutes=seconds/60;
        long hours=minutes/60;
        long price= (long) 0.0;
        if(hours==0){
            price=20;
        }else{
            price=hours*20;
        }

        bikeName.setText(ride.getName());
        startLocation.setText(ride.getStartLocation());
        startDateTime.setText(ride.getStartDateTime());
        endDatetime.setText(getDateTime());
        Price.setText(String.valueOf((int) price));

        btnGetLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    Toast.makeText(mContext, "You need to have granted permission, press again",
                            Toast.LENGTH_SHORT).show();
                    gps = new GPSTracker(mContext, getActivity());
                    if (gps.canGetLocation()) {

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        lati = Double.toString(latitude);
                        longi = Double.toString(longitude);
                    } else {
                        gps.showSettingsAlert();
                    }
                }

                try {
                    addressList = geocoder.getFromLocation(latitude,longitude,1);

                    String addressStr = addressList.get(0).getAddressLine(0);
                    String areaStr = addressList.get(0).getLocality();
                    String cityStr = addressList.get(0).getAdminArea();
                    String countryStr = addressList.get(0).getCountryName();
                    String postalcodeStr = addressList.get(0).getPostalCode();

                    String fullAddress = addressStr+", "+areaStr+", "+cityStr+", "+countryStr+", "+postalcodeStr;

                    endLocation.setText(fullAddress);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eLocation=endLocation.getText().toString();
                /*if(eLocation==""){
                    Toast.makeText(getActivity(),"Ending location cannot be empty",Toast.LENGTH_SHORT).show();
                }*/
                ride.setEndDateTime(endDatetime.getText().toString());
                ride.setEndLocation(eLocation);
                ride.setPrice(Price.getText().toString());
                RideLab.get(getActivity()).updateRide(ride);
                CheckFragment nextFrag= new CheckFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    gps = new GPSTracker(mContext, getActivity());
                    // Check if GPS enabled
                    if (gps.canGetLocation()) {
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();
                        // \n is for new line
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        gps.showSettingsAlert();
                    }
                } else {
                    Toast.makeText(mContext, "You need to grant permission",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }private static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
