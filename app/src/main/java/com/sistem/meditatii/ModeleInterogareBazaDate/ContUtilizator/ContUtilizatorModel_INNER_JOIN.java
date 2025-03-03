package com.sistem.meditatii.ModeleInterogareBazaDate.ContUtilizator;

public class ContUtilizatorModel_INNER_JOIN {
    private int id;
    private int idUtilizator;
    private int idStudent;

    private String numeContUtilizator;
    private String numeStudent;

    public ContUtilizatorModel_INNER_JOIN(int id, int idUtilizator, int idStudent) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNumeContUtilizator() {
        return numeContUtilizator;
    }

    public void setNumeContUtilizator(String numeContUtilizator) {
        this.numeContUtilizator = numeContUtilizator;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
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
