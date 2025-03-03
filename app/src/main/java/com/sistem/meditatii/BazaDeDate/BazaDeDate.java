package com.sistem.meditatii.BazaDeDate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BazaDeDate extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "AgentieStudenti";

    public BazaDeDate(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlFactura = "CREATE TABLE factura"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "data_emitere DATE,"
                + "suma_totala LONG, "
                + "plata LONG, "
                + "status_plata STRING )";

        String sqlAngajat = "CREATE TABLE angajat"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "telefon STRING,"
                + "cnp STRING,"
                + "email STRING,"
                + "nume STRING ,"
                + "prenume STRING)";

        String sqlProfesor = "CREATE TABLE profesor"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "telefon STRING,"
                + "email STRING,"
                + "nume STRING,"
                + "prenume STRING,"
                + "angajat STRING)";

        String sqlPlata = "CREATE TABLE plata"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "factura INTEGER,"
                + "metoda_plata STRING,"
                + "data_plata STRING,"
                + "suma STRING, "
                + "sesiune_meditatii STRING)";


        String sqlRaport = "CREATE TABLE raport"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "resursa STRING,"
                + "detalii STRING,"
                + "data_generare STRING,"
                + "tip_raport STRING)";

        String sqlCurs = "CREATE TABLE curs"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nume_curs STRING,"
                + "profesor STRING,"
                + "resursa STRING,"
                + "data_curs DATE,"
                + "meditatie STRING,"
                + "student STRING,"
                + "locatie_curs STRING)";

        String sqlSesiuneMeditatie = "CREATE TABLE sesiune_meditatie"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "resursa STRING,"
                + "curs STRING,"
                + "ora_inceput DATE,"
                + "ora_sfarsit DATE,"
                + "data_sesiune DATE,"
                + "plata STRING)";

        String sqlUtilizator = "CREATE TABLE utilizator"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nume STRING,"
                + "ultima_conectare DATE,"
                + "data_creare_cont DATE,"
                + "email STRING,"
                + "parola STRING)";


        String sqlStudent = "CREATE TABLE student"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nume STRING,"
                + "prenume STRING,"
                + "cnp STRING,"
                + "data_nastere DATE,"
                + "curs STRING,"
                + "utilizator STRING,"
                + "numar_telefon STRING)";

        String sqlResursa = "CREATE TABLE resursa"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "resursa_curs STRING,"
                + "nume_resursa STRING,"
                + "cantitate_resursa LONG,"
                + "resursa_sesiune DATE,"
                + "resursa_raport STRING,"
                + "tip_resursa STRING)";

        String sqlInregistrarePlata = "CREATE TABLE inregistrare_plata"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_factura INTEGER,"
                + "id_plata INTEGER)";


        String sqlAngajareProfesor = "CREATE TABLE angajare_profesor"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_profesor INTEGER,"
                + "id_angajat INTEGER)";

        String sqlPredareCurs = "CREATE TABLE predare_curs"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_profesor INTEGER,"
                + "id_curs INTEGER)";


        String sqlSesiuneCurs = "CREATE TABLE sesiune_curs"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_curs INTEGER,"
                + "id_sesiune INTEGER)";

        String sqlPlataSesiune = "CREATE TABLE plata_sesiune"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_plata INTEGER,"
                + "id_sesiune_meditatie INTEGER)";


        String sqlResurseSesiuneMeditatie = "CREATE TABLE resurse_sesiune_meditatie"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_sesiune_meditatie INTEGER,"
                + "id_resursa INTEGER)";

        String sqlResurseGenerareRapoarte = "CREATE TABLE resurse_generare_rapoarte"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_resursa INTEGER,"
                + "id_raport INTEGER)";


        String sqlParticipareCurs = "CREATE TABLE participare_curs"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_curs INTEGER,"
                + "id_student INTEGER)";


        String sqlResurseCurs = "CREATE TABLE resurse_curs"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_curs INTEGER,"
                + "id_resurse INTEGER)";


        String sqlContUtilizator = "CREATE TABLE cont_utilizator"
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "id_utilizator INTEGER,"
                + "id_student INTEGER)";


        db.execSQL(sqlFactura);
        db.execSQL(sqlAngajat);
        db.execSQL(sqlProfesor);
        db.execSQL(sqlPlata);
        db.execSQL(sqlRaport);
        db.execSQL(sqlCurs);
        db.execSQL(sqlSesiuneMeditatie);
        db.execSQL(sqlUtilizator);
        db.execSQL(sqlStudent);
        db.execSQL(sqlResursa);
        db.execSQL(sqlInregistrarePlata);
        db.execSQL(sqlAngajareProfesor);
        db.execSQL(sqlPredareCurs);
        db.execSQL(sqlSesiuneCurs);
        db.execSQL(sqlPlataSesiune);
        db.execSQL(sqlResurseSesiuneMeditatie);
        db.execSQL(sqlResurseGenerareRapoarte);
        db.execSQL(sqlParticipareCurs);
        db.execSQL(sqlResurseCurs);
        db.execSQL(sqlContUtilizator);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String factura = "DROP TABLE IF EXISTS factura";
        String angajat = "DROP TABLE IF EXISTS angajat";
        String profesor = "DROP TABLE IF EXISTS profesor";
        String plata = "DROP TABLE IF EXISTS plata";
        String raport = "DROP TABLE IF EXISTS raport";
        String curs = "DROP TABLE IF EXISTS curs";
        String sesiune_meditatie = "DROP TABLE IF EXISTS sesiune_meditatie";
        String utilizator = "DROP TABLE IF EXISTS utilizator";
        String student = "DROP TABLE IF EXISTS student";
        String resursa = "DROP TABLE IF EXISTS resursa";
        String inregistrare_plata = "DROP TABLE IF EXISTS inregistrare_plata";
        String angajare_profesor = "DROP TABLE IF EXISTS angajare_profesor";
        String predare_curs = "DROP TABLE IF EXISTS predare_curs";
        String sesiune_curs = "DROP TABLE IF EXISTS sesiune_curs";
        String plata_sesiune = "DROP TABLE IF EXISTS plata_sesiune";
        String resurse_sesiune_meditatie = "DROP TABLE IF EXISTS resurse_sesiune_meditatie";
        String resurse_generare_rapoarte = "DROP TABLE IF EXISTS resurse_generare_rapoarte";
        String participare_curs = "DROP TABLE IF EXISTS participare_curs";
        String resurse_curs = "DROP TABLE IF EXISTS resurse_curs";
        String cont_utilizator = "DROP TABLE IF EXISTS cont_utilizator";


        db.execSQL(factura);
        db.execSQL(angajat);
        db.execSQL(profesor);
        db.execSQL(plata);
        db.execSQL(raport);
        db.execSQL(curs);
        db.execSQL(sesiune_meditatie);
        db.execSQL(utilizator);
        db.execSQL(student);
        db.execSQL(resursa);
        db.execSQL(inregistrare_plata);
        db.execSQL(angajare_profesor);
        db.execSQL(predare_curs);
        db.execSQL(sesiune_curs);
        db.execSQL(plata_sesiune);
        db.execSQL(resurse_sesiune_meditatie);
        db.execSQL(resurse_generare_rapoarte);
        db.execSQL(participare_curs);
        db.execSQL(resurse_curs);
        db.execSQL(cont_utilizator);
        onCreate(db);
    }

    public void dropDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}