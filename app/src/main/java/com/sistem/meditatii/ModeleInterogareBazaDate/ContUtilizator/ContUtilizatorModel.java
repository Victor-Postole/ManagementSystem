package com.sistem.meditatii.ModeleInterogareBazaDate.ContUtilizator;

public class ContUtilizatorModel {
    private int id;
    private int idUtilizator;
    private int idStudent;

    public ContUtilizatorModel(int id, int idUtilizator, int idStudent) {
        this.id = id;
        this.idUtilizator = idUtilizator;
        this.idStudent = idStudent;
    }

    public int getId() {
        return id;
    }

    public int getIdUtilizator() {
        return idUtilizator;
    }

    public int getIdStudent() {
        return idStudent;
    }

    @Override
    public String toString() {
        return "ContUtilizatorModel{" +
                "id=" + id +
                ", idUtilizator=" + idUtilizator +
                ", idStudent=" + idStudent +
                '}';
    }
}
