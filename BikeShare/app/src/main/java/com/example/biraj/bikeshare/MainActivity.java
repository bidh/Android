package com.example.biraj.bikeshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;



public class MainActivity extends AppCompatActivity {
    private Button addRide;
    private TextView lastAdded;
    private TextView newWhat, newWhere;
    private Ride last= new Ride("", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastAdded= (EditText) findViewById(R.id.lastAdded);
        updateUI();
        // Button
        addRide = (Button) findViewById(R.id.btnAdd);
        // Texts
        newWhat= (EditText) findViewById(R.id.whatBike);
        newWhere=(EditText) findViewById(R.id.where);
        // view products click event
        addRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){
                    last.setBikeName(newWhat.getText().toString().trim());
                    last.setStartRide(newWhere.getText().toString().trim());
                    // reset text fields
                    newWhat.setText("");
                    newWhere.setText("");
                    updateUI();
                }
            }
        });

    }

    private void updateUI(){ lastAdded.setText(last.toString()); }
}
