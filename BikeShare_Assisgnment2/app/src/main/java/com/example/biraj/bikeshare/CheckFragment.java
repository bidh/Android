package com.example.biraj.bikeshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by biraj on 5/1/2018.
 */

public class CheckFragment extends Fragment {
    private RecyclerView mRideRecyclerView;
    private RideAdapter mAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_check_bike, container, false);
        mRideRecyclerView = (RecyclerView) v.
                findViewById(R.id.ride_recycler_view);
        mRideRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RideLab rideLab = RideLab.get(getActivity());
        List<Ride> rides= rideLab.getRides();

        if(mAdapter==null){
            mAdapter=new RideAdapter(rides);
            mRideRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setRides(rides);
            mAdapter.notifyDataSetChanged();
        }
        return v;
    }
    private class RideHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Ride mRide;
        //private TextView mBikeUUIDView;
        private TextView mBikeNameView;
        private TextView mStartView;
        private TextView mStartDateView;
        private TextView mEndView;
        private TextView mEndDateView;
        private TextView mPriceView;
        private TextView mBikeTypeView;

        public RideHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_ride, parent, false));
            itemView.setOnClickListener(this);

            //mBikeUUIDView = (TextView) itemView.findViewById(R.id.bikeUUID);
            mBikeNameView = (TextView) itemView.findViewById(R.id.bike_name_list_ride);
            mBikeTypeView = (TextView) itemView.findViewById(R.id.bike_type);
            mStartView = (TextView) itemView.findViewById(R.id.bike_start_list_ride);
            mStartDateView= (TextView) itemView.findViewById(R.id.bike_start_date_list_ride);
            mEndView = (TextView) itemView.findViewById(R.id.bike_end_list_ride);
            mEndDateView= (TextView) itemView.findViewById(R.id.bike_end_date_list_ride);
            mPriceView= (TextView) itemView.findViewById(R.id.bike_price_list_ride);

        }

        public void bind(Ride ride) {
            mRide = ride;
            //mBikeUUIDView.setText("Ride Id : "+mRide.getId().toString());
            mBikeNameView.setText(mRide.getName());
            mStartView.setText( mRide.getStartLocation());
            mStartDateView.setText( mRide.getStartDateTime());
            mEndView.setText(mRide.getEndLocation());
            mEndDateView.setText( mRide.getEndDateTime());
            mPriceView.setText(mRide.getPrice()+" kr ");
        }
        @Override
        public void onClick(View view) {
            Bundle bundle=new Bundle();
            bundle.putString("UUID",mRide.getId().toString());

            EndRideFragment endRideFragment=new EndRideFragment();
            endRideFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, endRideFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
    private class RideAdapter extends RecyclerView.Adapter<RideHolder> {
        private List<Ride> mRide;
        public RideAdapter(List<Ride> ride) {
            mRide = ride;
        }
        @Override
        public RideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RideHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(RideHolder holder, int position) {
            Ride ride=mRide.get(position);
            holder.bind(ride);
        }
        public int getItemCount() {
            return mRide.size();
        }

        public void setRides(List<Ride> rides){
            mRide=rides;
        }
    }
}
