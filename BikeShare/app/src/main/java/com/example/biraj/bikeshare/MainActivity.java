package com.example.biraj.bikeshare;

import android.content.Intent;
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


public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnEnd;
    private Button btnList;
    private RidesDB sRidesDB;
    private List<Ride> ridelst;
    private RideArrayAdapter adapter;
    private ListView rideListView;
    //FragmentManager fm= getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* Fragment fragment= fm.findFragmentById(R.id.fragment_container);
        fm.beginTransaction()
                .add(R.id.fragment_container, new BikeShareFragment())
                .commit();
*/

        sRidesDB=RidesDB.get(this);
        rideListView=(ListView) findViewById(R.id.mainListView);

        ridelst=new ArrayList<>();

        ridelst=sRidesDB.getRidesDB();

        adapter=new RideArrayAdapter(getApplicationContext(),ridelst);
        rideListView.setAdapter(adapter);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, startRideActivity.class);
                startActivity(intent);
            }
        });
        btnEnd=(Button)findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, endRideActivity.class);
                startActivity(intent);
            }
        });
        btnList=(Button)findViewById(R.id.btnList);
        btnList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

    }
   /* private void setUpFragment(Fragment newFragment, int res) {
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
    }*/
}