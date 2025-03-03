package com.sistem.meditatii.ModeleInterogareBazaDate;

public class InsertPredareCursDBModel {
    private int id;
    private int idProfesor;
    private int idCurs;

    public InsertPredareCursDBModel(int id, int idProfesor, int idCurs) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.idCurs = idCurs;
    }

    public int getId() {
        return id;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public int getIdCurs() {
        return idCurs;
    }

    @Override
    public String toString() {
        return "InsertPredareCursDBModel{" +
                "id=" + id +
                ", idProfesor=" + idProfesor +
                ", idCurs=" + idCurs +
                '}';
    }
}
