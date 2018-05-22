package com.example.biraj.bikeshare;

import java.util.Date;
import java.util.UUID;

/**
 * Created by biraj on 5/1/2018.
 */

public class Register {
    private UUID mId;
    private String mType;
    private String mName;
    private String mDate;
    private String mPrice;
    private byte[] mImage;

    public Register() {
        this(UUID.randomUUID());
    }

    public Register(UUID id) {
        mId = id;
    }
    public UUID getId() {
        return mId;
    }
    public String getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    public String getDate() {return mDate; }

    public String getPrice() {return mPrice;}
    public void setType(String mType) {
        this.mType = mType;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }
    public void setImage(byte[] image){
        this.mImage=image;
    }
    public byte[] getImage(){
        return mImage;
    }
    public String toString(){
        return this.mName;
    }
}
