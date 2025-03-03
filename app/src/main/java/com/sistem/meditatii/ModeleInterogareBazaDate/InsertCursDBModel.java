package com.sistem.meditatii.ModeleInterogareBazaDate;


public class InsertCursDBModel {
    private int id;
    private String numeCurs;
    private String profesor;
    private String resursa;
    private String dataCurs;
    private String meditatie;
    private String student;
    private String locatieCurs;

    public InsertCursDBModel(int id, String numeCurs, String profesor, String resursa, String dataCurs, String meditatie, String student, String locatieCurs) {
        this.id = id;
        this.numeCurs = numeCurs;
        this.profesor = profesor;
        this.resursa = resursa;
        this.dataCurs = dataCurs;
        this.meditatie = meditatie;
        this.student = student;
        this.locatieCurs = locatieCurs;
    }

    public int getId() {
        return id;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public String getProfesor() {
        return profesor;
    }

    public String getResursa() {
        return resursa;
    }

    public String getDataCurs() {
        return dataCurs;
    }

    public String getMeditatie() {
        return meditatie;
    }

    public String getStudent() {
        return student;
    }

    public String getLocatieCurs() {
        return locatieCurs;
    }

    @Override
    public String toString() {
        return numeCurs;
    }
}
