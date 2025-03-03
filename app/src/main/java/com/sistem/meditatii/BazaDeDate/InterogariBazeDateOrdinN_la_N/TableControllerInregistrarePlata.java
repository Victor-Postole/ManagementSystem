package com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sistem.meditatii.BazaDeDate.BazaDeDate;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InregistrarePlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InregistrarePlataModel_INNER_JOIN;

import java.util.ArrayList;
import java.util.List;
public class TableControllerInregistrarePlata extends BazaDeDate {

    public TableControllerInregistrarePlata(Context context) {
        super(context);
    }

    public boolean insertInregistrarePlata(int idFactura, int idPlata) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_factura", idFactura);
        values.put("id_plata", idPlata);

        long newRowId = db.insert("inregistrare_plata", null, values);
        db.close();

        return newRowId != -1;
    }

    public List<InregistrarePlataModel_INNER_JOIN> getInregistrariPlata() {
        List<InregistrarePlataModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "id_factura",
                "id_plata"
        };

        Cursor cursor = null;

        try {
            cursor = db.query(
                    "inregistrare_plata",
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
                    int idFactura = cursor.getInt(cursor.getColumnIndexOrThrow("id_factura"));
                    int idPlata = cursor.getInt(cursor.getColumnIndexOrThrow("id_plata"));

                    InregistrarePlataModel_INNER_JOIN model = new InregistrarePlataModel_INNER_JOIN(id, idFactura, idPlata);
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

    public List<InregistrarePlataModel_INNER_JOIN> getInregistrariPlataWithDetails() {
        List<InregistrarePlataModel_INNER_JOIN> resultList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT ip.id, f.id AS id_factura, p.suma AS suma_plata " +
                "FROM inregistrare_plata ip " +
                "INNER JOIN factura f ON ip.id_factura = f.id " +
                "INNER JOIN plata p ON ip.id_plata = p.id";

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    String nrFactura = cursor.getString(cursor.getColumnIndexOrThrow("id_factura"));
                    double sumaPlata = cursor.getDouble(cursor.getColumnIndexOrThrow("suma_plata"));

                    InregistrarePlataModel_INNER_JOIN model = new InregistrarePlataModel_INNER_JOIN(id, String.valueOf(sumaPlata), nrFactura);
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

    // Method to update a record by its ID
    public boolean updateInregistrarePlataById(int id, int idFactura, int idPlata) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_factura", idFactura);
        values.put("id_plata", idPlata);

        int affectedRows = db.update("inregistrare_plata", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return affectedRows > 0;
    }

    // Method to delete a record by its ID
    public boolean deleteInregistrarePlataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int affectedRows = db.delete("inregistrare_plata", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return affectedRows > 0;
    }
}
