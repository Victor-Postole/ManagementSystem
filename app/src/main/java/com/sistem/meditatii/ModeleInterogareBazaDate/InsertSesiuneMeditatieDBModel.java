package com.sistem.meditatii.ModeleInterogareBazaDate;


public class InsertSesiuneMeditatieDBModel {
    private int id;
    private String resursa;
    private String curs;
    private String oraInceput;
    private String oraSfarsit;
    private String dataSesiune;
    private String plata;

    public InsertSesiuneMeditatieDBModel(int id, String resursa, String curs, String oraInceput, String oraSfarsit, String dataSesiune, String plata) {
        this.id = id;
        this.resursa = resursa;
        this.curs = curs;
        this.oraInceput = oraInceput;
        this.oraSfarsit = oraSfarsit;
        this.dataSesiune = dataSesiune;
        this.plata = plata;
    }

    public int getId() {
        return id;
    }

    public String getResursa() {
        return resursa;
    }

    public String getCurs() {
        return curs;
    }

    public String getOraInceput() {
        return oraInceput;
    }

    public String getOraSfarsit() {
        return oraSfarsit;
    }

    public String getDataSesiune() {
        return dataSesiune;
    }

    public String getPlata() {
        return plata;
    }

    @Override
    public String toString() {
        return
                "id sesiune " + id;
    }
}
