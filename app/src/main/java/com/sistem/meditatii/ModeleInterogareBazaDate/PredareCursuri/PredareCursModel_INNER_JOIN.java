package com.sistem.meditatii.ModeleInterogareBazaDate.PredareCursuri;

public class PredareCursModel_INNER_JOIN {
    private int id;
    private int idProfesor;
    private int idCurs;

    private String numeProfesor;
    private String prenumeProfesor;
    private String numeCurs;

    public PredareCursModel_INNER_JOIN(int id, int idProfesor, int idCurs, String numeProfesor, String prenumeProfesor, String numeCurs) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.idCurs = idCurs;
        this.numeProfesor = numeProfesor;
        this.prenumeProfesor = prenumeProfesor;
        this.numeCurs = numeCurs;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
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

    public String getNumeCurs() {
        return numeCurs;
    }

    public void setNumeCurs(String numeCurs) {
        this.numeCurs = numeCurs;
    }

    @Override
    public String toString() {
        return "PredareCursModel_INNER_JOIN{" +
                "id=" + id +
                ", idProfesor=" + idProfesor +
                ", idCurs=" + idCurs +
                ", numeProfesor='" + numeProfesor + '\'' +
                ", prenumeProfesor='" + prenumeProfesor + '\'' +
                ", numeCurs='" + numeCurs + '\'' +
                '}';
    }
}
