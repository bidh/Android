package com.example.biraj.bikesharefinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by biraj on 5/20/2018.
 */

public class RegisterActivity extends AppCompatActivity{
    private static final String ARG_BIKE_ID = "bike_id";
    private ImageButton mPhotoButton;
    private Button btnRegister;
    private EditText txtBikeName,txtBikeType,txtBikePrice;
    Register register;
    private ImageView mImageView;
    private byte[] image;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register_bike);

        mPhotoButton = (ImageButton) findViewById(R.id.btn_bike_camera);
        mImageView=(ImageView) findViewById(R.id.bike_photo);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        btnRegister=(Button) findViewById(R.id.btn_register_bike);
        txtBikeName=(EditText) findViewById(R.id.bike_name);
        txtBikeType=(EditText) findViewById(R.id.bike_type);
        txtBikePrice=(EditText) findViewById(R.id.bike_price);
        register=new Register();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register.setName(txtBikeName.getText().toString());
                register.setType(txtBikeType.getText().toString());
                register.setPrice(txtBikePrice.getText().toString());

            }
        });
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            image=RegisterLab.convertBitmapToByteArray(imageBitmap);
        }
    }
}
