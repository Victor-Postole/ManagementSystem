package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerSesiuneMeditatie extends BazaDeDate {

    public TableControllerSesiuneMeditatie(Context context) {
        super(context);
    }

    public boolean insertSesiuneMeditatieInDatabase(InsertSesiuneMeditatieDBModel insertSesiuneMeditatie) {
        ContentValues values = new ContentValues();

        values.put("resursa", insertSesiuneMeditatie.getResursa());
        values.put("curs", insertSesiuneMeditatie.getCurs());
        values.put("ora_inceput", insertSesiuneMeditatie.getOraInceput());
        values.put("ora_sfarsit", insertSesiuneMeditatie.getOraSfarsit());
        values.put("data_sesiune", insertSesiuneMeditatie.getDataSesiune());
        values.put("plata", insertSesiuneMeditatie.getPlata());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("sesiune_meditatie", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM sesiune_meditatie";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertSesiuneMeditatieDBModel> readSesiuneMeditatie() {
        List<InsertSesiuneMeditatieDBModel> sesiuneMeditatieList = new ArrayList<>();

        String sql = "SELECT * FROM sesiune_meditatie";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String resursa = cursor.getString(cursor.getColumnIndex("resursa"));
                @SuppressLint("Range") String curs = cursor.getString(cursor.getColumnIndex("curs"));
                @SuppressLint("Range") String oraInceput = cursor.getString(cursor.getColumnIndex("ora_inceput"));
                @SuppressLint("Range") String oraSfarsit = cursor.getString(cursor.getColumnIndex("ora_sfarsit"));
                @SuppressLint("Range") String dataSesiune = cursor.getString(cursor.getColumnIndex("data_sesiune"));
                @SuppressLint("Range") String plata = cursor.getString(cursor.getColumnIndex("plata"));

                sesiuneMeditatieList.add(new InsertSesiuneMeditatieDBModel(id, resursa, curs, oraInceput, oraSfarsit, dataSesiune, plata));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return sesiuneMeditatieList;
    }

    public boolean deleteSesiuneMeditatieById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("sesiune_meditatie", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateSesiuneMeditatie(InsertSesiuneMeditatieDBModel insertSesiuneMeditatie) {
        ContentValues values = new ContentValues();

        values.put("resursa", insertSesiuneMeditatie.getResursa());
        values.put("curs", insertSesiuneMeditatie.getCurs());
        values.put("ora_inceput", insertSesiuneMeditatie.getOraInceput());
        values.put("ora_sfarsit", insertSesiuneMeditatie.getOraSfarsit());
        values.put("data_sesiune", insertSesiuneMeditatie.getDataSesiune());
        values.put("plata", insertSesiuneMeditatie.getPlata());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertSesiuneMeditatie.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("sesiune_meditatie", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
