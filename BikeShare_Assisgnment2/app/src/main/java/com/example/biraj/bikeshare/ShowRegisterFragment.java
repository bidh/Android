package com.example.biraj.bikeshare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by biraj on 5/2/2018.
 */

public class ShowRegisterFragment extends Fragment {
    private RecyclerView mRegisterRecyclerView;
    private RegisterAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_list, container, false);

        mRegisterRecyclerView = (RecyclerView) view
                .findViewById(R.id.register_recycler_view);
        mRegisterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }
    private void updateUI() {
        RegisterLab registerLab = RegisterLab.get(getActivity());
        List<Register> registers= registerLab.getRegisters();

        if(mAdapter==null){
            mAdapter=new RegisterAdapter(registers);
            mRegisterRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setRegisters(registers);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class RegisterHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Register mRegister;

        //private TextView mBikeUUIDView;
        private TextView mBikeNameView;
        private TextView mBikeTypeView;
        private TextView mBikePriceView;
        private ImageView mImageView;
        //private TextView mBikeDateTime;

        public RegisterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_register, parent, false));
            itemView.setOnClickListener(this);

            //mBikeUUIDView = (TextView) itemView.findViewById(R.id.bikeUUID);
            mBikeNameView = (TextView) itemView.findViewById(R.id.bikeName);
            mBikeTypeView = (TextView) itemView.findViewById(R.id.bikeType);
            mBikePriceView = (TextView)itemView.findViewById(R.id.bikePrice);
            //mBikeDateTime = (TextView) itemView.findViewById(R.id.bikeDate);
            mImageView=(ImageView) itemView.findViewById(R.id.imageView);
        }

        public void bind(Register register) {
            mRegister = register;
            //mBikeUUIDView.setText(mRegister.getId().toString());
            mBikeNameView.setText("Bike name:" + mRegister.getName());
            mBikeTypeView.setText("Bike type:" + mRegister.getType());
            mBikePriceView.setText("Bike price per hour: "+mRegister.getPrice()+ " kr");
            mImageView.setImageBitmap(RegisterLab.ConvertByteArrayToBitmap(mRegister.getImage()));
        }
        @Override
        public void onClick(View view) {
        }
    }
    private class RegisterAdapter extends RecyclerView.Adapter<RegisterHolder> {
        private List<Register> mRegister;
        public RegisterAdapter(List<Register> register) {
            mRegister = register;
        }
        @Override
        public RegisterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RegisterHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(RegisterHolder holder, int position) {
            Register register= mRegister.get(position);
            holder.bind(register);
        }
        public int getItemCount() {
            return mRegister.size();
        }

        public void setRegisters(List<Register> registers){
            mRegister=registers;
        }
    }
}
