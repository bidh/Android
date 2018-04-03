package com.example.biraj.bikeshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by biraj on 4/3/2018.
 */

public class ShowRideFragment extends Fragment {
    private static RidesDB sRidesDB;
    private List<Ride> ridelst;
    private RideArrayAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sRidesDB=RidesDB.get(getActivity());
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater. inflate(R.layout.show_ride, container, false);
        ridelst=new ArrayList<>();
        ridelst=sRidesDB.getRidesDB();
        adapter=new RideArrayAdapter(getActivity().getApplicationContext(),ridelst);
        ((ListView) v.findViewById(R.id.mainListView)).setAdapter(adapter);
        return v;
    }
}
