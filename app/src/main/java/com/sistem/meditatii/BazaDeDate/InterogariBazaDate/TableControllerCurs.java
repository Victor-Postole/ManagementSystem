package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerCurs extends BazaDeDate {

    public TableControllerCurs(Context context) {
        super(context);
    }

    public boolean insertCursInDatabase(InsertCursDBModel insertCurs) {
        ContentValues values = new ContentValues();

        values.put("nume_curs", insertCurs.getNumeCurs());
        values.put("profesor", insertCurs.getProfesor());
        values.put("resursa", insertCurs.getResursa());
        values.put("data_curs", insertCurs.getDataCurs());
        values.put("meditatie", insertCurs.getMeditatie());
        values.put("student", insertCurs.getStudent());
        values.put("locatie_curs", insertCurs.getLocatieCurs());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("curs", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM curs";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertCursDBModel> readCurs() {
        List<InsertCursDBModel> cursList = new ArrayList<>();

        String sql = "SELECT * FROM curs";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String numeCurs = cursor.getString(cursor.getColumnIndex("nume_curs"));
                @SuppressLint("Range") String profesor = cursor.getString(cursor.getColumnIndex("profesor"));
                @SuppressLint("Range") String resursa = cursor.getString(cursor.getColumnIndex("resursa"));
                @SuppressLint("Range") String dataCurs = cursor.getString(cursor.getColumnIndex("data_curs"));
                @SuppressLint("Range") String meditatie = cursor.getString(cursor.getColumnIndex("meditatie"));
                @SuppressLint("Range") String student = cursor.getString(cursor.getColumnIndex("student"));
                @SuppressLint("Range") String locatieCurs = cursor.getString(cursor.getColumnIndex("locatie_curs"));

                cursList.add(new InsertCursDBModel(id, numeCurs, profesor, resursa, dataCurs, meditatie, student, locatieCurs));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cursList;
    }

    public boolean deleteCursById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("curs", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateCurs(InsertCursDBModel insertCurs) {
        ContentValues values = new ContentValues();

        values.put("nume_curs", insertCurs.getNumeCurs());
        values.put("profesor", insertCurs.getProfesor());
        values.put("resursa", insertCurs.getResursa());
        values.put("data_curs", insertCurs.getDataCurs());
        values.put("meditatie", insertCurs.getMeditatie());
        values.put("student", insertCurs.getStudent());
        values.put("locatie_curs", insertCurs.getLocatieCurs());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertCurs.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("curs", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
