package com.sistem.meditatii.ModeleInterogareBazaDate.PlataSesiune;

public class PlataSesiuneModel_INNER_JOIN {
    private int id;
    private long idPlata;
    private String idSesiuneMeditatie;



    public PlataSesiuneModel_INNER_JOIN(int id, long idPlata, String idSesiuneMeditatie) {
        this.id = id;
        this.idPlata = idPlata;
        this.idSesiuneMeditatie = idSesiuneMeditatie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdPlata() {
        return idPlata;
    }

    public void setIdPlata(long idPlata) {
        this.idPlata = idPlata;
    }

    public String getIdSesiuneMeditatie() {
        return idSesiuneMeditatie;
    }

    public void setIdSesiuneMeditatie(String idSesiuneMeditatie) {
        this.idSesiuneMeditatie = idSesiuneMeditatie;
    }

    @Override
    public String toString() {
        return "PlataSesiuneModel_INNER_JOIN{" +
                "id=" + id +
                ", idPlata=" + idPlata +
                ", idSesiuneMeditatie='" + idSesiuneMeditatie + '\'' +
                '}';
    }
}
