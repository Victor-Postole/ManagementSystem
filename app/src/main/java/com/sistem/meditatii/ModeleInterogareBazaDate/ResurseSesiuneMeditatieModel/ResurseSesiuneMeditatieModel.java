package com.sistem.meditatii.ModeleInterogareBazaDate.ResurseSesiuneMeditatieModel;

public class ResurseSesiuneMeditatieModel {
    private int id;
    private int idSesiuneMeditatie;
    private int idResursa;

    public ResurseSesiuneMeditatieModel(int id, int idSesiuneMeditatie, int idResursa) {
        this.id = id;
        this.idSesiuneMeditatie = idSesiuneMeditatie;
        this.idResursa = idResursa;
    }

    public int getId() {
        return id;
    }

    public int getIdSesiuneMeditatie() {
        return idSesiuneMeditatie;
    }

    public int getIdResursa() {
        return idResursa;
    }

    @Override
    public String toString() {
        return "ResurseSesiuneMeditatieModel{" +
                "id=" + id +
                ", idSesiuneMeditatie=" + idSesiuneMeditatie +
                ", idResursa=" + idResursa +
                '}';
    }
}
