package com.sistem.meditatii.ModeleInterogareBazaDate.ResurseRaport;

public class ResurseGenerareRapoarteModel {
    private int id;
    private int idResursa;
    private int idRaport;

    public ResurseGenerareRapoarteModel(int id, int idResursa, int idRaport) {
        this.id = id;
        this.idResursa = idResursa;
        this.idRaport = idRaport;
    }

    public int getId() {
        return id;
    }

    public int getIdResursa() {
        return idResursa;
    }

    public int getIdRaport() {
        return idRaport;
    }

    @Override
    public String toString() {
        return "ResurseGenerareRapoarteModel{" +
                "id=" + id +
                ", idResursa=" + idResursa +
                ", idRaport=" + idRaport +
                '}';
    }
}
