package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseCurs.ResurseCursModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseCurs.ResurseCursModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;

public class TableControllerResurseCurs extends BazaDeDate {

    public TableControllerResurseCurs(Context context) {
        super(context);
    }

    public boolean insertResursaCurs(int idCurs, int idResurse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_curs", idCurs);
        values.put("id_resurse", idResurse);

        long newRowId = db.insert("resurse_curs", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<ResurseCursModel> getResurseCurs() {
        List<ResurseCursModel> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_curs",
                "id_resurse"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "resurse_curs",
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
                    int idResurse = cursor.getInt(cursor.getColumnIndexOrThrow("id_resurse"));

                    ResurseCursModel model = new ResurseCursModel(id, idCurs, idResurse);
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

    public List<ResurseCursModel_INNER_JOIN> getResurseCursWithDetails() {
        List<ResurseCursModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT rc.id, rc.id_curs, rc.id_resurse, c.nume_curs AS nume_curs, r.nume_resursa AS nume_resursa " +
                "FROM resurse_curs rc " +
                "INNER JOIN curs c ON rc.id_curs = c.id " +
                "INNER JOIN resursa r ON rc.id_resurse = r.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    int idCurs = cursor.getInt(cursor.getColumnIndexOrThrow("id_curs"));
                    int idResurse = cursor.getInt(cursor.getColumnIndexOrThrow("id_resurse"));
                    String numeCurs = cursor.getString(cursor.getColumnIndexOrThrow("nume_curs"));
                    String numeResursa = cursor.getString(cursor.getColumnIndexOrThrow("nume_resursa"));

                    ResurseCursModel_INNER_JOIN model = new ResurseCursModel_INNER_JOIN(id, idCurs, idResurse, numeCurs, numeResursa);
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
    public boolean updateResursaCursById(int id, int idCurs, int idResurse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_curs", idCurs);
        values.put("id_resurse", idResurse);

        int rowsAffected = db.update("resurse_curs", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }

    // New delete method
    public boolean deleteResursaCursById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.delete("resurse_curs", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return rowsAffected > 0;
    }
}
