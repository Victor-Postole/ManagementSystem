package com.sistem.meditatii.ModeleInterogareBazaDate.ResurseSesiuneMeditatieModel;

public class ResurseSesiuneMeditatieModel_INNER_JOIN {
    private int id;
    private int idSesiuneMeditatie;
    private int idResursa;

    private String numeResursa;

    public ResurseSesiuneMeditatieModel_INNER_JOIN(int id, int idSesiuneMeditatie, int idResursa, String numeResursa) {
        this.id = id;
        this.idSesiuneMeditatie = idSesiuneMeditatie;
        this.idResursa = idResursa;
        this.numeResursa = numeResursa;
    }

    public ResurseSesiuneMeditatieModel_INNER_JOIN(int id, int idSesiuneMeditatie, int idResursa) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setIdSesiuneMeditatie(int idSesiuneMeditatie) {
        this.idSesiuneMeditatie = idSesiuneMeditatie;
    }

    public void setIdResursa(int idResursa) {
        this.idResursa = idResursa;
    }

    public String getNumeResursa() {
        return numeResursa;
    }

    public void setNumeResursa(String numeResursa) {
        this.numeResursa = numeResursa;
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
