package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertPlataModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerPlata extends BazaDeDate {

    public TableControllerPlata(Context context) {
        super(context);
    }

    public boolean insertPlataInDatabase(InsertPlataModel insertPlata) {
        ContentValues values = new ContentValues();

        values.put("factura", insertPlata.getFactura());
        values.put("metoda_plata", insertPlata.getMetodaPlata());
        values.put("data_plata", insertPlata.getDataPlata());
        values.put("suma", insertPlata.getSuma());
        values.put("sesiune_meditatii", insertPlata.getSesiuneMeditatii());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("plata", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM plata";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertPlataModel> readPlati() {
        List<InsertPlataModel> plataList = new ArrayList<>();

        String sql = "SELECT * FROM plata";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") int factura = cursor.getInt(cursor.getColumnIndex("factura"));
                @SuppressLint("Range") String metodaPlata = cursor.getString(cursor.getColumnIndex("metoda_plata"));
                @SuppressLint("Range") String dataPlata = cursor.getString(cursor.getColumnIndex("data_plata"));
                @SuppressLint("Range") String suma = cursor.getString(cursor.getColumnIndex("suma"));
                @SuppressLint("Range") String sesiuneMeditatii = cursor.getString(cursor.getColumnIndex("sesiune_meditatii"));

                plataList.add(new InsertPlataModel(id, factura, metodaPlata, dataPlata, suma, sesiuneMeditatii));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return plataList;
    }

    public boolean deletePlataById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("plata", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updatePlata(InsertPlataModel insertPlata) {
        ContentValues values = new ContentValues();

        values.put("factura", insertPlata.getFactura());
        values.put("metoda_plata", insertPlata.getMetodaPlata());
        values.put("data_plata", insertPlata.getDataPlata());
        values.put("suma", insertPlata.getSuma());
        values.put("sesiune_meditatii", insertPlata.getSesiuneMeditatii());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertPlata.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("plata", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
