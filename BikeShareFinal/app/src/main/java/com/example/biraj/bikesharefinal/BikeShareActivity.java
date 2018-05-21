package com.example.biraj.bikesharefinal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BikeShareActivity extends AppCompatActivity {
    private FragmentManager fm= getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulist, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.register_bike:
                setUpFragment(new RegisterFragment(), R.id.fragment_container);
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
