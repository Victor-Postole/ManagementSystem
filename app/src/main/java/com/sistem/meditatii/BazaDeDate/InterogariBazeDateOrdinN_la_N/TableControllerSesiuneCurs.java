package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerSesiuneCurs extends BazaDeDate {

    public TableControllerSesiuneCurs(Context context) {
        super(context);
    }

    public boolean insertSesiuneCurs(int idCurs, int idSesiune) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_curs", idCurs);
        values.put("id_sesiune", idSesiune);

        long newRowId = db.insert("sesiune_curs", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<SesiuneCursModel> getSesiuniCursuri() {
        List<SesiuneCursModel> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_curs",
                "id_sesiune"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "sesiune_curs",
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
                    int idCurs = cursor.getInt(cursor.getColumnIndexOrThrow("id_curs"));
                    int idSesiune = cursor.getInt(cursor.getColumnIndexOrThrow("id_sesiune"));

                    SesiuneCursModel model = new SesiuneCursModel(id, idCurs, idSesiune);
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

    public List<SesiuneCursModel_INNER_JOIN> getSesiuniCursuriWithDetails() {
        List<SesiuneCursModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT sc.id, c.id AS id_curs, sm.id AS id_sesiune, c.nume_curs as nume_curs " +
                "FROM sesiune_curs sc " +
                "INNER JOIN curs c ON sc.id_curs = c.id " +
                "INNER JOIN sesiune_meditatie sm ON sc.id_sesiune = sm.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idCurs = cursor.getInt(cursor.getColumnIndexOrThrow("id_curs"));
                    int idSesiune = cursor.getInt(cursor.getColumnIndexOrThrow("id_sesiune"));
                    String nume_curs = cursor.getString(cursor.getColumnIndexOrThrow("nume_curs"));

                    SesiuneCursModel_INNER_JOIN model = new SesiuneCursModel_INNER_JOIN(id, idCurs, idSesiune, nume_curs);
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

    public boolean deleteSesiuneCursById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("sesiune_curs", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsDeleted > 0;
    }

    public boolean updateSesiuneCursById(int id, int idCurs, int idSesiune) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_curs", idCurs);
        values.put("id_sesiune", idSesiune);

        int rowsAffected = db.update("sesiune_curs", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }
}
