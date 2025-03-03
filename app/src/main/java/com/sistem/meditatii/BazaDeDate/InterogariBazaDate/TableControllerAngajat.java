package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerAngajat extends BazaDeDate {

    public TableControllerAngajat(Context context) {
        super(context);
    }

    public boolean insertAngajatInDatabase(InsertAngajatDBModel insertAngajat) {
        ContentValues values = new ContentValues();

        values.put("telefon", insertAngajat.getTelefon());
        values.put("cnp", insertAngajat.getCnp());
        values.put("email", insertAngajat.getEmail());
        values.put("nume", insertAngajat.getNume());
        values.put("prenume", insertAngajat.getPrenume());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("angajat", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM angajat";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertAngajatDBModel> readAngajati() {
        List<InsertAngajatDBModel> angajatList = new ArrayList<>();

        String sql = "SELECT * FROM angajat";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String telefon = cursor.getString(cursor.getColumnIndex("telefon"));
                @SuppressLint("Range") String cnp = cursor.getString(cursor.getColumnIndex("cnp"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String nume = cursor.getString(cursor.getColumnIndex("nume"));
                @SuppressLint("Range") String prenume = cursor.getString(cursor.getColumnIndex("prenume"));

                angajatList.add(new InsertAngajatDBModel(id, telefon, cnp, email, nume, prenume));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return angajatList;
    }

    public boolean deleteAngajatById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("angajat", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateAngajat(InsertAngajatDBModel insertAngajat) {
        ContentValues values = new ContentValues();

        values.put("telefon", insertAngajat.getTelefon());
        values.put("cnp", insertAngajat.getCnp());
        values.put("email", insertAngajat.getEmail());
        values.put("nume", insertAngajat.getNume());
        values.put("prenume", insertAngajat.getPrenume());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertAngajat.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("angajat", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
