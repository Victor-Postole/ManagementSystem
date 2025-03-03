package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.ContUtilizator.ContUtilizatorModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ContUtilizator.ContUtilizatorModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerContUtilizator extends BazaDeDate {

    public TableControllerContUtilizator(Context context) {
        super(context);
    }

    public boolean insertContUtilizator(int idUtilizator, int idStudent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_utilizator", idUtilizator);
        values.put("id_student", idStudent);

        long newRowId = db.insert("cont_utilizator", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<ContUtilizatorModel> getConturiUtilizatori() {
        List<ContUtilizatorModel> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_utilizator",
                "id_student"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "cont_utilizator",
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
                    int idUtilizator = cursor.getInt(cursor.getColumnIndexOrThrow("id_utilizator"));
                    int idStudent = cursor.getInt(cursor.getColumnIndexOrThrow("id_student"));

                    ContUtilizatorModel model = new ContUtilizatorModel(id, idUtilizator, idStudent);
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

    public List<ContUtilizatorModel_INNER_JOIN> getConturiUtilizatoriWithDetails() {
        List<ContUtilizatorModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT cu.id, cu.id_utilizator, cu.id_student, u.nume AS nume_utilizator, s.nume AS nume_student " +
                "FROM cont_utilizator cu " +
                "INNER JOIN utilizator u ON cu.id_utilizator = u.id " +
                "INNER JOIN student s ON cu.id_student = s.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idUtilizator = cursor.getInt(cursor.getColumnIndexOrThrow("id_utilizator"));
                    int idStudent = cursor.getInt(cursor.getColumnIndexOrThrow("id_student"));
                    String numeUtilizator = cursor.getString(cursor.getColumnIndexOrThrow("nume_utilizator"));
                    String numeStudent = cursor.getString(cursor.getColumnIndexOrThrow("nume_student"));

                    ContUtilizatorModel_INNER_JOIN model = new ContUtilizatorModel_INNER_JOIN(id, idUtilizator, idStudent);
                    model.setNumeContUtilizator(numeUtilizator);
                    model.setNumeStudent(numeStudent);

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
    public boolean updateContUtilizatorById(int id, int idUtilizator, int idStudent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_utilizator", idUtilizator);
        values.put("id_student", idStudent);

        int rowsAffected = db.update("cont_utilizator", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }

    // New delete method
    public boolean deleteContUtilizatorById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.delete("cont_utilizator", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }
}
