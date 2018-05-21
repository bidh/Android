package com.example.biraj.bikeshare;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {
    private Button btnAdd;
    private Button btnEnd;
    private Button btnShow;
    private RidesDB sRidesDB;
    private List<Ride> ridelst;
    private RideArrayAdapter adapter;
    private FragmentManager fm= getSupportFragmentManager();
    private Boolean activeRide=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnEnd=(Button)findViewById(R.id.btnEnd);
        btnShow=(Button)findViewById(R.id.btnShow);


        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setUpFragment(new StartRideFragment(), R.id.fragment_button_container);
                }
            });
            btnEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setUpFragment(new EndRideFragment(), R.id.fragment_button_container);
                }
            });
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            setUpFragment(new ShowRideFragment(), R.id.fragmentContainer);
        }
        else{
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setUpFragment(new StartRideFragment(), R.id.fragmentContainer);
                }
            });
            btnEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setUpFragment(new EndRideFragment(), R.id.fragmentContainer);
                }
            });
            setUpFragment(new ShowRideFragment(), R.id.fragmentContainer);
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
    private void setUpFragment(Fragment newFragment, int res) {
        Fragment fragment= fm.findFragmentById(res);
        if (fragment == null) {
            fragment= newFragment;
            fm.beginTransaction()
                    .add(res, fragment)
                    .commit();
        } else {
            fm.beginTransaction()
                    .remove(fragment)
                    .add(res, newFragment)
                    .commit();
        }
    }
}