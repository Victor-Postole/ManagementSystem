package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.AngajareProfesor.AngajareProfesorModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.AngajareProfesor.AngajareProfesorModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerAngajareProfesor extends BazaDeDate {

    public TableControllerAngajareProfesor(Context context) {
        super(context);
    }

    public boolean insertAngajareProfesor(int idProfesor, int idAngajat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_profesor", idProfesor);
        values.put("id_angajat", idAngajat);

        long newRowId = db.insert("angajare_profesor", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<AngajareProfesorModel_INNER_JOIN> getAngajariProfesori() {
        List<AngajareProfesorModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_profesor",
                "id_angajat"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "angajare_profesor",
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
                    int idProfesor = cursor.getInt(cursor.getColumnIndexOrThrow("id_profesor"));
                    int idAngajat = cursor.getInt(cursor.getColumnIndexOrThrow("id_angajat"));

                    AngajareProfesorModel_INNER_JOIN model = new AngajareProfesorModel_INNER_JOIN(id, idProfesor, idAngajat);
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

    public List<AngajareProfesorModel_INNER_JOIN> getAngajariProfesoriWithDetails() {
        List<AngajareProfesorModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT ap.id, ap.id_profesor, ap.id_angajat, p.nume AS nume_profesor, p.prenume AS prenume_profesor, " +
                "a.nume AS nume_angajat, a.prenume AS prenume_angajat " +
                "FROM angajare_profesor ap " +
                "INNER JOIN profesor p ON ap.id_profesor = p.id " +
                "INNER JOIN angajat a ON ap.id_angajat = a.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idProfesor = cursor.getInt(cursor.getColumnIndexOrThrow("id_profesor"));
                    int idAngajat = cursor.getInt(cursor.getColumnIndexOrThrow("id_angajat"));
                    String numeProfesor = cursor.getString(cursor.getColumnIndexOrThrow("nume_profesor"));
                    String prenumeProfesor = cursor.getString(cursor.getColumnIndexOrThrow("prenume_profesor"));
                    String numeAngajat = cursor.getString(cursor.getColumnIndexOrThrow("nume_angajat"));
                    String prenumeAngajat = cursor.getString(cursor.getColumnIndexOrThrow("prenume_angajat"));

                    AngajareProfesorModel_INNER_JOIN model = new AngajareProfesorModel_INNER_JOIN(id, idProfesor, idAngajat);
                    model.setNumeProfesor(numeProfesor);
                    model.setPrenumeProfesor(prenumeProfesor);
                    model.setNumeAngajat(numeAngajat);
                    model.setPrenumeAngajat(prenumeAngajat);

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

    public boolean updateAngajareProfesorById(int id, int idProfesor, int idAngajat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_profesor", idProfesor);
        values.put("id_angajat", idAngajat);

        int rowsUpdated = db.update("angajare_profesor", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsUpdated > 0;
    }


    public boolean deleteAngajareProfesorById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("angajare_profesor", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsDeleted > 0;
    }
}
