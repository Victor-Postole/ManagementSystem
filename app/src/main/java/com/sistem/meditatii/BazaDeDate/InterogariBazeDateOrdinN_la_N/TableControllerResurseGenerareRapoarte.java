package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseRaport.ResurseGenerareRapoarteModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseRaport.ResurseGenerareRapoarteModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerResurseGenerareRapoarte extends BazaDeDate {

    public TableControllerResurseGenerareRapoarte(Context context) {
        super(context);
    }

    public boolean insertResursaGenerareRaport(int idResursa, int idRaport) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_resursa", idResursa);
        values.put("id_raport", idRaport);

        long newRowId = db.insert("resurse_generare_rapoarte", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<ResurseGenerareRapoarteModel> getResurseGenerareRapoarte() {
        List<ResurseGenerareRapoarteModel> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_resursa",
                "id_raport"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "resurse_generare_rapoarte",
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
                    int idResursa = cursor.getInt(cursor.getColumnIndexOrThrow("id_resursa"));
                    int idRaport = cursor.getInt(cursor.getColumnIndexOrThrow("id_raport"));

                    ResurseGenerareRapoarteModel model = new ResurseGenerareRapoarteModel(id, idResursa, idRaport);
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

    public List<ResurseGenerareRapoarteModel_INNER_JOIN> getResurseGenerareRapoarteWithDetails() {
        List<ResurseGenerareRapoarteModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT rgr.id, rgr.id_resursa, rgr.id_raport, r.nume_resursa AS nume_resursa, rp.resursa AS nume_raport " +
                "FROM resurse_generare_rapoarte rgr " +
                "INNER JOIN resursa r ON rgr.id_resursa = r.id " +
                "INNER JOIN raport rp ON rgr.id_raport = rp.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idResursa = cursor.getInt(cursor.getColumnIndexOrThrow("id_resursa"));
                    int idRaport = cursor.getInt(cursor.getColumnIndexOrThrow("id_raport"));
                    String numeResursa = cursor.getString(cursor.getColumnIndexOrThrow("nume_resursa"));
                    String numeRaport = cursor.getString(cursor.getColumnIndexOrThrow("nume_raport"));

                    ResurseGenerareRapoarteModel_INNER_JOIN model = new ResurseGenerareRapoarteModel_INNER_JOIN(id, idResursa, idRaport, numeResursa, numeRaport);
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
    public boolean updateResursaGenerareRaportById(int id, int idResursa, int idRaport) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_resursa", idResursa);
        values.put("id_raport", idRaport);

        int rowsAffected = db.update("resurse_generare_rapoarte", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }

    // New delete method
    public boolean deleteResursaGenerareRaportById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.delete("resurse_generare_rapoarte", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }
}
