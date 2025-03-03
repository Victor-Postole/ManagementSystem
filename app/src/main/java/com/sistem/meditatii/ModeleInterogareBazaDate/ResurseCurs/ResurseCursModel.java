package com.sistem.meditatii.ModeleInterogareBazaDate.ResurseCurs;

public class ResurseCursModel {
    private int id;
    private int idCurs;
    private int idResurse;

    public ResurseCursModel(int id, int idCurs, int idResurse) {
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

    @Override
    public String toString() {
        return "ResurseCursModel{" +
                "id=" + id +
                ", idCurs=" + idCurs +
                ", idResurse=" + idResurse +
                '}';
    }
}
