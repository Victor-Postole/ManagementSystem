package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerFactura extends BazaDeDate{

    public TableControllerFactura(Context context) {
        super(context);
    }

    public boolean insertFacturaInDatabase(InsertFacturaDBModel insertFactura) {
        ContentValues values = new ContentValues();

        values.put("data_emitere", insertFactura.getDataEmitere());
        values.put("suma_totala", insertFactura.getSumaTotala());
        values.put("plata", insertFactura.getPlata());
        values.put("status_plata", insertFactura.getStatusPlata());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("factura", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM factura";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertFacturaDBModel> readFacturas() {
        List<InsertFacturaDBModel> facturaList = new ArrayList<>();

        String sql = "SELECT * FROM factura";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String dataEmitere = cursor.getString(cursor.getColumnIndex("data_emitere"));
                @SuppressLint("Range") long sumaTotala = cursor.getLong(cursor.getColumnIndex("suma_totala"));
                @SuppressLint("Range") long plata = cursor.getLong(cursor.getColumnIndex("plata"));
                @SuppressLint("Range") String statusPlata = cursor.getString(cursor.getColumnIndex("status_plata"));

                facturaList.add(new InsertFacturaDBModel(id, dataEmitere, sumaTotala, plata, statusPlata));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return facturaList;
    }

    public boolean deleteFacturaById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("factura", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateFactura(InsertFacturaDBModel insertFactura) {
        ContentValues values = new ContentValues();

        values.put("data_emitere", insertFactura.getDataEmitere());
        values.put("suma_totala", insertFactura.getSumaTotala());
        values.put("plata", insertFactura.getPlata());
        values.put("status_plata", insertFactura.getStatusPlata());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertFactura.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("factura", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
