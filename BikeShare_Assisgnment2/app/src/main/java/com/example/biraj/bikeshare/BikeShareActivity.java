package com.example.biraj.bikeshare;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BikeShareActivity extends AppCompatActivity {

    private FragmentManager fm= getSupportFragmentManager();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);
        setUpFragment(new ShowRegisterFragment(), R.id.fragment_container);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_ride_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.register_bike:
                setUpFragment(new RegisterFragment(), R.id.fragment_container);
                return true;
            case R.id.new_ride:
                setUpFragment(new StartRideFragment(), R.id.fragment_container);
                return true;
            case R.id.ViewRegisteredBike:
                setUpFragment(new ShowRegisterFragment(), R.id.fragment_container);
                return true;
            case R.id.check_ride:
                setUpFragment(new CheckFragment(), R.id.fragment_container);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
