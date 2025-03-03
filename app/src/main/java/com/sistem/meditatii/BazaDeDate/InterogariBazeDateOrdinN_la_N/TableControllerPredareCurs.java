package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.PredareCursuri.PredareCursModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerPredareCurs extends BazaDeDate {

    public TableControllerPredareCurs(Context context) {
        super(context);
    }

    public boolean insertPredareCurs(int idProfesor, int idCurs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_profesor", idProfesor);
        values.put("id_curs", idCurs);

        long newRowId = db.insert("predare_curs", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<PredareCursModel_INNER_JOIN> getPredariCursuri() {
        List<PredareCursModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_profesor",
                "id_curs"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "predare_curs",
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
                    int idCurs = cursor.getInt(cursor.getColumnIndexOrThrow("id_curs"));

                    PredareCursModel_INNER_JOIN model = new PredareCursModel_INNER_JOIN(id, idProfesor, idCurs, null, null, null);
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

    public List<PredareCursModel_INNER_JOIN> getPredariCursuriWithDetails() {
        List<PredareCursModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT pc.id, pc.id_profesor, pc.id_curs, p.nume AS nume_profesor, p.prenume AS prenume_profesor, " +
                "c.nume_curs AS nume_curs " +
                "FROM predare_curs pc " +
                "INNER JOIN profesor p ON pc.id_profesor = p.id " +
                "INNER JOIN curs c ON pc.id_curs = c.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idProfesor = cursor.getInt(cursor.getColumnIndexOrThrow("id_profesor"));
                    int idCurs = cursor.getInt(cursor.getColumnIndexOrThrow("id_curs"));
                    String numeProfesor = cursor.getString(cursor.getColumnIndexOrThrow("nume_profesor"));
                    String prenumeProfesor = cursor.getString(cursor.getColumnIndexOrThrow("prenume_profesor"));
                    String numeCurs = cursor.getString(cursor.getColumnIndexOrThrow("nume_curs"));

                    PredareCursModel_INNER_JOIN model = new PredareCursModel_INNER_JOIN(id, idProfesor, idCurs, numeProfesor, prenumeProfesor, numeCurs);
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

    public boolean deletePredareCursById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("predare_curs", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsDeleted > 0;
    }

    public boolean updatePredareCursById(int id, int idProfesor, int idCurs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_profesor", idProfesor);
        values.put("id_curs", idCurs);

        int rowsUpdated = db.update("predare_curs", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsUpdated > 0;
    }
}
