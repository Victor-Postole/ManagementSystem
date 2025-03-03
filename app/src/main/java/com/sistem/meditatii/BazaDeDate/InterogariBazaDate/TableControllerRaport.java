package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertRaportDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerRaport extends BazaDeDate {

    public TableControllerRaport(Context context) {
        super(context);
    }

    public boolean insertRaportInDatabase(InsertRaportDBModel insertRaport) {
        ContentValues values = new ContentValues();

        values.put("resursa", insertRaport.getResursa());
        values.put("detalii", insertRaport.getDetalii());
        values.put("data_generare", insertRaport.getDataGenerare());
        values.put("tip_raport", insertRaport.getTipRaport());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("raport", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM raport";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertRaportDBModel> readRaport() {
        List<InsertRaportDBModel> raportList = new ArrayList<>();

        String sql = "SELECT * FROM raport";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String resursa = cursor.getString(cursor.getColumnIndex("resursa"));
                @SuppressLint("Range") String detalii = cursor.getString(cursor.getColumnIndex("detalii"));
                @SuppressLint("Range") String dataGenerare = cursor.getString(cursor.getColumnIndex("data_generare"));
                @SuppressLint("Range") String tipRaport = cursor.getString(cursor.getColumnIndex("tip_raport"));

                raportList.add(new InsertRaportDBModel(id, resursa, detalii, dataGenerare, tipRaport));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return raportList;
    }

    public boolean deleteRaportById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("raport", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateRaport(InsertRaportDBModel insertRaport) {
        ContentValues values = new ContentValues();

        values.put("resursa", insertRaport.getResursa());
        values.put("detalii", insertRaport.getDetalii());
        values.put("data_generare", insertRaport.getDataGenerare());
        values.put("tip_raport", insertRaport.getTipRaport());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertRaport.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("raport", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
