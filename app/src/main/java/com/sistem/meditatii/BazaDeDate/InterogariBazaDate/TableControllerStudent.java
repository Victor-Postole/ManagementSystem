package com.sistem.meditatii.BazaDeDate.InterogariBazaDate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;

import java.util.ArrayList;
import java.util.List;

public class TableControllerStudent extends BazaDeDate {

    public TableControllerStudent(Context context) {
        super(context);
    }

    public boolean insertStudentInDatabase(InsertStudentDBModel insertStudent) {
        ContentValues values = new ContentValues();

        values.put("nume", insertStudent.getNume());
        values.put("prenume", insertStudent.getPrenume());
        values.put("cnp", insertStudent.getCnp());
        values.put("data_nastere", insertStudent.getDataNastere());
        values.put("curs", insertStudent.getCurs());
        values.put("utilizator", insertStudent.getUtilizator());
        values.put("numar_telefon", insertStudent.getNumarTelefon());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insertOrThrow("student", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM student";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<InsertStudentDBModel> readStudent() {
        List<InsertStudentDBModel> studentList = new ArrayList<>();

        String sql = "SELECT * FROM student";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String nume = cursor.getString(cursor.getColumnIndex("nume"));
                @SuppressLint("Range") String prenume = cursor.getString(cursor.getColumnIndex("prenume"));
                @SuppressLint("Range") String cnp = cursor.getString(cursor.getColumnIndex("cnp"));
                @SuppressLint("Range") String dataNastere = cursor.getString(cursor.getColumnIndex("data_nastere"));
                @SuppressLint("Range") String curs = cursor.getString(cursor.getColumnIndex("curs"));
                @SuppressLint("Range") String utilizator = cursor.getString(cursor.getColumnIndex("utilizator"));
                @SuppressLint("Range") String numarTelefon = cursor.getString(cursor.getColumnIndex("numar_telefon"));

                studentList.add(new InsertStudentDBModel(id, nume, prenume, cnp, dataNastere, curs, utilizator, numarTelefon));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentList;
    }

    public boolean deleteStudentById(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("student", "id = ?", new String[]{String.valueOf(id)}) > 0;
        db.close();

        return deleteSuccessful;
    }

    public boolean updateStudent(InsertStudentDBModel insertStudent) {
        ContentValues values = new ContentValues();

        values.put("nume", insertStudent.getNume());
        values.put("prenume", insertStudent.getPrenume());
        values.put("cnp", insertStudent.getCnp());
        values.put("data_nastere", insertStudent.getDataNastere());
        values.put("curs", insertStudent.getCurs());
        values.put("utilizator", insertStudent.getUtilizator());
        values.put("numar_telefon", insertStudent.getNumarTelefon());

        String where = "id = ?";
        String[] whereArgs = {String.valueOf(insertStudent.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("student", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}
