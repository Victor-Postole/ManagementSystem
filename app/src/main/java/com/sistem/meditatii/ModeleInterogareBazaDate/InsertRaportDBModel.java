package com.sistem.meditatii.ModeleInterogareBazaDate;

public class InsertRaportDBModel {
    private int id;
    private String resursa;
    private String detalii;
    private String dataGenerare;
    private String tipRaport;

    public InsertRaportDBModel(int id, String resursa, String detalii, String dataGenerare, String tipRaport) {
        this.id = id;
        this.resursa = resursa;
        this.detalii = detalii;
        this.dataGenerare = dataGenerare;
        this.tipRaport = tipRaport;
    }

    public int getId() {
        return id;
    }

    public String getResursa() {
        return resursa;
    }

    public String getDetalii() {
        return detalii;
    }

    public String getDataGenerare() {
        return dataGenerare;
    }

    public String getTipRaport() {
        return tipRaport;
    }

    @Override
    public String toString() {
        return "id " + id + " dataGenerare " + dataGenerare ;
    }
}
