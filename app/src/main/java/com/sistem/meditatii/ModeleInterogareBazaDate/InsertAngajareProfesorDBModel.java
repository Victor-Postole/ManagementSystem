package com.sistem.meditatii.ModeleInterogareBazaDate;

public class InsertAngajareProfesorDBModel {
    private int id;
    private int idProfesor;
    private int idAngajat;

    public InsertAngajareProfesorDBModel(int id, int idProfesor, int idAngajat) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.idAngajat = idAngajat;
    }

    public InsertAngajareProfesorDBModel(int id, String numeProfesor, String prenumeProfesor, String telefonAngajat, String emailAngajat) {
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

    @Override
    public String toString() {
        return "InsertAngajareProfesorDBModel{" +
                "id=" + id +
                ", idProfesor=" + idProfesor +
                ", idAngajat=" + idAngajat +
                '}';
    }
}
