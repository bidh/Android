package com.example.biraj.bikeshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by biraj on 4/27/2018.
 */

public class ShowRideFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private RideAdapter mAdapter;

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_ride_list, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_ride:
                Intent intentStart =new Intent(getActivity(),StartRideFragment.class);
                this.startActivity(intentStart);
                return true;
            case R.id.end_ride:
                Intent intentEnd=new Intent(getActivity(),EndRideFragment.class);
                this.startActivity(intentEnd);
                return true;
            case R.id.check_ride:
                Intent intentCheck=new Intent(getActivity(),CheckFragment.class);
                this.startActivity(intentCheck);
                return true;
            case R.id.register_bike:
                Intent intentRegister=new Intent(getActivity(),RegisterFragment.class);
                this.startActivity(intentRegister);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.register_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }
    private void updateUI() {
        RideLab crimeLab = RideLab.get(getActivity());
        List<Ride> crimes = crimeLab.getRides();

        mAdapter = new RideAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class RideHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Ride mRide;

        private TextView bikeName;
        private TextView bikeType;

        public RideHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_register, parent, false));
            itemView.setOnClickListener(this);

            bikeName = (TextView) itemView.findViewById(R.id.bikeName);
            bikeType = (TextView) itemView.findViewById(R.id.bikeType);
        }

        public void bind(Ride ride) {
            mRide = ride;
            bikeName.setText(mRide.getBikeName());
            bikeType.setText(mRide.getStartRide());

        }

        @Override
        public void onClick(View view) {

        }
    }
    private class RideAdapter extends RecyclerView.Adapter<RideHolder> {

        private List<Ride> mRides;

        public RideAdapter(List<Ride> rides) {
            mRides = rides;
        }

        @Override
        public RideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RideHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(RideHolder holder, int position) {
            Ride ride = mRides.get(position);
            holder.bind(ride);
        }

        @Override
        public int getItemCount() {
            return mRides.size();
        }
    }
}
