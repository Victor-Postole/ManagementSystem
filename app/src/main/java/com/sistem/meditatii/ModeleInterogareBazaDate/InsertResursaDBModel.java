package com.sistem.meditatii.ModeleInterogareBazaDate;

public class InsertResursaDBModel {
    private int id;
    private String resursaCurs;
    private String numeResursa;
    private long cantitateResursa;
    private String resursaSesiune;
    private String resursaRaport;
    private String tipResursa;

    public InsertResursaDBModel(int id, String resursaCurs, String numeResursa, long cantitateResursa, String resursaSesiune, String resursaRaport, String tipResursa) {
        this.id = id;
        this.resursaCurs = resursaCurs;
        this.numeResursa = numeResursa;
        this.cantitateResursa = cantitateResursa;
        this.resursaSesiune = resursaSesiune;
        this.resursaRaport = resursaRaport;
        this.tipResursa = tipResursa;
    }

    public int getId() {
        return id;
    }

    public String getResursaCurs() {
        return resursaCurs;
    }

    public String getNumeResursa() {
        return numeResursa;
    }

    public long getCantitateResursa() {
        return cantitateResursa;
    }

    public String getResursaSesiune() {
        return resursaSesiune;
    }

    public String getResursaRaport() {
        return resursaRaport;
    }

    public String getTipResursa() {
        return tipResursa;
    }

    @Override
    public String toString() {
        return numeResursa;
    }
}
