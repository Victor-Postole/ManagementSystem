package com.sistem.meditatii.ModeleInterogareBazaDate.AngajareProfesor;

public class AngajareProfesorModel_INNER_JOIN {
    private int id;
    private int idProfesor;
    private int idAngajat;

    private String numeProfesor;
    private String prenumeProfesor;
    private String numeAngajat;
    private String prenumeAngajat;

    public AngajareProfesorModel_INNER_JOIN(int id, int idProfesor, int idAngajat) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.idAngajat = idAngajat;
    }

    public int getId() {
        return id;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public String getPrenumeProfesor() {
        return prenumeProfesor;
    }

    public void setPrenumeProfesor(String prenumeProfesor) {
        this.prenumeProfesor = prenumeProfesor;
    }

    public String getNumeAngajat() {
        return numeAngajat;
    }

    public void setNumeAngajat(String numeAngajat) {
        this.numeAngajat = numeAngajat;
    }

    public String getPrenumeAngajat() {
        return prenumeAngajat;
    }

    public void setPrenumeAngajat(String prenumeAngajat) {
        this.prenumeAngajat = prenumeAngajat;
    }

    @Override
    public String toString() {
        return "AngajareProfesorModel{" +
                "id=" + id +
                ", idProfesor=" + idProfesor +
                ", idAngajat=" + idAngajat +
                '}';
    }
}
