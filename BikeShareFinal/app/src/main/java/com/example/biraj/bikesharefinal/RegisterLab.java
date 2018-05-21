package com.example.biraj.bikesharefinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.biraj.bikesharefinal.database.RegisterBaseHelper;
import com.example.biraj.bikesharefinal.database.RegisterCursorWrapper;
import com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.UUID;

import static com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable.Cols.DATE;
import static com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable.Cols.UUID;
import static com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable.Cols.PRICE;
import static com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable.Cols.TYPE;
import static com.example.biraj.bikesharefinal.database.RegisterDbSchema.RegisterTable.NAME;

/**
 * Created by biraj on 5/19/2018.
 */

public class RegisterLab{
    private static RegisterLab sRegisterLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static RegisterLab get(Context context) {
        if (sRegisterLab == null) {
            sRegisterLab = new RegisterLab(context);
        }

        return sRegisterLab;
    }
    private RegisterLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new RegisterBaseHelper(mContext)
                .getWritableDatabase();

    }
    public void addRegister(Register c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(RegisterTable.NAME, null, values);
    }
    public List<Register> getRegisters() {
        List<Register> registers = new ArrayList<>();
        RegisterCursorWrapper cursor = queryRegisters(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                registers.add(cursor.getRegister());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return registers;
    }
    public Register getRegister(UUID id) {
        RegisterCursorWrapper cursor = queryRegisters(
                RegisterTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRegister();
        } finally {
            cursor.close();
        }
    }
    /*
    public void updateRegister(Register crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(RegisterTable.NAME, values,
                UUID + " = ?",
                new String[]{uuidString});
    }*/
    private RegisterCursorWrapper queryRegisters(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RegisterTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new RegisterCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Register register) {
        ContentValues values = new ContentValues();
        values.put(UUID,register.getId().toString());
        values.put(NAME, register.getName());
        values.put(TYPE, register.getType());
        values.put(PRICE,register.getPrice());
        values.put(DATE, getDateTime());
        //values.put(IMAGE,register.getImage());
        return values;
    }
    private static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream buffer= new
                ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, buffer);
        return buffer.toByteArray();
    }
    public static Bitmap ConvertByteArrayToBitmap(byte[] blop) {
        return BitmapFactory.decodeByteArray(blop, 0, blop.length);
    }

}
