package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.ParticipareCurs.ParticipareCursModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ParticipareCurs.ParticipareCursModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerParticipareCurs extends BazaDeDate {

    public TableControllerParticipareCurs(Context context) {
        super(context);
    }

    public boolean insertParticipareCurs(int idCurs, int idStudent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_curs", idCurs);
        values.put("id_student", idStudent);

        long newRowId = db.insert("participare_curs", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<ParticipareCursModel> getParticipariCurs() {
        List<ParticipareCursModel> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_curs",
                "id_student"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "participare_curs",
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
                    int idStudent = cursor.getInt(cursor.getColumnIndexOrThrow("id_student"));

                    ParticipareCursModel model = new ParticipareCursModel(id, idCurs, idStudent);
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

    public List<ParticipareCursModel_INNER_JOIN> getParticipariCursWithDetails() {
        List<ParticipareCursModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT pc.id, c.nume_curs AS nume_curs, s.nume AS nume_student " +
                "FROM participare_curs pc " +
                "INNER JOIN curs c ON pc.id_curs = c.id " +
                "INNER JOIN student s ON pc.id_student = s.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String numeCurs = cursor.getString(cursor.getColumnIndexOrThrow("nume_curs"));
                    String numeStudent = cursor.getString(cursor.getColumnIndexOrThrow("nume_student"));

                    ParticipareCursModel_INNER_JOIN model = new ParticipareCursModel_INNER_JOIN(id, 0, 0, numeCurs, numeStudent);
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

    public boolean deleteParticipareCursById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "id" + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int deletedRows = db.delete("participare_curs", selection, selectionArgs);
        db.close();

        return deletedRows > 0;
    }

    public boolean updateParticipareCursById(int id, int newIdCurs, int newIdStudent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_curs", newIdCurs);
        values.put("id_student", newIdStudent);

        String selection = "id" + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = db.update(
                "participare_curs",
                values,
                selection,
                selectionArgs);

        db.close();

        return count > 0;
    }
}
