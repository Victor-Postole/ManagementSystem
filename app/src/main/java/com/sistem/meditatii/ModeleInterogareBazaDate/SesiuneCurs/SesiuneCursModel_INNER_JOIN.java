package com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs;

public class SesiuneCursModel_INNER_JOIN {
    private int id;
    private int idCurs;
    private int idSesiune;
    private String nume_curs;

    public SesiuneCursModel_INNER_JOIN(int id, int idCurs, int idSesiune, String nume_curs) {
        this.id = id;
        this.idCurs = idCurs;
        this.idSesiune = idSesiune;
        this.nume_curs = nume_curs;
    }

    public SesiuneCursModel_INNER_JOIN(int id, int idCurs, int idSesiune) {
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

    public String getNume_curs() {
        return nume_curs;
    }

    public void setNume_curs(String nume_curs) {
        this.nume_curs = nume_curs;
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
