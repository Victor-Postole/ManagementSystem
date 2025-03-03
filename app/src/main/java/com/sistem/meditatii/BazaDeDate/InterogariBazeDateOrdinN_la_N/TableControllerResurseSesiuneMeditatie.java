package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseSesiuneMeditatieModel.ResurseSesiuneMeditatieModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerResurseSesiuneMeditatie extends BazaDeDate {

    public TableControllerResurseSesiuneMeditatie(Context context) {
        super(context);
    }

    public boolean insertResursaSesiuneMeditatie(int idSesiuneMeditatie, int idResursa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_sesiune_meditatie", idSesiuneMeditatie);
        values.put("id_resursa", idResursa);

        long newRowId = db.insert("resurse_sesiune_meditatie", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<ResurseSesiuneMeditatieModel_INNER_JOIN> getResurseSesiuniMeditatie() {
        List<ResurseSesiuneMeditatieModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_sesiune_meditatie",
                "id_resursa"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "resurse_sesiune_meditatie",
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idSesiuneMeditatie = cursor.getInt(cursor.getColumnIndexOrThrow("id_sesiune_meditatie"));
                    int idResursa = cursor.getInt(cursor.getColumnIndexOrThrow("id_resursa"));

                    ResurseSesiuneMeditatieModel_INNER_JOIN model = new ResurseSesiuneMeditatieModel_INNER_JOIN(id, idSesiuneMeditatie, idResursa);
                    resultList.add(model);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return resultList;
    }

    public List<ResurseSesiuneMeditatieModel_INNER_JOIN> getResurseSesiuniMeditatieWithDetails() {
        List<ResurseSesiuneMeditatieModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT rsm.id, rsm.id_sesiune_meditatie, rsm.id_resursa, r.nume_resursa AS nume_resursa " +
                "FROM resurse_sesiune_meditatie rsm " +
                "INNER JOIN resursa r ON rsm.id_resursa = r.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idSesiuneMeditatie = cursor.getInt(cursor.getColumnIndexOrThrow("id_sesiune_meditatie"));
                    int idResursa = cursor.getInt(cursor.getColumnIndexOrThrow("id_resursa"));
                    String numeResursa = cursor.getString(cursor.getColumnIndexOrThrow("nume_resursa"));

                    ResurseSesiuneMeditatieModel_INNER_JOIN model = new ResurseSesiuneMeditatieModel_INNER_JOIN(id, idSesiuneMeditatie, idResursa, numeResursa);
                    resultList.add(model);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return resultList;
    }

    // New update method
    public boolean updateResursaSesiuneMeditatieById(int id, int idSesiuneMeditatie, int idResursa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_sesiune_meditatie", idSesiuneMeditatie);
        values.put("id_resursa", idResursa);

        int rowsAffected = db.update("resurse_sesiune_meditatie", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }

    // New delete method
    public boolean deleteResursaSesiuneMeditatieById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.delete("resurse_sesiune_meditatie", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }
}
