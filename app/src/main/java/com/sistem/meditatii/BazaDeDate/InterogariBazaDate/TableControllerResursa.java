package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerResursa extends BazaDeDate {

    public TableControllerResursa(Context context) {
        super(context);
    }

    public boolean insertResursaInDatabase(InsertResursaDBModel insertResursa) {
        ContentValues values = new ContentValues();

        values.put("resursa_curs", insertResursa.getResursaCurs());
        values.put("nume_resursa", insertResursa.getNumeResursa());
        values.put("cantitate_resursa", insertResursa.getCantitateResursa());
        values.put("resursa_sesiune", insertResursa.getResursaSesiune());
        values.put("resursa_raport", insertResursa.getResursaRaport());
        values.put("tip_resursa", insertResursa.getTipResursa());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("resursa", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM resursa";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertResursaDBModel> readResursa() {
        List<InsertResursaDBModel> resursaList = new ArrayList<>();

        String sql = "SELECT * FROM resursa";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String resursaCurs = cursor.getString(cursor.getColumnIndex("resursa_curs"));
                @SuppressLint("Range") String numeResursa = cursor.getString(cursor.getColumnIndex("nume_resursa"));
                @SuppressLint("Range") long cantitateResursa = cursor.getLong(cursor.getColumnIndex("cantitate_resursa"));
                @SuppressLint("Range") String resursaSesiune = cursor.getString(cursor.getColumnIndex("resursa_sesiune"));
                @SuppressLint("Range") String resursaRaport = cursor.getString(cursor.getColumnIndex("resursa_raport"));
                @SuppressLint("Range") String tipResursa = cursor.getString(cursor.getColumnIndex("tip_resursa"));

                resursaList.add(new InsertResursaDBModel(id, resursaCurs, numeResursa, cantitateResursa, resursaSesiune, resursaRaport, tipResursa));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return resursaList;
    }

    public boolean deleteResursaById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("resursa", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateResursa(InsertResursaDBModel insertResursa) {
        ContentValues values = new ContentValues();

        values.put("resursa_curs", insertResursa.getResursaCurs());
        values.put("nume_resursa", insertResursa.getNumeResursa());
        values.put("cantitate_resursa", insertResursa.getCantitateResursa());
        values.put("resursa_sesiune", insertResursa.getResursaSesiune());
        values.put("resursa_raport", insertResursa.getResursaRaport());
        values.put("tip_resursa", insertResursa.getTipResursa());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertResursa.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("resursa", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
