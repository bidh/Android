package com.example.biraj.bikeshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by biraj on 5/1/2018.
 */

public class StartRideFragment extends Fragment {
    private Button btnStartRide;
    private TextView bikeName,startLocation,bikeType,bikePrice;
    private Ride mRide;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start_ride, container, false);

        bikeName=(EditText) v.findViewById(R.id.bike_name);
        startLocation=(EditText) v.findViewById(R.id.start_ride);
        btnStartRide=(Button) v.findViewById(R.id.btn_add_ride);
        return v;
    }
}
