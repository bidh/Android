package com.example.biraj.bikeshare;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Created by biraj on 5/1/2018.
 */

public class RegisterFragment extends Fragment {
    private static final String ARG_BIKE_ID = "bike_id";
    private static final int REQUEST_PHOTO= 2;

    private File mPhotoFile;
    private ImageButton mPhotoButton;
    private Button btnRegister;
    private ImageView mImageView;
    private byte[] image;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText txtBikeName,txtBikeType,txtBikePrice;
    Register register;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_bike, container, false);

        btnRegister=(Button) v.findViewById(R.id.btn_register_bike);
        txtBikeName=(EditText) v.findViewById(R.id.bike_name);
        txtBikeType=(EditText) v.findViewById(R.id.bike_type);
        txtBikePrice=(EditText) v.findViewById(R.id.bike_price);
        register=new Register();

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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bikeName=txtBikeName.getText().toString();
                String bikeType=txtBikeType.getText().toString();

                register.setName(bikeName);
                register.setType(bikeType);
                register.setPrice(txtBikePrice.getText().toString());
                register.setImage(image);
                RegisterLab.get(getActivity()).addRegister(register);
                getActivity().recreate();
            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            image=RegisterLab.convertBitmapToByteArray(imageBitmap);
        }
    }
}
