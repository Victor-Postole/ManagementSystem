package com.sistem.meditatii.ModeleInterogareBazaDate.ResurseCurs;

public class ResurseCursModel_INNER_JOIN {
    private int id;
    private int idCurs;
    private int idResurse;

    private String numeCurs;
    private String numeResursa;

    public ResurseCursModel_INNER_JOIN(int id, int idCurs, int idResurse, String numeCurs, String numeResursa) {
        this.id = id;
        this.idCurs = idCurs;
        this.idResurse = idResurse;
        this.numeCurs = numeCurs;
        this.numeResursa = numeResursa;
    }

    public ResurseCursModel_INNER_JOIN(int id, int idCurs, int idResurse) {
        this.id = id;
        this.idCurs = idCurs;
        this.idResurse = idResurse;
    }

    public int getId() {
        return id;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public int getIdResurse() {
        return idResurse;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public String getNumeResursa() {
        return numeResursa;
    }

    @Override
    public String toString() {
        return "ResurseCursModel{" +
                "id=" + id +
                ", idCurs=" + idCurs +
                ", idResurse=" + idResurse +
                '}';
    }
}
