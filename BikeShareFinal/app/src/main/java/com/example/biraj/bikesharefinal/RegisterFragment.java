package com.example.biraj.bikesharefinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * Created by biraj on 5/19/2018.
 */

public class RegisterFragment extends Fragment {
    private static final String ARG_BIKE_ID = "bike_id";
    private ImageButton mPhotoButton;
    private Button btnRegister;
    private EditText txtBikeName,txtBikeType,txtBikePrice;
    Register register;
    private ImageView mImageView;
    private byte[] image;
    RegisterLab registerLab;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerLab = RegisterLab.get(getActivity());
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_bike, container, false);

        mPhotoButton = (ImageButton) v.findViewById(R.id.btn_bike_camera);
        mImageView=(ImageView) v.findViewById(R.id.bike_photo);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        btnRegister=(Button) v.findViewById(R.id.btn_register_bike);
        txtBikeName=(EditText) v.findViewById(R.id.bike_name);
        txtBikeType=(EditText) v.findViewById(R.id.bike_type);
        txtBikePrice=(EditText) v.findViewById(R.id.bike_price);
        register=new Register();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register.setName(txtBikeName.getText().toString());
                register.setType(txtBikeType.getText().toString());
                register.setPrice(txtBikePrice.getText().toString());
                //register.setImage(image);
                registerLab.addRegister(register);
                getActivity().recreate();
            }
        });
        return v;
    }
    /*
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            image=RegisterLab.convertBitmapToByteArray(imageBitmap);
        }
    }*/

}
