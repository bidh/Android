package com.example.biraj.bikeshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by biraj on 3/3/2018.
 */

public class RideArrayAdapter extends ArrayAdapter<Ride>{
    private final Context context;
    private final List<Ride> values;
    public RideArrayAdapter(Context context, List<Ride> values) {
        super(context, R.layout.listitem, values);
        this.context= context;
        this.values= values;
    }
    public View getView(int i, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.listitem, parent, false);

        // Lookup view for data population
        TextView whatView = (TextView) rowView.findViewById(R.id.start_ride);
        whatView.append(values.get(i).toString());
        return rowView;
    }
}