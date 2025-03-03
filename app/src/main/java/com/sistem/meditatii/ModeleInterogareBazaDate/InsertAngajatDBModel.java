package com.sistem.meditatii.ModeleInterogareBazaDate;

public class InsertAngajatDBModel {
    private int id;
    private String telefon;
    private String cnp;
    private String email;
    private String nume;
    private String prenume;

    public InsertAngajatDBModel(int id, String telefon, String cnp, String email, String nume, String prenume) {
        this.id = id;
        this.telefon = telefon;
        this.cnp = cnp;
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
    }

    public int getId() {
        return id;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getCnp() {
        return cnp;
    }

    public String getEmail() {
        return email;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    @Override
    public String toString() {
        return  nume + " " + prenume;
    }
}
