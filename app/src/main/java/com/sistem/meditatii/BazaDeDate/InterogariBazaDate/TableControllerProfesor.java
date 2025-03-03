package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerProfesor extends BazaDeDate {

    public TableControllerProfesor(Context context) {
        super(context);
    }

    public boolean insertProfesorInDatabase(InsertFacturaDBModel.InsertProfesorDBModel insertProfesor) {
        ContentValues values = new ContentValues();

        values.put("telefon", insertProfesor.getTelefon());
        values.put("email", insertProfesor.getEmail());
        values.put("nume", insertProfesor.getNume());
        values.put("prenume", insertProfesor.getPrenume());
        values.put("angajat", insertProfesor.getAngajat());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("profesor", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM profesor";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertFacturaDBModel.InsertProfesorDBModel> readProfesori() {
        List<InsertFacturaDBModel.InsertProfesorDBModel> profesorList = new ArrayList<>();

        String sql = "SELECT * FROM profesor";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String telefon = cursor.getString(cursor.getColumnIndex("telefon"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String nume = cursor.getString(cursor.getColumnIndex("nume"));
                @SuppressLint("Range") String prenume = cursor.getString(cursor.getColumnIndex("prenume"));
                @SuppressLint("Range") String angajat = cursor.getString(cursor.getColumnIndex("angajat"));

                profesorList.add(new InsertFacturaDBModel.InsertProfesorDBModel(id, telefon, email, nume, prenume, angajat));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return profesorList;
    }

    public boolean deleteProfesorById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("profesor", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateProfesor(InsertFacturaDBModel.InsertProfesorDBModel insertProfesor) {
        ContentValues values = new ContentValues();

        values.put("telefon", insertProfesor.getTelefon());
        values.put("email", insertProfesor.getEmail());
        values.put("nume", insertProfesor.getNume());
        values.put("prenume", insertProfesor.getPrenume());
        values.put("angajat", insertProfesor.getAngajat());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertProfesor.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("profesor", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
