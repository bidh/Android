package com.example.biraj.bikeshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by biraj on 2/21/2018.
 */

public class startRideActivity extends AppCompatActivity{
    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;
    private Ride last = new Ride("", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);

        lastAdded = (EditText) findViewById(R.id.lastAdded);
        // Button
        addRide = (Button) findViewById(R.id.btnAdd);
        // Texts
        newWhat = (EditText) findViewById(R.id.whatBike);
        newWhere = (EditText) findViewById(R.id.where);
        // view products click event
        addRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length() > 0) && (newWhere.getText().length() > 0)) {
                    last.setBikeName(newWhat.getText().toString().trim());
                    last.setStartRide(newWhere.getText().toString().trim());
                    // reset text fields
                    newWhat.setText("");
                    newWhere.setText("");
                }
            }
        });
    }
}
