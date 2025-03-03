package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertUtilizatorDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerUtilizator extends BazaDeDate {

    public TableControllerUtilizator(Context context) {
        super(context);
    }

    public boolean insertUtilizatorInDatabase(InsertUtilizatorDBModel insertUtilizator) {
        ContentValues values = new ContentValues();

        values.put("nume", insertUtilizator.getNume());
        values.put("ultima_conectare", insertUtilizator.getUltimaConectare());
        values.put("data_creare_cont", insertUtilizator.getDataCreareCont());
        values.put("email", insertUtilizator.getEmail());
        values.put("parola", insertUtilizator.getParola());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("utilizator", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM utilizator";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertUtilizatorDBModel> readUtilizator() {
        List<InsertUtilizatorDBModel> utilizatorList = new ArrayList<>();

        String sql = "SELECT * FROM utilizator";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String nume = cursor.getString(cursor.getColumnIndex("nume"));
                @SuppressLint("Range") String ultimaConectare = cursor.getString(cursor.getColumnIndex("ultima_conectare"));
                @SuppressLint("Range") String dataCreareCont = cursor.getString(cursor.getColumnIndex("data_creare_cont"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String parola = cursor.getString(cursor.getColumnIndex("parola"));

                utilizatorList.add(new InsertUtilizatorDBModel(id, nume, ultimaConectare, dataCreareCont, email, parola));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return utilizatorList;
    }

    public boolean deleteUtilizatorById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("utilizator", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateUtilizator(InsertUtilizatorDBModel insertUtilizator) {
        ContentValues values = new ContentValues();

        values.put("nume", insertUtilizator.getNume());
        values.put("ultima_conectare", insertUtilizator.getUltimaConectare());
        values.put("data_creare_cont", insertUtilizator.getDataCreareCont());
        values.put("email", insertUtilizator.getEmail());
        values.put("parola", insertUtilizator.getParola());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertUtilizator.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("utilizator", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }

    public InsertUtilizatorDBModel getUtilizatorByName(String name) {
        InsertUtilizatorDBModel utilizator = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM utilizator WHERE nume = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{name});

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String nume = cursor.getString(cursor.getColumnIndex("nume"));
            @SuppressLint("Range") String ultimaConectare = cursor.getString(cursor.getColumnIndex("ultima_conectare"));
            @SuppressLint("Range") String dataCreareCont = cursor.getString(cursor.getColumnIndex("data_creare_cont"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String parola = cursor.getString(cursor.getColumnIndex("parola"));

            utilizator = new InsertUtilizatorDBModel(id, nume, ultimaConectare, dataCreareCont, email, parola);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return utilizator;
    }
}
