package com.sistem.meditatii.ModeleInterogareBazaDate;


public class InsertStudentDBModel {
    private int id;
    private String nume;
    private String prenume;
    private String cnp;
    private String dataNastere;
    private String curs;
    private String utilizator;
    private String numarTelefon;

    public InsertStudentDBModel(int id, String nume, String prenume,  String curs, String utilizator, String numarTelefon) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.curs = curs;
        this.utilizator = utilizator;
        this.numarTelefon = numarTelefon;
    }

    public InsertStudentDBModel(int id, String nume, String prenume, String cnp, String dataNastere, String curs, String utilizator, String numarTelefon) {

        this.id = id;
        this.nume = nume;
        this.cnp = cnp;
        this.prenume = prenume;
        this.dataNastere = dataNastere;
        this.curs = curs;
        this.utilizator = utilizator;
        this.numarTelefon = numarTelefon;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public String getCurs() {
        return curs;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    @Override
    public String toString() {
        return nume + " " + prenume;
    }
}
