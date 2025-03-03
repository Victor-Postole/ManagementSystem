package com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs;

public class SesiuneCursModel {
    private int id;
    private int idCurs;
    private int idSesiune;

    public SesiuneCursModel(int id, int idCurs, int idSesiune) {
        this.id = id;
        this.idCurs = idCurs;
        this.idSesiune = idSesiune;
    }

    public int getId() {
        return id;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public int getIdSesiune() {
        return idSesiune;
    }

    @Override
    public String toString() {
        return "SesiuneCursModel{" +
                "id=" + id +
                ", idCurs=" + idCurs +
                ", idSesiune=" + idSesiune +
                '}';
    }
}
