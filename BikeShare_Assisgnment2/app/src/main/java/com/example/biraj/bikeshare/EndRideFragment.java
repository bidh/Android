package com.example.biraj.bikeshare;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by biraj on 5/1/2018.
 */

public class EndRideFragment extends Fragment {
    private Button btnEndRide,btnGetLocation;
    private TextView bikeName,endLocation;
    private Ride mRide;
    Context mContext;
    Spinner spinner;

    String lati,longi;
    Geocoder geocoder;
    List<Address> addressList;
    // GPSTracker class
    GPSTracker gps;
    double latitude,longitude;
    Ride ride;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_end_ride, container, false);
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        bikeName=(EditText) v.findViewById(R.id.bike_name);
        endLocation=(EditText) v.findViewById(R.id.end_ride);
        btnEndRide=(Button)  v.findViewById(R.id.btn_end_ride);
        btnGetLocation=(Button) v.findViewById(R.id.btn_end_location);
        mContext=getActivity();
        ride=new Ride();

        RegisterLab registerLab = RegisterLab.get(getActivity());
        List<Register> registers= registerLab.getRegisters();

        List<String> names=new ArrayList<>();
        for(Register r:registers){
            names.add(r.getName());
        }
        spinner = (Spinner) v.findViewById(R.id.bikeIdSpinnerEndRide);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, names);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

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
                String text = spinner.getSelectedItem().toString();
                ride.setName(text);
                ride.setStartLocation(endLocation.getText().toString());
                RideLab.get(getActivity()).addRide(ride);
                //getActivity().recreate();
                Toast.makeText(getActivity().getApplicationContext(),"ride started successfully",Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
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
    }
}
