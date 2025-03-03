package com.sistem.meditatii.ModeleInterogareBazaDate.AngajareProfesor;

public class AngajareProfesorModel {
    private int id;
    private int idProfesor;
    private int idAngajat;

    public AngajareProfesorModel(int id, int idProfesor, int idAngajat) {
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

    @Override
    public String toString() {
        return "AngajareProfesorModel{" +
                "id=" + id +
                ", idProfesor=" + idProfesor +
                ", idAngajat=" + idAngajat +
                '}';
    }
}
