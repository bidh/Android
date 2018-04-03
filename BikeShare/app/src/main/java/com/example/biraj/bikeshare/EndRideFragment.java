package com.example.biraj.bikeshare;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by biraj on 3/23/2018.
 */

public class EndRideFragment extends Fragment{
    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;
    private Ride last= new Ride("", "");
    private static RidesDB sRidesdb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sRidesdb=RidesDB.get(getActivity());
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState){
        View v= inflater. inflate(R.layout.activity_end_ride, container, false);

        lastAdded= v.findViewById(R.id.lastAdded);
        // Button
        addRide = v.findViewById(R.id.btnAdd);
        // Texts
        newWhat= v.findViewById(R.id.whatBike);
        newWhere=v.findViewById(R.id.where);
        // view products click event
        addRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( (newWhere.getText().length() > 0)) {
                    sRidesdb.endRide(newWhere.getText().toString().trim());
                    getActivity().recreate();  //restarts BikeShareActivity
                }
            }
        });

        return v;
    }

}
