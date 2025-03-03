package com.sistem.meditatii.ModeleInterogareBazaDate.ParticipareCurs;

public class ParticipareCursModel_INNER_JOIN {
    private int id;
    private int idCurs;
    private int idStudent;

    private String numeCurs;
    private String numeStudent;

    public ParticipareCursModel_INNER_JOIN(int id, int idCurs, int idStudent) {
        this.id = id;
        this.idCurs = idCurs;
        this.idStudent = idStudent;
    }


    public ParticipareCursModel_INNER_JOIN(int id, int idCurs, int idStudent, String numeCurs, String numeStudent) {
        this.id = id;
        this.idCurs = idCurs;
        this.idStudent = idStudent;
        this.numeCurs = numeCurs;
        this.numeStudent = numeStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public void setNumeCurs(String numeCurs) {
        this.numeCurs = numeCurs;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    @Override
    public String toString() {
        return "ParticipareCursModel_INNER_JOIN{" +
                "id=" + id +
                ", idCurs=" + idCurs +
                ", idStudent=" + idStudent +
                ", numeCurs='" + numeCurs + '\'' +
                ", numeStudent='" + numeStudent + '\'' +
                '}';
    }
}
