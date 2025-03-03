package com.sistem.meditatii.ModeleInterogareBazaDate.PlataSesiune;

public class PlataSesiuneModel {
    private int id;
    private int idPlata;
    private int idSesiuneMeditatie;

    public PlataSesiuneModel(int id, int idPlata, int idSesiuneMeditatie) {
        this.id = id;
        this.idPlata = idPlata;
        this.idSesiuneMeditatie = idSesiuneMeditatie;
    }

    public int getId() {
        return id;
    }

    public int getIdPlata() {
        return idPlata;
    }

    public int getIdSesiuneMeditatie() {
        return idSesiuneMeditatie;
    }

    @Override
    public String toString() {
        return "PlataSesiuneModel{" +
                "id=" + id +
                ", idPlata=" + idPlata +
                ", idSesiuneMeditatie=" + idSesiuneMeditatie +
                '}';
    }
}
