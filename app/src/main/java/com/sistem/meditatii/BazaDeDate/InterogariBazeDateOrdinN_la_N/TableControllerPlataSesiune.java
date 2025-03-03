package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.PlataSesiune.PlataSesiuneModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.PlataSesiune.PlataSesiuneModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerPlataSesiune extends BazaDeDate {

    public TableControllerPlataSesiune(Context context) {
        super(context);
    }

    public boolean insertPlataSesiune(int idPlata, int idSesiuneMeditatie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_plata", idPlata);
        values.put("id_sesiune_meditatie", idSesiuneMeditatie);

        long newRowId = db.insert("plata_sesiune", null, values);
        db.close();

        return newRowId != -1;
    }

    public boolean insertPlataSesiuneById(int id, int idPlata, int idSesiuneMeditatie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("id_plata", idPlata);
        values.put("id_sesiune_meditatie", idSesiuneMeditatie);

        long newRowId = db.insertWithOnConflict("plata_sesiune", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

        return newRowId != -1;
    }

    public boolean updatePlataSesiuneById(int id, int idPlata, int idSesiuneMeditatie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_plata", idPlata);
        values.put("id_sesiune_meditatie", idSesiuneMeditatie);

        int rowsAffected = db.update("plata_sesiune", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean deletePlataSesiuneById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("plata_sesiune", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsDeleted > 0;
    }

    public List<PlataSesiuneModel> getPlatiSesiuni() {
        List<PlataSesiuneModel> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_plata",
                "id_sesiune_meditatie"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "plata_sesiune",
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
                    int idPlata = cursor.getInt(cursor.getColumnIndexOrThrow("id_plata"));
                    int idSesiuneMeditatie = cursor.getInt(cursor.getColumnIndexOrThrow("id_sesiune_meditatie"));

                    PlataSesiuneModel model = new PlataSesiuneModel(id, idPlata, idSesiuneMeditatie);
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

    public List<PlataSesiuneModel_INNER_JOIN> getPlatiSesiuniWithDetails() {
        List<PlataSesiuneModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT ps.id, p.suma AS suma_plata, sm.resursa AS nume_sesiune " +
                "FROM plata_sesiune ps " +
                "INNER JOIN plata p ON ps.id_plata = p.id " +
                "INNER JOIN sesiune_meditatie sm ON ps.id_sesiune_meditatie = sm.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    long sumaPlata = cursor.getLong(cursor.getColumnIndexOrThrow("suma_plata"));
                    String numeSesiune = cursor.getString(cursor.getColumnIndexOrThrow("nume_sesiune"));

                    PlataSesiuneModel_INNER_JOIN model = new PlataSesiuneModel_INNER_JOIN(id, sumaPlata, numeSesiune);
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
}
