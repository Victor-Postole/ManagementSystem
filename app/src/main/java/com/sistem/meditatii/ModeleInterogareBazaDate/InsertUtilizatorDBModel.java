package com.sistem.meditatii.ModeleInterogareBazaDate;


public class InsertUtilizatorDBModel {
    private int id;
    private String nume;
    private String ultimaConectare;
    private String dataCreareCont;
    private String email;
    private String parola;




    public InsertUtilizatorDBModel(int id, String nume, String ultimaConectare, String dataCreareCont, String email, String parola) {
        this.id = id;
        this.nume = nume;
        this.ultimaConectare = ultimaConectare;
        this.dataCreareCont = dataCreareCont;
        this.email = email;
        this.parola = parola;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getUltimaConectare() {
        return ultimaConectare;
    }

    public String getDataCreareCont() {
        return dataCreareCont;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    @Override
    public String toString() {
        return nume + " deconectare: " + ultimaConectare + " creare: " + dataCreareCont;
    }
}
