package com.sistem.meditatii.ModeleInterogareBazaDate.ParticipareCurs;

public class ParticipareCursModel {
    private int id;
    private int idCurs;
    private int idStudent;

    public ParticipareCursModel(int id, int idCurs, int idStudent) {
        this.id = id;
        this.idCurs = idCurs;
        this.idStudent = idStudent;
    }

    public int getId() {
        return id;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public int getIdStudent() {
        return idStudent;
    }

    @Override
    public String toString() {
        return "ParticipareCursModel{" +
                "id=" + id +
                ", idCurs=" + idCurs +
                ", idStudent=" + idStudent +
                '}';
    }
}
