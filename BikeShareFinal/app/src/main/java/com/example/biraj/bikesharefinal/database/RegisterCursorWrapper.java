package com.example.biraj.bikesharefinal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.biraj.bikesharefinal.Register;
import com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable;

import java.sql.Blob;

/**
 * Created by biraj on 5/19/2018.
 */

public class RegisterCursorWrapper extends CursorWrapper {
    public RegisterCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Register getRegister() {
        String uuidString = getString(getColumnIndex(RegisterTable.Cols.UUID));
        String name = getString(getColumnIndex(RegisterTable.Cols.NAME));
        String type= getString(getColumnIndex(RegisterTable.Cols.TYPE));
        String date = getString(getColumnIndex(RegisterTable.Cols.DATE));
        String price = getString(getColumnIndex(RegisterTable.Cols.PRICE));
        //byte[] image=getBlob(getColumnIndex(RegisterTable.Cols.IMAGE));

        Register register = new Register();
        register.setName(name);
        register.setType(type);
        register.setDate(date);
        register.setPrice(price);
        //register.setImage(image);
        return register;
    }
}
